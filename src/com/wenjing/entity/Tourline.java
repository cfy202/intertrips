package com.wenjing.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tourline implements Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -7890362563108143499L;

	/**
     * tourline.id (线路id)
     * @ibatorgenerated 2015-04-22 23:01:25
     */
    private String id;

    /**
     * tourline.days (天数)
     * @ibatorgenerated 2015-04-22 23:01:25
     */
    private Integer days;
    
    private Integer interval;

	/**
     * tourline.notice (注意，须知)
     * @ibatorgenerated 2015-04-22 23:01:25
     */
    private String notice;

    /**
     * tourline.highlights (行程亮点)
     * @ibatorgenerated 2015-04-22 23:01:25
     */
    private String highlights;

    /**
     * tourline.include (包含项)
     * @ibatorgenerated 2015-04-22 23:01:25
     */
    private String include;

    /**
     * tourline.exclude (不包含项)
     * @ibatorgenerated 2015-04-22 23:01:25
     */
    private String exclude;

    /**
     * tourline.mapUrl (线路地图)
     * @ibatorgenerated 2015-04-22 23:01:25
     */
    private String mapurl;

    /**
     * tourline.sort (排序)
     * @ibatorgenerated 2015-04-22 23:01:25
     */
    private Integer sort;

    /**
     * tourline.regionId (线路分类ID)
     * @ibatorgenerated 2015-04-22 23:01:25
     */
    private String regionid;

    /**
     * tourline.productId (产品ID)
     * @ibatorgenerated 2015-04-22 23:01:25
     */
    private String productid;
    
    /**
     * tourline.selfPayList (自费项目集)
     * @ibatorgenerated 2015-04-22 23:01:25
     */
    private String selfpaylist;
    
    /**
     * tourline.tourname (产品名称)
     * @ibatorgenerated 2015-05-06 16:43:25
     */
    private String tourname;
    
    /**
     * 运营中心Id
     */
    private String costnumber;
    
    /**
     * intertrips的平均分
     */
    private BigDecimal avgScore;
    
	/**
     * 销售中心编号集合
     */
    private String costnumberids;
    
    /**
     * tourline.destinationList (目的地名称集合)
     * @ibatorgenerated 2015-04-22 23:01:25
     */
    private String destinationlist;

    /**
     * tourline.attractionList (关联景点集合)
     * @ibatorgenerated 2015-04-22 23:01:25
     */
    private String attractionlist;

    /**
     * 线路所关联的行程
     * 
     */
	private List<Itinerary> itinerarys;
	
	/**
	 * 线路所关联的目的地
	 */
	private List<Destination> destinations;

    private List<Tourdate> tourdatesTourlineid;

	private Set<Tourlineattraction> tourlineattractionsTourlineid = new HashSet<Tourlineattraction>(0);

    private Set<Tourlinedestination> tourlinedestinationsTourlineid = new HashSet<Tourlinedestination>(0);

    private Set<Tourlinehotel> tourlinehotelsTourlineid = new HashSet<Tourlinehotel>(0);

    private Set<Tourlineimage> tourlineimagesTourlineid = new HashSet<Tourlineimage>(0);

    private Set<Tourlineselfpay> tourlineselfpaysTourlineid = new HashSet<Tourlineselfpay>(0);
    
    private List<Producttag> productTagList;
    
    private List<Productvideo> productVideoList;

    private Region region;
    
    private BigDecimal lowmprice;		//线路最低标价
    
    private BigDecimal lowsprice;		//线路最低售价
    
	private BigDecimal discountPrice;		//线路最低售价
    
    private String filepath;		//线路页面路径
    
    private String coverimageurl;		//线路封面图
    
    private String startDestination;		//参团地
    
    private String endDestination;			//离团地
    
    private String minStartDate;		//最小开始日期
    
    private String maxEndDate;		//最大结束日期
    
    private Product productProductid;
    
    private String parentIds;
    
    private Cost cost;
    
    private BigDecimal tipPrice; // 小费,导游服务费

    private BigDecimal steamPrice; //自费,行程外精彩景点/观光/演出
    
    private String tagsname;	//线路相关标签名字
    
    private List<Tag> tagList;		//线路相关标签集合
    
    List<Promotion> promotionList;
    
    private Integer reviewCount;	//线路评价数量
    
    private Integer rate;
    
    private String departure;
    
    private String flightnotice; //航空公司说明
    
   
    
    private List<ServiceItem> serviceItemList;	//服务项目集合
    
    
    
    
   
	public List<ServiceItem> getServiceItemList() {
		return serviceItemList;
	}

	public void setServiceItemList(List<ServiceItem> serviceItemList) {
		this.serviceItemList = serviceItemList;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Integer getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(Integer reviewCount) {
		this.reviewCount = reviewCount;
	}

	public List<Promotion> getPromotionList() {
		return promotionList;
	}

	public void setPromotionList(List<Promotion> promotionList) {
		this.promotionList = promotionList;
	}

	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public List<Tag> getTagList() {
		return tagList;
	}
   
	public List<Productvideo> getProductVideoList() {
		return productVideoList;
	}

	public void setProductVideoList(List<Productvideo> productVideoList) {
		this.productVideoList = productVideoList;
	}

	public void setTagList(List<Tag> tagList) {
		this.tagList = tagList;
	}

	public String getTagsname() {
		return tagsname;
	}

	public void setTagsname(String tagsname) {
		this.tagsname = tagsname;
	}

	//在产品页面异步获取价格时用来传输积分的数量
    public List<Producttag> getProductTagList() {
		return productTagList;
	}

	public void setProductTagList(List<Producttag> productTagList) {
		this.productTagList = productTagList;
	}

	//在产品页面异步获取价格时用来传输积分的数量
    private Integer score;

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getStartDestination() {
		return startDestination;
	}

	public void setStartDestination(String startDestination) {
		this.startDestination = startDestination;
	}

	public String getEndDestination() {
		return endDestination;
	}

	public void setEndDestination(String endDestination) {
		this.endDestination = endDestination;
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	/**
	 * @return the selfpaylist
	 */
	public String getSelfpaylist() {
		return selfpaylist;
	}

	/**
	 * @param selfpaylist the selfpaylist to set
	 */
	public void setSelfpaylist(String selfpaylist) {
		this.selfpaylist = selfpaylist;
	}

	/**
	 * @return the destinationlist
	 */
	public String getDestinationlist() {
		return destinationlist;
	}

	/**
	 * @param destinationlist the destinationlist to set
	 */
	public void setDestinationlist(String destinationlist) {
		this.destinationlist = destinationlist;
	}

	/**
	 * @return the attractionlist
	 */
	public String getAttractionlist() {
		return attractionlist;
	}

	/**
	 * @param attractionlist the attractionlist to set
	 */
	public void setAttractionlist(String attractionlist) {
		this.attractionlist = attractionlist;
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

	public String getMinStartDate() {
		return minStartDate;
	}

	public void setMinStartDate(String minStartDate) {
		this.minStartDate = minStartDate;
	}

	public String getMaxEndDate() {
		return maxEndDate;
	}

	public void setMaxEndDate(String maxEndDate) {
		this.maxEndDate = maxEndDate;
	}

	public String getCoverimageurl() {
		return coverimageurl;
	}

	public void setCoverimageurl(String coverimageurl) {
		this.coverimageurl = coverimageurl;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public BigDecimal getLowmprice() {
		return lowmprice;
	}

	public void setLowmprice(BigDecimal lowmprice) {
		this.lowmprice = lowmprice;
	}

	public BigDecimal getLowsprice() {
		return lowsprice;
	}

	public void setLowsprice(BigDecimal lowsprice) {
		this.lowsprice = lowsprice;
	}

	
	public String getCostnumberids() {
		return costnumberids;
	}

	public void setCostnumberids(String costnumberids) {
		this.costnumberids = costnumberids;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getHighlights() {
        return highlights;
    }

    public void setHighlights(String highlights) {
        this.highlights = highlights;
    }

    public String getInclude() {
        return include;
    }

    public void setInclude(String include) {
        this.include = include;
    }

    public String getExclude() {
        return exclude;
    }

    public void setExclude(String exclude) {
        this.exclude = exclude;
    }

    public String getMapurl() {
        return mapurl;
    }

    public void setMapurl(String mapurl) {
        this.mapurl = mapurl;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRegionid() {
        return regionid;
    }

    public void setRegionid(String regionid) {
        this.regionid = regionid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }
    
	public List<Itinerary> getItinerarys() {
		return itinerarys;
	}

	public void setItinerarys(List<Itinerary> itinerarys) {
		this.itinerarys = itinerarys;
	}

    public void setTourlineattractionsTourlineid(Set<Tourlineattraction> tourlineattractionsTourlineid) {
        this.tourlineattractionsTourlineid=tourlineattractionsTourlineid;
    }

    public Set<Tourlineattraction> getTourlineattractionsTourlineid() {
        return tourlineattractionsTourlineid;
    }

    public void setTourlinedestinationsTourlineid(Set<Tourlinedestination> tourlinedestinationsTourlineid) {
        this.tourlinedestinationsTourlineid=tourlinedestinationsTourlineid;
    }

    public Set<Tourlinedestination> getTourlinedestinationsTourlineid() {
        return tourlinedestinationsTourlineid;
    }

    public void setTourlinehotelsTourlineid(Set<Tourlinehotel> tourlinehotelsTourlineid) {
        this.tourlinehotelsTourlineid=tourlinehotelsTourlineid;
    }

    public Set<Tourlinehotel> getTourlinehotelsTourlineid() {
        return tourlinehotelsTourlineid;
    }

    public void setTourlineimagesTourlineid(Set<Tourlineimage> tourlineimagesTourlineid) {
        this.tourlineimagesTourlineid=tourlineimagesTourlineid;
    }

    public Set<Tourlineimage> getTourlineimagesTourlineid() {
        return tourlineimagesTourlineid;
    }

    public void setTourlineselfpaysTourlineid(Set<Tourlineselfpay> tourlineselfpaysTourlineid) {
        this.tourlineselfpaysTourlineid=tourlineselfpaysTourlineid;
    }

    public Set<Tourlineselfpay> getTourlineselfpaysTourlineid() {
        return tourlineselfpaysTourlineid;
    }

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public String getTourname() {
		return tourname;
	}

	public List<Destination> getDestinations() {
		return destinations;
	}

	public void setDestinations(List<Destination> destinations) {
		this.destinations = destinations;
	}

	public void setTourname(String tourname) {
		this.tourname = tourname;
	}

    public List<Tourdate> getTourdatesTourlineid() {
		return tourdatesTourlineid;
	}

	public void setTourdatesTourlineid(List<Tourdate> tourdatesTourlineid) {
		this.tourdatesTourlineid = tourdatesTourlineid;
	}

	public Product getProductProductid() {
		return productProductid;
	}

	public void setProductProductid(Product productProductid) {
		this.productProductid = productProductid;
	}

	public BigDecimal getTipPrice() {
		return tipPrice;
	}

	public void setTipPrice(BigDecimal tipPrice) {
		this.tipPrice = tipPrice;
	}

	public BigDecimal getSteamPrice() {
		return steamPrice;
	}

	public void setSteamPrice(BigDecimal steamPrice) {
		this.steamPrice = steamPrice;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}
	
    public BigDecimal getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(BigDecimal avgScore) {
		this.avgScore = avgScore;
	}
	
    public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public String getFlightnotice() {
		return flightnotice;
	}

	public void setFlightnotice(String flightnotice) {
		this.flightnotice = flightnotice;
	}
}