package com.wenjing.vo;

import java.util.Date;

public class BookingQueryVO {
	
	private String name;
	
	private String productNameOrCode;
	
	private String gateWay;
	
	private String agentCode;
	
	private Date departureDateBeforeLimit;
		
	private Date departureDateAfterLimit;
	
	private Date bookingTimeBeforeLimit;
	
    private Date bookingTimeAfterLimit;
    
    private String pageNow;
    
    private String pageSize;
    
	private String costId;
    
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductNameOrCode() {
		return productNameOrCode;
	}

	public void setProductNameOrCode(String productNameOrCode) {
		this.productNameOrCode = productNameOrCode;
	}

	public String getGateWay() {
		return gateWay;
	}

	public void setGateWay(String gateWay) {
		this.gateWay = gateWay;
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

	public String getCostId() {
		return costId;
	}

	public void setCostId(String costId) {
		this.costId = costId;
	}
	
	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
}
