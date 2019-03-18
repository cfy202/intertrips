package com.wenjing.dao;

import java.util.List;

import com.wenjing.entity.Tourlinehotel;

public interface TourlinehotelMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    int insert(Tourlinehotel record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    int insertSelective(Tourlinehotel record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    Tourlinehotel selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    int updateByPrimaryKeySelective(Tourlinehotel record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    int updateByPrimaryKey(Tourlinehotel record);
    /**
     * @author Sevens
     * @param tourlineId
     * @return
     * 时间2015-5-20
     */
    List<Tourlinehotel> selectByTourlineid(String tourlineId);
    
    /**
     * @author Sevens
     * @param tourlineId
     * @return
     * 时间2015-5-20
     */
    int deleteByTourlineId(String tourlineId);
    
    /**
     * @Title: deleteByHotelId
     * @Description: 根据酒店id删除线路酒店关联信息
     * @param hotelId
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
    int deleteByHotelId(String hotelId);
}