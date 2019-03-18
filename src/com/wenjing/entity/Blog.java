package com.wenjing.entity;

import java.util.Date;
import java.util.List;

/**
 * Entity - 博客
 * 
 * @author Jared
 * 
 */
public class Blog {

	/** 主键 */
	private String id;

	/** 标题 */
	private String tittle;

	/** 名称 */
	private String name;

	/** 内容 */
	private String content;

	/** 评论数 */
	private Integer numberOfComments;

	/** 封面图片URL */
	private String coverImageUrl;

	/**
	 * 博客状态
	 * 
	 * 0.已发布 1.等待审核 2.草稿
	 */
	private Integer status;

	/**
	 * 是否推荐 1.推荐 0.不推荐
	 */
	private Integer isRecommended;

	/**
	 * 评论状态 1.允许评论 0.禁止评论
	 */
	private Integer commentStatus;

	/**
	 * 共享状态 1.允许共享 0.禁止共享
	 */
	private Integer pingStatus;

	/**
	 * 是否首页显示 1.显示 0.不显示
	 */
	private Integer sticky;

	/** 发布时间 */
	private Date releaseTime;

	/** 添加日期 */
	private Date createTime;

	/** 最后更新时间 */
	private Date lastUpdateTime;

	/** 封面图片ID */
	private String coverImageId;

	/** 发布人ID */
	private String releaseAdminId;

	/** 种类ID */
	private String categoryId;

	/** 博客对应的页面ID */
	private String pageId;
	
	/**
	 * 是否生成模版
	 * 0.未生成 1.已生成
	 */
	private Integer isCreate;

	/** 发布人 */
	private Admin releaseAdmin;

	/**
	 * 对应的博客类别
	 */
	private BlogCategory blogCategory;

	/**
	 * 博客的页面信息
	 */
	private Page page;

	/**
	 * 博客标签的集合
	 */
	private List<BlogTag> blogTagList;

	/**
	 * 发布时间英文显示
	 */
	private String releaseTimeString;

	public String getReleaseTimeString() {
		return releaseTimeString;
	}

	public void setReleaseTimeString(String releaseTimeString) {
		this.releaseTimeString = releaseTimeString;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getReleaseAdminId() {
		return releaseAdminId;
	}

	public void setReleaseAdminId(String releaseAdminId) {
		this.releaseAdminId = releaseAdminId;
	}

	public Integer getNumberOfComments() {
		return numberOfComments;
	}

	public void setNumberOfComments(Integer numberOfComments) {
		this.numberOfComments = numberOfComments;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(Integer commentStatus) {
		this.commentStatus = commentStatus;
	}

	public Integer getPingStatus() {
		return pingStatus;
	}

	public void setPingStatus(Integer pingStatus) {
		this.pingStatus = pingStatus;
	}

	public Integer getSticky() {
		return sticky;
	}

	public void setSticky(Integer sticky) {
		this.sticky = sticky;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getIsRecommended() {
		return isRecommended;
	}

	public void setIsRecommended(Integer isRecommended) {
		this.isRecommended = isRecommended;
	}

	public String getCoverImageUrl() {
		return coverImageUrl;
	}

	public void setCoverImageUrl(String coverImageUrl) {
		this.coverImageUrl = coverImageUrl;
	}

	public String getCoverImageId() {
		return coverImageId;
	}

	public void setCoverImageId(String coverImageId) {
		this.coverImageId = coverImageId;
	}

	public BlogCategory getBlogCategory() {
		return blogCategory;
	}

	public void setBlogCategory(BlogCategory blogCategory) {
		this.blogCategory = blogCategory;
	}

	public Admin getReleaseAdmin() {
		return releaseAdmin;
	}

	public void setReleaseAdmin(Admin releaseAdmin) {
		this.releaseAdmin = releaseAdmin;
	}

	public List<BlogTag> getBlogTagList() {
		return blogTagList;
	}

	public void setBlogTagList(List<BlogTag> blogTagList) {
		this.blogTagList = blogTagList;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	public Integer getIsCreate() {
		return isCreate;
	}

	public void setIsCreate(Integer isCreate) {
		this.isCreate = isCreate;
	}

	public String getStatusInfo() {
		switch (status) {
		case 0:
			return "已发布";
		case 1:
			return "等待审核";
		case 2:
			return "草稿";
		}
		return "";
	}
}