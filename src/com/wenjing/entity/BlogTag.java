package com.wenjing.entity;

/**
 * Entity - 博客标签
 * 
 * @author Jared
 *
 */
public class BlogTag {
	
	/** 主键 */
	private String id;
	
	/** 名称 */
	private String name;
	
	/** 标签所对应的页面ID */
	private String pageId;
	
	/**
	 * 是否热门
	 * 
	 * 1.热门  0.不热门
	 */
	private Integer isHot;
	
	/**
	 * 是否生成模版
	 * 0.未生成  1.已生成
	 */
	private Integer isCreate;
	
	private Page page;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
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

	public Integer getIsHot() {
		return isHot;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
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
