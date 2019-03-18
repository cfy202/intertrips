package com.wenjing.action.front;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wenjing.PayAttributes;
import com.wenjing.dao.OrdercontacterMapper;
import com.wenjing.entity.Ordercontacter;
import com.wenjing.entity.Orderdetail;
import com.wenjing.service.IPNPaymentService;
import com.wenjing.service.OrderService;

/**
 * 类说明
 * 
 * @author xiejin
 * @date 2015-7-28
 * @date 2015-7-28 下午4:15:50
 */
@Controller
@RequestMapping("/ipnPay")
public class IPNHandleController {

	@Resource
	private HttpServletRequest request;
	@Resource
	private IPNPaymentService ipnPaymentService;
	@Resource
	private OrderService orderService;
	@Autowired
	private OrdercontacterMapper orderContacterMapper;

	/**
	 * @Title: execute
	 * @Description: 接收通知
	 * @return
	 * @return String 返回类型
	 * @author xiejin
	 */
//	@RequestMapping("/notify")
//	public void notify(HttpServletResponse response) {
//
//		/**
//		 * 将请求来的数据封装成map
//		 */
//		Enumeration<?> en = request.getParameterNames();
//		Map<String, String> paramsMap = new HashMap<String, String>();
//		while (en.hasMoreElements()) {
//			String paramName = (String) en.nextElement();
//			String paramValue = request.getParameter(paramName);
//			System.out.println(paramName + "," + paramValue);
//			paramsMap.put(paramName, paramValue);
//		}
//
//		/**
//		 * 向paypal返回消息 根据paypal返回的信息来更改订单状态以及支付状态 正常执行 return 0 出异常 return -1
//		 */
//		int flag = ipnPaymentService.changeStatusPassIpnNotice(paramsMap);
//		PrintWriter jspWriter = null;
//		try {
//			jspWriter = response.getWriter();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		if (flag == 0) {
//			jspWriter.println("200 OK");
//		} else {
//			jspWriter.println("");
//		}
//	}

	
	/**
	 * @Title: paypalNotity
	 * @Description: paypal通知
	 * @param request
	 * @param response    
	 * @return void    返回类型
	 * @author xiejin
	 */
	@RequestMapping("/notify2")
	public void paypalNotity(HttpServletRequest request,HttpServletResponse response) {
		//信息存入日志文件
		String information = null;
		String orderNo = null;
		String ordersId = null;
		String params = "";
		String responseString = null;
		try {
			// 从 PayPal 出读取 POST 信息同时添加变量„cmd‟
			Enumeration<?> en = request.getParameterNames();
			params = "cmd=_notify-validate";
			while (en.hasMoreElements()) {
				String paramName = (String) en.nextElement();
				String paramValue = request.getParameter(paramName);
				paramValue=URLEncoder.encode(paramValue, "utf-8");		
				params = params + "&" + paramName + "="+paramValue;
			}
			
			URL u = new URL(PayAttributes.PAYPALURL);
			URLConnection uc = u.openConnection();
			
			uc.setDoOutput(true);
			uc.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			PrintWriter pw = new PrintWriter(uc.getOutputStream());
			
			pw.println(params);
			pw.close();
			
			// 接受 PayPal 对 IPN 回发的回复信息
			BufferedReader in = new BufferedReader(new InputStreamReader(
					uc.getInputStream()));
			responseString = in.readLine();
			in.close();
			// 将 POST 信息分配给本地变量，可以根据您的需要添加
			// 该付款明细所有变量可参考：
			// https://www.paypal.com/IntegrationCenter/ic_ipn-pdt-variable-reference.html
//			String payerEmail = request.getParameter("payer_email");						//付款邮箱
//			String  item_name = URLEncoder.encode(request.getParameter("item_name"), "utf-8");
			orderNo = request.getParameter("item_number");	//订单号
			ordersId = request.getParameter("invoice");  //订单ID
			BigDecimal payedPrice = new BigDecimal(request.getParameter("mc_gross"));	//付款价格
			String  payment_status = request.getParameter("payment_status");  //支付状态
			String  receiver_email = request.getParameter("receiver_email");   //收款email
			String  mc_currency = request.getParameter("mc_currency");	 //付款币种
			String firstName = request.getParameter("first_name");
			String lastName = request.getParameter("last_name");
			String pay_account = request.getParameter("payer_email");
//			System.out.println("paypalIPN最终握手确认结果：" + res);
			// 获取 PayPal 对回发信息的回复信息，判断刚才的通知是否为 PayPal 发出的
			if ("VERIFIED".equals(responseString)) {
				// 处理其他数据，包括写数据库
				if ("Completed".equals(payment_status)) {
					Orderdetail orderdetail = orderService.findByOrderId(ordersId);	//订单详情
					
					String currencyCode = orderdetail.getCurrencySign();					//订单对应的币种
					BigDecimal countPrice = orderService.countPrice(ordersId, 5);	//计算的付款价格
					// 根据项目实际需要处理其业务逻辑
					if ((!orderdetail.getPayStatus().equals(5)) && PayAttributes.checkReceiveAccount(currencyCode,receiver_email)	
							&& countPrice.compareTo(payedPrice)==0 
							&& currencyCode.equals(mc_currency)) {
						orderService.payComplete(request, 5, ordersId, orderNo, orderdetail.getPrice(), 1,firstName + " "+ lastName);
					} else {
						information="Payment information is incorrect, please contact our customer service without delay.";
					}
				} else {
					information="Sorry, payment unfinished or the results have not been processed.";
				}
			} else if ("INVALID".equals(responseString)) {
				information="Sorry, payment failed.";
			} else {
				information="Sorry, an exception occurs.";
			}
		} catch (IOException e) {
			e.printStackTrace();
			information="Sorry, an exception occurs.";
		}
		
		//信息存入日志文件
		String orderstr = request.getSession().getServletContext().getRealPath("/") + File.separator +"orderLog"+ File.separator + ordersId+".txt"; 
		createNewFile(orderstr,params +responseString);
		
		PrintWriter jspWriter = null;
		try {
			jspWriter = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (information == null) {
			jspWriter.println("200 OK");
		} else {
			jspWriter.println("fail");
		}
	}
	
	/**
	 * @Title: paypalReturn
	 * @Description: 支付完成后paypal页面跳转返回
	 * @param model
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 * @throws IOException 
	 */
	@RequestMapping("/return")
	public String paypalReturn(Model model) throws IOException{
		String information = null;
		String orderNo = null;
		String ordersId = null;
		String params = "";
		String responseString = null;
		try {
			// 从 PayPal 出读取 POST 信息同时添加变量„cmd‟
			Enumeration<?> en = request.getParameterNames();
			params = "cmd=_notify-validate";
			while (en.hasMoreElements()) {
				String paramName = (String) en.nextElement();
				String paramValue = request.getParameter(paramName);
				paramValue=URLEncoder.encode(paramValue, "utf-8");		
				params = params + "&" + paramName + "="+paramValue;
			}
			
			URL u = new URL(PayAttributes.PAYPALURL);
			URLConnection uc = u.openConnection();
			
			uc.setDoOutput(true);
			uc.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			PrintWriter pw = new PrintWriter(uc.getOutputStream());
			
			pw.println(params);
			pw.close();
			
			// 接受 PayPal 对 IPN 回发的回复信息
			BufferedReader in = new BufferedReader(new InputStreamReader(
					uc.getInputStream()));
			responseString = in.readLine();
			in.close();
			// 将 POST 信息分配给本地变量，可以根据您的需要添加
			// 该付款明细所有变量可参考：
			// https://www.paypal.com/IntegrationCenter/ic_ipn-pdt-variable-reference.html
//			String payerEmail = request.getParameter("payer_email");						//付款邮箱
//			String  item_name = URLEncoder.encode(request.getParameter("item_name"), "utf-8");
			orderNo = request.getParameter("item_number");	//订单号
			ordersId = request.getParameter("invoice");
			BigDecimal payedPrice = new BigDecimal(request.getParameter("amt"));		//付款价格
			String  payment_status = request.getParameter("payment_status");
			String  receiver_email = request.getParameter("receiver_email");
			String  mc_currency = request.getParameter("mc_currency");				//付款币种
//			System.out.println("paypalIPN最终握手确认结果：" + res);
			// 获取 PayPal 对回发信息的回复信息，判断刚才的通知是否为 PayPal 发出的
			if ("VERIFIED".equals(responseString)) {
				// 处理其他数据，包括写数据库
				if ("Completed".equals(payment_status)) {
					Orderdetail orderdetail = orderService.findByOrderId(ordersId);	//订单详情
					
					String currencyCode = orderdetail.getCurrencySign();					//订单对应的币种
					BigDecimal countPrice = orderService.countPrice(ordersId, 5);	//计算的付款价格
					
					// 根据项目实际需要处理其业务逻辑
					if (PayAttributes.checkReceiveAccount(currencyCode,receiver_email)
							&& countPrice.compareTo(payedPrice)==0 
							&& currencyCode.equals(mc_currency)) {
					} else {
						information="Payment information is incorrect, please contact our customer service without delay.";
					}
				} else {
					information="Sorry, payment unfinished or the results have not been processed.";
				}
			} else if ("INVALID".equals(responseString)) {
				information="Sorry, payment failed.";
			} else {
				information="Sorry, an exception occurs.";
			}
		} catch (IOException e) {
			e.printStackTrace();
			information="Sorry, an exception occurs.";
		}
		
		if(information == null){
			Ordercontacter orderContacter = orderContacterMapper.selectByOrderId(ordersId);
			return "redirect:/pay_success.htm?orderNo=" + orderNo + "&email=" + orderContacter.getEmail();
		}else{
			model.addAttribute("noticeString", information);
			return "/front/payNotice/tourFail.ftl";
		}
	}
	
	/**
	 * @Title: createNewFile
	 * @Description: 订单付款返回状态写入文件
	 * @param fileDirectoryAndName
	 * @param fileContent    
	 * @return void    返回类型
	 * @author xiejin
	 */
	 public void createNewFile(String fileDirectoryAndName,String fileContent){
	 try{
		 String directory = fileDirectoryAndName.substring(0, fileDirectoryAndName.lastIndexOf(File.separator));
		
		 File directoryFile = new File(directory);
		 if(!directoryFile.exists()){
			 directoryFile.mkdirs();
		 }
		 //创建File对象，参数为String类型，表示目录名
		 File myFile = new File(fileDirectoryAndName);
		 //判断文件是否存在，如果不存在则调用createNewFile()方法创建新目录，否则跳至异常处理代码
		 if(!myFile.exists()){
			 myFile.createNewFile();
		 }else {	//如果存在则扔出异常
			 myFile.delete();
			 myFile.createNewFile();
//			 throw new Exception("The new file already exists!");
		 } 
		 //下面把数据写入创建的文件，首先新建文件名为参数创建FileWriter对象
		 FileWriter resultFile = new FileWriter(myFile);
		 //把该对象包装进PrinterWriter对象
		 PrintWriter myNewFile = new PrintWriter(resultFile);
		 //再通过PrinterWriter对象的println()方法把字符串数据写入新建文件
		 myNewFile.println(fileContent);
		 resultFile.close();   //关闭文件写入流
	  }catch(Exception ex){
		  System.out.println("无法创建新文件！");
		  ex.printStackTrace();
	  }
	}

}
