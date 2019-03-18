package com.wenjing.entity;

import java.io.Serializable;

public class Producttag implements Serializable {
    /**
     * producttag.id
     * @ibatorgenerated 2015-09-06 18:21:14
     */
    private String id;

    /**
     * producttag.tagId
     * @ibatorgenerated 2015-09-06 18:21:14
     */
    private String tagid;

    /**
     * producttag.productId
     * @ibatorgenerated 2015-09-06 18:21:14
     */
    private String productid;
    
    private String costnumber;
    
    

    public String getCostnumber() {
		return costnumber;
	}

	public void setCostnumber(String costnumber) {
		this.costnumber = costnumber;
	}

	private Tag tagTagid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTagid() {
        return tagid;
    }

    public void setTagid(String tagid) {
        this.tagid = tagid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public void setTagTagid(Tag tagTagid) {
        this.tagTagid=tagTagid;
    }

    public Tag getTagTagid() {
        return tagTagid;
    }
}