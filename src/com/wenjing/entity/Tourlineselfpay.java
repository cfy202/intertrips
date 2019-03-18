package com.wenjing.entity;

import java.io.Serializable;

public class Tourlineselfpay implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1023286015026186758L;

	/**
     * tourlineselfpay.id (关系表id)
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    private String id;

    /**
     * tourlineselfpay.tourLineId (线路id)
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    private String tourlineid;

    /**
     * tourlineselfpay.selfPayId (自费项目id)
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    private String selfpayid;

    private Tourline tourlineTourlineid;

    private Selfpay selfpaySelfpayid;

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

    public String getSelfpayid() {
        return selfpayid;
    }

    public void setSelfpayid(String selfpayid) {
        this.selfpayid = selfpayid;
    }

    public void setTourlineTourlineid(Tourline tourlineTourlineid) {
        this.tourlineTourlineid=tourlineTourlineid;
    }

    public Tourline getTourlineTourlineid() {
        return tourlineTourlineid;
    }

    public void setSelfpaySelfpayid(Selfpay selfpaySelfpayid) {
        this.selfpaySelfpayid=selfpaySelfpayid;
    }

    public Selfpay getSelfpaySelfpayid() {
        return selfpaySelfpayid;
    }
}