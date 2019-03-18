package com.wenjing.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Selfpay implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6654892489300947245L;

	/**
     * selfpay.id (自费项目id)
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    private String id;

    /**
     * selfpay.name (项目名字)
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    private String name;

    /**
     * selfpay.price (项目价格)
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    private BigDecimal price;

    /**
     * selfpay.city (所在城市)
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    private String city;

    /**
     * selfpay.sort (排序)
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    private Integer sort;

    /**
     * selfpay.remark (说明)
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    private String remark;
    
    /**
     * selfpay.costNumber (运营中心id)
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    private String costnumber;
    /**
     * 自选项类型
     * 
     * 0 目的地关联服务项
     * 1 导游服务费
     * 2 行程外观光
     * 3 接送机
     * 4 其他
     */
    private Integer type;
    
    /**
     * 图片路径
     */
    private String imgUrl;
    
    
    private Cost cost;
    
    /**
     * 关联目的地名称
     */
    private String destinationName;

    private Set<Tourlineselfpay> tourlineselfpaysSelfpayid = new HashSet<Tourlineselfpay>(0);

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setTourlineselfpaysSelfpayid(Set<Tourlineselfpay> tourlineselfpaysSelfpayid) {
        this.tourlineselfpaysSelfpayid=tourlineselfpaysSelfpayid;
    }

    public Set<Tourlineselfpay> getTourlineselfpaysSelfpayid() {
        return tourlineselfpaysSelfpayid;
    }

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

	public String getCostnumber() {
		return costnumber;
	}

	public void setCostnumber(String costnumber) {
		this.costnumber = costnumber;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
	
}