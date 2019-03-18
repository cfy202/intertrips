package com.wenjing.entity;

import java.io.Serializable;

public class Page implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 9120531283408473814L;

	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 页面生成路径，以webContent为根目录
	 */
    private String filepath;
    
    /**
     * 生成页面的模版地址,以/WEB-INF/template/为根目录
     */
    private String templateUrl;

    /**
     * 页面的title信息
     */
    private String metatitle;
    
    /**
     * 页面的关键字
     */
    private String metakeywords;
    
    /**
     * 页面的描述信息
     */
    private String metadescription;
    
    /**
     * 页面的内容
     */
    private String content;
    
    /**
     * 页面的图片
     */
    private String imageurl;
    
    /**
     * 该记录的标题
     */
    private String title;
    
    /**
     * 运营中心ID
     */
    private String costnumber;
    
    /**
     * 0.首页
     * 1.产品线路
     * 6.分类导航
     * 7.博客导航
     * 8.contact us
     * 9.博客
     */
    private Integer type;
    
    /**
     * 联系方式
     */
    private String tel;

    public String getCostnumber() {
		return costnumber;
	}

	public void setCostnumber(String costnumber) {
		this.costnumber = costnumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getMetatitle() {
        return metatitle;
    }

    public void setMetatitle(String metatitle) {
        this.metatitle = metatitle;
    }

    public String getMetakeywords() {
        return metakeywords;
    }

    public void setMetakeywords(String metakeywords) {
        this.metakeywords = metakeywords;
    }

    public String getMetadescription() {
        return metadescription;
    }

    public void setMetadescription(String metadescription) {
        this.metadescription = metadescription;
    }

    public String getTemplateUrl() {
        return templateUrl;
    }

    public void setTemplateUrl(String templateUrl) {
        this.templateUrl = templateUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	
    
}