package com.wenjing.entity;

import java.io.Serializable;

public class Hotelimage implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6322906625450804402L;

	/**
     * hotelimage.id (关联ID)
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private String id;

    /**
     * hotelimage.hotelId (酒店ID)
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private String hotelid;

    /**
     * hotelimage.imageId (图片ID)
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private String imageid;

    private Image imageImageid;

    private Hotel hotelHotelid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHotelid() {
        return hotelid;
    }

    public void setHotelid(String hotelid) {
        this.hotelid = hotelid;
    }

    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid;
    }

    public void setImageImageid(Image imageImageid) {
        this.imageImageid=imageImageid;
    }

    public Image getImageImageid() {
        return imageImageid;
    }

    public void setHotelHotelid(Hotel hotelHotelid) {
        this.hotelHotelid=hotelHotelid;
    }

    public Hotel getHotelHotelid() {
        return hotelHotelid;
    }
}