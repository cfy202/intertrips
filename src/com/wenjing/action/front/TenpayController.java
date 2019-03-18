
package com.wenjing.action.front;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wenjing.entity.Orderdetail;
import com.wenjing.entity.Orders;
import com.wenjing.service.OrderService;
import com.wenjing.tenpay.RequestHandler;
import com.wenjing.tenpay.ResponseHandler;
import com.wenjing.tenpay.client.ClientResponseHandler;
import com.wenjing.tenpay.client.TenpayHttpClient;

/**
 * 类说明
 * @author xiejin
 * @date 2015-7-13 
 * @date 2015-7-13 下午3:56:10
 */
@Controller
@RequestMapping("/tenpay")
public class TenpayController {
	@Resource
	private HttpServletRequest request;
	@Resource
	private OrderService orderService;
	
	//测试账号
//	private static final String partner = "1900000113";						//商户号
//	private static final String key = "e82573dc7e6136ba414f2e2affbe39fa";	//密钥
	
	//正式账号
	private static final String partner = "1251521801";						//商户号
	private static final String key = "89b72cd18143f0bc5995969ec62bbbbb";	//密钥

	/**
	 * @Title: tenReturn
	 * @Description: 财付通支付成功页面返回
	 * @param response
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 */
	@RequestMapping("/return")
	public String tenReturn(HttpServletResponse response){
		
		//---------------------------------------------------------
		//财付通支付应答（处理回调）示例，商户按照此文档进行开发即可
		//---------------------------------------------------------
		//创建支付应答对象
		ResponseHandler resHandler = new ResponseHandler(request, response);
		resHandler.setKey(key);
		
		System.out.println("前台回调返回参数:"+resHandler.getAllParameters());
		
		//判断签名
		if(resHandler.isTenpaySign()) {
		
		    //通知id
			String notify_id = resHandler.getParameter("notify_id");
			//商户订单号
			String out_trade_no = resHandler.getParameter("out_trade_no");
			//财付通订单号
			String transaction_id = resHandler.getParameter("transaction_id");
			//金额,以分为单位
			String total_fee = resHandler.getParameter("total_fee");
			//如果有使用折扣券，discount有值，total_fee+discount=原请求的total_fee
			String discount = resHandler.getParameter("discount");
			//支付结果
			String trade_state = resHandler.getParameter("trade_state");
			//交易模式，1即时到账，2中介担保
			String trade_mode = resHandler.getParameter("trade_mode");
			//附加信息
			String attach = resHandler.getParameter("attach");
			
			if("1".equals(trade_mode)){       //即时到账 
				if( "0".equals(trade_state)){
					//即时到账处理业务开始
					//注意交易单不要重复处理
					//注意判断返回金额
//					Integer payStatus = Integer.valueOf(attach);				//支付状态
//					String[] orderNoArray = (out_trade_no+"_").split("_");
//					String orderNo = orderNoArray[0];		//订单号
//					BigDecimal totalFee = new BigDecimal(total_fee).divide(BigDecimal.valueOf(100));
//					Orders orders = orderService.finByOrderNo(orderNo);	//总单
//					Orderdetail orderdetail = orderService.findByOrderId(orders.getId());//订单详情
//					BigDecimal countPrice = orderService.countPrice(orderNo, payStatus);	//计算的付款价格
//					if (!orderdetail.getPayStatus().equals(payStatus) && countPrice.compareTo(totalFee)==0) {
//						orderService.payComplete(payStatus, orderNo, totalFee);	//支付完成后改变订单等状态
//					}
					//即时到账处理业务完毕
					return "redirect:/";
				}else{
					System.out.println("即时到帐付款失败");
				}
			}
		} else {
//			out.println("认证签名失败");
		}
		//获取debug信息,建议把debug信息写入日志，方便定位问题
		String debuginfo = resHandler.getDebugInfo();
		System.out.println("debuginfo:" + debuginfo);
		//out.print("sign_String:  " + debuginfo + "<br><br>");
		return null;
	}
	
