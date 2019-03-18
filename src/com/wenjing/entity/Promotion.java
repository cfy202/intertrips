package com.wenjing.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Promotion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1958128629970199455L;

	/**
	 * promotion.id (编号)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:21
	 */
	private String id;

	/**
	 * promotion.title (促销标题)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:21
	 */
	private String title;

	/**
	 * promotion.discount (折扣)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:21
	 */
	private BigDecimal discount;

	/**
	 * promotion.full (消费满多少)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:21
	 */
	private Integer full;

	/**
	 * promotion.reduce (价格减多少)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:21
	 */
	private Integer reduce;

	/**
	 * promotion.description (活动描述)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:21
	 */
	private String description;

	/**
	 * promotion.isShow (是否显示)
	 * 1.显示, 0.不显示
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:21
	 */
	private Byte isshow;

	/**
	 * promotion.imageUrl (促销活动图片路径)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:21
	 */
	private String imageurl;

	/**
	 * promotion.sort (排序)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:21
	 */
	private Integer sort;

	/**
	 * promotion.costNumber (运营中心ID)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:21
	 */
	private String costnumber;

	/**
	 * promotion.currencyId (币种id)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:21
	 */
	private String currencyid;

	/**
	 * promotionproduct.starttime (开始时间)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:26
	 */
	private Integer starttime;

	/**
	 * promotion.endtime (结束时间)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:26
	 */
	private Integer endtime;

	/**
	 * promotion.totalNum (活动限制总订单数（限额）)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:26
	 */
	private Integer totalnum;

	/**
	 * promotion.soldNum (售出订单数)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:26
	 */
	private Integer soldnum;

	/**
	 * promotion.createdate (创建时间)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:23
	 */
	private Date createdate;

	/**
	 * promotion.modifydate (修改时间)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:23
	 */
	private Date modifydate;

	private Set<PromotionProduct> promotionproductsPromotionid = new HashSet<PromotionProduct>(0);

	private Currency currency; // 币种

	private Cost cost; // 运营中心
	
	private String beginDate;
	
	private String endDate;
	
	private Integer type;	//促销活动类型
	
	private String filePath;
	
	private Integer isCreate;
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getIsCreate() {
		return isCreate;
	}

	public void setIsCreate(Integer isCreate) {
		this.isCreate = isCreate;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Integer getFull() {
		return full;
	}

	public void setFull(Integer full) {
		this.full = full;
	}

	public Integer getReduce() {
		return reduce;
	}

	public void setReduce(Integer reduce) {
		this.reduce = reduce;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Byte getIsshow() {
		return isshow;
	}

	public void setIsshow(Byte isshow) {
		this.isshow = isshow;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getCostnumber() {
		return costnumber;
	}

	public void setCostnumber(String costnumber) {
		this.costnumber = costnumber;
	}

	public String getCurrencyid() {
		return currencyid;
	}

	public void setCurrencyid(String currencyid) {
		this.currencyid = currencyid;
	}

	public void setpromotionproductsPromotionid(
			Set<PromotionProduct> promotionproductsPromotionid) {
		this.promotionproductsPromotionid = promotionproductsPromotionid;
	}

	public Set<PromotionProduct> getpromotionproductsPromotionid() {
		return promotionproductsPromotionid;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

	public Integer getStarttime() {
		return starttime;
	}

	public void setStarttime(Integer starttime) {
		this.starttime = starttime;
	}

	public Integer getEndtime() {
		return endtime;
	}

	public void setEndtime(Integer endtime) {
		this.endtime = endtime;
	}

	public Integer getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(Integer totalnum) {
		this.totalnum = totalnum;
	}

	public Integer getSoldnum() {
		return soldnum;
	}

	public void setSoldnum(Integer soldnum) {
		this.soldnum = soldnum;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}