package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Orderexpense;

public interface OrderexpenseMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    int insert(Orderexpense record);
    
    /**
     * 批量插入费用信息
     * 
     * @param orderExpenseList
     * @return
     */
    int insertBatch(@Param("orderExpenseList")List<Orderexpense> orderExpenseList);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    int insertSelective(Orderexpense record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    Orderexpense selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    int updateByPrimaryKeySelective(Orderexpense record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    int updateByPrimaryKey(Orderexpense record);
    
    /**
     * 根据订单ID查找所有的费用
     * 
     * @param orderId
     * @return
     */
    List<Orderexpense> findByOrderId(String orderId);
}