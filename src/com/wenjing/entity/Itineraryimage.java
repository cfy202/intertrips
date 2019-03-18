package com.wenjing.entity;

import java.io.Serializable;

public class Itineraryimage implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8731065923294738543L;

	/**
     * itineraryimage.id (编号)
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    private String id;

    /**
     * itineraryimage.itinId (行程ID)
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    private String itinid;

    /**
     * itineraryimage.imageId (图片ID)
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    private String imageid;

    private Image imageImageid;

    private Itinerary itineraryItinid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItinid() {
        return itinid;
    }

    public void setItinid(String itinid) {
        this.itinid = itinid;
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

    public void setItineraryItinid(Itinerary itineraryItinid) {
        this.itineraryItinid=itineraryItinid;
    }

    public Itinerary getItineraryItinid() {
        return itineraryItinid;
    }
}