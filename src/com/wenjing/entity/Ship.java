package com.wenjing.entity;

import java.io.Serializable;

public class Ship implements Serializable {
    /**
     * ship.id (编号)
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private String id;

    /**
     * ship.name (名称)
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private String name;

    /**
     * ship.companyId (所属公司)
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private Integer companyid;

    /**
     * ship.Rating (星级)
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private Integer rating;

    /**
     * ship.Routename (航线)
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private String routename;

    /**
     * ship.Maiden (生成日期)
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private Integer maiden;

    /**
     * ship.speed (航速)
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private String speed;

    /**
     * ship.Crew (船员)
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private Integer crew;

    /**
     * ship.Capacity (容量)
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private Long capacity;

    /**
     * ship.Length (船长)
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private Long length;

    /**
     * ship.Width (船宽)
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private Long width;

    /**
     * ship.AirconditioningSystem (空调系统)
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private String airconditioningsystem;

    /**
     * ship.Decks (甲板楼层)
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private Integer decks;

    /**
     * ship.Voltage (电压)
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private Integer voltage;

    /**
     * ship.TotalTonnage (总吨位)
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private Long totaltonnage;

    /**
     * ship.TotalArea (总面积)
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private Long totalarea;

    /**
     * ship.TotalDrainage (满载拍数量)
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private Long totaldrainage;

    /**
     * ship.Description (描述)
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private String description;

    /**
     * ship.CabinInfo (客房信息)
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private String cabininfo;

    /**
     * ship.Amenities (设施)
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private String amenities;

    /**
     * ship.logourl (logo)
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private String logourl;

    /**
     * ship.productId (产品ID)
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private String productid;

    /**
     * ship.costNumberIds
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private String costnumberids;

    /**
     * ship.costNumber
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private String costnumber;

    /**
     * ship.RouteId
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private String routeid;

    /**
     * ship.DeckPlan (甲板计划)
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    private String deckplan;

    private ShipCompany shipCompanyCompanyid;

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

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getRoutename() {
        return routename;
    }

    public void setRoutename(String routename) {
        this.routename = routename;
    }

    public Integer getMaiden() {
        return maiden;
    }

    public void setMaiden(Integer maiden) {
        this.maiden = maiden;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public Integer getCrew() {
        return crew;
    }

    public void setCrew(Integer crew) {
        this.crew = crew;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public String getAirconditioningsystem() {
        return airconditioningsystem;
    }

    public void setAirconditioningsystem(String airconditioningsystem) {
        this.airconditioningsystem = airconditioningsystem;
    }

    public Integer getDecks() {
        return decks;
    }

    public void setDecks(Integer decks) {
        this.decks = decks;
    }

    public Integer getVoltage() {
        return voltage;
    }

    public void setVoltage(Integer voltage) {
        this.voltage = voltage;
    }

    public Long getTotaltonnage() {
        return totaltonnage;
    }

    public void setTotaltonnage(Long totaltonnage) {
        this.totaltonnage = totaltonnage;
    }

    public Long getTotalarea() {
        return totalarea;
    }

    public void setTotalarea(Long totalarea) {
        this.totalarea = totalarea;
    }

    public Long getTotaldrainage() {
        return totaldrainage;
    }

    public void setTotaldrainage(Long totaldrainage) {
        this.totaldrainage = totaldrainage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCabininfo() {
        return cabininfo;
    }

    public void setCabininfo(String cabininfo) {
        this.cabininfo = cabininfo;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public String getLogourl() {
        return logourl;
    }

    public void setLogourl(String logourl) {
        this.logourl = logourl;
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

    public String getCostnumber() {
        return costnumber;
    }

    public void setCostnumber(String costnumber) {
        this.costnumber = costnumber;
    }

    public String getRouteid() {
        return routeid;
    }

    public void setRouteid(String routeid) {
        this.routeid = routeid;
    }

    public String getDeckplan() {
        return deckplan;
    }

    public void setDeckplan(String deckplan) {
        this.deckplan = deckplan;
    }

    public void setShipCompanyCompanyid(ShipCompany shipCompanyCompanyid) {
        this.shipCompanyCompanyid=shipCompanyCompanyid;
    }

    public ShipCompany getShipCompanyCompanyid() {
        return shipCompanyCompanyid;
    }
}