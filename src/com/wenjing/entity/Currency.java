package com.wenjing.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Currency implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2385689618325365706L;

	/**
     * currency.id (币种id)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private String id;

    /**
     * currency.code (货币编码)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private String code;

    /**
     * currency.name (币种名称)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private String name;

    /**
     * currency.sign (货币符号)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private String sign;

    /**
     * currency.sort (排序)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private Integer sort;

    private Set<Promotion> promotionsCurrencyid = new HashSet<Promotion>(0);

    private Set<Tourprice> tourpricesCurrencyid = new HashSet<Tourprice>(0);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public void setPromotionsCurrencyid(Set<Promotion> promotionsCurrencyid) {
        this.promotionsCurrencyid=promotionsCurrencyid;
    }

    public Set<Promotion> getPromotionsCurrencyid() {
        return promotionsCurrencyid;
    }

    public void setTourpricesCurrencyid(Set<Tourprice> tourpricesCurrencyid) {
        this.tourpricesCurrencyid=tourpricesCurrencyid;
    }

    public Set<Tourprice> getTourpricesCurrencyid() {
        return tourpricesCurrencyid;
    }
}