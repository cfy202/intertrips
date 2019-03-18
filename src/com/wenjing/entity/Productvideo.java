package com.wenjing.entity;

import java.io.Serializable;

public class Productvideo implements Serializable {
    /**
     * productvideo.id
     * @ibatorgenerated 2015-10-12 11:38:17
     */
    private String id;

    /**
     * productvideo.videoId
     * @ibatorgenerated 2015-10-12 11:38:17
     */
    private String videoid;

    /**
     * productvideo.productId
     * @ibatorgenerated 2015-10-12 11:38:17
     */
    private String productid;

    /**
     * productvideo.costNumber
     * @ibatorgenerated 2015-10-12 11:38:17
     */
    private String costnumber;

    private Video videoVideoid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVideoid() {
        return videoid;
    }

    public void setVideoid(String videoid) {
        this.videoid = videoid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getCostnumber() {
        return costnumber;
    }

    public void setCostnumber(String costnumber) {
        this.costnumber = costnumber;
    }

    public void setVideoVideoid(Video videoVideoid) {
        this.videoVideoid=videoVideoid;
    }

    public Video getVideoVideoid() {
        return videoVideoid;
    }
}