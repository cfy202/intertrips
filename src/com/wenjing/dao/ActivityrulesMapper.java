package com.wenjing.dao;


import java.util.List;

import com.wenjing.entity.Activityrules;

public interface ActivityrulesMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-10-21 12:02:59
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-10-21 12:02:59
     */
    int insert(Activityrules record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-10-21 12:02:59
     */
    int insertSelective(Activityrules record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-10-21 12:02:59
     */
    Activityrules selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-10-21 12:02:59
     */
    int updateByPrimaryKeySelective(Activityrules record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-10-21 12:02:59
     */
    int updateByPrimaryKey(Activityrules record);
    
    /**
     * 根据优惠券Id查询活动规则
     * @param couponseid
     * @return
     */
    List<Activityrules> findByCouponseId(String couponseid);
}