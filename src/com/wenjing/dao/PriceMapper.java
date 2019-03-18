package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Price;

public interface PriceMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-09-23 09:30:50
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-09-23 09:30:50
     */
    int insert(Price record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-09-23 09:30:50
     */
    int insertSelective(Price record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-09-23 09:30:50
     */
    Price selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-09-23 09:30:50
     */
    int updateByPrimaryKeySelective(Price record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-09-23 09:30:50
     */
    int updateByPrimaryKey(Price record);
    /**
     * 
     * @author Sevens
     * 时间2015-9-23
     * @param costnumber
     * @param productId
     * @return
     */ 
    List<Price> findByCostnumberOrProdcutId(@Param("productid")String productid);
    
    /**
	 * 查询线路下价格对应的costnum
	 * @param tourlineid
	 * @return
	 */
	List<String> getPriceCostNumByProductId(String productid);
	/**
	 * 删除除当前销售中心以外的价格
	 * @author Administrator
	 * 时间：2015-11-15
	 * @param costnumber
	 * @param productid
	 * @return
	 */
	int deleteNotCostnumber(@Param("costnumber")String costnumber,@Param("productid")String productid);
	/**
	 * 批量插入
	 * @author Administrator
	 * 时间：2015-11-15
	 * @param pricelist
	 * @return
	 */
	int batchAddPrice(List<Price> pricelist);
	
	
	
	List<Price> findByCostnumberOrProdcutId2(@Param("costnumber")String costnumber,@Param("productid")String productid);
    
}