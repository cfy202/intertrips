package com.wenjing.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Region implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3093987969008837764L;

	/**
     * region.id
     * @ibatorgenerated 2015-04-22 23:01:20
     */
    private String id;

    /**
     * region.name
     * @ibatorgenerated 2015-04-22 23:01:20
     */
    private String name;

    /**
     * region.upId (上级级别)
     * @ibatorgenerated 2015-04-22 23:01:20
     */
    private String upid;
    /**
     * 上级目录名称
     */
    private String pname;

    /**
     * region.level (目录级别)
     * @ibatorgenerated 2015-04-22 23:01:20
     */
    private Integer level;

    /**
     * region.sort (排序)
     * @ibatorgenerated 2015-04-22 23:01:20
     */
    private Integer sort;
    
    /**
     * region.sonmaxsort (儿子数最大排序号)
     * @ibatorgenerated 2015-04-22 23:01:20
     */
    private Integer sonmaxsort;
    
    /**
     * pageid
     */
    private String pageid;
    
    /**
     * 访问路径
     */
    private String url;
    
    private String costnumber;
    
    /**
     * 类型
     * 1.线路
     * 2.签证
     * 3.游船
     * 4.酒店
     * 5.机票
     */
    private Integer type;
    
    private String namepy; // 拼音名称
    
    private String levelstr;
    
    /**
     * 1.显示
     */
    private Integer isShow;
    
    /**
     * 销售中心id
     * @return
     */
    private String costNumberIds;
    
    /**
     * @author Sevens
     * 目的地冗余字段
     */
    private String destinationList;
    
    private String destinationShow;
    
    

    public String getDestinationShow() {
		return destinationShow;
	}

	public void setDestinationShow(String destinationShow) {
		this.destinationShow = destinationShow;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	private Set<Tourline> tourlinesRegionid = new HashSet<Tourline>(0);
    
    private Cost cost;
    
    private Page page;

    public String getCostnumber() {
		return costnumber;
	}

	public void setCostnumber(String costnumber) {
		this.costnumber = costnumber;
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

    public String getUpid() {
        return upid;
    }

    public void setUpid(String upid) {
        this.upid = upid;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public void setTourlinesRegionid(Set<Tourline> tourlinesRegionid) {
        this.tourlinesRegionid=tourlinesRegionid;
    }

    public Set<Tourline> getTourlinesRegionid() {
        return tourlinesRegionid;
    }

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

	public Integer getSonmaxsort() {
		return sonmaxsort;
	}

	public void setSonmaxsort(Integer sonmaxsort) {
		this.sonmaxsort = sonmaxsort;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNamepy() {
		return namepy;
	}

	public void setNamepy(String namepy) {
		this.namepy = namepy;
	}

	public String getLevelstr() {
		return levelstr;
	}

	public void setLevelstr(String levelstr) {
		this.levelstr = levelstr;
	}

	public String getCostNumberIds() {
		return costNumberIds;
	}

	public void setCostNumberIds(String costNumberIds) {
		this.costNumberIds = costNumberIds;
	}

	public String getDestinationList() {
		return destinationList;
	}

	public void setDestinationList(String destinationList) {
		this.destinationList = destinationList;
	}

	public String getPageid() {
		return pageid;
	}

	public void setPageid(String pageid) {
		this.pageid = pageid;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
}