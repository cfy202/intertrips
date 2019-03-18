/**
 * 
 */
package com.wenjing.action.front;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.authorize.Environment;
import net.authorize.api.contract.v1.CreateTransactionRequest;
import net.authorize.api.contract.v1.CreateTransactionResponse;
import net.authorize.api.contract.v1.CreditCardType;
import net.authorize.api.contract.v1.CustomerAddressType;
import net.authorize.api.contract.v1.CustomerDataType;
import net.authorize.api.contract.v1.MerchantAuthenticationType;
import net.authorize.api.contract.v1.MessageTypeEnum;
import net.authorize.api.contract.v1.OrderType;
import net.authorize.api.contract.v1.PaymentType;
import net.authorize.api.contract.v1.TransactionRequestType;
import net.authorize.api.contract.v1.TransactionResponse;
import net.authorize.api.contract.v1.TransactionTypeEnum;
import net.authorize.api.controller.CreateTransactionController;
import net.authorize.api.controller.base.ApiOperationBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wenjing.entity.CreditCardInfo;
import com.wenjing.entity.Ordercontacter;
import com.wenjing.service.CreditCardInfoService;
import com.wenjing.service.OrderService;
import com.wenjing.util.Tools;

/**
 * 类说明
 * @author xiejin
 * @date 2016-2-23 
 * @date 2016-2-23 上午10:26:52
 */
@Controller
@RequestMapping("/authorizeChargeCreditCard")
public class AuthorizeChargeCreditCardController {
	
	@Resource
	private OrderService orderService;
	@Autowired
	private CreditCardInfoService creditCardInfoService;
	
	/**测试账号**/
//	public final static String LOGINID = "5fm7GxU2X";
//	public final static String TRANSACTIONKEY = "7aQe8geBZ9T3z26B";
//	private final static Environment ENVIRONMENT = Environment.SANDBOX;
//	private final static String secretKey = "Simon";
	
	/**正式账号**/
	public final static String LOGINID = "2rj2JPMa5K";
	public final static String TRANSACTIONKEY = "53W5jK7F94u3wn7t";
	private final static Environment ENVIRONMENT = Environment.PRODUCTION;

