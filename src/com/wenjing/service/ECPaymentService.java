package com.wenjing.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Service;

import com.wenjing.HttpClientConnect;
import com.wenjing.dao.OrdersMapper;
import com.wenjing.entity.Orders;
import com.wenjing.util.Tools;

@Service
public class ECPaymentService extends BasePaymentService {
	@Resource
	private OrdersMapper ordersMapper;
	@Resource
	private OrderService orderService;
	

	// 保存卖主信息
	private static Map<String, String> sellerPaypalInformation = new HashMap<String, String>();

	// 执行API的URL
	// private static final String CALL_API_URL = "https://api-3t.paypal.com/nvp?";
	// 重定向到paypal的基本路径
	// private static final String BATH_TO_PAYPAL_URL = "https://www.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=";

	// 执行API的URL,沙盒
	private static final String CALL_API_URL = "https://api-3t.sandbox.paypal.com/nvp?";
	// 重定向到paypal的基本路径，沙盒
	private static final String BATH_TO_PAYPAL_URL = "https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=";

	// paypay返回本网站的基本路径
	private static final String BATH_BACK_URL = "http://192.168.1.117:8080/uswenjing/";
//	private static final String BATH_BACK_URL = "http://192.168.1.5:8080/uswenjing/";
//	private static final String BATH_BACK_URL = "http://121.43.114.206/";

	// 设置卖主信息
	static {
		// 正式环境数据
		// sellerPaypalInformation.put("USER",
		// "payment.chinatour.com_api1.gmail.com");
		// sellerPaypalInformation.put("PWD", "WD8CLWVW995P9QMU");
		// sellerPaypalInformation.put("SIGNATURE",
		// "AiPC9BjkCyDFQXbSkoZcgqH3hpacAf9ETNn.XfsvjK6m3HI-e3GA7yxZ");

		// sandbox 环境
		sellerPaypalInformation.put("USER", "JaredSell_api3.paypal.com");
		sellerPaypalInformation.put("PWD", "1406595654");
		sellerPaypalInformation.put("SIGNATURE",
				"AFcWxV21C7fd0v3bYYYRCpSSRl31A6ygsa4sUIR3y9CMoR9vQarUeTlw");
	}

	/*
	 * (non-Javadoc)
	 * @see com.edu.biz.IPayBiz#payStart(int, int, java.lang.String,
	 * java.lang.String)
	 */

