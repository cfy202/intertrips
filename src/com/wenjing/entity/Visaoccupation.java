package com.wenjing.entity;

import java.io.Serializable;

public class Visaoccupation implements Serializable {
    /**
	 * sevens 签证职业
	 */
	private static final long serialVersionUID = -3383644765010535690L;

	private String id;

    private String title;

    private String typeName;

    private String content;
    
    private String visaId;
    
    private Integer sort;

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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getVisaId() {
		return visaId;
	}

	public void setVisaId(String visaId) {
		this.visaId = visaId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
}