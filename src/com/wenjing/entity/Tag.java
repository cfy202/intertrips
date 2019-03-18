package com.wenjing.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Tag implements Serializable {
    /**
     * tag.id
     * @ibatorgenerated 2015-09-06 18:21:13
     */
    private String id;

    /**
     * tag.name
     * @ibatorgenerated 2015-09-06 18:21:13
     */
    private String name;

    /**
     * tag.bgColor
     * @ibatorgenerated 2015-09-06 18:21:13
     */
    private String bgcolor;

    /**
     * tag.textColor
     * @ibatorgenerated 2015-09-06 18:21:13
     */
    private String textcolor;
    
    /**
     * tag.costnumber
     * @ibatorgenerated 2015-09-06 18:21:13
     */
    private String costnumber;
    
    /**
     * tag.type
     * @ibatorgenerated 2015-09-06 18:21:13
     */
    private Integer type;
    
    private Cost cost;
    
    
    
    
    public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

	private Set<Producttag> producttagsTagid = new HashSet(0);;

    
    
    public String getCostnumber() {
		return costnumber;
	}

	public void setCostnumber(String costnumber) {
		this.costnumber = costnumber;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

    public String getBgcolor() {
        return bgcolor;
    }

    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }

    public String getTextcolor() {
        return textcolor;
    }

    public void setTextcolor(String textcolor) {
        this.textcolor = textcolor;
    }

    public void setProducttagsTagid(Set producttagsTagid) {
        this.producttagsTagid=producttagsTagid;
    }

    public Set<Producttag> getProducttagsTagid() {
        return producttagsTagid;
    }
}