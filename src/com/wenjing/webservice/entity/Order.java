package com.wenjing.webservice.entity;

/**
 * @author Jared
 *
 * Sep 28, 2015
 */
public class Order{
	
	//用户ID
	private String userId;
	
	//总人数
	private int totalPeople;
	
	//线路CODE
	private String tourCode;
	
	//联系人名称
	private String contactName;
	
	private String orderNoIn;
	
	//邮箱
	private String year;

	//联系电话
	private String time;
	
	//订单号
	private String refNo;
	
	//支付时间
	private String arrivaDate;
	
	private int tax;
	
	// paypal: 1   credit card: 2
	private Integer sorceId;
	
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getOrderNoIn() {
		return orderNoIn;
	}

	public void setOrderNoIn(String orderNoIn) {
		this.orderNoIn = orderNoIn;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getTotalPeople() {
		return totalPeople;
	}

	public void setTotalPeople(int totalPeople) {
		this.totalPeople = totalPeople;
	}

	public String getTourCode() {
		return tourCode;
	}

	public void setTourCode(String tourCode) {
		this.tourCode = tourCode;
	}
	
	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}
	
	public String getArrivaDate() {
		return arrivaDate;
	}

	public void setArrivaDate(String arrivaDate) {
		this.arrivaDate = arrivaDate;
	}

	public Integer getSorceId() {
		return sorceId;
	}

	public void setSorceId(Integer sorceId) {
		this.sorceId = sorceId;
	}
	
}
