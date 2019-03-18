package com.wenjing.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Price implements Serializable {
    /**
     * price.id (编号)
     * @ibatorgenerated 2015-09-23 09:30:49
     */
    private String id;

    /**
     * price.title (标题)
     * @ibatorgenerated 2015-09-23 09:30:49
     */
    private String title;

    /**
     * price.price (价格)
     * @ibatorgenerated 2015-09-23 09:30:49
     */
    private BigDecimal price;

    /**
     * price.information (说明)
     * @ibatorgenerated 2015-09-23 09:30:49
     */
    private String information;

    /**
     * price.costNumber (销售中心编号)
     * @ibatorgenerated 2015-09-23 09:30:49
     */
    private String costnumber;

    /**
     * price.productId (产品编号)
     * @ibatorgenerated 2015-09-23 09:30:49
     */
    private String productid;
    
    /**
     * 超时费用(/小时)
     */
    private BigDecimal overTimePrice;
    
    /**
     * serviceType(服务类型)
     * 1.导服
     * 2.自选项
     * 3.行程外观光
     * 4.接送机
     * @ibatorgenerated 2015-09-23 09:30:49
     */
    private Integer ServiceType;
    
	private Cost cost;
    
    public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
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

	public Integer getServiceType() {
		return ServiceType;
	}

	public void setServiceType(Integer serviceType) {
		ServiceType = serviceType;
	}
	
    public BigDecimal getOverTimePrice() {
		return overTimePrice;
	}

	public void setOverTimePrice(BigDecimal overTimePrice) {
		this.overTimePrice = overTimePrice;
	}
}