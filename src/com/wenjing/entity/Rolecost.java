package com.wenjing.entity;

import java.io.Serializable;

public class Rolecost implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4739572672684202214L;

	private String id;

    private String roleid;

    private String costnumber;

    private Cost costCostnumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getCostnumber() {
        return costnumber;
    }

    public void setCostnumber(String costnumber) {
        this.costnumber = costnumber;
    }

    public void setCostCostnumber(Cost costCostnumber) {
        this.costCostnumber=costCostnumber;
    }

    public Cost getCostCostnumber() {
        return costCostnumber;
    }
}