	public Map<String, String> payStart(String orderId, int payMethod ,BigDecimal price,String currencyType) {
		String returnURL = BATH_BACK_URL + "pay/getInformation.do?id=";
		returnURL += orderId + "&method=" + payMethod+"&price="+price;
		String cancelURL = BATH_BACK_URL;
		Orders orders = ordersMapper.selectByPrimaryKey(orderId);
		// cancelURL +="mobilebook/orderList.do?ordersId="+orders.getId();
		String orderNo = orders.getOrderno();
		Map<String, String> result = pay(orders, 1, payMethod, null, null,
				returnURL, cancelURL ,price,currencyType);
		String status = passStatus(result);
		result.put("status", status);
		if (status.equals("success")) {
			String redirectURL = BATH_TO_PAYPAL_URL + result.get("TOKEN");
			result.put("redirectURL", redirectURL);
		} else {
			result.put("orderNo", orderNo);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.edu.biz.IPayBiz#payGetInformation(int, java.lang.String)
	 */

	public Map<String, String> payGetInformation(String orderId, String token,
			BigDecimal price,int method,String currencyType) {

		Orders orders = ordersMapper.selectByPrimaryKey(orderId);
		Map<String, String> result = pay(orders, 2, method, token, null, null, null,price,currencyType);

		result.put("status", passStatus(result));
		result.put("orderNo", orders.getOrderno());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.edu.biz.IPayBiz#payComplete(int, int, java.lang.String,
	 * java.lang.String)
	 */

	 public Map<String, String> payComplete(String orderId, int payMethod,
	 String token, String payerId,BigDecimal price,String currencyType) {
		 Orders orders = ordersMapper.selectByPrimaryKey(orderId);
		 Map<String, String> result = pay(orders, 3, payMethod, token, payerId,
		 null, null,price,currencyType);
		 result.put("status", passStatus(result));
		 if (result.get("status").equals("success")) {
			 String orderNo = orders.getOrderno();
//			 orderService.payComplete(payMethod, orderNo, price,1);
		 }
		 return result;
	 }

	/*
	 * 根据各种执行情况封装成统一的状态提示 如果status为runNormal，而且调用ExpressCheckout
	 * API返回参数ACK的值也为success，说明支付流程成功运行，status设为success
	 * 如果status为runNormal,且ACK值不为success
	 * ，说明支付流程没成功，若pal提示已支付过，status设为hasPayed，否则返回fail
	 * 如果status不为runNormal，说明程序运行出错，返回error
	 */
	private String passStatus(Map<String, String> result) {
		String status = result.get("status");
		if (status.equals("runNormal")) {
			if (result.get("ACK").equalsIgnoreCase("success")) {
				return "success";
			} else {
				if (result.get("L_ERRORCODE0").equalsIgnoreCase("11607")) {
					return "hasPayed";
				}
				return "fail";
			}
		} else {
			return "error";
		}
	}

	/*
	 * 根据订单信息,支付方式 对哪个ExpressCheckout API的调用 1 ： SetExpressCheckout 2：
	 * GetExpressCheckoutDetails 3： DoExpressCheckoutPayment 以及调用
	 * DoExpressCheckoutPayment时所需的参数payerId 来调用ExpressCheckout API
	 * 并将调用之后Paypal的返回值封装成map 并在其中设入key为status，类型为String表示执行情况的值 执行成功为 success
	 * 异常为 error
	 */
	private Map<String, String> pay(Orders orders, int step, int payMethod,
			String token, String payerId, String returnURL,
			String cancelURL,BigDecimal price,String currencyType) {

		Map<String, String> resultMap = null;
		boolean status = true;

// 		double price = this.getPayPrice(orders.getId(), payMethod);
//		BigDecimal price = new BigDecimal(100);
		// 获得请求参数
		Map<String, String> paramsMap = this.getRequestParams(orders, price,
				step, token, payerId, returnURL, cancelURL,currencyType);

		// 执行
		try {
			resultMap = HttpClientConnect.doECAPI(CALL_API_URL, paramsMap);	//执行完之后返回结果集
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			if (step == 3) {
				Tools.logErrorTransaction(Tools.getnowtime() + ": orderNo:"
						+ orders.getOrderno() + ";price:" + price);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			status = false;
		} catch (IOException e) {
			e.printStackTrace();
			status = false;
		}

		/*
		 * 根据执行结果在resultMap加入执行状态 如果status为true并且resultMap不为null，说明paypal
		 * api正常运行， 在resultMap中加入status，value为runNormal 除此之外，表示paypal
		 * api执行出现异常情况 在resultMap中加入status，value为exception
		 */
		if (resultMap == null) {
			resultMap = new HashMap<String, String>();
			resultMap.put("status", "exception");
		} else {
			if (status) {
				resultMap.put("status", "runNormal");
			} else {
				resultMap.put("status", "exception");
			}
		}
		return resultMap;
	}

	/*
	 * 根据支付金额，订单信息 对哪个ExpressCheckout API的调用 1 ： SetExpressCheckout 2：
	 * GetExpressCheckoutDetails 3： DoExpressCheckoutPayment 以及调用
	 * DoExpressCheckoutPayment时所需的参数payerId
	 * 和调用SetExpressCheckout时所需的returnURL,cancelURL 来将调用ExpressCheckout
	 * API所需的参数封装成Map类型并返回
	 */
	private Map<String, String> getRequestParams(Orders orders, BigDecimal price,
			int step, String token, String payerId, String returnURL,
			String cancelURL,String currencyType) {

		Map<String, String> paramsMap = new HashMap<String, String>(
				sellerPaypalInformation);
		paramsMap.put("VERSION", "115.0");
		switch (step) {
		case 1:
			paramsMap.put("METHOD", "SetExpressCheckout");
			paramsMap.put("PAYMENTREQUEST_0_PAYMENTACTION", "sale");
			paramsMap.put("RETURNURL", returnURL);
			paramsMap.put("CANCELURL", cancelURL);
			setIndividualShowInformation(paramsMap, orders, price,currencyType);
			break;
		case 2:
			paramsMap.put("METHOD", "GetExpressCheckoutDetails");
			paramsMap.put("TOKEN", token);
			break;
		case 3:
			paramsMap.put("METHOD", "DoExpressCheckoutPayment");
			paramsMap.put("PAYMENTREQUEST_0_PAYMENTACTION", "sale");
			paramsMap.put("TOKEN", token);
			paramsMap.put("PAYERID", payerId);
			setIndividualShowInformation(paramsMap, orders, price,currencyType);
		}
		return paramsMap;
	}

	/*
	 * 传递参数给paypal定义页面显示
	 */
	private void setIndividualShowInformation(Map<String, String> paramsMap,
			Orders orders, BigDecimal price,String currencyType) {
		paramsMap.put("L_PAYMENTREQUEST_0_NAME0",
				"Order No:" + orders.getOrderno());
		// paramsMap.put("L_PAYMENTREQUEST_0_NUMBER0", orders.getTourcode());
		paramsMap.put("L_PAYMENTREQUEST_0_AMT0", "" + price);
		paramsMap.put("L_PAYMENTREQUEST_0_QTY0", "1");
		paramsMap.put("PAYMENTREQUEST_0_AMT", "" + price);
		paramsMap.put("PAYMENTREQUEST_0_CURRENCYCODE", currencyType);
		paramsMap.put("ALLOWNOTE", "1");
	}
}
