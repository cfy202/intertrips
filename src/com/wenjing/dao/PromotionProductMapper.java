package com.wenjing.dao;

import java.util.List;

import com.wenjing.entity.PromotionProduct;

public interface PromotionProductMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    int insert(PromotionProduct record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    int insertSelective(PromotionProduct record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    PromotionProduct selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    int updateByPrimaryKeySelective(PromotionProduct record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    int updateByPrimaryKey(PromotionProduct record);

    /**
     * 批量插入
     * @param pList
     */
	int batchAdd(List<PromotionProduct> pList);

	/**
	 * 根据promotionid删除
	 * @param id
	 * @return
	 */
	int deleteBypromotionid(String promotionid);

	/**
	 * 根据promotionid查询
	 * @param promotionid
	 * @return
	 */
	List<PromotionProduct> selectByPromotionid(String promotionId);
	
	/**
	 * @Title: findByProductid
	 * @Description:查询特卖线路时，根据productId查询
	 * @param productId
	 * @return    
	 * @return List<PromotionProduct>    返回类型
	 * @author xiejin
	 */
	List<PromotionProduct> findByProductid(String productId);
	/**
	 * 根据线路Id删除促销活动关系
	 * @author Sevens
	 * 时间2015-8-18
	 * @param tourlineId
	 * @return
	 */
	int deleteWithProductId(String productId);
}