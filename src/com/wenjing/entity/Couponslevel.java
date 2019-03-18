package com.wenjing.entity;

import java.io.Serializable;

public class Couponslevel implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4006319282359585168L;

	private String id;

    private String name;

    private Integer places;

    private String distype;

    private Integer disvalue;

    private String distext;

    private String couponseid;
    
    private Integer remainingQuota;
    
    

    /**
	 * @return the remainingQuota
	 */
	public Integer getRemainingQuota() {
		return remainingQuota;
	}

	/**
	 * @param remainingQuota the remainingQuota to set
	 */
	public void setRemainingQuota(Integer remainingQuota) {
		this.remainingQuota = remainingQuota;
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

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    public String getDistype() {
        return distype;
    }

    public void setDistype(String distype) {
        this.distype = distype;
    }

    public Integer getDisvalue() {
        return disvalue;
    }

    public void setDisvalue(Integer disvalue) {
        this.disvalue = disvalue;
    }

    public String getDistext() {
        return distext;
    }

    public void setDistext(String distext) {
        this.distext = distext;
    }

	/**
	 * @return the couponseid
	 */
	public String getCouponseid() {
		return couponseid;
	}

	/**
	 * @param couponseid the couponseid to set
	 */
	public void setCouponseid(String couponseid) {
		this.couponseid = couponseid;
	}

   
}