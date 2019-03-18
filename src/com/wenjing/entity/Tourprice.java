package com.wenjing.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Tourprice implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4230691256474429639L;

	/**
     * tourprice.id
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private String id;

    /**
     * tourprice.markedPrice (标价)
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private BigDecimal markedprice;

    /**
     * tourprice.sellingPrice (售价)
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private BigDecimal sellingprice;

    /**
     * tourprice.singleRoomPrice (单房差)
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private BigDecimal singleroomprice;

    /**
     * tourprice.extraBedPrice (儿童占床价(5-11岁))
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private BigDecimal extrabedprice;

    /**
     * tourprice.noBedPrice (小孩不占床价(5-11岁))
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private BigDecimal nobedprice;

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
    private BigDecimal threesellingprice;
    
    /**
     * tourprice.foursellingPrice (第四人价)
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private BigDecimal foursellingprice;
    
    /**
     * tourprice.babyPrice (婴儿价（0-2岁）)
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private BigDecimal babyPrice;
    
    /**
     * tourprice.childPrice (小童价（2-5岁）)
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private BigDecimal childPrice;

    private Tourdate tourdateTourdateid;

    private Currency currencyCurrencyid;
    
    public Tourprice() {
		super();
	}

	public Tourprice(String id, BigDecimal markedprice,
			BigDecimal sellingprice, BigDecimal singleroomprice,
			BigDecimal extrabedprice, BigDecimal nobedprice, String currencyid,
			String tourdateid, String costnumber, BigDecimal threesellingprice,
			BigDecimal foursellingprice, BigDecimal babyPrice,
			BigDecimal childPrice) {
		super();
		this.id = id;
		this.markedprice = markedprice;
		this.sellingprice = sellingprice;
		this.singleroomprice = singleroomprice;
		this.extrabedprice = extrabedprice;
		this.nobedprice = nobedprice;
		this.currencyid = currencyid;
		this.tourdateid = tourdateid;
		this.costnumber = costnumber;
		this.threesellingprice = threesellingprice;
		this.foursellingprice = foursellingprice;
		this.babyPrice = babyPrice;
		this.childPrice = childPrice;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getMarkedprice() {
        return markedprice;
    }

    public void setMarkedprice(BigDecimal markedprice) {
        this.markedprice = markedprice;
    }

    public BigDecimal getSellingprice() {
        return sellingprice;
    }

    public void setSellingprice(BigDecimal sellingprice) {
        this.sellingprice = sellingprice;
    }

    public BigDecimal getSingleroomprice() {
        return singleroomprice;
    }

    public void setSingleroomprice(BigDecimal singleroomprice) {
        this.singleroomprice = singleroomprice;
    }

    public BigDecimal getExtrabedprice() {
        return extrabedprice;
    }

    public void setExtrabedprice(BigDecimal extrabedprice) {
        this.extrabedprice = extrabedprice;
    }

    public BigDecimal getNobedprice() {
        return nobedprice;
    }

    public void setNobedprice(BigDecimal nobedprice) {
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

    public void setTourdateTourdateid(Tourdate tourdateTourdateid) {
        this.tourdateTourdateid=tourdateTourdateid;
    }

    public Tourdate getTourdateTourdateid() {
        return tourdateTourdateid;
    }

    public void setCurrencyCurrencyid(Currency currencyCurrencyid) {
        this.currencyCurrencyid=currencyCurrencyid;
    }

    public Currency getCurrencyCurrencyid() {
        return currencyCurrencyid;
    }

	public BigDecimal getThreesellingprice() {
		return threesellingprice;
	}

	public void setThreesellingprice(BigDecimal threesellingprice) {
		this.threesellingprice = threesellingprice;
	}

	public BigDecimal getFoursellingprice() {
		return foursellingprice;
	}

	public void setFoursellingprice(BigDecimal foursellingprice) {
		this.foursellingprice = foursellingprice;
	}

	public BigDecimal getBabyPrice() {
		return babyPrice;
	}

	public void setBabyPrice(BigDecimal babyPrice) {
		this.babyPrice = babyPrice;
	}

	public BigDecimal getChildPrice() {
		return childPrice;
	}

	public void setChildPrice(BigDecimal childPrice) {
		this.childPrice = childPrice;
	}
}