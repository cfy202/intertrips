package com.wenjing.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Food implements Serializable {
    /**
     * food.id (酒店Id)
     * @ibatorgenerated 2016-03-12 15:35:12
     */
    private String id;

    /**
     * food.foodName (名称)
     * @ibatorgenerated 2016-03-12 15:35:12
     */
    private String foodname;

    /**
     * food.foodCN (中文名)
     * @ibatorgenerated 2016-03-12 15:35:12
     */
    private String foodcn;

    /**
     * food.foodStar (推荐指数)
     * @ibatorgenerated 2016-03-12 15:35:12
     */
    private Byte foodstar;

    /**
     * food.address (地址)
     * @ibatorgenerated 2016-03-12 15:35:12
     */
    private String address;

    /**
     * food.Telephone (电话)
     * @ibatorgenerated 2016-03-12 15:35:12
     */
    private String telephone;

    /**
     * food.city (城市)
     * @ibatorgenerated 2016-03-12 15:35:12
     */
    private String city;

    /**
     * food.sort (排序)
     * @ibatorgenerated 2016-03-12 15:35:12
     */
    private Integer sort;

    /**
     * food.destinationId (����ID)
     * @ibatorgenerated 2016-03-12 15:35:12
     */
    private String destinationid;

    /**
     * food.costNumber (��Ӫ����ID)
     * @ibatorgenerated 2016-03-12 15:35:12
     */
    private String costnumber;

    /**
     * food.productId (产品编号)
     * @ibatorgenerated 2016-03-12 15:35:12
     */
    private String productid;

    /**
     * food.costNumberIds (销售中心集合)
     * @ibatorgenerated 2016-03-12 15:35:12
     */
    private String costnumberids;

    /**
     * food.regionId (分类)
     * @ibatorgenerated 2016-03-12 15:35:12
     */
    private String regionid;
    
    /**
     * food.imageUrl (图片地址)
     * @ibatorgenerated 2016-03-12 15:35:12
     */
    private String imageurl;

    /**
     * food.foodDescription (说明)
     * @ibatorgenerated 2016-03-12 15:35:12
     */
    private String fooddescription;

    /**
     * food.foodLocation (所在地)
     * @ibatorgenerated 2016-03-12 15:35:12
     */
    private String foodlocation;

    /**
     * food.foodAmenities (备注)
     * @ibatorgenerated 2016-03-12 15:35:12
     */
    private String foodamenities;
    
    /**
     * 价格
     */
    private BigDecimal price;
    
    private String productId;

	private Destination destinationDestinationid;

    private Product productProductid;

    private List<Producttag> productTagList;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getFoodcn() {
        return foodcn;
    }

    public void setFoodcn(String foodcn) {
        this.foodcn = foodcn;
    }

    public Byte getFoodstar() {
        return foodstar;
    }

    public void setFoodstar(Byte foodstar) {
        this.foodstar = foodstar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDestinationid() {
        return destinationid;
    }

    public void setDestinationid(String destinationid) {
        this.destinationid = destinationid;
    }

    public String getCostnumber() {
        return costnumber;
    }

    public void setCostnumber(String costnumber) {
        this.costnumber = costnumber;
    }

    public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getCostnumberids() {
        return costnumberids;
    }

    public void setCostnumberids(String costnumberids) {
        this.costnumberids = costnumberids;
    }

    public String getRegionid() {
        return regionid;
    }

    public void setRegionid(String regionid) {
        this.regionid = regionid;
    }

    public void setDestinationDestinationid(Destination destinationDestinationid) {
        this.destinationDestinationid=destinationDestinationid;
    }

    public Destination getDestinationDestinationid() {
        return destinationDestinationid;
    }

    public void setProductProductid(Product productProductid) {
        this.productProductid=productProductid;
    }

    public Product getProductProductid() {
        return productProductid;
    }

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getFooddescription() {
		return fooddescription;
	}

	public void setFooddescription(String fooddescription) {
		this.fooddescription = fooddescription;
	}

	public String getFoodlocation() {
		return foodlocation;
	}

	public void setFoodlocation(String foodlocation) {
		this.foodlocation = foodlocation;
	}

	public String getFoodamenities() {
		return foodamenities;
	}

	public void setFoodamenities(String foodamenities) {
		this.foodamenities = foodamenities;
	}

	public List<Producttag> getProductTagList() {
		return productTagList;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setProductTagList(List<Producttag> productTagList) {
		this.productTagList = productTagList;
	}
}