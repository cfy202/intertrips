package com.wenjing.dao;

import java.util.List;

import com.wenjing.entity.Tourlineselfpay;

public interface TourlineselfpayMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    int insert(Tourlineselfpay record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    int insertSelective(Tourlineselfpay record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    Tourlineselfpay selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    int updateByPrimaryKeySelective(Tourlineselfpay record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    int updateByPrimaryKey(Tourlineselfpay record);
    
    /**
     * @author Sevens
     * @param tourlineId
     * @return
     * 时间2015-5-18
     */
    List<Tourlineselfpay> selectByTourlineid(String tourlineId);
    
    /**
     * @author Sevens
     * @param tourlineId
     * @return
     * 时间2015-5-20
     */
    int deleteByTourlineId(String tourlineId);
}