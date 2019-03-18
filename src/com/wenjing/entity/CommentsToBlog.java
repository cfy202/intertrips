package com.wenjing.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Entity - 博客下的评论
 * 
 * @author Jared
 *
 */
public class CommentsToBlog implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String id;
	
	/** 内容 */
	private String content;
	
	/** 父级评论 */
	private String parentId;
	
	/** 所属的博客ID */
	private String blogId;
	
	/** 评论时间 */
	private Date createTime;
	
	/** 评论人Id */
	private String commentMemberId;
	
	/** 是否显示  */
	private Integer isShow;
	
	/**
	 * 审核状态
	 * 1.审核通过  0.未审核
	 */
	private Integer status;
	
	/**
	 * 目录层次的等级
	 */
	private Integer level;
	
	/**
	 * 评论时间的英文
	 */
	private String englishCreateTime;
	
	/**
	 * 父级评论
	 */
	private String parentAdminName;

	private CommentsToBlog parent;
	
	private Blog blog;
	
	private Member member;
	
	private List<CommentsToBlog> commentsToBlogList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCommentMemberId() {
		return commentMemberId;
	}

	public void setCommentMemberId(String commentMemberId) {
		this.commentMemberId = commentMemberId;
	}
	
	public CommentsToBlog getParent() {
		return parent;
	}

	public void setParent(CommentsToBlog parent) {
		this.parent = parent;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	
	public String getEnglishCreateTime() {
		return englishCreateTime;
	}

	public void setEnglishCreateTime(String englishCreateTime) {
		this.englishCreateTime = englishCreateTime;
	}
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public List<CommentsToBlog> getCommentsToBlogList() {
		return commentsToBlogList;
	}

	public void setCommentsToBlogList(List<CommentsToBlog> commentsToBlogList) {
		this.commentsToBlogList = commentsToBlogList;
	}
	
	public String getParentAdminName() {
		return parentAdminName;
	}

	public void setParentAdminName(String parentAdminName) {
		this.parentAdminName = parentAdminName;
	}
}
