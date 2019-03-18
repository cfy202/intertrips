package com.wenjing.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Itinerary implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6321016499045096211L;

	/**
     * itinerary.id (行程id)
     * @ibatorgenerated 2015-04-22 23:01:24
     */
    private String id;

    /**
     * itinerary.day (行程第几天)
     * @ibatorgenerated 2015-04-22 23:01:24
     */
    private Integer day;

    /**
     * itinerary.title (标题)
     * @ibatorgenerated 2015-04-22 23:01:24
     */
    private String title;

    /**
     * itinerary.content (行程内容(中文))
     * @ibatorgenerated 2015-04-22 23:01:24
     */
    private String content;

    /**
     * itinerary.hotel (住宿)
     * @ibatorgenerated 2015-04-22 23:01:24
     */
    private String hotelId;
   /**
    *itinerary.hotelRemark (酒店说明) 
    */
    private String hotelRemark; 
    /**
     * itinerary.updateTime (更新时间)
     * @ibatorgenerated 2015-04-22 23:01:24
     */
    private Date updatetime;

    /**
     * itinerary.editor (编辑者)
     * @ibatorgenerated 2015-04-22 23:01:24
     */
    private String editor;

    /**
     * itinerary.traffic (交通工具)
     * @ibatorgenerated 2015-04-22 23:01:24
     */
    private String traffic;

    /**
     * itinerary.tourLineId (线路ID)
     * @ibatorgenerated 2015-04-22 23:01:24
     */
    private String tourlineid;

    /**
     * itinerary.costNumber (运营中心ID)
     * @ibatorgenerated 2015-04-22 23:01:24
     */
    private String costnumber;

    /**
     * itinerary.imageUrl (图片路径)
     * @ibatorgenerated 2015-04-22 23:01:24
     */
    private String imageurl;

    private Set<Itineraryimage> itineraryimagesItinid = new HashSet<Itineraryimage>(0);

    private Tourline tourlineTourlineid;
    
    private String reminder;
    
    private String meal;
    
    /**
     * itinerary.city (所在目的地)
     * @ibatorgenerated 2015-04-22 23:01:24
     */
    private String itintaryDids;

    /**
     * itinerary.city (所在景点)
     * @ibatorgenerated 2015-04-22 23:01:24
     */
    private String itintaryCids;
    /**
     * itinerary.itintarySids(自选项Ids)
     */
    private String itintarySids;
    
    
    private List<Destination> destinations ;
    
    private List<Attraction> attractions;
    
    private Hotel hotel;
    
    public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public String getHotelRemark() {
		return hotelRemark;
	}

	public void setHotelRemark(String hotelRemark) {
		this.hotelRemark = hotelRemark;
	}

	public List<Destination> getDestinations() {
		return destinations;
	}

	public void setDestinations(List<Destination> destinations) {
		this.destinations = destinations;
	}

	public List<Attraction> getAttractions() {
		return attractions;
	}

	public void setAttractions(List<Attraction> attractions) {
		this.attractions = attractions;
	}

	public String getReminder() {
		return reminder;
	}

	public void setReminder(String reminder) {
		this.reminder = reminder;
	}

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

   
    public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getItintaryDids() {
		return itintaryDids;
	}

	public void setItintaryDids(String itintaryDids) {
		this.itintaryDids = itintaryDids;
	}

	public String getItintaryCids() {
		return itintaryCids;
	}

	public void setItintaryCids(String itintaryCids) {
		this.itintaryCids = itintaryCids;
	}

	public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public String getTourlineid() {
        return tourlineid;
    }

    public void setTourlineid(String tourlineid) {
        this.tourlineid = tourlineid;
    }

    public String getCostnumber() {
        return costnumber;
    }

    public void setCostnumber(String costnumber) {
        this.costnumber = costnumber;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public void setItineraryimagesItinid(Set<Itineraryimage> itineraryimagesItinid) {
        this.itineraryimagesItinid=itineraryimagesItinid;
    }

    public Set<Itineraryimage> getItineraryimagesItinid() {
        return itineraryimagesItinid;
    }

    public void setTourlineTourlineid(Tourline tourlineTourlineid) {
        this.tourlineTourlineid=tourlineTourlineid;
    }

    public Tourline getTourlineTourlineid() {
        return tourlineTourlineid;
    }

	public String getItintarySids() {
		return itintarySids;
	}

	public void setItintarySids(String itintarySids) {
		this.itintarySids = itintarySids;
	}
    
    
}