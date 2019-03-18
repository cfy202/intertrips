package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Promotion;

public interface PromotionMapper {
	/**
	 * 根据主键删除 参数:主键 返回:删除个数
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:21
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * 插入，空属性也会插入 参数:pojo对象 返回:删除个数
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:21
	 */
	int insert(Promotion record);

	/**
	 * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:21
	 */
	int insertSelective(Promotion record);

	/**
	 * 根据主键查询 参数:查询条件,主键值 返回:对象
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:21
	 */
	Promotion selectByPrimaryKey(String id);

	/**
	 * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:21
	 */
	int updateByPrimaryKeySelective(Promotion record);

	/**
	 * 根据主键修改，空值条件会修改成null 参数:1.要修改成的值 返回:成功修改个数
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:21
	 */
	int updateByPrimaryKey(Promotion record);

	/**
	 * 查询所有促销活动列表 返回：促销列表集合
	 */
	List<Promotion> findAllByCostNumber(List<String> costnumber);

	/**
	 * 根据运营中心costnumber,查询此运营中心orderid最大值
	 * 
	 * @return ：最大值数
	 */
	Integer getMaxSort(String costnumber);

	/**
	 * 根据产品ID查询出促销信息
	 * 
	 * @param productId
	 * @return
	 */
	List<Promotion> findByProductId(String productId);
	
	/**
	 * @Title: findBytourlineId
	 * @Description: 根据线路id查询促销活动
	 * @param tourlineId
	 * @return    
	 * @return List<Promotion>    返回类型
	 * @author xiejin
	 */
	List<Promotion> findBytourlineId(@Param("tourlineId")String tourlineId,
		@Param("costnumber")String costnumber,
		@Param("timeNow")int timeNow);
	
	/**
	 * @Title: findByProductIdCostnumber
	 * @Description: 根据产品id和costnumber查询促销活动
	 * @param tourlineId
	 * @return    
	 * @return List<Promotion>    返回类型
	 * @author xiejin
	 */
	List<Promotion> findByProductIdCostnumber(@Param("productId")String productId,
			@Param("costnumber")String costnumber,
			@Param("timeNow")int timeNow);
	
	/**
	 * @Title: getByCostnumber
	 * @Description: 前台根据销售中心查询促销活动
	 * @param costnumber
	 * @param timeNow
	 * @return    
	 * @return List<Promotion>    返回类型
	 * @author xiejin
	 */
	List<Promotion> getByCostnumber(@Param("costnumber")String costnumber,
			@Param("timeNow") int timeNow);
	
	/**
	 * @Title: getByCostnumber
	 * @Description: 前台根据销售中心查询促销活动id,title
	 * @param costnumber
	 * @param timeNow
	 * @return    
	 * @return List<Promotion>    返回类型
	 * @author xiejin
	 */
	List<Promotion> getPartByCostnumber(@Param("costnumber")String costnumber,
			@Param("timeNow") int timeNow);
	/**
	 * 查询所有促销活动
	 * 时间：2015-12-30
	 * @author Sevens
	 * @return
	 */
	List<Promotion> findAllNotByCostNumber();
}