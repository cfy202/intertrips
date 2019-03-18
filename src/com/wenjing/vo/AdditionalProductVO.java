package com.wenjing.vo;

import java.math.BigDecimal;

public class AdditionalProductVO {
	
	private String itineryId;
	
	private String itineryDay;
	
	private String dayString;
	
	private String destinationId;
	
	private String destinationName;
	
	private String productId;
	
	private String productName;
	
	private BigDecimal unitPrice;

	private Integer quantity;
	
	private BigDecimal totalFee;
	
	//1.景点 2.美食
	private int type;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getItineryId() {
		return itineryId;
	}

	public void setItineryId(String itineryId) {
		this.itineryId = itineryId;
	}

	public String getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(String destinationId) {
		this.destinationId = destinationId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public String getItineryDay() {
		return itineryDay;
	}

	public void setItineryDay(String itineryDay) {
		this.itineryDay = itineryDay;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getDayString() {
		return dayString;
	}

	public void setDayString(String dayString) {
		this.dayString = dayString;
	}
	
	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}
	
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
}