    //
    // Run this sample from command line with:
    //                 java -jar target/ChargeCreditCard-jar-with-dependencies.jar
    //
	@RequestMapping("/run")
    public String run(Model model,HttpServletRequest request) {
		String cardNumber = request.getParameter("cardnum");
//		String expirationDate = request.getParameter("expirationDate");
		String amount = request.getParameter("amount");
		String orderNo = request.getParameter("orderNo");
		String cardType = request.getParameter("cardtype");
		String currencyCode = request.getParameter("currencyCode");
		String cardCode = request.getParameter("authenticationnum");	//信用卡识别码
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		String address = request.getParameter("streetAddress");
		String state = request.getParameter("state");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		String zip = request.getParameter("postCode");
		String expireMonth = request.getParameter("expireMonth");		//信用卡失效月份
		String expireYear = request.getParameter("expireYear");			//信用卡失效年份
		String ordersId = request.getParameter("ordersId");	
		String productName = request.getParameter("productName");
		String expirationDate = expireMonth + expireYear;	//信用卡失效日期

		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("apiLoginId", LOGINID);
		paramMap.put("transactionKey", TRANSACTIONKEY);
		paramMap.put("cardNumber", cardNumber);
		paramMap.put("orderNo", orderNo);
		paramMap.put("currencyCode", currencyCode);
		paramMap.put("cardCode", cardCode);
		paramMap.put("firstName", firstName);
		paramMap.put("lastName", lastName);
		paramMap.put("phoneNumber", phoneNumber);
		paramMap.put("email", email);
		paramMap.put("address", address);
		paramMap.put("state", state);
		paramMap.put("city", city);
		paramMap.put("country", country);
		paramMap.put("zip", zip);
		paramMap.put("expirationDate", expirationDate);
		paramMap.put("amount",amount);
		paramMap.put("productName",productName);
		paramMap.put("transactionType",TransactionTypeEnum.AUTH_ONLY_TRANSACTION.value());
		
		String urlString = null;			//返回路径
		String noticeString = null;			//支付异常通知信息
		String payExceptionCode = "";     //
		
		String payOrderNo = (String)request.getSession().getAttribute("payingOrderNo_"+ orderNo);
		if(orderNo.equals(payOrderNo)){
        	noticeString = "Your order is being paid...";
        	urlString = "/front/payNotice/tourFail.ftl"; 
		}else{
			String checkResult = orderService.checkStore(ordersId,new BigDecimal(amount));
			if("invalid".equals(checkResult)){ //不是新订单
				noticeString = "Your order has been paid or cancelled.";
				urlString = "/front/payNotice/tourFail.ftl"; 
			}else if("uncorrectprice".equals(checkResult)){ //价格不正确
				noticeString = "The amount you pay is not in conformity with the order.";
				urlString = "/front/payNotice/tourFail.ftl";
			}else if("success".equals(checkResult)){ //验证通过
				request.getSession().setAttribute("payingOrderNo_"+ orderNo,orderNo);
				/**
				 * 开始支付
				 */
				CreateTransactionResponse response = null;
				int index = 0;
				while(response == null && index < 3){
					response = pay(paramMap);
					index ++;
				}
		        if (response != null) {

		            // If API Response is ok, go ahead and check the transaction response
		            if (response.getMessages().getResultCode() == MessageTypeEnum.OK) {
		                TransactionResponse result = response.getTransactionResponse();
		                if (result.getResponseCode().equals("1")) {
		                	Integer payMethod = 5;													//付款方式,默认为全款，5
		        			BigDecimal countPrice = orderService.countPrice(ordersId, payMethod);	//计算的付款价格
		        			BigDecimal payedPrice = new BigDecimal(amount);
		        			if (countPrice.compareTo(payedPrice)==0) {
		        				CreditCardInfo creditCardInfo = new CreditCardInfo();
		        		        creditCardInfo.setId(Tools.getUUID());
		        		        creditCardInfo.setCardTypeString(cardType);
		        		        creditCardInfo.setCardnumber(cardNumber);
		        		        creditCardInfo.setExpiredate(expireYear + "-" + expireMonth);
		        		        creditCardInfo.setCardcode(cardCode);
		        		        creditCardInfo.setNationality(country);
		        		        creditCardInfo.setFirstname(firstName);
		        		        creditCardInfo.setLastname(lastName);
		        		        creditCardInfo.setAddress(address);
		        		        creditCardInfo.setCity(city);
		        		        creditCardInfo.setState(state);
		        		        creditCardInfo.setZipcode(zip);
		        		        creditCardInfo.setTel(phoneNumber);
		        		        creditCardInfo.setMail(email);
		        		        creditCardInfo.setOrdersid(ordersId);
		        		        creditCardInfoService.save(creditCardInfo);
		        				orderService.payComplete(request,payMethod, ordersId,orderNo, countPrice, 4,"");	//支付完成后修改订单状态
		        				Ordercontacter orderContacter = orderService.findContacterByOrdersId(ordersId);
		        				request.getSession().removeAttribute("payingOrderNo_"+ orderNo);
		        				return "redirect:/pay_success.htm?orderNo=" + orderNo + "&email=" + orderContacter.getEmail();
		        			}else{
		        				noticeString = "Payment amount is incorrect, please contact our customer service without delay.";
		        				urlString = "/front/payNotice/tourFail.ftl";
		        			}
		                } else {
		                	noticeString = "Payment Unsuccessful,Please try again or contact our customer service staff.";
		                	urlString = "/front/payNotice/tourFail.ftl";
		                	payExceptionCode = "Failed Transaction(responseCode)"+result.getResponseCode();
		                }
		            } else {
		            	noticeString = "Payment Unsuccessful,Please try again or contact our customer service staff.";
		            	urlString = "/front/payNotice/tourFail.ftl";
		            	payExceptionCode = "Failed Transaction(resultCode)"+response.getMessages().getResultCode();
		            }
		        }else{
		        	noticeString = "Payment Unsuccessful(No results were returned),Please try again or contact our customer service staff.";
		        	urlString = "/front/payNotice/tourFail.ftl";
		        }
			}else{ //没有库存
				urlString = "redirect:/" + checkResult + "?soldout";    
			}
		}
		model.addAttribute("noticeString", noticeString);
		model.addAttribute("payExceptionCode", payExceptionCode);
		request.getSession().removeAttribute("payingOrderNo_"+ orderNo);
        return urlString;
    }
	
	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/bookrun")
    public String bookrun(Model model,HttpServletRequest request) {
		String cardNumber = request.getParameter("cardnum");
//		String expirationDate = request.getParameter("expirationDate");
		String amount = request.getParameter("amount");
		String orderNo = request.getParameter("orderNo");
		String cardType = request.getParameter("cardtype");
		String currencyCode = request.getParameter("currencyCode");
		String cardCode = request.getParameter("authenticationnum");	//信用卡识别码
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		String address = request.getParameter("streetAddress");
		String state = request.getParameter("state");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		String zip = request.getParameter("postCode");
		String expireMonth = request.getParameter("expireMonth");		//信用卡失效月份
		String expireYear = request.getParameter("expireYear");			//信用卡失效年份
		String bookId = request.getParameter("ordersId");	
		String productName = request.getParameter("productName");
		String expirationDate = expireMonth + expireYear;	//信用卡失效日期

		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("apiLoginId", LOGINID);
		paramMap.put("transactionKey", TRANSACTIONKEY);
		paramMap.put("cardNumber", cardNumber);
		paramMap.put("orderNo", orderNo);
		paramMap.put("currencyCode", currencyCode);
		paramMap.put("cardCode", cardCode);
		paramMap.put("firstName", firstName);
		paramMap.put("lastName", lastName);
		paramMap.put("phoneNumber", phoneNumber);
		paramMap.put("email", email);
		paramMap.put("address", address);
		paramMap.put("state", state);
		paramMap.put("city", city);
		paramMap.put("country", country);
		paramMap.put("zip", zip);
		paramMap.put("expirationDate", expirationDate);
		paramMap.put("amount",amount);
		paramMap.put("productName",productName);
		paramMap.put("transactionType",TransactionTypeEnum.AUTH_ONLY_TRANSACTION.value());
		
		String urlString = null;			//返回路径
		String noticeString = null;			//支付异常通知信息
		String payExceptionCode = "";     //
		
		String payOrderNo = (String)request.getSession().getAttribute("payingOrderNo_"+ orderNo);
		if(orderNo.equals(payOrderNo)){
        	noticeString = "Your order is being paid...";
        	urlString = "/front/payNotice/tourFail.ftl"; 
		}else{
				request.getSession().setAttribute("payingOrderNo_"+ orderNo,orderNo);
				/**
				 * 开始支付
				 */
				CreateTransactionResponse response = null;
				int index = 0;
				while(response == null && index < 3){
					response = pay(paramMap);
					index ++;
				}
		        if (response != null) {

		            // If API Response is ok, go ahead and check the transaction response
		            if (response.getMessages().getResultCode() == MessageTypeEnum.OK) {
		                TransactionResponse result = response.getTransactionResponse();
		                if (result.getResponseCode().equals("1")) {
		        			BigDecimal payedPrice = new BigDecimal(amount);
	        				CreditCardInfo creditCardInfo = new CreditCardInfo();
	        		        creditCardInfo.setId(Tools.getUUID());
	        		        creditCardInfo.setCardTypeString(cardType);
	        		        creditCardInfo.setCardnumber(cardNumber);
	        		        creditCardInfo.setExpiredate(expireYear + "-" + expireMonth);
	        		        creditCardInfo.setCardcode(cardCode);
	        		        creditCardInfo.setNationality(country);
	        		        creditCardInfo.setFirstname(firstName);
	        		        creditCardInfo.setLastname(lastName);
	        		        creditCardInfo.setAddress(address);
	        		        creditCardInfo.setCity(city);
	        		        creditCardInfo.setState(state);
	        		        creditCardInfo.setZipcode(zip);
	        		        creditCardInfo.setTel(phoneNumber);
	        		        creditCardInfo.setMail(email);
	        		        creditCardInfo.setBookingId(bookId);
	        		        creditCardInfoService.save(creditCardInfo);
	        				orderService.bookComplete(request, bookId,orderNo, payedPrice, 4,"");	//支付完成后修改订单状态
	        				request.getSession().removeAttribute("payingOrderNo_"+ orderNo);
	        				return "redirect:/book_complete.htm";
		                } else {
		                	noticeString = "Payment Unsuccessful,Please try again or contact our customer service staff.";
		                	urlString = "/front/payNotice/tourFail.ftl";
		                	payExceptionCode = "Failed Transaction(responseCode)"+result.getResponseCode();
		                }
		            } else {
		            	noticeString = "Payment Unsuccessful,Please try again or contact our customer service staff.";
		            	urlString = "/front/payNotice/tourFail.ftl";
		            	payExceptionCode = "Failed Transaction(resultCode)"+response.getMessages().getResultCode();
		            }
		        }else{
		        	noticeString = "Payment Unsuccessful(No results were returned),Please try again or contact our customer service staff.";
		        	urlString = "/front/payNotice/tourFail.ftl";
		        }
		}
		model.addAttribute("noticeString", noticeString);
		model.addAttribute("payExceptionCode", payExceptionCode);
		request.getSession().removeAttribute("payingOrderNo_"+ orderNo);
        return urlString;
    }
	
