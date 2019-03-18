package com.wenjing.entity;

import java.io.Serializable;

public class Attractionimage implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7635694991381181176L;

	/**
     * attractionimage.id (编号)
     * @ibatorgenerated 2015-04-22 23:01:24
     */
    private String id;

    /**
     * attractionimage.attractionId (景点ID)
     * @ibatorgenerated 2015-04-22 23:01:24
     */
    private String attractionid;

    /**
     * attractionimage.imageId (图片ID)
     * @ibatorgenerated 2015-04-22 23:01:24
     */
    private String imageid;

    private Attraction attractionAttractionid;

    private Image imageImageid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAttractionid() {
        return attractionid;
    }

    public void setAttractionid(String attractionid) {
        this.attractionid = attractionid;
    }

    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid;
    }

    public void setAttractionAttractionid(Attraction attractionAttractionid) {
        this.attractionAttractionid=attractionAttractionid;
    }

    public Attraction getAttractionAttractionid() {
        return attractionAttractionid;
    }

    public void setImageImageid(Image imageImageid) {
        this.imageImageid=imageImageid;
    }

    public Image getImageImageid() {
        return imageImageid;
    }
}