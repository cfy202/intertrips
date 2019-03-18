package com.wenjing.action.front;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wenjing.service.ECPaymentService;

@RequestMapping("/pay")
@Controller
public class ECPaymentController  {
	
	// 传入的id作为orders的id用于查出order
	private String id;
	
    // 支付的方式 0代表all; 1代表deposit; 2代表支付剩余款项
	private int method;
	
	// 执行 GetExpressCheckoutDetails和 DoExpressCheckoutPayment所需的token
	private String token;

	// 执行 DoExpressCheckoutPayment所需的payerID
    private String payerId;
    
    //付款金额
    private BigDecimal price;
    
	//执行完ExpressCheckout API的返还参数
   	private Map<String, String> result;
   	
	//重定向到paypal的url
   	public String redirectURL;
   	
	//币种
	private String currencyType;
   	
 	// pay的业务层
   	@Resource
	private ECPaymentService ecpaymentService;
    @Autowired  
    private  HttpServletRequest request;
    
	/*
	 * 执行SetExpressCheckout 若成功返回toPaypal 
	 * 支付失败，返回"/front/paypal/pay_notice.ftl"
	 */
   	@RequestMapping("/checkout")
	public String checkout(Model models) {
   		id = request.getParameter("id");
   		String methods = request.getParameter("payStatus");
   		if(methods!=null){
   			method = Integer.valueOf(methods);
   		}
   		currencyType = request.getParameter("currencyType");
   		price = new BigDecimal(request.getParameter("fee"));
		result = ecpaymentService.payStart(id,method,price,currencyType);
        String control = getControlAndSetErrorMessageByStatus("toPaypal",1);
		if (control.equals("toPaypal")) {
			 redirectURL=result.get("redirectURL");
			 return "redirect:"+redirectURL;
		}else{
			models.addAttribute("result",result);
			String mesage = getMessage();
			models.addAttribute("mesage", mesage);
			return control;
		}
	}	

	/*
	 * 执行GetExpressCheckoutDetails 若成功返回toConfirm
	 * 支付失败，返回"/front/paypal/pay_notice.ftl"
	 */
   	@RequestMapping("/getInformation")
	public String getInformation(Model models) {
   		id = request.getParameter("id");
   		String methods = request.getParameter("method");
   		if(methods!=null){
   			method = Integer.valueOf(methods);
   		}
   		BigDecimal price1 = new BigDecimal(request.getParameter("price"));
   		token = request.getParameter("token");
   		payerId = request.getParameter("PayerID");
   		models.addAttribute("id",id);
   		models.addAttribute("method",method);
   		models.addAttribute("token",token);
   		models.addAttribute("payerId",payerId);
   		models.addAttribute("price", price1);
		result = ecpaymentService.payGetInformation(id, token,price1,method,currencyType);
		models.addAttribute("result",result);
		String mesage = getMessage();
		models.addAttribute("mesage", mesage);
		return getControlAndSetErrorMessageByStatus("/front/paypal/pay_comfirm.ftl",2);
	}

	/*
	 * 执行 DoExpressCheckoutPayment 若成功返回completed 
	 * 支付失败，返回"/front/paypal/pay_notice.ftl"
	 */
   	@RequestMapping("/complete")
	public String complete(Model models,@RequestParam("id")String id, @RequestParam("method")int method,
			@RequestParam("token")String token, @RequestParam("payerId")String payerId, @RequestParam("price")BigDecimal price) {
		String orderNo = result.get("orderNo");
		result = ecpaymentService.payComplete(id,method, token, payerId,price,currencyType);
		result.put("orderNo", orderNo);
		models.addAttribute("result",result);
		String mesage = getMessage();
		models.addAttribute("mesage", mesage);
	    return getControlAndSetErrorMessageByStatus("/front/paypal/pay_success.ftl",3);
	}

	/*
	 * 根据业务层的执行结果
	 * 如果status为success，说明paypal成功执行，control不变
	 * 如果status为不为success，control变为input
	 * 然后根据各类情况输入提示信息 
	 */
	private String getControlAndSetErrorMessageByStatus(String control,int step) {
		String status = result.get("status");
		if (!status.equals("success")) {
            control = "/front/paypal/pay_notice.ftl";
//			if (status.equals("hasPayed")) {
//				mesage = "Your have paid successfully, please do not repeat submission.";
//			} else if (status.equals("fail")) {
//				mesage ="Your payment account may be insufficient, please pay after you recharged.";
//			} else {
//				mesage ="Sorry, the server is busy now. We'll solve the problem as soon as possible."; 
//			}
		}
		return control;
	}
	
	/**
	 * @Title: getMessage
	 * @Description: 支付异常通知
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 */
	private String getMessage() {
		String status = result.get("status");
		String mesage = "";
		if (!status.equals("success")) {
			if (status.equals("hasPayed")) {
				mesage = "您已经支付成功，请不要重复提交。";
			} else if (status.equals("fail")) {
				mesage ="您的账户余额可能不足，请充值后再支付。";
			} else {
				mesage ="对不起，服务器繁忙，我们将尽快解决问题。"; 
			}
		}
		return mesage;
	}

}
