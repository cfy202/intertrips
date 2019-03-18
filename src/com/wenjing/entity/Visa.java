package com.wenjing.entity;

import java.io.Serializable;
import java.util.List;

public class Visa implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3383644765010535690L;

	private String id;

    private String visatype;

    private String handlelength;

    private String numberentries;

    private String stayingdays;

    private String visavalid;

    private String acceptancerange;

    private String bookingPolicy;
    
    private String bookingProcess;

    private String productid;
    
    private String regionid;
    
    private Integer sort;
    
    private String costnumberids;
    
    private List<Producttag> productTagList;
    
    
    
    public String getCostnumberids() {
		return costnumberids;
	}

	public void setCostnumberids(String costnumberids) {
		this.costnumberids = costnumberids;
	}

	public List<Producttag> getProductTagList() {
		return productTagList;
	}

	public void setProductTagList(List<Producttag> productTagList) {
		this.productTagList = productTagList;
	}

	public String getRegionid() {
		return regionid;
	}

	public void setRegionid(String regionid) {
		this.regionid = regionid;
	}

	/**
     * 运营中心Id
     */
    private String costnumber;
    
    /**
	 * @return the productProductid
	 */
	public Product getProductProductid() {
		return productProductid;
	}

	/**
	 * @param productProductid the productProductid to set
	 */
	public void setProductProductid(Product productProductid) {
		this.productProductid = productProductid;
	}

	private Product productProductid;
    
    

    /**
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVisatype() {
        return visatype;
    }

    public void setVisatype(String visatype) {
        this.visatype = visatype;
    }

    public String getHandlelength() {
        return handlelength;
    }

    public void setHandlelength(String handlelength) {
        this.handlelength = handlelength;
    }

    public String getNumberentries() {
        return numberentries;
    }

    public void setNumberentries(String numberentries) {
        this.numberentries = numberentries;
    }

    public String getStayingdays() {
        return stayingdays;
    }

    public void setStayingdays(String stayingdays) {
        this.stayingdays = stayingdays;
    }

    public String getVisavalid() {
        return visavalid;
    }

    public void setVisavalid(String visavalid) {
        this.visavalid = visavalid;
    }

    public String getAcceptancerange() {
        return acceptancerange;
    }

    public void setAcceptancerange(String acceptancerange) {
        this.acceptancerange = acceptancerange;
    }

   
    public String getBookingPolicy() {
		return bookingPolicy;
	}

	public void setBookingPolicy(String bookingPolicy) {
		this.bookingPolicy = bookingPolicy;
	}

	public String getBookingProcess() {
		return bookingProcess;
	}

	public void setBookingProcess(String bookingProcess) {
		this.bookingProcess = bookingProcess;
	}

	public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

	/**
	 * @return the costnumber
	 */
	public String getCostnumber() {
		return costnumber;
	}

	/**
	 * @param costnumber the costnumber to set
	 */
	public void setCostnumber(String costnumber) {
		this.costnumber = costnumber;
	}
    
    
}