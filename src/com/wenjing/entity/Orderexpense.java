package com.wenjing.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Orderexpense implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2147671130912718329L;

	/**
     * orderexpense.id (费用ID)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private String id;

    /**
     * orderexpense.name (费用名称)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private String name;

    /**
     * orderexpense.visitors (人次)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private Integer visitors;

    /**
     * orderexpense.price (费用价格)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private BigDecimal price;

    /**
     * orderexpense.text (说明)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private String text;

    /**
     * orderexpense.orderId (订单ID)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private String orderid;

    /**
     * orderexpense.costNumber (运营中心ID)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private String costnumber;

    private Orders ordersOrderid;

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

    public Integer getVisitors() {
        return visitors;
    }

    public void setVisitors(Integer visitors) {
        this.visitors = visitors;
    }

    public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getCostnumber() {
        return costnumber;
    }

    public void setCostnumber(String costnumber) {
        this.costnumber = costnumber;
    }

    public void setOrdersOrderid(Orders ordersOrderid) {
        this.ordersOrderid=ordersOrderid;
    }

    public Orders getOrdersOrderid() {
        return ordersOrderid;
    }
}