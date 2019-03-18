package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Attractionimage;

public interface AttractionimageMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:25
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:25
     */
    int insert(Attractionimage record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:25
     */
    int insertSelective(Attractionimage record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:25
     */
    Attractionimage selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:25
     */
    int updateByPrimaryKeySelective(Attractionimage record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:25
     */
    int updateByPrimaryKey(Attractionimage record);
    
    /**
     * 根据imageid和attractionid删除Attractionimage信息
     * @param imageId
     * @param attractionid
     * @return
     * xiejin
     */
    int deleteByImageId(@Param("imageId")String imageId,@Param("attractionid")String attractionid);
    
    /**
     * @Title: findByAttractionId
     * @Description: 根据attractionId查询所有Attractionimage,带分页
     * @param attractionId
     * @return    
     * @return List<Attractionimage>    返回类型
     * @author xiejin
     */
    List<Attractionimage> findByAttractionId(
    		@Param("attractionId") List<String> attractionId,
    		@Param(value="startPos") Integer startPos,
			@Param(value="pageSize") Integer pageSize);
    
    /**
     * @Title: getTotalCount
     * @Description: 根据attractionId查询所有Attractionimage数量
     * @param attractionId
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
    int getTotalCount(@Param("attractionId") List<String> attractionId);
    
    /**
     * @Title: deleteByAttractionId
     * @Description: 根据景点id删除景点图片关联信息
     * @param attractionId
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
    int deleteByAttractionId(String attractionId);
    
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
     * @Title: selectByAttractionid
     * @Description: 根据景点id查询
     * @param attractionId
     * @return    
     * @return List<Attractionimage>    返回类型
     * @author xiejin
     */
    List<Attractionimage> selectByAttractionid(String attractionId);
    
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