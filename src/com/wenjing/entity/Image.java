package com.wenjing.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Image implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3837204845547285472L;

	/**
     * image.id (图片ID)
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    private String id;

    /**
     * image.name (名称)
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    private String name;

    /**
     * image.title (标题)
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    private String title;

    /**
     * image.createTime (上传时间)
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    private String createtime;

    /**
     * image.url (图片访问路径)
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    private String url;

    /**
     * image.opUser (上传人)
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    private String opuser;

    /**
     * image.useType (使用类型)
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    private String usetype;

    /**
     * image.costNumber (运营中心ID)
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    private String costnumber;

    private Set<Attractionimage> attractionimagesImageid = new HashSet<Attractionimage>(0);

    private Set<Hotelimage> hotelimagesImageid = new HashSet<Hotelimage>(0);

    private Set<Itineraryimage> itineraryimagesImageid = new HashSet<Itineraryimage>(0);

    private Set<Tourlineimage> tourlineimagesImageid = new HashSet<Tourlineimage>(0);
    
    private int imageUseCount;		//图片使用次数

    public int getImageUseCount() {
		return imageUseCount;
	}

	public void setImageUseCount(int imageUseCount) {
		this.imageUseCount = imageUseCount;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOpuser() {
        return opuser;
    }

    public void setOpuser(String opuser) {
        this.opuser = opuser;
    }

    public String getUsetype() {
        return usetype;
    }

    public void setUsetype(String usetype) {
        this.usetype = usetype;
    }

    public String getCostnumber() {
        return costnumber;
    }

    public void setCostnumber(String costnumber) {
        this.costnumber = costnumber;
    }

    public void setAttractionimagesImageid(Set<Attractionimage> attractionimagesImageid) {
        this.attractionimagesImageid=attractionimagesImageid;
    }

    public Set<Attractionimage> getAttractionimagesImageid() {
        return attractionimagesImageid;
    }

    public void setHotelimagesImageid(Set<Hotelimage> hotelimagesImageid) {
        this.hotelimagesImageid=hotelimagesImageid;
    }

    public Set<Hotelimage> getHotelimagesImageid() {
        return hotelimagesImageid;
    }

    public void setItineraryimagesImageid(Set<Itineraryimage> itineraryimagesImageid) {
        this.itineraryimagesImageid=itineraryimagesImageid;
    }

    public Set<Itineraryimage> getItineraryimagesImageid() {
        return itineraryimagesImageid;
    }

    public void setTourlineimagesImageid(Set<Tourlineimage> tourlineimagesImageid) {
        this.tourlineimagesImageid=tourlineimagesImageid;
    }

    public Set<Tourlineimage> getTourlineimagesImageid() {
        return tourlineimagesImageid;
    }
}