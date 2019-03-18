package com.wenjing.dao;

import java.util.List;

import com.wenjing.entity.Ordercontacter;

public interface OrdercontacterMapper {
	/**
	 * 根据主键删除 参数:主键 返回:删除个数
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:28
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * 插入，空属性也会插入 参数:pojo对象 返回:删除个数
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:28
	 */
	int insert(Ordercontacter record);

	/**
	 * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:28
	 */
	int insertSelective(Ordercontacter record);

	/**
	 * 根据主键查询 参数:查询条件,主键值 返回:对象
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:28
	 */
	Ordercontacter selectByPrimaryKey(String id);

	/**
	 * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:28
	 */
	int updateByPrimaryKeySelective(Ordercontacter record);

	/**
	 * 根据主键修改，空值条件会修改成null 参数:1.要修改成的值 返回:成功修改个数
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:28
	 */
	int updateByPrimaryKey(Ordercontacter record);

	/**
	 * 根据Email查询联系人信息
	 * 
	 * @param nonUserAccount
	 * @return
	 */
	List<String> findOrderContacterByEmail(String email);

	/**
	 * 根据总订单ID查询出联系人信息
	 * 
	 * @param orderId
	 * @return
	 */
	Ordercontacter selectByOrderId(String orderId);

	/**
	 * 根据订单编号查询联系人
	 * 
	 * @param orderNo
	 * @return
	 */
	Ordercontacter findByOrdersId(String ordersId);
}