package com.wenjing.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Destination implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -47103754849707350L;

	public static final String China  = "3fe29d417f3849739acc062724b89a5a";

	/**
     * destination.id (城市ID)
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    private String id;

    /**
     * destination.name (名称)
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    private String name;

    /**
     * destination.nameCN (中文名称)
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    private String namecn;

    /**
     * destination.namePY (拼音)
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    private String namepy;

    /**
     * destination.short (简写)
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    private String myshort;

    /**
     * destination.upId (父节点城市ID)
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    private String upid;

    /**
     * destination.typeId (城市类型)
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    private Byte typeid;

    /**
     * destination.isHot (是否热点)
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    private Byte ishot;

    /**
     * destination.sort (排序)
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    private Integer sort;

    /**
     * destination.costNumber (运营中心ID)
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    private String costnumber;
    
	private List<Selfpay> selfpayList;
    	
    private List<Attraction> attractions;

    private List<Hotel> hotels;
    
    private List<Food> foods;

    private List<Admissionticket> admissiontickets;
    
	private Set<Tourlinedestination> tourlinedestinationsDestinationid = new HashSet<Tourlinedestination>(0);
    
    private String ups;
    
	private int level;
    
    private String imageUrl;
    
    private String upName;	//上级英文名
    
    private String fileUrl;	
    
    private String desUrl;	//不同销售中心，目的地二级页面路径

    public String getDesUrl() {
		return desUrl;
	}

	public void setDesUrl(String desUrl) {
		this.desUrl = desUrl;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getUpName() {
		return upName;
	}

	public void setUpName(String upName) {
		this.upName = upName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getUps() {
		return ups;
	}

	public void setUps(String ups) {
		this.ups = ups;
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

    public String getNamecn() {
        return namecn;
    }

    public void setNamecn(String namecn) {
        this.namecn = namecn;
    }

    public String getNamepy() {
        return namepy;
    }

    public void setNamepy(String namepy) {
        this.namepy = namepy;
    }

    public String getMyshort() {
        return myshort;
    }

    public void setMyshort(String myshort) {
        this.myshort = myshort;
    }

    public String getUpid() {
        return upid;
    }

    public void setUpid(String upid) {
        this.upid = upid;
    }

    public Byte getTypeid() {
        return typeid;
    }

    public void setTypeid(Byte typeid) {
        this.typeid = typeid;
    }  

    public Byte getIshot() {
		return ishot;
	}

	public void setIshot(Byte ishot) {
		this.ishot = ishot;
	}

	public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCostnumber() {
        return costnumber;
    }

    public void setCostnumber(String costnumber) {
        this.costnumber = costnumber;
    }
    
    public List<Attraction> getAttractions() {
		return attractions;
	}

	public void setAttractions(List<Attraction> attractions) {
		this.attractions = attractions;
	}

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	public void setTourlinedestinationsDestinationid(Set<Tourlinedestination> tourlinedestinationsDestinationid) {
        this.tourlinedestinationsDestinationid=tourlinedestinationsDestinationid;
    }

    public Set<Tourlinedestination> getTourlinedestinationsDestinationid() {
        return tourlinedestinationsDestinationid;
    }
    
    public List<Food> getFoods() {
		return foods;
	}

	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}

	public List<Admissionticket> getAdmissiontickets() {
		return admissiontickets;
	}

	public void setAdmissiontickets(List<Admissionticket> admissiontickets) {
		this.admissiontickets = admissiontickets;
	}
	
    public List<Selfpay> getSelfpayList() {
		return selfpayList;
	}

	public void setSelfpayList(List<Selfpay> selfpayList) {
		this.selfpayList = selfpayList;
	}	
}