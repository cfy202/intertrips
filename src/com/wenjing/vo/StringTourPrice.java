package com.wenjing.vo;

import com.wenjing.entity.Tourprice;

/**
 * 产品价格
 * 以字符串类型表示数字
 * 
 * @author Jared
 *
 * Jul 18, 2015
 */
public class StringTourPrice {
	
	/**
     * tourprice.id
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private String id;

    /**
     * tourprice.markedPrice (标价)
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private String markedprice;

    /**
     * tourprice.sellingPrice (售价)
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private String sellingprice;

    /**
     * tourprice.singleRoomPrice (单房差)
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private String singleroomprice;

    /**
     * tourprice.extraBedPrice (儿童占床价(5-11岁))
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private String extrabedprice;

    /**
     * tourprice.noBedPrice (小孩不占床价(5-11岁))
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private String nobedprice;

    /**
     * tourprice.currencyId (币种)
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private String currencyid;

    /**
     * tourprice.tourDateId (线路日期Id)
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private String tourdateid;

    /**
     * tourprice.costNumber (运营中心ID)
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private String costnumber;
    
    /**
     * tourprice.threesellingPrice (第三人价)
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private String threesellingprice;
    
    /**
     * tourprice.foursellingPrice (第四人价)
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private String foursellingprice;
    
    /**
     * tourprice.babyPrice (婴儿价（0-2岁）)
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private String babyPrice;
    
    /**
     * tourprice.childPrice (小童价（2-5岁）)
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private String childPrice;
    
    /**
     * 单人价
     */
    private String singlePrice;
    
	/**
     * 
     * @param tourPrice
     */
	public StringTourPrice(Tourprice tourPrice) {
		this.id = tourPrice.getId();
		this.markedprice = tourPrice.getMarkedprice().toString();
		this.sellingprice = tourPrice.getSellingprice().toString();
		this.singleroomprice = tourPrice.getSingleroomprice().toString();
		this.extrabedprice = tourPrice.getExtrabedprice().toString();
		this.nobedprice = tourPrice.getNobedprice().toString();
		this.currencyid = tourPrice.getCurrencyid();
		this.tourdateid = tourPrice.getTourdateid();
		this.costnumber = tourPrice.getCostnumber();
		this.threesellingprice = tourPrice.getThreesellingprice().toString();
		this.foursellingprice = tourPrice.getFoursellingprice().toString();
		this.babyPrice = tourPrice.getBabyPrice().toString();
		this.childPrice = tourPrice.getChildPrice().toString();
		this.singlePrice = tourPrice.getSellingprice().add(tourPrice.getSingleroomprice()).toString();
	}
	
	public StringTourPrice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMarkedprice() {
		return markedprice;
	}

	public void setMarkedprice(String markedprice) {
		this.markedprice = markedprice;
	}

	public String getSellingprice() {
		return sellingprice;
	}

	public void setSellingprice(String sellingprice) {
		this.sellingprice = sellingprice;
	}

	public String getSingleroomprice() {
		return singleroomprice;
	}

	public void setSingleroomprice(String singleroomprice) {
		this.singleroomprice = singleroomprice;
	}

	public String getExtrabedprice() {
		return extrabedprice;
	}

	public void setExtrabedprice(String extrabedprice) {
		this.extrabedprice = extrabedprice;
	}

	public String getNobedprice() {
		return nobedprice;
	}

	public void setNobedprice(String nobedprice) {
		this.nobedprice = nobedprice;
	}

	public String getCurrencyid() {
		return currencyid;
	}

	public void setCurrencyid(String currencyid) {
		this.currencyid = currencyid;
	}

	public String getTourdateid() {
		return tourdateid;
	}

	public void setTourdateid(String tourdateid) {
		this.tourdateid = tourdateid;
	}

	public String getCostnumber() {
		return costnumber;
	}

	public void setCostnumber(String costnumber) {
		this.costnumber = costnumber;
	}

	public String getThreesellingprice() {
		return threesellingprice;
	}

	public void setThreesellingprice(String threesellingprice) {
		this.threesellingprice = threesellingprice;
	}

	public String getFoursellingprice() {
		return foursellingprice;
	}

	public void setFoursellingprice(String foursellingprice) {
		this.foursellingprice = foursellingprice;
	}

	public String getBabyPrice() {
		return babyPrice;
	}

	public void setBabyPrice(String babyPrice) {
		this.babyPrice = babyPrice;
	}

	public String getChildPrice() {
		return childPrice;
	}

	public void setChildPrice(String childPrice) {
		this.childPrice = childPrice;
	}
	
    public String getSinglePrice() {
		return singlePrice;
	}

	public void setSinglePrice(String singlePrice) {
		this.singlePrice = singlePrice;
	}
}