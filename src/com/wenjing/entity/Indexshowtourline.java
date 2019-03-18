package com.wenjing.entity;

import java.io.Serializable;

public class Indexshowtourline implements Serializable {
    /**
     * indexshowtourline.id
     * @ibatorgenerated 2015-08-29 02:38:55
     */
    private String id;

    /**
     * indexshowtourline.costNumber
     * @ibatorgenerated 2015-08-29 02:38:55
     */
    private String costnumber;

    /**
     * indexshowtourline.tourlineId (线路id)
     * @ibatorgenerated 2015-08-29 02:38:55
     */
    private String tourlineid;

    /**
     * indexshowtourline.addTime (添加热卖时间)
     * @ibatorgenerated 2015-08-29 02:38:55
     */
    private Integer addtime;

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