package com.wenjing.dao;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Itineraryimage;

public interface ItineraryimageMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    int insert(Itineraryimage record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    int insertSelective(Itineraryimage record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    Itineraryimage selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    int updateByPrimaryKeySelective(Itineraryimage record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:34
     */
    int updateByPrimaryKey(Itineraryimage record);
    
    /**
     * 根据imageid和itineraryid删除itineraryImage信息
     * @param imageId
     * @param itineraryid
     * @return
     * xiejin
     */
    int deleteByImageId(@Param("imageId")String imageId,@Param("itineraryid")String itineraryid);

    /**
     * 根据线路Id删除关联的行程图片
     * 时间2015-8-17
     * @author Sevens
     * @param tourlineId
     * @return
     */
    int deleteWithTourlineId(String tourlineId);

    
    /**
     * @Title: deleteByImageId
     * @Description: 根据图片id删除关联关系
     * @param imageId
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
    int deleteByImageId2(String imageId);
    
    /**
     * @Title: deleteByItineraryId
     * @Description: 根据行程id删除关系表
     * @param itineraryId
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
    int deleteByItineraryId(String itineraryId);

}