package com.wenjing.entity;

import java.io.Serializable;
import java.util.Date;

public class Admin implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8960585658492606817L;

	/**
     * `admin`.id (用户ID)
     * @ibatorgenerated 2015-04-22 23:01:31
     */
    private String id;

    /**
     * `admin`.username (登录名称)
     * @ibatorgenerated 2015-04-22 23:01:31
     */
    private String username;

    /**
     * `admin`.password (登录密码)
     * @ibatorgenerated 2015-04-22 23:01:31
     */
    private String password;

    /**
     * `admin`.createDate (创建时间)
     * @ibatorgenerated 2015-04-22 23:01:31
     */
    private String createdate;

    /**
     * `admin`.modifyDate (最后修改时间)
     * @ibatorgenerated 2015-04-22 23:01:31
     */
    private String modifydate;

    /**
     * `admin`.imageUrl (用户图像路径)
     * @ibatorgenerated 2015-04-22 23:01:31
     */
    private String imageurl;

    /**
     * `admin`.email (邮箱)
     * @ibatorgenerated 2015-04-22 23:01:31
     */
    private String email;

    /**
     * `admin`.isEnabled (是否可用)
     * @ibatorgenerated 2015-04-22 23:01:31
     */
    private boolean isenabled;

    /**
     * `admin`.isLocked (是否锁定)
     * @ibatorgenerated 2015-04-22 23:01:31
     */
    private boolean islocked;

    /**
     * `admin`.lockedDate (锁定时间)
     * @ibatorgenerated 2015-04-22 23:01:31
     */
    private Date lockeddate;

    /**
     * `admin`.loginDate (登录时间)
     * @ibatorgenerated 2015-04-22 23:01:31
     */
    private Date logindate;

    /**
     * `admin`.loginFailureCount (登录失败次数)
     * @ibatorgenerated 2015-04-22 23:01:31
     */
    private Integer loginfailurecount;

    /**
     * `admin`.loginIP (登录IP)
     * @ibatorgenerated 2015-04-22 23:01:31
     */
    private String loginip;

    /**
     * `admin`.name (真实姓名)
     * @ibatorgenerated 2015-04-22 23:01:31
     */
    private String name;

    /**
     * `admin`.roleId (角色ID)
     * @ibatorgenerated 2015-04-22 23:01:31
     */
    private String roleid;
    
    /**
     * `admin`.remark (简介)
     */
    private String remark;

    private Role roleRoleid;
    
    

    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getModifydate() {
        return modifydate;
    }

    public void setModifydate(String modifydate) {
        this.modifydate = modifydate;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    

    /**
	 * @return the isenabled
	 */
	public boolean isIsenabled() {
		return isenabled;
	}

	/**
	 * @param isenabled the isenabled to set
	 */
	public void setIsenabled(boolean isenabled) {
		this.isenabled = isenabled;
	}

	/**
	 * @return the islocked
	 */
	public boolean isIslocked() {
		return islocked;
	}

	/**
	 * @param islocked the islocked to set
	 */
	public void setIslocked(boolean islocked) {
		this.islocked = islocked;
	}

	/**
	 * @return the lockeddate
	 */
	public Date getLockeddate() {
		return lockeddate;
	}

	/**
	 * @param lockeddate the lockeddate to set
	 */
	public void setLockeddate(Date lockeddate) {
		this.lockeddate = lockeddate;
	}

	public Date getLogindate() {
        return logindate;
    }

    public void setLogindate(Date logindate) {
        this.logindate = logindate;
    }

    public Integer getLoginfailurecount() {
        return loginfailurecount;
    }

    public void setLoginfailurecount(Integer loginfailurecount) {
        this.loginfailurecount = loginfailurecount;
    }

    public String getLoginip() {
        return loginip;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public void setRoleRoleid(Role roleRoleid) {
        this.roleRoleid=roleRoleid;
    }

    public Role getRoleRoleid() {
        return roleRoleid;
    }
}