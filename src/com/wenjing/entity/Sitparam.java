package com.wenjing.entity;

import java.io.Serializable;

public class Sitparam implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5804219093416284446L;

	/**
     * sitparam.id (参数ID)
     * @ibatorgenerated 2015-04-22 23:01:32
     */
    private String id;

    /**
     * sitparam.name (参数名称)
     * @ibatorgenerated 2015-04-22 23:01:32
     */
    private String name;

    /**
     * sitparam.text (参数内容)
     * @ibatorgenerated 2015-04-22 23:01:32
     */
    private String text;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}