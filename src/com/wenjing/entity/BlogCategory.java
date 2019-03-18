package com.wenjing.entity;

import java.util.Date;

/**
 * Entity - 博客的分类
 * 
 * @author Jared
 * 
 */
public class BlogCategory {

	/** 主键 */
	private String id;

	/** 名称 */
	private String name;

	/** 该种类下所对应博客数量 */
	private Integer blogNumber;

	/**
	 * 是否展示 1.允许展示 0.禁止展示
	 */
	private Integer isShow;
	
	/**
	 * 页面Id
	 */
	private String pageId;
	

	/** 创建时间 */
	private Date createTime;
	
	/**
	 * 是否生成页面
	 * 0.未生成 1.已生成
	 */
	private Integer isCreate;
	
	/**
	 * blog分类对应的页面信息
	 */
	private Page page;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

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

	public Integer getBlogNumber() {
		return blogNumber;
	}

	public void setBlogNumber(Integer blogNumber) {
		this.blogNumber = blogNumber;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	
	public Integer getIsCreate() {
		return isCreate;
	}

	public void setIsCreate(Integer isCreate) {
		this.isCreate = isCreate;
	}
}
