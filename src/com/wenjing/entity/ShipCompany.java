package com.wenjing.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ShipCompany implements Serializable {
    /**
     * ship_company.id (编号)
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private String id;

    /**
     * ship_company.name (公司名称)
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private String name;

    /**
     * ship_company.orderid (排序)
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private Integer orderid;

    /**
     * ship_company.logoUrl (logo)
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private String logourl;

    /**
     * ship_company.Description (描述)
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private String description;

    private Set<Ship> shipsCompanyid = new HashSet(0);;

  
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

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getLogourl() {
        return logourl;
    }

    public void setLogourl(String logourl) {
        this.logourl = logourl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setShipsCompanyid(Set shipsCompanyid) {
        this.shipsCompanyid=shipsCompanyid;
    }

    public Set<Ship> getShipsCompanyid() {
        return shipsCompanyid;
    }
}