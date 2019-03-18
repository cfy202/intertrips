package com.wenjing.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hotel implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2298073014144639306L;

	/**
     * hotel.id (酒店ID)
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    private String id;

    /**
     * hotel.hotelName (酒店名称)
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    private String hotelname;

    /**
     * hotel.hotelWeb (酒店首页)
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    private String hotelweb;

    /**
     * hotel.hotelCN (酒店中文名称)
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    private String hotelcn;

    /**
     * hotel.hotelStar (酒店星级)
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    private BigDecimal hotelstar;

    /**
     * hotel.hotelAddress (酒店地址)
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    private String hoteladdress;

    /**
     * hotel.hotelWebsite (酒店酒店主页)
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    private String hotelwebsite;

    /**
     * hotel.hotelTelephone (酒店电话)
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    private String hoteltelephone;

    /**
     * hotel.city (城市名称)
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    private String city;

    /**
     * hotel.sort (排序)
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    private Integer sort;

    /**
     * hotel.destinationId (城市ID)
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    private String destinationid;

    /**
     * hotel.costNumber (运营中心ID)
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    private String costnumber;

    private Set<Hotelimage> hotelimagesHotelid = new HashSet<Hotelimage>(0);

    private Set<Tourlinehotel> tourlinehotelsHotelid = new HashSet<Tourlinehotel>(0);

    private Destination destinationDestinationid;
    
    /**
     * hotel.imageUrl (酒店图片)
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    private String imageurl;

    /**
     * hotel.hotelDescription (酒店描述)
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    private String hoteldescription;

    /**
     * hotel.hotelLocation (酒店位置)
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    private String hotellocation;

    /**
     * hotel.hotelAmenities (酒店娱乐项目)
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    private String hotelamenities;

    /**
     * hotel.hotelFacilities (酒店设施)
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    private String hotelfacilities;
    /**
     * hotel.productId (酒店关联的产品Id)
     */
    private String productid;
    /**
     * hotel.costNumberIds(关联销售中心的id)
     */
    private String costNumberids; 
    /**
     * hotel.regionId(分类id)
     */
    private String regionid;
    
    private Product productProductid;
    
    private List<Producttag> productTagList;
    
    
    
    
    public String getRegionid() {
		return regionid;
	}

	public void setRegionid(String regionid) {
		this.regionid = regionid;
	}

	public List<Producttag> getProductTagList() {
		return productTagList;
	}

	public void setProductTagList(List<Producttag> productTagList) {
		this.productTagList = productTagList;
	}

	public String getCostNumberids() {
		return costNumberids;
	}

	public void setCostNumberids(String costNumberids) {
		this.costNumberids = costNumberids;
	}

	public Product getProductProductid() {
		return productProductid;
	}

	public void setProductProductid(Product productProductid) {
		this.productProductid = productProductid;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getHoteldescription() {
        return hoteldescription;
    }

    public void setHoteldescription(String hoteldescription) {
        this.hoteldescription = hoteldescription;
    }

    public String getHotellocation() {
        return hotellocation;
    }

    public void setHotellocation(String hotellocation) {
        this.hotellocation = hotellocation;
    }

    public String getHotelamenities() {
        return hotelamenities;
    }

    public void setHotelamenities(String hotelamenities) {
        this.hotelamenities = hotelamenities;
    }

    public String getHotelfacilities() {
        return hotelfacilities;
    }

    public void setHotelfacilities(String hotelfacilities) {
        this.hotelfacilities = hotelfacilities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHotelname() {
        return hotelname;
    }

    public void setHotelname(String hotelname) {
        this.hotelname = hotelname;
    }

    public String getHotelweb() {
        return hotelweb;
    }

    public void setHotelweb(String hotelweb) {
        this.hotelweb = hotelweb;
    }

    public String getHotelcn() {
        return hotelcn;
    }

    public void setHotelcn(String hotelcn) {
        this.hotelcn = hotelcn;
    }

    public BigDecimal getHotelstar() {
		return hotelstar;
	}

	public void setHotelstar(BigDecimal hotelstar) {
		this.hotelstar = hotelstar;
	}

	public String getHoteladdress() {
        return hoteladdress;
    }

    public void setHoteladdress(String hoteladdress) {
        this.hoteladdress = hoteladdress;
    }

    public String getHotelwebsite() {
        return hotelwebsite;
    }

    public void setHotelwebsite(String hotelwebsite) {
        this.hotelwebsite = hotelwebsite;
    }

    public String getHoteltelephone() {
        return hoteltelephone;
    }

    public void setHoteltelephone(String hoteltelephone) {
        this.hoteltelephone = hoteltelephone;
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

    public void setHotelimagesHotelid(Set<Hotelimage> hotelimagesHotelid) {
        this.hotelimagesHotelid=hotelimagesHotelid;
    }

    public Set<Hotelimage> getHotelimagesHotelid() {
        return hotelimagesHotelid;
    }

    public void setTourlinehotelsHotelid(Set<Tourlinehotel> tourlinehotelsHotelid) {
        this.tourlinehotelsHotelid=tourlinehotelsHotelid;
    }

    public Set<Tourlinehotel> getTourlinehotelsHotelid() {
        return tourlinehotelsHotelid;
    }

    public void setDestinationDestinationid(Destination destinationDestinationid) {
        this.destinationDestinationid=destinationDestinationid;
    }

    public Destination getDestinationDestinationid() {
        return destinationDestinationid;
    }
}