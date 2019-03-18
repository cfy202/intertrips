package com.wenjing.entity;

import java.io.Serializable;

public class Tourlineattraction implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -9112188985602036882L;

	/**
     * tourlineattraction.id (编号)
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    private String id;

    /**
     * tourlineattraction.tourLineId (线路ID)
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    private String tourlineid;

    /**
     * tourlineattraction.attractionId (景点ID)
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    private String attractionid;

    private Attraction attractionAttractionid;

    private Tourline tourlineTourlineid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTourlineid() {
        return tourlineid;
    }

    public void setTourlineid(String tourlineid) {
        this.tourlineid = tourlineid;
    }

    public String getAttractionid() {
        return attractionid;
    }

    public void setAttractionid(String attractionid) {
        this.attractionid = attractionid;
    }

    public void setAttractionAttractionid(Attraction attractionAttractionid) {
        this.attractionAttractionid=attractionAttractionid;
    }

    public Attraction getAttractionAttractionid() {
        return attractionAttractionid;
    }

    public void setTourlineTourlineid(Tourline tourlineTourlineid) {
        this.tourlineTourlineid=tourlineTourlineid;
    }

    public Tourline getTourlineTourlineid() {
        return tourlineTourlineid;
    }
}