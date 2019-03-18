package com.wenjing.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wenjing.dao.OrdersMapper;


/**
 * 
 * @author
 */
@Service
public class BasePaymentService {
	@Resource
	private OrdersMapper ordersMapper;
//	@Resource
//	private MobileBookService mobilebookService;
	// 全部支付的订单状态
	private static final int PAY_ALL = 3;

	// 付定金的订单状态
	private static final int PAY_DEPOSIT = 2;

	/*
	 * 根据返回结果，订单id，支付类型 更改数据库状态
	 */
//	public void changeOrderStatus(int orderId, int payMethod) {
//
//		int orderStatus = (payMethod == 1 ? PAY_DEPOSIT : PAY_ALL);
//		System.out.println(orderStatus);
//		Orders orders = new Orders();
//		orders.setOrderStatus(orderStatus);
//		orders.setOrderstatus(orderStatus);
//		orders.setId(orderId);
//		
//		ordersMoblieMapper.updateByPrimaryKeySelective(orders);
//		if (payMethod != 2) {
//			OrdersMoblie ordersd = new OrdersMoblie();
//			ordersd.setId(orderId);
//			ordersd.setDepostidate(Tools.getnowtime());
//			
//			ordersMoblieMapper.updateByPrimaryKeySelective(ordersd);
//		}
//		if (payMethod != 1) {
//			OrdersMoblie ordersf = new OrdersMoblie();
//			ordersf.setId(orderId);
//			ordersf.setFinalpaydate(Tools.getnowtime());
//			
//			ordersMoblieMapper.updateByPrimaryKeySelective(ordersf);
//		}
//	}

	/*
	 * 根据订单id，支付类型算出应付金额
	 */
//	public double getPayPrice(String orderId, int payMethod) {
//		double price = 0;
//		Orders orders = ordersMapper.selectByPrimaryKey(orderId);
//		String sumper = mobilebookService.getTotal(orders);
//		switch (payMethod) {
//		case 0:
//			price = Double.parseDouble(mobilebookService.getTotal(orders));
//			break;
//		case 1:
//			price = mobilebookService.getDeposit(orders, sumper);
//			break;
//		case 2:
//			price = Double.parseDouble(mobilebookService.getFinalpay(orders,sumper));
//		}
//		return Tools.TowDecimal(price);
//	}
}
