package com.wenjing.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @copyright   Copyright: 2014 
 * @author Sevens
 * @create-time 2015-5-8 下午1:55:35
 * @revision  1.0
 */
public class Role implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5905234249805014630L;

	/**
     * `role`.id (角色ID)
     * @ibatorgenerated 2015-04-22 23:01:24
     */
    private String id;

    /**
     * `role`.name (角色名称)
     * @ibatorgenerated 2015-04-22 23:01:24
     */
    private String name;

    /**
     * `role`.title (标题)
     * @ibatorgenerated 2015-04-22 23:01:24
     */
    private String title;

    /**
     * `role`.editName (编辑者姓名)
     * @ibatorgenerated 2015-04-22 23:01:24
     */
    private String editname;

    /**
     * `role`.operaterStr (操作权限)
     * @ibatorgenerated 2015-04-22 23:01:24
     */
    private String operaterstr;

    private Set<Admin> adminsRoleid = new HashSet<Admin>(0);

    private Set<Rolecost> rolecostsRoleid = new HashSet<Rolecost>(0);

    private Set<Roletree> roletreesRoleid = new HashSet<Roletree>(0);

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

    public String getEditname() {
        return editname;
    }

    public void setEditname(String editname) {
        this.editname = editname;
    }

    public String getOperaterstr() {
        return operaterstr;
    }

    public void setOperaterstr(String operaterstr) {
        this.operaterstr = operaterstr;
    }

    public void setAdminsRoleid(Set<Admin> adminsRoleid) {
        this.adminsRoleid=adminsRoleid;
    }

    public Set<Admin> getAdminsRoleid() {
        return adminsRoleid;
    }

   

    /**
	 * @return the rolecostsRoleid
	 */
	public Set<Rolecost> getRolecostsRoleid() {
		return rolecostsRoleid;
	}

	/**
	 * @param rolecostsRoleid the rolecostsRoleid to set
	 */
	public void setRolecostsRoleid(Set<Rolecost> rolecostsRoleid) {
		this.rolecostsRoleid = rolecostsRoleid;
	}

	public void setRoletreesRoleid(Set<Roletree> roletreesRoleid) {
        this.roletreesRoleid=roletreesRoleid;
    }

    public Set<Roletree> getRoletreesRoleid() {
        return roletreesRoleid;
    }
}