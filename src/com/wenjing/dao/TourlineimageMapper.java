package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Tourlineimage;

public interface TourlineimageMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    int insert(Tourlineimage record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    int insertSelective(Tourlineimage record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    Tourlineimage selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    int updateByPrimaryKeySelective(Tourlineimage record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    int updateByPrimaryKey(Tourlineimage record);
    /**
     * @author Sevens
     * @param tourLineId
     * @return
     * 时间2015-5-18
     */
    List<Tourlineimage> selectByTourlineid(String tourLineId);
    /**
     * @author Sevens
     * @param tourlineId
     * @return
     * 时间2015-5-20
     */
    int deleteByTourlineId(String tourlineId);
    
    int deleteByImageId(@Param("imageId")String imageId,@Param("tourlineId")String tourlineId);
    
    /**
     * @Title: deleteByImageId
     * @Description: 根据图片id删除关联关系
     * @param imageId
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
    int deleteByImageId2(String imageId);
}