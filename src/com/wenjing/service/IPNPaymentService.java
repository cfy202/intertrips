/**
 * 
 */
package com.wenjing.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.HttpClientConnect;
import com.wenjing.PayAttributes;
import com.wenjing.dao.CostMapper;
import com.wenjing.dao.OrdersMapper;
import com.wenjing.entity.Orderdetail;
import com.wenjing.entity.Orders;

/**
 * 类说明
 * @author xiejin
 * @date 2015-7-28 
 * @date 2015-7-28 下午4:40:35
 */
@Service
public class IPNPaymentService {
	@Resource
	private OrdersMapper ordersMapper;
	@Resource
	private OrderService orderService;
	@Resource
	private HttpServletRequest request;
	@Resource
	private CostMapper costMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.edu.biz.IIPNPaymentBiz#pay(java.util.Map)
	 */
	@Transactional
	public int changeStatusPassIpnNotice(Map<String, String> paramsMap) {
		int flag = 0;
		String responseStr = null;
		paramsMap.put("cmd", "_notify-validate");
		try {
			responseStr = HttpClientConnect.doPost(PayAttributes.PAYPALURL, paramsMap);
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			flag = -1;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			flag = -1;
		} catch (IOException e) {
			e.printStackTrace();
			flag = -1;
		}
		
//		String orderId = paramsMap.get("orderId");		//订单id
//		Orders orders = ordersMapper.selectByPrimaryKey(orderId);
//		int payMethod = Integer.valueOf(paramsMap.get("method"));	//支付方式
//		double standardPrice = this.getPayPrice(orderId, payMethod);
		String orderNo = paramsMap.get("item_number");		//订单号
		int payMethod = Integer.valueOf(paramsMap.get("custom"));	//支付方式
		BigDecimal payedPrice = new BigDecimal(paramsMap.get("mc_gross"));
		String  payment_status = paramsMap.get("payment_status");
		String  receiver_email = paramsMap.get("receiver_email");
		String  mc_currency = paramsMap.get("mc_currency");	//付款币种
		String ordersId = paramsMap.get("ordersId");

		Orders orders = orderService.finByOrderNo(orderNo);	//总单
		Orderdetail orderdetail = orderService.findByOrderId(orders.getId());//订单详情
		String currencyCode = orderdetail.getCurrencySign();	//订单对应的币种
		BigDecimal countPrice = orderService.countPrice(orders.getId(), payMethod);
		if ("VERIFIED".equals(responseStr)) {
			if ("Completed".equals(payment_status)) {
       			if (countPrice.compareTo(payedPrice)==0
						&& currencyCode.equals(mc_currency)
						) {
//						 orderService.payComplete(payMethod, ordersId,orderNo, payedPrice,1);
				} else {
					// 付款信息不正确
//					save(new PaymentStatus(orderId,"WRONG_INFORMATION"));
				}
			} else {
				// not completed
//				save(new PaymentStatus(orderId,"NOT_COMPLETED"));
			}
		} else if ("INVALID".equals(responseStr)) {
			// invalid
//			save(new PaymentStatus(orderId,"INVALID"));
		}
        return flag;
	}
}
