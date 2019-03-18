/**
 * 
 */
package com.wenjing.action.front;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wenjing.entity.Ordercontacter;
import com.wenjing.entity.Orders;
import com.wenjing.service.OrderService;

/**
 * 类说明
 * @author xiejin
 * @date 2015-11-9 
 * @date 2015-11-9 下午3:20:27
 */
@Controller
@RequestMapping("/authorizeSim")
public class AuthorizeSimController {
	
	@Resource 
	private HttpServletRequest request;
	@Resource
	private OrderService orderService;
	

	
	/**
	 * @Title: simReturn
	 * @Description: sim返回结果处理
	 * @param model
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 */
	@RequestMapping("/relay")
	public String simReturn(Model model){
		
		String urlString = null;	//返回路径
		String noticeString = null;	//支付异常通知信息
//		String str = "";			//支付返回的参数字符串
//		Enumeration<?> en = request.getParameterNames();
//		while (en.hasMoreElements()) {
//			String paramName = (String) en.nextElement();
//			String paramValue = request.getParameter(paramName);
//			try {
//				paramValue=URLEncoder.encode(paramValue, "utf-8");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}		
//			str = str + "&" + paramName + "="+paramValue;
//		}
		String x_response_code = request.getParameter("x_response_code");				//交易状态
		String orderNo = request.getParameter("x_invoice_num");							//订单号
		Integer payMethod = 5;															//付款方式,默认为全款，5
		BigDecimal payedPrice = new BigDecimal(request.getParameter("x_amount"));       //付款价格
		Orders orders = orderService.finByOrderNo(orderNo);
		try{
			if (x_response_code.equals("1")) {
				BigDecimal countPrice = orderService.countPrice(orders.getId(), payMethod);	//计算的付款价格
				if (countPrice.compareTo(payedPrice)==0) {
//					orderService.payComplete(payMethod, orderNo, payedPrice,4);
					model.addAttribute("orderNo", orderNo);
//					Ordercontacter orderContacter = orderService.findContacterByOrderNo(orderNo);
//					model.addAttribute("email", orderContacter.getEmail());
					urlString =  "/front/payNotice/tourComplete.ftl";
				}else{
					noticeString = "Payment amount is incorrect, please contact our customer service without delay.";
					urlString =  "/front/payNotice/tourFail.ftl";
				}
			}else{
				noticeString = "Sorry, payment failed, please contact our customer service.";
				urlString =  "/front/payNotice/tourFail.ftl";
			}
		}catch(Exception e){
			System.out.println("authorizeSim pay exception.");
			noticeString = "Sorry, an exception occurs.";
			urlString =  "/front/payNotice/tourFail.ftl";
		}
//		model.addAttribute("str", str);
		model.addAttribute("noticeString", noticeString);
		return urlString;
	}
	
	/**
	 * @Title: simReturn
	 * @Description: 测试，sim返回结果处理
	 * @param model
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 */
	@RequestMapping("/relayTest")
	public String simReturnTest(Model model){
		String str = "";			//支付返回的参数字符串
		Enumeration<?> en = request.getParameterNames();
		while (en.hasMoreElements()) {
			String paramName = (String) en.nextElement();
			String paramValue = request.getParameter(paramName);
			try {
				paramValue=URLEncoder.encode(paramValue, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}		
			str = str + "&" + paramName + "="+paramValue;
		}
		String x_response_code = request.getParameter("x_response_code");				//交易状态
		String orderNo = request.getParameter("x_invoice_num");							//订单号
		BigDecimal payedPrice = new BigDecimal(request.getParameter("x_amount"));		//付款价格
		model.addAttribute("x_response_code", x_response_code);
		model.addAttribute("orderNo", orderNo);
		model.addAttribute("payedPrice", payedPrice);
		model.addAttribute("str", str);
		return "/front/sim_test.ftl";
	}
}
