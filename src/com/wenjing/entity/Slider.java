package com.wenjing.entity;

import java.io.Serializable;

public class Slider implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3113947984571189343L;

	/**
     * slider.id (编号)
     * @ibatorgenerated 2015-04-22 23:01:20
     */
    private String id;

    /**
     * slider.url (图片路径)
     * @ibatorgenerated 2015-04-22 23:01:20
     */
    private String url;

    /**
     * slider.description (描述)
     * @ibatorgenerated 2015-04-22 23:01:20
     */
    private String description;

    /**
     * slider.type (类型)
     * @ibatorgenerated 2015-04-22 23:01:20
     */
    private Integer type;

    /**
     * slider.isshow (是否显示)
     * @ibatorgenerated 2015-04-22 23:01:20
     */
    private Integer isshow;

    /**
     * slider.link (连接)
     * @ibatorgenerated 2015-04-22 23:01:20
     */
    private String link;
    
    /**
     * slider.costnumber (运营中心id)
     * @ibatorgenerated 2015-04-22 23:01:20
     */
    private String costnumber;
    
    /**
     * 排序号
     */
    private Integer sort;
    
    private Cost cost;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsshow() {
        return isshow;
    }

    public void setIsshow(Integer isshow) {
        this.isshow = isshow;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

	public String getCostnumber() {
		return costnumber;
	}

	public void setCostnumber(String costnumber) {
		this.costnumber = costnumber;
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
}