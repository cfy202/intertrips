package com.wenjing.entity;

import java.io.Serializable;

public class ServiceItem implements Serializable {
    
	/** id */
	private String id;
	
	/** 服务项的标题  */
	private String tittle;
	
	/** 服务项的样式  */
	private String ico;
	
	/** 自定义图标*/
	private String icoUrl;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getIco() {
		return ico;
	}

	public void setIco(String ico) {
		this.ico = ico;
	}

	public String getIcoUrl() {
		return icoUrl;
	}

	public void setIcoUrl(String icoUrl) {
		this.icoUrl = icoUrl;
	}
	
	
}