package com.wenjing.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Video implements Serializable {
    /**
     * video.id
     * @ibatorgenerated 2015-10-12 11:38:18
     */
    private String id;

    /**
     * video.title
     * @ibatorgenerated 2015-10-12 11:38:18
     */
    private String title;

    /**
     * video.infor
     * @ibatorgenerated 2015-10-12 11:38:18
     */
    private String infor;

    /**
     * video.pic
     * @ibatorgenerated 2015-10-12 11:38:18
     */
    private String pic;

    /**
     * video.isshow
     * @ibatorgenerated 2015-10-12 11:38:18
     */
    private Integer isshow;

    /**
     * video.url
     * @ibatorgenerated 2015-10-12 11:38:18
     */
    private String url;

    /**
     * video.type
     * @ibatorgenerated 2015-10-12 11:38:18
     */
    private Integer type;
    /**
     * 销售中心
     */
    private String costnumber;
    
    private Cost cost;

    private Set<Productvideo> productvideosVideoid = new HashSet(0);;
    	public String getCostnumber() {
		return costnumber;
	}

    	
	public Cost getCost() {
			return cost;
		}


		public void setCost(Cost cost) {
			this.cost = cost;
		}


	public void setCostnumber(String costnumber) {
		this.costnumber = costnumber;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfor() {
        return infor;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getIsshow() {
        return isshow;
    }

    public void setIsshow(Integer isshow) {
        this.isshow = isshow;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setProductvideosVideoid(Set productvideosVideoid) {
        this.productvideosVideoid=productvideosVideoid;
    }

    public Set<Productvideo> getProductvideosVideoid() {
        return productvideosVideoid;
    }
}