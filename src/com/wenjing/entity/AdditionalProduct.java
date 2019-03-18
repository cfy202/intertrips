package com.wenjing.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AdditionalProduct implements Serializable {
	
	public static final int OPTIONAL_TOUR_TYPE = 10;
	
	public static final int OPTIONAL_TOUR_TYPE_IN_TOURLINE = 11;
    
	//主键
	private String id;
	
    //名称
    private String name;
    
    //封面图片路径
    private String imageurl;

    //描述
    private String discription;
    
    //单价
    private BigDecimal unitcost;
    
    //数量
    private Integer quantity;    
    
	//总价
    private BigDecimal price;
    
    //类型 1.景点 2.美食  10.自费项
    private Integer type;
    
    //行程Id
    private String itinerarayid;
    
    //行程天数
    private String itinerarayDay;

    //目的地Id
    private String destinationid;
    
    //目的地名称
    private String destinationName;
    
    //日期
    private Date date;
    
	//产品Id
    private String productid;
   
	//子订单Id
    private String orderdetailid;
    
    private String dayString;

    public String getDayString() {
		return dayString;
	}

	public void setDayString(String dayString) {
		this.dayString = dayString;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItinerarayid() {
        return itinerarayid;
    }

    public void setItinerarayid(String itinerarayid) {
        this.itinerarayid = itinerarayid;
    }

    public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getDestinationid() {
        return destinationid;
    }

    public void setDestinationid(String destinationid) {
        this.destinationid = destinationid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public BigDecimal getUnitcost() {
        return unitcost;
    }

    public void setUnitcost(BigDecimal unitcost) {
        this.unitcost = unitcost;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getOrderdetailid() {
        return orderdetailid;
    }

    public void setOrderdetailid(String orderdetailid) {
        this.orderdetailid = orderdetailid;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
    
    public String getItinerarayDay() {
		return itinerarayDay;
	}

	public void setItinerarayDay(String itinerarayDay) {
		this.itinerarayDay = itinerarayDay;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
	
    public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}	
}