	/**
	 * 支付成功
	 * 
	 * @return
	 */
	@RequestMapping("/paysuccess")
	public String pay_success(Model model,String orderNo,String email){
		model.addAttribute("orderNo", orderNo);
		model.addAttribute("email", email);
		return "/front/payNotice/tourComplete.ftl";
	}
	
	/**
	 * 
	 */
	public CreateTransactionResponse pay(Map<String,String> paramMap){
		/**设置支付环境**/
        ApiOperationBase.setEnvironment(ENVIRONMENT);

        MerchantAuthenticationType merchantAuthenticationType  = new MerchantAuthenticationType();
        merchantAuthenticationType.setName(paramMap.get("apiLoginId"));
        merchantAuthenticationType.setTransactionKey(paramMap.get("transactionKey"));
        ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);   //设置loginId和transactionKey

        // Populate the payment data
        PaymentType paymentType = new PaymentType();
        CreditCardType creditCard = new CreditCardType();
        creditCard.setCardNumber(paramMap.get("cardNumber"));						//卡号
        creditCard.setExpirationDate(paramMap.get("expirationDate"));				//有效期
        creditCard.setCardCode(paramMap.get("cardCode"));							//信用卡安全码
        paymentType.setCreditCard(creditCard);  //paymentType设置信用卡类型
        
