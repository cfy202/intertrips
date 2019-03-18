package com.wenjing.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Tree implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6997613693169415621L;

	/**
     * tree.id (编号)
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    private String id;

    /**
     * tree.name (名称)
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    private String name;

    /**
     * tree.parentId (父节点ID)
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    private String parentid;
    /**
     * 父节点名称
     */
    private String pname;
    /**
     * 目录层级
     */
    private Integer level;

    /**
     * tree.url (访问路径)
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    private String url;

    /**
     * tree.orderId (排序)
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    private Integer orderid;
    
    private String opationNames;
    
    private int sonmaxsort;
    
    public String getOpationNames() {
		return opationNames;
	}

	public void setOpationNames(String opationNames) {
		this.opationNames = opationNames;
	}

	public String getOpationIds() {
		return opationIds;
	}

	public void setOpationIds(String opationIds) {
		this.opationIds = opationIds;
	}

	private String opationIds;

    private Set<Roletree> roletreesParentid = new HashSet<Roletree>(0);
    
    

    public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
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

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
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

    public void setRoletreesParentid(Set<Roletree> roletreesParentid) {
        this.roletreesParentid=roletreesParentid;
    }

    public Set<Roletree> getRoletreesParentid() {
        return roletreesParentid;
    }

	public int getSonmaxsort() {
		return sonmaxsort;
	}

	public void setSonmaxsort(int sonmaxsort) {
		this.sonmaxsort = sonmaxsort;
	}
}