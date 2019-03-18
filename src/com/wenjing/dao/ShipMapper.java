package com.wenjing.dao;

import com.wenjing.entity.Ship;

public interface ShipMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    int insert(Ship record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    int insertSelective(Ship record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    Ship selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    int updateByPrimaryKeySelective(Ship record);

    /**
     * 根据主键修改，空值条件会修改成null,支持大字段类型
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    int updateByPrimaryKeyWithBLOBs(Ship record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-09-06 16:25:59
     */
    int updateByPrimaryKey(Ship record);
}