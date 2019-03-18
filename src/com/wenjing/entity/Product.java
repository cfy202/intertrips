package com.wenjing.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Product implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -819014669749962632L;

	/**
     * product.id (产品ID)
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    private String id;

    /**
     * product.code (产品编号)
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    private String code;

    /**
     * product.name (产品名称)
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    private String name;

    /**
     * product.briefInfo (产品描述)
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    private String briefinfo;

    /**
     * product.updateTime (更新时间)
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    private Date updatetime;

    /**
     * product.editor (更新用户)
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    private String editor;

    /**
     * product.isShow (是否显示)
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    private Byte isshow;

    /**
     * product.isHot (是否热推)
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    private Byte ishot;
     

    /**
     * product.indexShow (是否首页显示)
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    private Byte indexShow;

    /**
     * product.minPrice (最低价格)
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    private BigDecimal minprice;

    /**
     * product.costNumber (运营中心ID)
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    private String costnumber;
     /**
      * 销售中心编号集合
      */
    private String costnumberids;
    /**
     * product.pageId (页面ID)
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    private String pageid;

    /**
     * product.imageUrl (线路图片)
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    private String imageurl;
    
    /**
     * 产品积分
     */
    private Integer score;
    /**
     * 产品系统编号
     */
    private String productNo;
    /**
     * 出发地集合
     */
    
    private String departureIds;
    
    private Integer isSynchronizedToERP;
    
    private Integer excludeLandPrice;
    
	/**
     *热推出发地 
     * 
     */
     private String hotDeparture;
     
	private Tourline tourline;
	
	/**
	 * 运营中心
	 */
	private Cost cost;
     
	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

	public String getHotDeparture() {
		return hotDeparture;
	}

	public void setHotDeparture(String hotDeparture) {
		this.hotDeparture = hotDeparture;
	}

	public String getDepartureIds() {
		return departureIds;
	}

	public void setDepartureIds(String departureIds) {
		this.departureIds = departureIds;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	private String scoreString;
    

	private Integer type;
	
	private String managerUrl;
    
	/**
     * 产品所关联的促销
     */
    private List<Promotion> promotions;
    
	private Set<Orderdetail> orderdetailsProductid = new HashSet<Orderdetail>(0);

    private Set<PromotionProduct> promotionassociationsProductid = new HashSet<PromotionProduct>(0);
    
    private List<PromotionProduct> promotionProductList;

    private Set<Tourline> tourlinesProductid = new HashSet<Tourline>(0);
    
   	private Page pagePageid;
    
   	
   	
    public String getManagerUrl() {
		return managerUrl;
	}

	public void setManagerUrl(String managerUrl) {
		this.managerUrl = managerUrl;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}


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

    public String getBriefinfo() {
        return briefinfo;
    }

    public void setBriefinfo(String briefinfo) {
        this.briefinfo = briefinfo;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getScoreString() {
		return scoreString;
	}

	public void setScoreString(String scoreString) {
		this.scoreString = scoreString;
	}
    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }
   
    public String getCostnumberids() {
		return costnumberids;
	}

	public void setCostnumberids(String costnumberids) {
		this.costnumberids = costnumberids;
	}

	/**
	 * @return the isshow
	 */
	public Byte getIsshow() {
		return isshow;
	}

	/**
	 * @param isshow the isshow to set
	 */
	public void setIsshow(Byte isshow) {
		this.isshow = isshow;
	}

	/**
	 * @return the ishot
	 */
	public Byte getIshot() {
		return ishot;
	}

	/**
	 * @param ishot the ishot to set
	 */
	public void setIshot(Byte ishot) {
		this.ishot = ishot;
	}

	/**
	 * @param indexShow the indexShow to set
	 */
	public void setIndexShow(Byte indexShow) {
		this.indexShow = indexShow;
	}

	public BigDecimal getMinprice() {
        return minprice;
    }

    public void setMinprice(BigDecimal minprice) {
        this.minprice = minprice;
    }

    public String getCostnumber() {
        return costnumber;
    }

    public void setCostnumber(String costnumber) {
        this.costnumber = costnumber;
    }

    public String getPageid() {
        return pageid;
    }

    public void setPageid(String pageid) {
        this.pageid = pageid;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
    
    /**
	 * @return the indexShow
	 */
	

	public void setOrderdetailsProductid(Set<Orderdetail> orderdetailsProductid) {
        this.orderdetailsProductid=orderdetailsProductid;
    }
	
    public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

    /**
	 * @return the indexShow
	 */
	public Byte getIndexShow() {
		return indexShow;
	}

	public Set<Orderdetail> getOrderdetailsProductid() {
        return orderdetailsProductid;
    }

    public void setPromotionassociationsProductid(Set<PromotionProduct> promotionassociationsProductid) {
        this.promotionassociationsProductid=promotionassociationsProductid;
    }

    public Set<PromotionProduct> getPromotionassociationsProductid() {
        return promotionassociationsProductid;
    }

    public void setTourlinesProductid(Set<Tourline> tourlinesProductid) {
        this.tourlinesProductid=tourlinesProductid;
    }

    public Set<Tourline> getTourlinesProductid() {
        return tourlinesProductid;
    }

    public void setPagePageid(Page pagePageid) {
        this.pagePageid=pagePageid;
    }

    public Page getPagePageid() {
        return pagePageid;
    }
    
    public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public List<PromotionProduct> getPromotionProductList() {
		return promotionProductList;
	}

	public void setPromotionProductList(List<PromotionProduct> promotionProductList) {
		this.promotionProductList = promotionProductList;
	}
	
    public Tourline getTourline() {
		return tourline;
	}

	public void setTourline(Tourline tourline) {
		this.tourline = tourline;
	}

    public Integer getIsSynchronizedToERP() {
		return isSynchronizedToERP;
	}

	public void setIsSynchronizedToERP(Integer isSynchronizedToERP) {
		this.isSynchronizedToERP = isSynchronizedToERP;
	}
	
	public Integer getExcludeLandPrice() {
		return excludeLandPrice;
	}

	public void setExcludeLandPrice(Integer excludeLandPrice) {
		this.excludeLandPrice = excludeLandPrice;
	}
}