        //设置账单信息
        CustomerAddressType customerAddressType = new CustomerAddressType();
        customerAddressType.setFirstName(paramMap.get("firstName"));				//firstName
     	customerAddressType.setLastName(paramMap.get("lastName"));					//lastName
     	customerAddressType.setPhoneNumber(paramMap.get("phoneNumber"));			//phoneNumber
     	customerAddressType.setAddress(paramMap.get("address"));					//地址
     	customerAddressType.setZip(paramMap.get("zip"));							//邮编
     	customerAddressType.setCity(paramMap.get("city"));							//城市
     	customerAddressType.setState(paramMap.get("state"));						//州，省
     	customerAddressType.setCountry(paramMap.get("country"));					//国家
//     	customerAddressType.setEmail(email);						//email
     	
     	//设置消费者信息
     	CustomerDataType customerDataType = new CustomerDataType();
     	customerDataType.setEmail(paramMap.get("email"));

        // Create the payment transaction request
        TransactionRequestType txnRequest = new TransactionRequestType();
        txnRequest.setTransactionType(TransactionTypeEnum.AUTH_ONLY_TRANSACTION.value());
        txnRequest.setPayment(paymentType);					//set支付方式信息
        txnRequest.setAmount(new BigDecimal(paramMap.get("amount")));		//金额
        txnRequest.setCurrencyCode(paramMap.get("currencyCode"));			//设置币种
        OrderType orderType = new OrderType();
        orderType.setDescription(paramMap.get("productName"));
        orderType.setInvoiceNumber(paramMap.get("orderNo"));				//设置订单号
        txnRequest.setOrder(orderType);
        txnRequest.setBillTo(customerAddressType);			//set账单信息
        txnRequest.setCustomer(customerDataType);			//设置客户信息

