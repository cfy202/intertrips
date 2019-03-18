package com.wenjing.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.wenjing.entity.TourPassenger;

/**
 * 客人信息Mapper
 * 
 * @author Jared
 *
 * Jun 5, 2015
 */
public interface TourPassengerMapper {
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	 TourPassenger selectByPrimaryKey(String id);
	
	/**
	 * 
	 * @param id
	 * @return 
	 */
	int deleteByPrimaryKey(String id);
	
	/**
	 * 
	 * @param tourPassenger
	 * @return
	 */
	int insertSelective(TourPassenger tourPassenger);
	
	/**
	 * 批量插入
	 * 
	 * @param tourPassengerList
	 * @return
	 */
	int insertBatch(@Param("tourPassengerList")List<TourPassenger> tourPassengerList);
	
	/**
	 * 
	 * 
	 * @return
	 */
	int updateByPrimaryKeySelective(TourPassenger tourPassenger);
	
	/**
	 * 根据订单ID查询所有的客人
	 * 
	 * @return
	 */
	List<TourPassenger> findByOrdersId(String orderId);
	
	/**
	 * 批量插入tourPassenger
	 * 
	 * @param tourPassengerList
	 * @return
	 */
	int batchInsert(@Param("tourPassengerList")List<TourPassenger> tourPassengerList);
}
