package com.wenjing.entity;

import java.io.Serializable;
import java.util.List;

public class Navigation implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5632102587953827199L;

	private String id;

    private String name;

    private String url;

    private Integer orderid;

    private Integer type;
    
    private String costnumber;
    
    private String upid; 
    
    private Integer level;
    
    private Integer sonmaxsort;
    
    private String pname;
    
    private Cost cost;
    
    private String levelstr;
    
    private Integer isShow;
    
    private List<Tag> tagList;	//上导航下的标签集合

    public List<Tag> getTagList() {
		return tagList;
	}

	public void setTagList(List<Tag> tagList) {
		this.tagList = tagList;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public String getCostnumber() {
		return costnumber;
	}

	public void setCostnumber(String costnumber) {
		this.costnumber = costnumber;
	}

	public String getUpid() {
		return upid;
	}

	public void setUpid(String upid) {
		this.upid = upid;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getSonmaxsort() {
		return sonmaxsort;
	}

	public void setSonmaxsort(Integer sonmaxsort) {
		this.sonmaxsort = sonmaxsort;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getLevelstr() {
		return levelstr;
	}

	public void setLevelstr(String levelstr) {
		this.levelstr = levelstr;
	}
}