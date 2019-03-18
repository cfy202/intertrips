
package com.wenjing.entity;

import java.io.Serializable;

/**
 * 类说明
 * @author xiejin
 * @date 2015-11-16 
 * @date 2015-11-16 下午4:00:46
 */
public class IndexShowDestination implements Serializable{
	
	
	private static final long serialVersionUID = 6698981685652053406L;

	private String id;

    private String costNumber;

    private String destinationId;
    
    private Integer showType;

    private Integer addTime;
    
    private String fileUrl;
    
    private String pageId; 

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Integer getShowType() {
		return showType;
	}

	public void setShowType(Integer showType) {
		this.showType = showType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCostNumber() {
		return costNumber;
	}

	public void setCostNumber(String costNumber) {
		this.costNumber = costNumber;
	}

	public String getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(String destinationId) {
		this.destinationId = destinationId;
	}

	public Integer getAddTime() {
		return addTime;
	}

	public void setAddTime(Integer addTime) {
		this.addTime = addTime;
	}

}
