package com.wenjing.entity;

import java.io.Serializable;

public class Tourlineimage implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3414112205809030224L;

	/**
     * tourlineimage.id (编号)
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    private String id;

    /**
     * tourlineimage.tourLineId (线路ID)
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    private String tourlineid;

    /**
     * tourlineimage.imageId (图片ID)
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    private String imageid;

    private Tourline tourlineTourlineid;

    private Image imageImageid;

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

    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid;
    }

    public void setTourlineTourlineid(Tourline tourlineTourlineid) {
        this.tourlineTourlineid=tourlineTourlineid;
    }

    public Tourline getTourlineTourlineid() {
        return tourlineTourlineid;
    }

    public void setImageImageid(Image imageImageid) {
        this.imageImageid=imageImageid;
    }

    public Image getImageImageid() {
        return imageImageid;
    }
}