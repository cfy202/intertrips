package com.wenjing.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Orders;

public interface OrdersMapper {
    
	/**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    int insert(Orders record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    int insertSelective(Orders record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    Orders selectByPrimaryKey(String id);
    
    /**
     * 根据id查询订单
     * @param id
     * @return
     */
    Orders selectByIdWithOccupyTime(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    int updateByPrimaryKeySelective(Orders record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    int updateByPrimaryKey(Orders record);
    
    /**
     * 查询出总订单详情
     */
    Orders findOrders(String ordersId);
    
    /**
     * 根据总订单Id查询出订单所有的信息
     * 
     * @param ordersId
     * @return
     */
    Orders findAllInfoByOrdersId(String ordersId);

    /**
     * 根据用户id查询订单（用户中心）
     * @param memberid
     * @return
     */
	List<Orders> findByMemberId(@Param("memberid")String memberid, @Param("startPos") Integer startPos, @Param("pageSize") Integer pageSize, @Param("type")Integer type);

	/**
	 * 查询用户总订单条数（用户中心）
	 * @param memberid
	 * @return
	 */
	int getCountByMemberid(String userId);
	
	/**
	 * 根据订单编号和邮箱查询出总订单信息
	 * 
	 * @param orderNo
	 * @param email
	 * @return
	 */
	Orders findByOrderNoAndEmail(@Param("orderNo")String orderNo,@Param("email")String email);
	
	/**
	 * 根据订单Id查询会员订单详情（会员中心）
	 * @param orderNo
	 * @return
	 */
	Orders getMemberOrderDetailById(String id);

	/**
	 * 根据订单号查询会员订单详情（会员中心）
	 * @param orderNo
	 * @return
	 */
	Orders getMemberOrderDetailByOrderNo(String orderNo);
	
	/**
	 * 根据订单号查询
	 * @param orderNo
	 * @return
	 */
	Orders finByOrderNo(String orderNo);
	
	/**
	 * @Title: updateByOrderNo
	 * @Description: 根据订单编号修改订单状态
	 * @param orderNo
	 * @param statusId
	 * @return    
	 * @return int    返回类型
	 * @author xiejin
	 */
	int updateByOrderNo(@Param("orderNo")String orderNo,@Param("statusId")String statusId);
	
	/**
	 * 非注册用户根据订单号和orderid查询会员订单详情（会员中心）
	 * @param orderNo
	 * @return
	 */
	Orders getOrderDetailByOrderNoAndEmail(@Param("orderNo")String orderNo, @Param("email")String email);
	/**
	 * @author Sevens
	 * 时间2015-7-25
	 * @param type
	 * @return
	 */
	List<Orders> findWithVisa(@Param("type")Integer type);
	
	/**
	 * 根据销售中心编号和查询条件或许总条目数
	 * 
	 * @param costId
	 * @param search
	 * @return
	 */
	int getTotalNumberByCostNumberAndSearch(@Param("costIds")List<String> costIds,@Param("search")String search);
	
	/**
	 * 根据传入参数查询出总订单数
	 * 
	 * @param paramtersMap
	 * @return
	 */
	int getOrdersTotalNumber(Map<String,Object> paramtersMap);
	
	/**
	 * 根据条件查询所有支付订单的总金额和总人数
	 * @param paramtersMap
	 * @return
	 */
	Orders getTotalCollectionAndTotalPeople(Map<String,Object> paramtersMap);
	
	/**
	 * 
	 * @param paramtersMap
	 * @return
	 */
	List<Orders> getOrdersByPage(Map<String,Object> paramtersMap);
	/**
	 * @author Sevens
	 * 时间2015-8-31
	 * @param costnumbers
	 * @param date
	 * @return
	 */
	int findOrderWithNow(@Param("costnumbers")List<String> costnumbers,@Param("date")String date);
	
	Orders findPayInfo(String orderId);
	/**
	 * 根据起始时间查询所有未同步的线路订单
	 * 
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	List<Orders> findAllTourlineOrders(@Param("starttime")long starttime,@Param("endtime")long endtime);
	
	
	/**
	 * 
	 * @return
	 */
	List<Orders> findAllInfoByOrderIds(@Param("orderIds")String[] orderIds);
	
	/**
	 * 根据失效时间查询失效订单
	 * 
	 * @return
	 */
	List<String> findCancelingOrders(@Param("time")Integer time);
	
	void cancelOrders(@Param("orderIds")List<String> orderIds);
	
	void returnStores(@Param("orderIds")List<String> orderIds);
	
	void setOccupyTimeByIds(@Param("orderIds")List<String> orderIds,@Param("occupyTime")Date occupyTime);
	
	void setOccupyTimeById(@Param("id")String id,@Param("occupyTime")Date occupyTime);
	
	Orders findWithOccupyTimeById(String id);
}