package com.wenjing.entity;

import java.io.Serializable;

public class Couponsactivity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1381557504185271796L;

	private String id;

    private String code;

    private Integer createtime;

    private Integer releasestatus;

    private Integer usestatus;

    private String couponseid;
    
    private String levelid;
    
    

    /**
	 * @return the levelid
	 */
	public String getLevelid() {
		return levelid;
	}

	/**
	 * @param levelid the levelid to set
	 */
	public void setLevelid(String levelid) {
		this.levelid = levelid;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Integer createtime) {
        this.createtime = createtime;
    }

    public Integer getReleasestatus() {
        return releasestatus;
    }

    public void setReleasestatus(Integer releasestatus) {
        this.releasestatus = releasestatus;
    }

    public Integer getUsestatus() {
        return usestatus;
    }

    public void setUsestatus(Integer usestatus) {
        this.usestatus = usestatus;
    }

    public String getCouponseid() {
        return couponseid;
    }

    public void setCouponseid(String couponseid) {
        this.couponseid = couponseid;
    }
}