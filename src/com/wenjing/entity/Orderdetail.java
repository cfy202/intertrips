package com.wenjing.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Orderdetail implements Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -1161863666611809939L;

	/**
     * orderdetail.id (编号)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private String id;

    /**
     * orderdetail.orderId (订单id)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private String orderid;

    /**
     * orderdetail.days (行程天数)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private Integer days;

    /**
     * orderdetail.departureDate (出发日期)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private String departuredate;

    /**
     * orderdetail.endDate (结束日期)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private String enddate;

    /**
     * orderdetail.depostiDate (预付日期)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private String depostidate;

    /**
     * orderdetail.despotPrice (预付款金额)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private BigDecimal despotprice;

    /**
     * orderdetail.finalPayDate (最终付款日期)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private String finalpaydate;

    /**
     * orderdetail.finalPrice (尾款金额)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private BigDecimal finalprice;

    /**
     * orderdetail.roomCount (选择房间数量)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private Integer roomcount;

    /**
     * orderdetail.adults (成人人数)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private Integer adults;

    /**
     * orderdetail.children (小孩人数)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private Integer children;

    /**
     * orderdetail.babies (婴儿人数)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private Integer babies;

    /**
     * orderdetail.specialRequest (特殊要求)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private String specialrequest;

    /**
     * orderdetail.price (总价)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private BigDecimal price;
    
    /**
     * 每个房间的住客人情况
     */
    private String roomInfo;
    
    /**
     * tripleRoom的数量
     */
    private int tripleRoomNumber;

	/**
     * doubleRoom的数量
     */
    private int doubleRoomNumber;
    
    /**
     * twinRoom的数量
     */
    private int twinRoomNumber;
    
    /**
     * singleRoom的数量
     */
    private int singleRoomNumber;
    
    /**
     * 关联促销活动
     */
    private String promotionId;
    
    /**
     * 关联优惠券
     */
    private String couponseId;

	/**
     * orderdetail.productId (产品ID)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private String productid;

    /**
     * orderdetail.costNumber (运营中心ID)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private String costnumber;
    
    /**
     * 出发地ID
     */
    private String departureId;
    
    /**
     * 机场接送机ID
     */
    private String airportPickUpId;
    
    /**
     * 机票的ID
     */
    private String airTicketPriceId;
    
    /**
     * 自费项
     */
    private String selfPayId;
    
    /**
     * 选用的价格ID
     */
    private String tourdateId;

	/**
     * 出发地
     */
    private Departure departure;
    
    /**
     * 货币符号
     */
    private String currencySign;

    private Orders ordersOrderid;

    private Product product;
    
    private Promotion promotion;

	/**
     * 订单的订金
     */
    private BigDecimal frontMoney;

	/**
     * 子订单对应的线路信息
     */
    private Tourline tourline;
    
    /**
     * 
     */
    private Tourdate tourdate;	

	/**
     * 子订单对应的客人
     */
    private List<TourPassenger> tourPassengerList;
    
    private BigDecimal prePrice;	//订金
    
    private BigDecimal finalPrice1;	//部分尾款
    
    private BigDecimal finalPrice2;	//剩余尾款
    
    private BigDecimal totalFinalPrice;	//尾款
    
    private BigDecimal fullPrice;	//全款
    
	private Integer payStatus;	//付款状态
	

	public String getTourdateId() {
		return tourdateId;
	}

	public void setTourdateId(String tourdateId) {
		this.tourdateId = tourdateId;
	}

	private List<AdditionalProduct> additionalProductList;

	public BigDecimal getFinalPrice1() {
		return finalPrice1;
	}

	public void setFinalPrice1(BigDecimal finalPrice1) {
		this.finalPrice1 = finalPrice1;
	}

	public BigDecimal getFinalPrice2() {
		return finalPrice2;
	}

	public void setFinalPrice2(BigDecimal finalPrice2) {
		this.finalPrice2 = finalPrice2;
	}

	public BigDecimal getTotalFinalPrice() {
		return totalFinalPrice;
	}

	public void setTotalFinalPrice(BigDecimal totalFinalPrice) {
		this.totalFinalPrice = totalFinalPrice;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public BigDecimal getPrePrice() {
		return prePrice;
	}

	public void setPrePrice(BigDecimal prePrice) {
		this.prePrice = prePrice;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getDeparturedate() {
        return departuredate;
    }

    public void setDeparturedate(String departuredate) {
        this.departuredate = departuredate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getDepostidate() {
        return depostidate;
    }

    public void setDepostidate(String depostidate) {
        this.depostidate = depostidate;
    }

    public BigDecimal getDespotprice() {
        return despotprice;
    }

    public void setDespotprice(BigDecimal despotprice) {
        this.despotprice = despotprice;
    }

    public String getFinalpaydate() {
        return finalpaydate;
    }

    public void setFinalpaydate(String finalpaydate) {
        this.finalpaydate = finalpaydate;
    }

	public List<AdditionalProduct> getAdditionalProductList() {
		return additionalProductList;
	}

	public void setAdditionalProductList(
			List<AdditionalProduct> additionalProductList) {
		this.additionalProductList = additionalProductList;
	}   
    public BigDecimal getFinalprice() {
        return finalprice;
    }

    public void setFinalprice(BigDecimal finalprice) {
        this.finalprice = finalprice;
    }

    public Integer getRoomcount() {
        return roomcount;
    }

    public void setRoomcount(Integer roomcount) {
        this.roomcount = roomcount;
    }

    public Integer getAdults() {
        return adults;
    }

    public void setAdults(Integer adults) {
        this.adults = adults;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    public Integer getBabies() {
        return babies;
    }

    public void setBabies(Integer babies) {
        this.babies = babies;
    }

    public String getSpecialrequest() {
        return specialrequest;
    }

    public void setSpecialrequest(String specialrequest) {
        this.specialrequest = specialrequest;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getCostnumber() {
        return costnumber;
    }

    public void setCostnumber(String costnumber) {
        this.costnumber = costnumber;
    }

    public void setOrdersOrderid(Orders ordersOrderid) {
        this.ordersOrderid=ordersOrderid;
    }

    public Orders getOrdersOrderid() {
        return ordersOrderid;
    }

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
    public Tourline getTourline() {
		return tourline;
	}

	public void setTourline(Tourline tourline) {
		this.tourline = tourline;
	}
	
    public String getDepartureId() {
		return departureId;
	}

	public void setDepartureId(String departureId) {
		this.departureId = departureId;
	}

	public Departure getDeparture() {
		return departure;
	}

	public void setDeparture(Departure departure) {
		this.departure = departure;
	}

	public String getCurrencySign() {
		return currencySign;
	}

	public void setCurrencySign(String currencySign) {
		this.currencySign = currencySign;
	}
	
    public String getRoomInfo() {
		return roomInfo;
	}

	public void setRoomInfo(String roomInfo) {
		this.roomInfo = roomInfo;
	}
	
	public String getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}

	public String getCouponseId() {
		return couponseId;
	}

	public void setCouponseId(String couponseId) {
		this.couponseId = couponseId;
	}
	
	public List<TourPassenger> getTourPassengerList() {
		return tourPassengerList;
	}

	public void setTourPassengerList(List<TourPassenger> tourPassengerList) {
		this.tourPassengerList = tourPassengerList;
	}
	
	public String getAirportPickUpId() {
		return airportPickUpId;
	}

	public void setAirportPickUpId(String airportPickUpId) {
		this.airportPickUpId = airportPickUpId;
	}
	
	public String getAirTicketPriceId() {
		return airTicketPriceId;
	}

	public void setAirTicketPriceId(String airTicketPriceId) {
		this.airTicketPriceId = airTicketPriceId;
	}
	
    public BigDecimal getFrontMoney() {
		return frontMoney;
	}

	public void setFrontMoney(BigDecimal frontMoney) {
		this.frontMoney = frontMoney;
	}

	public BigDecimal getFullPrice() {
		return fullPrice;
	}

	public void setFullPrice(BigDecimal fullPrice) {
		this.fullPrice = fullPrice;
	}
	
    public int getTripleRoomNumber() {
		return tripleRoomNumber;
	}

	public void setTripleRoomNumber(int tripleRoomNumber) {
		this.tripleRoomNumber = tripleRoomNumber;
	}

	public int getDoubleRoomNumber() {
		return doubleRoomNumber;
	}

	public void setDoubleRoomNumber(int doubleRoomNumber) {
		this.doubleRoomNumber = doubleRoomNumber;
	}

	public int getTwinRoomNumber() {
		return twinRoomNumber;
	}

	public void setTwinRoomNumber(int twinRoomNumber) {
		this.twinRoomNumber = twinRoomNumber;
	}

	public int getSingleRoomNumber() {
		return singleRoomNumber;
	}

	public void setSingleRoomNumber(int singleRoomNumber) {
		this.singleRoomNumber = singleRoomNumber;
	}
	
	public String getSelfPayId() {
		return selfPayId;
	}

	public void setSelfPayId(String selfPayId) {
		this.selfPayId = selfPayId;
	}
	
    public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
	
	public Tourdate getTourdate() {
		return tourdate;
	}

	public void setTourdate(Tourdate tourdate) {
		this.tourdate = tourdate;
	}
}