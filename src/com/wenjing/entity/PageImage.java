
package com.wenjing.entity;

import java.io.Serializable;

/**类说明
 * @author xiejin
 * @date 2015-5-5 
 */
public class PageImage implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String pageId;
	
	private String imageId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
}
