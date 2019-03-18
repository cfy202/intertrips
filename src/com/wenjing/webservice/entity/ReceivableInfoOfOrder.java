package com.wenjing.webservice.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * @copyright   Copyright: 2014 
 * @author Jared
 * @create-time Sep 3, 2014 1:53:52 PM
 * @revision  3.0
 */
public class ReceivableInfoOfOrder {
	
	private String id;
	
	private BigDecimal totalCommonTourFee; //常规团费总额
	
	private BigDecimal totalFeeOfOthers; //《非团无》《团==其他费用总额》
	
	private BigDecimal sumFee; //《非团==共计应收团款》《团==共计应收团款》
	
	private String orderId; //订单ID
	
	private BigDecimal peerUserCost; //同行订单同行佣金
	
	private Integer totalPeople; //总人数
	
	private List<OrderReceiveItem> orderReceiveItemList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public List<OrderReceiveItem> getOrderReceiveItemList() {
		return orderReceiveItemList;
	}

	public void setOrderReceiveItemList(List<OrderReceiveItem> orderReceiveItemList) {
		this.orderReceiveItemList = orderReceiveItemList;
	}

	public BigDecimal getTotalCommonTourFee() {
		return totalCommonTourFee;
	}

	public void setTotalCommonTourFee(BigDecimal totalCommonTourFee) {
		this.totalCommonTourFee = totalCommonTourFee;
	}

	public BigDecimal getTotalFeeOfOthers() {
		return totalFeeOfOthers;
	}

	public void setTotalFeeOfOthers(BigDecimal totalFeeOfOthers) {
		this.totalFeeOfOthers = totalFeeOfOthers;
	}

	public BigDecimal getSumFee() {
		return sumFee;
	}

	public void setSumFee(BigDecimal sumFee) {
		this.sumFee = sumFee;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getPeerUserCost() {
		return peerUserCost;
	}

	public void setPeerUserCost(BigDecimal peerUserCost) {
		this.peerUserCost = peerUserCost;
	}

	public Integer getTotalPeople() {
		return totalPeople;
	}

	public void setTotalPeople(Integer totalPeople) {
		this.totalPeople = totalPeople;
	}
}
