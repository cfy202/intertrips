package com.wenjing.entity;

/**
 * Entity - 博客和标签的关联关系表
 * 
 * @author Jared
 *
 */
public class BlogTagAssociation {
	
	/** 主键 */
	private String id;
	
	/** 博客ID */
	private String blogId;
	
	/** 标签ID */
	private String tagId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
}
