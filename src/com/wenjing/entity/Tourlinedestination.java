package com.wenjing.entity;

import java.io.Serializable;

public class Tourlinedestination implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4699047324651695959L;

	/**
     * tourlinedestination.id (编号)
     * @ibatorgenerated 2015-04-22 23:01:32
     */
    private String id;

    /**
     * tourlinedestination.tourLineId (线路ID)
     * @ibatorgenerated 2015-04-22 23:01:32
     */
    private String tourlineid;

    /**
     * tourlinedestination.destinationId (目的地ID)
     * @ibatorgenerated 2015-04-22 23:01:32
     */
    private String destinationid;
    
    private Integer sort;

    private Destination destinationDestinationid;

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

    public String getDestinationid() {
        return destinationid;
    }

    public void setDestinationid(String destinationid) {
        this.destinationid = destinationid;
    }

    public void setDestinationDestinationid(Destination destinationDestinationid) {
        this.destinationDestinationid=destinationDestinationid;
    }

    public Destination getDestinationDestinationid() {
        return destinationDestinationid;
    }

    public void setTourlineTourlineid(Tourline tourlineTourlineid) {
        this.tourlineTourlineid=tourlineTourlineid;
    }

    public Tourline getTourlineTourlineid() {
        return tourlineTourlineid;
    }

	/**
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
    
    
}