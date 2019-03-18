/**
 * 
 */
package com.wenjing.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 类说明		订单支付详情实体类
 * @author xiejin
 * @date 2015-7-21 
 * @date 2015-7-21 上午10:08:52
 */
public class PayDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String orderNo;
	
	private BigDecimal payPrice;
	
	private String payTime;
	
	private Integer payWay;

	public Integer getPayWay() {
		return payWay;
	}

	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public BigDecimal getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(BigDecimal payPrice) {
		this.payPrice = payPrice;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
}
