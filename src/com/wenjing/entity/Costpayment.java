package com.wenjing.entity;

import java.io.Serializable;

public class Costpayment implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6852046414619764765L;

	/**
     * costpayment.id (支付方式ID)
     * @ibatorgenerated 2015-04-22 23:01:31
     */
    private String id;

    /**
     * costpayment.name (支付方式名称)
     * @ibatorgenerated 2015-04-22 23:01:31
     */
    private String name;

    /**
     * costpayment.paymentAccount (收款账户)
     * @ibatorgenerated 2015-04-22 23:01:31
     */
    private String paymentaccount;

    /**
     * costpayment.url (支付方式图片)
     * @ibatorgenerated 2015-04-22 23:01:31
     */
    private String url;

    /**
     * costpayment.costNumber (运营中心ID)
     * @ibatorgenerated 2015-04-22 23:01:31
     */
    private String costnumber;
    
    private String password;
    
    private String partner;
    
    private String key;

    private Cost costCostnumber;

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaymentaccount() {
        return paymentaccount;
    }

    public void setPaymentaccount(String paymentaccount) {
        this.paymentaccount = paymentaccount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCostnumber() {
        return costnumber;
    }

    public void setCostnumber(String costnumber) {
        this.costnumber = costnumber;
    }

    public void setCostCostnumber(Cost costCostnumber) {
        this.costCostnumber=costCostnumber;
    }

    public Cost getCostCostnumber() {
        return costCostnumber;
    }
}