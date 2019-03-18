package com.wenjing.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Product;

public interface ProductMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:31
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:31
     */
    int insert(Product record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:31
     */
    int insertSelective(Product record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:31
     */
    Product selectByPrimaryKey(String id);
    
    /**
     * 根据线路ID查询到产品
     * @param id
     * @return
     */
    Product selectByTourlineId(String tourlineId);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:31
     */
    int updateByPrimaryKeySelective(Product record);

    /**
     * 根据主键修改，空值条件会修改成null,支持大字段类型
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:31
     */
    int updateByPrimaryKeyWithBLOBs(Product record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:31
     */
    int updateByPrimaryKey(Product record);
    
    /**
     * @Title: getfilePath
     * @Description: 获得线路的页面路径
     * @param id
     * @return    
     * @return String    返回类型
     * @author xiejin
     */
    String getfilePath(String id);

    /**
     * 查询所有产品列表
     * @return
     */
	List<Product> findAll();

	/**
	 * 根据promotionid查询参与活动列表
	 * @param id
	 * @return
	 */
	List<Product> findBypromotionid(String promotionid);

	/**
	 * 根据costnumber获取产品列表
	 * bowden
	 * @param costnumber
	 * @return
	 */
	List<Product> findAllByCostnumber(@Param("costnumber")String costnumber,
			@Param("time")int time);

	/**
	 * 根据productidList查询
	 * @param productidList
	 * @return
	 */
	List<Product> findByProductidList(List<String> productidList);
	
	/**
	 * @author Sevens
	 * 时间2015-5-29
	 * @param isshow
	 * @param id
	 * @return
	 */
	int updateWithShow(@Param("id")String id,@Param("isshow") Integer isshow);
	/**
	 * @author Sevens
	 * 时间2015-5-29
	 * @param id
	 * @param ishost
	 * @return
	 */
    int updateWithHot(@Param("id") String id,@Param("ishot") Integer ishost);
    
    /**
     * @author Sevens
     * 时间2015-5-29
     * @param id
     * @param indexShow
     * @return
     */
    int updateWithIndexshow(@Param("id") String id,@Param("indexShow") Integer indexShow);

    /**
     * 修改最低价
     * @param minprice
     * @param productid
     */
	int updateminprice(@Param("minprice")BigDecimal minprice,@Param("productid") String productid);
	/**
	 * 查询热推签证
	 * @param type
	 * @param costnumber
	 * @return
	 */
	List<Product> findIshotVisa(@Param("type")Integer type,@Param("costnumber") String costnumber);
	/**
	 * @author Sevens
	 * 时间2015-6-25
	 * @param code
	 * @return
	 */
	List<Product> isExistUserCode(@Param("code")String code);
	
	
	List<Product> isExistProductNo(@Param("productNo")String productNo,@Param("costnumber")String costnumber);
	
	/**
	 * @Title: findById
	 * @Description: 根据id查询
	 * @param id
	 * @return    
	 * @return Product    返回类型
	 * @author xiejin
	 */
	Product findById(String id);

	/**
	 * 修改costNumIds
	 * @param productid
	 * @param costNumIds
	 * @return
	 */
	int updateCostNumIds(@Param("productid")String productid, @Param("costNumIds")String costNumIds);
	/**
	 * @author Sevens
	 * 时间2015-9-7
	 * @param tagnames
	 * @param productIds
	 * @return
	 */
	int updateProductTags(@Param("tagnames")String tagnames,@Param("productIds")List<String>  productIds);
	
	/**
	 * @Title: findByTourlineId
	 * @Description: 根据线路id查询产品code
	 * @return    
	 * @return List<Product>    返回类型
	 * @author xiejin
	 */
	Product findByTourlineId(String tourlineId);
	
	/**
	 * 根据id查询
	 * 包含page
	 * @param id
	 * @return
	 */
	Product getWithPageById(String id);
	
	/**
	 * @Title: getProductCount
	 * @Description: 查询产品数量
	 * @param regionName
	 * @param costnumber
	 * @param tourName
	 * @param tourCode
	 * @param time
	 * @return    
	 * @return int    返回类型
	 * @author xiejin
	 */
    int getProductCount(@Param("regionName")String regionName,
    		@Param("costnumber")String costnumber,
    		@Param("tourName")String tourName,
    		@Param("tourCode")String tourCode,
    		@Param("time")int time);
    
	/**
	 * @Title: getProductCount
	 * @Description: 查询产品数量
	 * @param regionName
	 * @param costnumber
	 * @param tourName
	 * @param tourCode
	 * @param time
	 * @return    
	 * @return int    返回类型
	 * @author xiejin
	 */
    List<Product> findProductByCondition(@Param("regionName")String regionName,
    		@Param("costnumber")String costnumber,
    		@Param("tourName")String tourName,
    		@Param("tourCode")String tourCode,
    		@Param("time")int time,
    		@Param("startPos")Integer startPos,
    		@Param("pageSize")Integer pageSize);
    
    List<Product> findAllTourline(@Param("startPos")Integer startPos,@Param("pageSize")Integer pageSize);
    
    int findTourlineSize();
    
    /**
     * 查询出将要同步的线路产品
     * 
     * @param starttime
     * @param endtime
     * @return
     */
    List<Product> findPendingSynchronousTourline(@Param("starttime")long starttime,@Param("endtime")long endtime);
    
    String findFilePathByProductId(String id);
    
    void synchronizeProduct(@Param("ids")List<String> ids);
    
    Product findPendingSynchronousTourlineById(String id);
    
    void setProductUnsynchronizeByProductId(String productId);
    
    void setProductUnsynchronizeByTourlineId(String tourlineId);
    
    void setProductUnsynchronizeByHotel(String hotelId);
    
    void setProductUnsynchronizeByTourdate(String tourdateId);
}