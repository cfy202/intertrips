package com.wenjing.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Attractiontype implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3959170601566995029L;

	/**
     * attractiontype.id (景点类型id)
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    private String id;

    /**
     * attractiontype.name (AAA级别)
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    private String name;

    /**
     * attractiontype.nameCN (中文名称(如四A))
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    private String namecn;

    /**
     * attractiontype.sort (排序)
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    private Integer sort;

    private Set<Attraction> attractionsAttractiontypeid = new HashSet<Attraction>(0);

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

    public String getNamecn() {
        return namecn;
    }

    public void setNamecn(String namecn) {
        this.namecn = namecn;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public void setAttractionsAttractiontypeid(Set<Attraction> attractionsAttractiontypeid) {
        this.attractionsAttractiontypeid=attractionsAttractiontypeid;
    }

    public Set<Attraction> getAttractionsAttractiontypeid() {
        return attractionsAttractiontypeid;
    }
}