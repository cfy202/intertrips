package com.wenjing.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2015-6-24 下午1:42:26
 * 类说明
 */
public class Selfpaycurrency implements Serializable{

	private static final long serialVersionUID = 8960585658492606817L;
	
	private String id;
	
	private String currencyid; // 币种id
	
	private BigDecimal adultprice; // 价格
	
	private BigDecimal childprice; //儿童价
	
	private String selfpayid; //自费项目id
	
	private Currency currency;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCurrencyid() {
		return currencyid;
	}

	public void setCurrencyid(String currencyid) {
		this.currencyid = currencyid;
	}

	public BigDecimal getAdultprice() {
		return adultprice;
	}

	public void setAdultprice(BigDecimal adultprice) {
		this.adultprice = adultprice;
	}

	public BigDecimal getChildprice() {
		return childprice;
	}

	public void setChildprice(BigDecimal childprice) {
		this.childprice = childprice;
	}

	public String getSelfpayid() {
		return selfpayid;
	}

	public void setSelfpayid(String selfpayid) {
		this.selfpayid = selfpayid;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
}