	/**
	 * @Title: tenNotify
	 * @Description: 财付通支付成功服务器异步通知
	 * @param response    
	 * @return void    返回类型
	 * @author xiejin
	 */
	@RequestMapping("/notify")
	public void tenNotify(HttpServletResponse response){
		//---------------------------------------------------------
		//财付通支付通知（后台通知）示例，商户按照此文档进行开发即可
		//---------------------------------------------------------

		//创建支付应答对象
		ResponseHandler resHandler = new ResponseHandler(request, response);
		resHandler.setKey(key);
//		System.out.println("后台回调返回参数:"+resHandler.getAllParameters());
		//判断签名
		if(resHandler.isTenpaySign()) {
			//通知id
			String notify_id = resHandler.getParameter("notify_id");
			//创建请求对象
			RequestHandler queryReq = new RequestHandler(null, null);
			//通信对象
			TenpayHttpClient httpClient = new TenpayHttpClient();
			//应答对象
			ClientResponseHandler queryRes = new ClientResponseHandler();
			//通过通知ID查询，确保通知来至财付通
			queryReq.init();
			queryReq.setKey(key);
			queryReq.setGateUrl("https://gw.tenpay.com/gateway/simpleverifynotifyid.xml");
			queryReq.setParameter("partner", partner);
			queryReq.setParameter("notify_id", notify_id);
			//通信对象
			httpClient.setTimeOut(5);
			//设置请求内容
			try {
				httpClient.setReqContent(queryReq.getRequestURL());
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			try {
				System.out.println("验证ID请求字符串:" + queryReq.getRequestURL());
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			
			//后台调用
			if(httpClient.call()) {
				//设置结果参数
				try {
					queryRes.setContent(httpClient.getResContent());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				System.out.println("验证ID返回字符串:" + httpClient.getResContent());
				queryRes.setKey(key);
					
				//获取id验证返回状态码，0表示此通知id是财付通发起
				String retcode = queryRes.getParameter("retcode");
				//商户订单号
				String out_trade_no = resHandler.getParameter("out_trade_no");
				//财付通订单号
				String transaction_id = resHandler.getParameter("transaction_id");
				//金额,以分为单位
				String total_fee = resHandler.getParameter("total_fee");
				//如果有使用折扣券，discount有值，total_fee+discount=原请求的total_fee
				String discount = resHandler.getParameter("discount");
				//支付结果
				String trade_state = resHandler.getParameter("trade_state");
				//交易模式，1即时到账，2中介担保
				String trade_mode = resHandler.getParameter("trade_mode");
				//附加信息
				String attach = resHandler.getParameter("attach");
					
				//判断签名及结果
				if(queryRes.isTenpaySign() && "0".equals(retcode)){ 
					System.out.println("id验证成功");
					
					if("1".equals(trade_mode)){       //即时到账 
						if( "0".equals(trade_state)){
							//即时到账处理业务开始
							Integer payStatus = Integer.valueOf(attach);				//支付状态
							String[] orderNoArray = (out_trade_no+"_").split("_");
							String orderNo = orderNoArray[0];		//订单号
							BigDecimal totalFee = new BigDecimal(total_fee).divide(BigDecimal.valueOf(100));
							Orders orders = orderService.finByOrderNo(orderNo);	//总单
							Orderdetail orderdetail = orderService.findByOrderId(orders.getId());//订单详情
							BigDecimal countPrice = orderService.countPrice(orders.getId(), payStatus);	//计算的付款价格
							if (!orderdetail.getPayStatus().equals(payStatus) && countPrice.compareTo(totalFee)==0) {
//								orderService.payComplete(payStatus, orderNo, totalFee,3);	//支付完成后改变订单等状态
							}
							//处理数据库逻辑
							//注意交易单不要重复处理
							//注意判断返回金额
							//即时到账处理业务完毕
							
							//给财付通系统发送成功信息，财付通系统收到此结果后不再进行后续通知
							try {
								resHandler.sendToCFT("success");
							} catch (IOException e) {
								e.printStackTrace();
							}
						}else{
							System.out.println("即时到账支付失败");
							try {
								resHandler.sendToCFT("fail");
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}else if("2".equals(trade_mode)){}
				}else{
					//错误时，返回结果未签名，记录retcode、retmsg看失败详情。
					System.out.println("查询验证签名失败或id验证失败"+",retcode:" + queryRes.getParameter("retcode"));
				}
			} else {
				System.out.println("后台调用通信失败");
				System.out.println(httpClient.getResponseCode());
				System.out.println(httpClient.getErrInfo());
				//有可能因为网络原因，请求已经处理，但未收到应答。
			}
		}else{
			System.out.println("通知签名验证失败");
		}
	}
}
