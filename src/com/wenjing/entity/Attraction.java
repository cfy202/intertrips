package com.wenjing.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Attraction implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1230374248346225083L;

	/**
     * attraction.id (景点ID)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private String id;

    /**
     * attraction.name (景点名称)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private String name;

    /**
     * attraction.nameCN (中文名称)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private String namecn;

    /**
     * attraction.namePY (拼音)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private String namepy;

    /**
     * attraction.isHot (是否热点)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private Integer ishot;

    /**
     * attraction.sort (排序)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private Integer sort;

    /**
     * attraction.attractionTypeId (景点类型id)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private String attractiontypeid;

    /**
     * attraction.destinationId (目的地ID)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private String destinationid;

    /**
     * attraction.costNumber (运营中心ID)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private String costnumber;

    /**
     * attraction.imageUrl (景点logo)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private String imageurl;

    private Set<Attractionimage> attractionimagesAttractionid = new HashSet<Attractionimage>(0);

    private Set<Tourlineattraction> tourlineattractionsAttractionid = new HashSet<Tourlineattraction>(0);

    private Attractiontype attractiontypeAttractiontypeid;

    private Destination destinationDestinationid;
    
    private int level;

    private BigDecimal ticketPrice; 

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

    public String getNamecn() {
        return namecn;
    }

    public void setNamecn(String namecn) {
        this.namecn = namecn;
    }

    public String getNamepy() {
        return namepy;
    }

    public void setNamepy(String namepy) {
        this.namepy = namepy;
    }

    public Integer getIshot() {
        return ishot;
    }

    public void setIshot(Integer ishot) {
        this.ishot = ishot;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getAttractiontypeid() {
        return attractiontypeid;
    }

    public void setAttractiontypeid(String attractiontypeid) {
        this.attractiontypeid = attractiontypeid;
    }

    public String getDestinationid() {
        return destinationid;
    }

    public void setDestinationid(String destinationid) {
        this.destinationid = destinationid;
    }

    public String getCostnumber() {
        return costnumber;
    }

    public void setCostnumber(String costnumber) {
        this.costnumber = costnumber;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public void setAttractionimagesAttractionid(Set<Attractionimage> attractionimagesAttractionid) {
        this.attractionimagesAttractionid=attractionimagesAttractionid;
    }

    public Set<Attractionimage> getAttractionimagesAttractionid() {
        return attractionimagesAttractionid;
    }

    public void setTourlineattractionsAttractionid(Set<Tourlineattraction> tourlineattractionsAttractionid) {
        this.tourlineattractionsAttractionid=tourlineattractionsAttractionid;
    }

    public Set<Tourlineattraction> getTourlineattractionsAttractionid() {
        return tourlineattractionsAttractionid;
    }

    public void setAttractiontypeAttractiontypeid(Attractiontype attractiontypeAttractiontypeid) {
        this.attractiontypeAttractiontypeid=attractiontypeAttractiontypeid;
    }

    public Attractiontype getAttractiontypeAttractiontypeid() {
        return attractiontypeAttractiontypeid;
    }

    public void setDestinationDestinationid(Destination destinationDestinationid) {
        this.destinationDestinationid=destinationDestinationid;
    }

    public Destination getDestinationDestinationid() {
        return destinationDestinationid;
    }
    
    public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public BigDecimal getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(BigDecimal ticketPrice) {
		this.ticketPrice = ticketPrice;
	}    
}