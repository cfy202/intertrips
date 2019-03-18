package com.wenjing.vo;

import java.util.Date;

public class OrderQueryVO {
	
	//订单编号
	private String orderNo;
	
	//产品编号或CODE
	private String productNoOrCode;
	
	//联系人名称
	private String contacterName;
	
	//出发日期的最小日期
	private Date departureDateBeforeLimit;
	
	//出发日期的最大日期
	private Date departureDateAfterLimit;
	
	//下单日期的最小日期
	private Date bookingTimeBeforeLimit;
	
	//下单日期的最大日期
	private Date bookingTimeAfterLimit;
	
	//订单状态ID
	private String orderStatusId;
	
	//当前分页页数
	private String pageNow;
	
	//每页容量
	private String pageSize;
	
	//运营中心ID
	private String costId;
	
	public String getCostId() {
		return costId;
	}

	public void setCostId(String costId) {
		this.costId = costId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getProductNoOrCode() {
		return productNoOrCode;
	}

	public void setProductNoOrCode(String productNoOrCode) {
		this.productNoOrCode = productNoOrCode;
	}

	public String getContacterName() {
		return contacterName;
	}

	public void setContacterName(String contacterName) {
		this.contacterName = contacterName;
	}

	public Date getDepartureDateBeforeLimit() {
		return departureDateBeforeLimit;
	}

	public void setDepartureDateBeforeLimit(Date departureDateBeforeLimit) {
		this.departureDateBeforeLimit = departureDateBeforeLimit;
	}

	public Date getDepartureDateAfterLimit() {
		return departureDateAfterLimit;
	}

	public void setDepartureDateAfterLimit(Date departureDateAfterLimit) {
		this.departureDateAfterLimit = departureDateAfterLimit;
	}

	public Date getBookingTimeBeforeLimit() {
		return bookingTimeBeforeLimit;
	}

	public void setBookingTimeBeforeLimit(Date bookingTimeBeforeLimit) {
		this.bookingTimeBeforeLimit = bookingTimeBeforeLimit;
	}

	public Date getBookingTimeAfterLimit() {
		return bookingTimeAfterLimit;
	}

	public void setBookingTimeAfterLimit(Date bookingTimeAfterLimit) {
		this.bookingTimeAfterLimit = bookingTimeAfterLimit;
	}

	public String getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(String orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public String getPageNow() {
		return pageNow;
	}

	public void setPageNow(String pageNow) {
		this.pageNow = pageNow;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
}
