package com.wenjing.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Admissionticket implements Serializable {
    /**
     * admissionticket.id (酒店Id)
     * @ibatorgenerated 2016-03-12 15:35:13
     */
    private String id;

    /**
     * admissionticket.ticketName (名称)
     * @ibatorgenerated 2016-03-12 15:35:13
     */
    private String ticketname;

    /**
     * admissionticket.ticketCN (中文名)
     * @ibatorgenerated 2016-03-12 15:35:13
     */
    private String ticketcn;

    /**
     * admissionticket.ticketInclude (地址)
     * @ibatorgenerated 2016-03-12 15:35:13
     */
    private String ticketinclude;

    /**
     * admissionticket.notInclude (酒店网址)
     * @ibatorgenerated 2016-03-12 15:35:13
     */
    private String notinclude;

    /**
     * admissionticket.city (城市)
     * @ibatorgenerated 2016-03-12 15:35:13
     */
    private String city;

    /**
     * admissionticket.sort (排序)
     * @ibatorgenerated 2016-03-12 15:35:13
     */
    private Integer sort;

    /**
     * admissionticket.attractionId (景点Id)
     * @ibatorgenerated 2016-03-12 15:35:13
     */
    private String attractionid;

    /**
     * admissionticket.costNumber (��Ӫ����ID)
     * @ibatorgenerated 2016-03-12 15:35:13
     */
    private String costnumber;

    /**
     * admissionticket.productId (产品编号)
     * @ibatorgenerated 2016-03-12 15:35:13
     */
    private String productid;

    /**
     * admissionticket.costNumberIds (销售中心集合)
     * @ibatorgenerated 2016-03-12 15:35:13
     */
    private String costnumberids;

    /**
     * admissionticket.regionId (分类)
     * @ibatorgenerated 2016-03-12 15:35:13
     */
    private String regionid;

    
    /**
     * admissionticket.imageUrl (图片地址)
     * @ibatorgenerated 2016-03-12 15:35:13
     */
    private String imageurl;

    /**
     * admissionticket.ticketDescription (说明)
     * @ibatorgenerated 2016-03-12 15:35:13
     */
    private String ticketdescription;

    /**
     * admissionticket.ticketAmenities (备注)
     * @ibatorgenerated 2016-03-12 15:35:13
     */
    private String ticketamenities;
    
    private String productId;
    
	private BigDecimal price;
    
    private Attraction attractionAttractionid;

    private Product productProductid;
    
    private List<Producttag> productTagList;

    public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTicketname() {
        return ticketname;
    }

    public void setTicketname(String ticketname) {
        this.ticketname = ticketname;
    }

    public String getTicketcn() {
        return ticketcn;
    }

    public void setTicketcn(String ticketcn) {
        this.ticketcn = ticketcn;
    }

    public String getTicketinclude() {
        return ticketinclude;
    }

    public void setTicketinclude(String ticketinclude) {
        this.ticketinclude = ticketinclude;
    }

    public String getNotinclude() {
        return notinclude;
    }

    public void setNotinclude(String notinclude) {
        this.notinclude = notinclude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getAttractionid() {
        return attractionid;
    }

    public void setAttractionid(String attractionid) {
        this.attractionid = attractionid;
    }

    public String getCostnumber() {
        return costnumber;
    }

    public void setCostnumber(String costnumber) {
        this.costnumber = costnumber;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getCostnumberids() {
        return costnumberids;
    }

    public void setCostnumberids(String costnumberids) {
        this.costnumberids = costnumberids;
    }

    public String getRegionid() {
        return regionid;
    }

    public void setRegionid(String regionid) {
        this.regionid = regionid;
    }

    public void setAttractionAttractionid(Attraction attractionAttractionid) {
        this.attractionAttractionid=attractionAttractionid;
    }

    public Attraction getAttractionAttractionid() {
        return attractionAttractionid;
    }

    public void setProductProductid(Product productProductid) {
        this.productProductid=productProductid;
    }

    public Product getProductProductid() {
        return productProductid;
    }

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getTicketdescription() {
		return ticketdescription;
	}

	public void setTicketdescription(String ticketdescription) {
		this.ticketdescription = ticketdescription;
	}

	public String getTicketamenities() {
		return ticketamenities;
	}

	public void setTicketamenities(String ticketamenities) {
		this.ticketamenities = ticketamenities;
	}

	public List<Producttag> getProductTagList() {
		return productTagList;
	}

	public void setProductTagList(List<Producttag> productTagList) {
		this.productTagList = productTagList;
	}
    
    public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}	
}