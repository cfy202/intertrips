package com.wenjing.entity;

import java.io.Serializable;

public class Tourlinehotel implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5993445604867125419L;

	/**
     * tourlinehotel.id (编号)
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    private String id;

    /**
     * tourlinehotel.tourLineId (线路ID)
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    private String tourlineid;

    /**
     * tourlinehotel.hotelId (酒店ID)
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    private String hotelid;

    private Hotel hotelHotelid;

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

    public String getHotelid() {
        return hotelid;
    }

    public void setHotelid(String hotelid) {
        this.hotelid = hotelid;
    }

    public void setHotelHotelid(Hotel hotelHotelid) {
        this.hotelHotelid=hotelHotelid;
    }

    public Hotel getHotelHotelid() {
        return hotelHotelid;
    }

    public void setTourlineTourlineid(Tourline tourlineTourlineid) {
        this.tourlineTourlineid=tourlineTourlineid;
    }

    public Tourline getTourlineTourlineid() {
        return tourlineTourlineid;
    }
}