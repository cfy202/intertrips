package com.wenjing.dao;

import java.util.List;

import com.wenjing.entity.ShipLine;

public interface ShipLineMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-09-06 16:26:00
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-09-06 16:26:00
     */
    int insert(ShipLine record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-09-06 16:26:00
     */
    int insertSelective(ShipLine record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-09-06 16:26:00
     */
    ShipLine selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-09-06 16:26:00
     */
    int updateByPrimaryKeySelective(ShipLine record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-09-06 16:26:00
     */
    int updateByPrimaryKey(ShipLine record);
    
    /**
     * @author Sevens
     * 时间2015-9-6
     * @return
     */
    List<ShipLine> findAll();
   
}