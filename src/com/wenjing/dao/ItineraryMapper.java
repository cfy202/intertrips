package com.wenjing.dao;

import java.util.List;

import com.wenjing.entity.Itinerary;

public interface ItineraryMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:24
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:24
     */
    int insert(Itinerary record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:24
     */
    int insertSelective(Itinerary record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:24
     */
    Itinerary selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:24
     */
    int updateByPrimaryKeySelective(Itinerary record);

    /**
     * 根据主键修改，空值条件会修改成null,支持大字段类型
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:24
     */
    int updateByPrimaryKeyWithBLOBs(Itinerary record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:24
     */
    int updateByPrimaryKey(Itinerary record);
    
    /**
     * 根据tourlineiId查询所有行程
     * @return
     * xiejin
     */
    List<Itinerary>  findByTourlineId(String tourlineid);
    
    /**
     * 
     * @param tourlineId
     * @return
     */
    List<Itinerary> findBaseByTourlineId(String tourlineId);
    
    /**
     * 根据tourlineid查询最大day
     * @param tourlineid
     * @return
     * xiejin
     */
    int getMaxDay(String tourlineid);

    /**
     * 根据线路ID删除行程
     * @author Sevens
     * 时间2015-8-17
     * @param tourlineId
     * @return
     */
    int deleteWithTourlineId(String tourlineId);
}