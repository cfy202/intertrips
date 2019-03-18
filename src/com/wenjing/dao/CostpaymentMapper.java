package com.wenjing.dao;

import java.util.List;

import com.wenjing.entity.Costpayment;

public interface CostpaymentMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:32
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:32
     */
    int insert(Costpayment record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:32
     */
    int insertSelective(Costpayment record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:32
     */
    Costpayment selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:32
     */
    int updateByPrimaryKeySelective(Costpayment record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:32
     */
    int updateByPrimaryKey(Costpayment record);
    
    /**
     * @Title: findByCostnumber
     * @Description: 根据costnumber查询对应支付账户
     * @param costnumber
     * @return    
     * @return List<Costpayment>    返回类型
     * @author xiejin
     */
    List<Costpayment> findByCostnumber(String costnumber);
}