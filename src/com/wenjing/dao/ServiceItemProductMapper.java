package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.ServiceItemProduct;

public interface ServiceItemProductMapper {

	/**
	 * 添加服务项和产品的关系
	 * 
	 * @param record
	 * @return
	 */
    int insert(ServiceItemProduct record);
    
    /**
     * 批量添加服务项和产品的关系
     * 
     * @param serviceItemProductList
     * @return
     */
    int batchInsert(@Param("serviceItemProductList")List<ServiceItemProduct> serviceItemProductList);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-09-06 18:21:13
     */
    ServiceItemProduct selectByPrimaryKey(String id);

    /**
     * 根据产品ID查询所有的关联关系
     * 
     * @param productId
     * @return
     */
    List<ServiceItemProduct> selectByProductId(String productId);
   
    /**
     * 根据服务项ID查询所有的关联关系
     * 
     * @param serviceItemId
     * @return
     */
    List<ServiceItemProduct> selectByServiceItemId(String serviceItemId);
    
    /**
     * 根据主键删除服务项和产品的关系
     * 
     * @param id
     * @return
     */
    int deleteById(String id);
    
    /**
     * 根据产品ID删除服务项和产品的关系
     * 
     * @param productId
     * @return
     */
    int deleteByProductId(String productId);
    
    /**
     * 根据服务项ID删除服务项和产品的关系
     * 
     * @param serviceItemId
     * @return
     */
    int deleteByServiceItemId(String serviceItemId);
    
   /**
    * 根据服务项ID和产品ID查出关联数量
    * 
    * @param itemId
    * @param productId
    * @return
    */
    int findByItemIdAndProductId(@Param("itemId") String itemId, @Param("productId") String productId);
    
}