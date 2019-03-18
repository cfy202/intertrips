package com.wenjing.entity;

import java.io.Serializable;

public class ShipLine implements Serializable {
    /**
     * ship_line.id (编号)
     * @ibatorgenerated 2015-09-06 16:26:00
     */
    private String id;

    /**
     * ship_line.name (名称)
     * @ibatorgenerated 2015-09-06 16:26:00
     */
    private String name;

    /**
     * ship_line.Course (航向)
     * @ibatorgenerated 2015-09-06 16:26:00
     */
    private Integer course;

    /**
     * ship_line.companyId
     * @ibatorgenerated 2015-09-06 16:26:00
     */
    private Integer companyid;

    /**
     * ship_line.deCity (城市一)
     * @ibatorgenerated 2015-09-06 16:26:00
     */
    private String decity;

    /**
     * ship_line.arrCity (城市二)
     * @ibatorgenerated 2015-09-06 16:26:00
     */
    private String arrcity;

    /**
     * ship_line.upDays (上水天数)
     * @ibatorgenerated 2015-09-06 16:26:00
     */
    private Integer updays;

    /**
     * ship_line.shipId
     * @ibatorgenerated 2015-09-06 16:26:00
     */
    private String shipid;

    /**
     * ship_line.downDays (下水天数)
     * @ibatorgenerated 2015-09-06 16:26:00
     */
    private Integer downdays;

   
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

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public String getDecity() {
        return decity;
    }

    public void setDecity(String decity) {
        this.decity = decity;
    }

    public String getArrcity() {
        return arrcity;
    }

    public void setArrcity(String arrcity) {
        this.arrcity = arrcity;
    }

    public Integer getUpdays() {
        return updays;
    }

    public void setUpdays(Integer updays) {
        this.updays = updays;
    }

    public String getShipid() {
        return shipid;
    }

    public void setShipid(String shipid) {
        this.shipid = shipid;
    }

    public Integer getDowndays() {
        return downdays;
    }

    public void setDowndays(Integer downdays) {
        this.downdays = downdays;
    }
}