        // Make the API Request
        CreateTransactionRequest apiRequest = new CreateTransactionRequest();
        apiRequest.setTransactionRequest(txnRequest);
        CreateTransactionController controller = new CreateTransactionController(apiRequest);
        controller.execute();
		return controller.getApiResponse();
	}
	
	/**
	 * @Title: runTest
	 * @Description: 测试
	 * @param model
	 * @param request
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 */
	@RequestMapping("/runTest")
    public String runTest(Model model,HttpServletRequest request) {
		String apiLoginId = LOGINID;
		String transactionKey = TRANSACTIONKEY;
		String cardNumber = "4012888818888";
		String expirationDate = "0920";
		String amount = "1";
		String orderNo = "1604010012";
		String currencyCode = "USD";
		String cardCode = "3609";
		String firstName = "Bo";
		String lastName = "Wang";
		String phoneNumber = "6263779969";
		String email = "wang@gmail.com";
		String address = "918 Clement St, STE 101";
		String state = "CA";
		String city = "San Francisco";
		String country = "US";
		String zip = "94118";

        //Common code to set for all requests
		/**设置支付环境**/
        ApiOperationBase.setEnvironment(ENVIRONMENT);

        MerchantAuthenticationType merchantAuthenticationType  = new MerchantAuthenticationType();
        merchantAuthenticationType.setName(apiLoginId);
        merchantAuthenticationType.setTransactionKey(transactionKey);
        ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);

        // Populate the payment data
        PaymentType paymentType = new PaymentType();
        CreditCardType creditCard = new CreditCardType();
        creditCard.setCardNumber(cardNumber);						//卡号
        creditCard.setExpirationDate(expirationDate);				//有效期
        creditCard.setCardCode(cardCode);							//信用卡安全码
        paymentType.setCreditCard(creditCard);
        
        //设置账单信息
        CustomerAddressType customerAddressType = new CustomerAddressType();
        customerAddressType.setFirstName(firstName);				//firstName
     	customerAddressType.setLastName(lastName);					//lastName
     	customerAddressType.setPhoneNumber(phoneNumber);			//phoneNumber
     	customerAddressType.setAddress(address);					//地址
     	customerAddressType.setZip(zip);							//邮编
     	customerAddressType.setCity(city);							//城市
     	customerAddressType.setState(state);						//州，省
     	customerAddressType.setCountry(country);					//国家
//     	customerAddressType.setEmail(email);						//email
     	
     	//设置消费者信息
     	CustomerDataType customerDataType = new CustomerDataType();
     	customerDataType.setEmail(email);

        // Create the payment transaction request
        TransactionRequestType txnRequest = new TransactionRequestType();
        txnRequest.setTransactionType(TransactionTypeEnum.AUTH_ONLY_TRANSACTION.value());
        txnRequest.setPayment(paymentType);					//set支付方式信息
        txnRequest.setAmount(new BigDecimal(amount));		//金额
        txnRequest.setCurrencyCode(currencyCode);			//设置币种
        OrderType orderType = new OrderType();
        orderType.setInvoiceNumber(orderNo);				//设置订单号
        txnRequest.setOrder(orderType);
        txnRequest.setBillTo(customerAddressType);			//set账单信息
        txnRequest.setCustomer(customerDataType);			//设置客户信息

        // Make the API Request
        CreateTransactionRequest apiRequest = new CreateTransactionRequest();
        apiRequest.setTransactionRequest(txnRequest);
        CreateTransactionController controller = new CreateTransactionController(apiRequest);
        controller.execute();

        CreateTransactionResponse response = controller.getApiResponse();

        if (response!=null) {

            // If API Response is ok, go ahead and check the transaction response
            if (response.getMessages().getResultCode() == MessageTypeEnum.OK) {

                TransactionResponse result = response.getTransactionResponse();
                if (result.getResponseCode().equals("1")) {
//                	Integer payMethod = 5;													//付款方式,默认为全款，5
//        			BigDecimal countPrice = orderService.countPrice(orderNo, payMethod);	//计算的付款价格
//        			BigDecimal payedPrice = new BigDecimal(amount);
//        			if (countPrice.compareTo(payedPrice)==0) {
//        				orderService.payComplete(payMethod, orderNo, payedPrice,4);
////        				urlString =  "/front/sim/sim_success.ftl";
//        			}else{
////        				noticeString = "支付金额不正确，请及时联我们的客服。";
//        			}
                	
                	System.out.println("Successful Credit Card Transaction");
                    System.out.println(result.getResponseCode());
                    System.out.println(result.getAuthCode());
                    System.out.println(result.getTransId());
                    
                    model.addAttribute("status", "Successful Credit Card Transaction");
                    model.addAttribute("responseCode", result.getResponseCode());
                    model.addAttribute("authCode", result.getAuthCode());
                    model.addAttribute("transId", result.getTransId());
                    model.addAttribute("accountNumber", result.getAccountNumber());
                    model.addAttribute("accountType", result.getAccountType());
                }
                else
                {
                    System.out.println("Failed Transaction"+result.getResponseCode());
                    
                    model.addAttribute("status", "Failed Transaction(responseCode)"+result.getResponseCode());
                }
            }
            else
            {
                System.out.println("Failed Transaction:  "+response.getMessages().getResultCode());
                
                model.addAttribute("status", "Failed Transaction(resultCode)"+response.getMessages().getResultCode());
            }
        }
        return "/front/ccc_test.ftl";
    }
}
