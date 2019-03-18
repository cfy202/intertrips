package com.wenjing.action.front;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wenjing.alipay.util.AlipayNotify;
import com.wenjing.entity.Orderdetail;
import com.wenjing.entity.Orders;
import com.wenjing.service.OrderService;

/**
 * 类说明
 * @author xiejin
 * @date 2015-7-9 
 * @date 2015-7-9 下午8:28:16
 */
@Controller
@RequestMapping("/ali")
public class AlipayController {
	
	@Resource 
	private HttpServletRequest request;
	@Resource
	private OrderService orderService;
	
	/**
	 * @Title: aliNotify
	 * @Description: 支付宝服务器异步通知
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 */
	@RequestMapping("/notify")
	public void aliNotify(HttpServletResponse response){
		PrintWriter jspWriter = null;
		try {
			jspWriter = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		
		//商户订单号
		String out_trade_no = request.getParameter("out_trade_no");
		//支付宝交易号
		String trade_no = request.getParameter("trade_no");
		//交易状态
		String trade_status = request.getParameter("trade_status");
		
		String extra_common_param = request.getParameter("extra_common_param");
		
		String total_fee = request.getParameter("total_fee");
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if(AlipayNotify.verify(params)){//验证成功
			//请在这里加上商户的业务逻辑程序代码

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			
			if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
				
				Integer payStatus = Integer.valueOf(extra_common_param);	//支付状态
				String[] orderNoArray = (out_trade_no+"_").split("_");
				String orderNo = orderNoArray[0];							//订单号
				BigDecimal totalFee = new BigDecimal(total_fee);
				Orders orders = orderService.finByOrderNo(orderNo);			//总单
				Orderdetail orderdetail = orderService.findByOrderId(orders.getId());	//订单详情
				BigDecimal countPrice = orderService.countPrice(orders.getId(), payStatus);	//计算的付款价格
				if (!orderdetail.getPayStatus().equals(payStatus) && countPrice.compareTo(totalFee)==0) {//判断订单是否做过处理
//					orderService.payComplete(payStatus, orderNo, totalFee,2);				//支付完成后改变订单等状态
				}
			
				//注意：
				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
				jspWriter.println("success");
			}
			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				
//			try {
//				jspWriter.println("success");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}	//请不要修改或删除

		}else{//验证失败
			jspWriter.println("fail");
		}
	}
	
	/**
	 * @Title: aliReturn
	 * @Description: 支付宝页面同步跳转
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 */
	@RequestMapping("/return")
	public String aliReturn(){
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			try {
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		
		//商户订单号
		String out_trade_no = request.getParameter("out_trade_no");
		//支付宝交易号
		String trade_no = request.getParameter("trade_no");
		//交易状态
		String trade_status = request.getParameter("trade_status");
		//公共回传参数
		String extra_common_param = request.getParameter("extra_common_param");
		//总金额
		String total_fee = request.getParameter("total_fee");
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		
		//计算得出通知验证结果
		boolean verify_result = AlipayNotify.verify(params);
		if(verify_result){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
//				Integer payStatus = Integer.valueOf(extra_common_param);	//支付状态
//				String[] orderNoArray = (out_trade_no+"_").split("_");
//				String orderNo = orderNoArray[0];		//订单号
//				BigDecimal totalFee = new BigDecimal(total_fee);
//				Orders orders = orderService.finByOrderNo(orderNo);	//总单
//				Orderdetail orderdetail = orderService.findByOrderId(orders.getId());//订单详情
//				BigDecimal countPrice = orderService.countPrice(orderNo, payStatus);	//计算的付款价格
//				if (!orderdetail.getPayStatus().equals(payStatus) && countPrice.compareTo(totalFee)==0) {//判断订单是否做过处理
//					orderService.payComplete(payStatus, orderNo, totalFee);	//支付完成后改变订单等状态
//				}
				return"redirect:/";
			}
			
			//该页面可做页面美工编辑
//			out.println("验证成功<br />");
			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
		}else{
			//该页面可做页面美工编辑
//			System.out.println("验证失败");
		}
		return null;
	}
}
