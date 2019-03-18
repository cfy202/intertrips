package com.wenjing.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 作者 E-mail: Bowden
 * @version 创建时间：2015-9-6 上午11:12:55 
 * 类说明 ：用户评价
 */
public class Review implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 **/
	private static final long serialVersionUID = -5983536692199075179L;

	/**
	 * id
	 */
	private String id;
	/**
	 * 评论标题
	 */
	private String title;
	/**
	 * 评论内容
	 */
	private String content;
	/**
	 * 评论时间
	 */
	private Date createDate;
	/**
	 * 评论对应的线路id
	 */
	private String productId;
	/**
	 * 评论人的会员id
	 */
	private String memberId;
	/**
	 * 上传的照片路径
	 */
	private String picture;
	/**
	 * 是否显示
	 */
	private int isshow;
	/**
	 * 旅行团评分
	 */
	private int tourGroupScore;
	/**
	 * 文景假期评分
	 */
	private int wenjingScore;
	/**
	 * 评论者ip
	 */
	private String ip;
	
	/**
	 * 销售中心id
	 */
	private String costNumber;
	
	/**
	 * 处理状态（0：未处理，1：已处理）
	 */
	private int status;
	
	/**
	 * 邮箱地址
	 */
	private String email;
	
	/**
	 * 订单号
	 */
	private String orderNo;
	
	private Tourline tourline;
	
	private Member member;
	
	private Cost cost;
	
	private Page page;
	
	private String createDateString;

	public Review() {
		super();
	}

	public Review(String id, int isshow, int status) {
		super();
		this.id = id;
		this.isshow = isshow;
		this.status = status;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getIsshow() {
		return isshow;
	}

	public void setIsshow(int isshow) {
		this.isshow = isshow;
	}

	public int getTourGroupScore() {
		return tourGroupScore;
	}

	public void setTourGroupScore(int tourGroupScore) {
		this.tourGroupScore = tourGroupScore;
	}

	public int getWenjingScore() {
		return wenjingScore;
	}

	public void setWenjingScore(int wenjingScore) {
		this.wenjingScore = wenjingScore;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCostNumber() {
		return costNumber;
	}

	public void setCostNumber(String costNumber) {
		this.costNumber = costNumber;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Tourline getTourline() {
		return tourline;
	}

	public void setTourline(Tourline tourline) {
		this.tourline = tourline;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCreateDateString() {
		return createDateString;
	}

	public void setCreateDateString(String createDateString) {
		this.createDateString = createDateString;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
