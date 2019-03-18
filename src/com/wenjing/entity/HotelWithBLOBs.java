package com.wenjing.entity;

public class HotelWithBLOBs extends Hotel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5752973947232087268L;

	/**
     * hotel.imageUrl (酒店图片)
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    private String imageurl;

    /**
     * hotel.hotelDescription (酒店描述)
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    private String hoteldescription;

    /**
     * hotel.hotelLocation (酒店位置)
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    private String hotellocation;

    /**
     * hotel.hotelAmenities (酒店娱乐项目)
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    private String hotelamenities;

    /**
     * hotel.hotelFacilities (酒店设施)
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    private String hotelfacilities;

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getHoteldescription() {
        return hoteldescription;
    }

    public void setHoteldescription(String hoteldescription) {
        this.hoteldescription = hoteldescription;
    }

    public String getHotellocation() {
        return hotellocation;
    }

    public void setHotellocation(String hotellocation) {
        this.hotellocation = hotellocation;
    }

    public String getHotelamenities() {
        return hotelamenities;
    }

    public void setHotelamenities(String hotelamenities) {
        this.hotelamenities = hotelamenities;
    }

    public String getHotelfacilities() {
        return hotelfacilities;
    }

    public void setHotelfacilities(String hotelfacilities) {
        this.hotelfacilities = hotelfacilities;
    }
}