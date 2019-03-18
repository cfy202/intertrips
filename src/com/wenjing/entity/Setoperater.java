package com.wenjing.entity;

import java.io.Serializable;

public class Setoperater implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6308008927892616919L;

	/**
     * setoperater.id (操作ID)
     * @ibatorgenerated 2015-04-22 23:01:21
     */
    private String id;

    /**
     * setoperater.name (名称)
     * @ibatorgenerated 2015-04-22 23:01:21
     */
    private String name;

    /**
     * setoperater.title (标题)
     * @ibatorgenerated 2015-04-22 23:01:21
     */
    private String title;

    /**
     * setoperater.url (访问路径)
     * @ibatorgenerated 2015-04-22 23:01:21
     */
    private String url;

    /**
     * setoperater.fileType (文件类型)
     * @ibatorgenerated 2015-04-22 23:01:21
     */
    private String filetype;

    /**
     * setoperater.orderId (排序)
     * @ibatorgenerated 2015-04-22 23:01:21
     */
    private Integer orderid;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }
}