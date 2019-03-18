package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Tourlineattraction;

public interface TourlineattractionMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    int insert(Tourlineattraction record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    int insertSelective(Tourlineattraction record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    Tourlineattraction selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    int updateByPrimaryKeySelective(Tourlineattraction record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    int updateByPrimaryKey(Tourlineattraction record);
    /**
     * @author Sevens
     * @param tourlineId
     * @return
     * 时间2015-5-20
     */
    List<Tourlineattraction> selectByTourlineid(String tourlineId);
    
    /**
     * @author Sevens
     * @param tourlineId
     * @return
     * 时间2015-5-20
     */
    int deleteByTourlineId(String tourlineId);
    
    /**
     * @Title: deleteByAttractionId
     * @Description: 根据景点id删除线路图片关联信息
     * @param attractionId
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
    int deleteByAttractionId(String attractionId);
    
    /**
     * @author Sevens
     * 时间2015-9-28
     * @param attractionId
     * @return
     */
    List<Tourlineattraction> selectByAttractionid(String attractionId);
    
    /**
     * @Title: replaceByAttractionId
     * @Description: 替换景点id
     * @param attractionId
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
    int replaceByAttractionId(@Param("attractionId")String attractionId,
    		@Param("replaceId")String replaceId);
}