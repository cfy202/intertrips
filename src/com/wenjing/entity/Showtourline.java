package com.wenjing.entity;

import java.io.Serializable;

public class Showtourline implements Serializable {
    /**
     * showtourline.id
     * @ibatorgenerated 2015-08-29 02:38:54
     */
    private String id;

    /**
     * showtourline.costNumber
     * @ibatorgenerated 2015-08-29 02:38:54
     */
    private String costnumber;

    /**
     * showtourline.tourlineId (线路id)
     * @ibatorgenerated 2015-08-29 02:38:54
     */
    private String tourlineid;

    /**
     * showtourline.addTime (添加热卖时间)
     * @ibatorgenerated 2015-08-29 02:38:54
     */
    private Integer addtime;
    /**
     * 页面生成状态
     */
    private Integer isCreate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCostnumber() {
        return costnumber;
    }

    public void setCostnumber(String costnumber) {
        this.costnumber = costnumber;
    }

    public String getTourlineid() {
        return tourlineid;
    }

    public void setTourlineid(String tourlineid) {
        this.tourlineid = tourlineid;
    }

    public Integer getAddtime() {
        return addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }
}