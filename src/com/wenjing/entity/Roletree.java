package com.wenjing.entity;

import java.io.Serializable;

public class Roletree implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5080640280278283305L;

	/**
     * roletree.id (编号)
     * @ibatorgenerated 2015-04-22 23:01:32
     */
    private String id;

    /**
     * roletree.roleId (角色ID)
     * @ibatorgenerated 2015-04-22 23:01:32
     */
    private String roleid;

    private String treeid;
    
    private String opationIds;

    private Role roleRoleid;

    private Tree treeTreeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleid() {
        return roleid;
    }

    
    
    public String getOpationIds() {
		return opationIds;
	}

	public void setOpationIds(String opationIds) {
		this.opationIds = opationIds;
	}

	public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getTreeid() {
        return treeid;
    }

    public void setTreeid(String treeid) {
        this.treeid = treeid;
    }

    public void setRoleRoleid(Role roleRoleid) {
        this.roleRoleid=roleRoleid;
    }

    public Role getRoleRoleid() {
        return roleRoleid;
    }

    public void setTreeTreeid(Tree treeTreeId) {
        this.treeTreeId=treeTreeId;
    }

    public Tree getTreeTreeid() {
        return treeTreeId;
    }
}