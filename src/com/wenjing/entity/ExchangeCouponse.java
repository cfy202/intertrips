package com.wenjing.entity;

import java.math.BigDecimal;

public class ExchangeCouponse {
	
	private BigDecimal totalprice;
	private BigDecimal releaseprice;
	private String meassage ;
	
	
	public String getMeassage() {
		return meassage;
	}
	public void setMeassage(String meassage) {
		this.meassage = meassage;
	}
	public BigDecimal getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}
	public BigDecimal getReleaseprice() {
		return releaseprice;
	}
	public void setReleaseprice(BigDecimal releaseprice) {
		this.releaseprice = releaseprice;
	}
	

}
