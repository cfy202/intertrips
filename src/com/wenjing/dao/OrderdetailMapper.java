package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Orderdetail;

public interface OrderdetailMapper {
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
    int insert(Orderdetail record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    int insertSelective(Orderdetail record);
    
    /**
     * 批量插入orderDetail
     * @param orderDetails
     * @return
     */
    int batchInsert(@Param("orderDetails") List<Orderdetail> orderDetails);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    Orderdetail selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    int updateByPrimaryKeySelective(Orderdetail record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    int updateByPrimaryKey(Orderdetail record);
    
   
    /**
     * 根据总订单ID查询出子订单以及该子订单下的客人
     * 
     * @param ordersId
     * @return
     */
    List<Orderdetail> selectWithTourPassengersAndProductByOrdersId(String ordersId);
    
    /**
     * @Title: findByOrdersId
     * @Description: 根据总单id查询订单详情
     * @param orderId
     * @return    
     * @return Orderdetail    返回类型
     * @author xiejin
     */
    Orderdetail findByOrderId(String orderId);
    
    /**
     * @Title: updateOrderDetailPart
     * @Description: 付款完成后修改订单详情
     * @param record
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
    int updateOrderDetailPart(Orderdetail record);
    /**
     * 查询该产品在订单中的记录数
     * @author Sevens
     * 时间2015-8-18
     * @param productId
     * @return
     */
    int selectCountByProductId(String productId);
    
    /**
     * 
     * @param ordersId
     * @return
     */
    Orderdetail findWithTourdateByOrdersId(String ordersId);
}