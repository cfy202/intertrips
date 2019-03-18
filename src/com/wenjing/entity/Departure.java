package com.wenjing.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 作者 E-mail: bowden
 * @version 创建时间：2015-5-12 上午10:59:25
 * 类说明 : 出发地
 */
public class Departure implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2385689618325365706L;

	/**
     * departure.id (出发地id)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private String id;

    /**
     * departure.name (出发地名称)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private String name;

    /**
     * departure.city (出发城市名称)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private String city;

    /**
     * departure.address (出发地详细地址)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private String address;
    
    /**
     * departure.price (出发地额外加收费用)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private BigDecimal price;
    
    /**
     * departure.remark (备注)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private String remark;

    /**
     * departure.sort (排序)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private Integer sort;
    
    /**
     * departure.costnumber (运营中心)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private String costnumber;
    
    private Cost cost;

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getCostnumber() {
		return costnumber;
	}

	public void setCostnumber(String costnumber) {
		this.costnumber = costnumber;
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}
}
