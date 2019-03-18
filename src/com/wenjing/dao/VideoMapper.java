package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Tag;
import com.wenjing.entity.Video;

public interface VideoMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-10-12 11:38:18
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-10-12 11:38:18
     */
    int insert(Video record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-10-12 11:38:18
     */
    int insertSelective(Video record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-10-12 11:38:18
     */
    Video selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-10-12 11:38:18
     */
    int updateByPrimaryKeySelective(Video record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-10-12 11:38:18
     */
    int updateByPrimaryKey(Video record);
    /**
     * 查询所有产品标签
     * @author Sevens
     * 时间2015-9-7
     * @return
     */
    List<Video> findAll(@Param("costnumber")String costnumber,@Param("type")Integer type);
    
    /**
     * @Title: findRegionShow
     * @Description: 查询线路标签名字
     * @param region
     * @param costnumber
     * @param time
     * @return    
     * @return List<Tag>    返回类型
     * @author xiejin
     */
    List<Video> findRegionShow(@Param("regionid")String region,
    		@Param("costnumber")String costnumber,
    		@Param("time")int time);
    
    /**
     * @Title: findByIdCostnumber
     * @Description: 查询线路相关标签对象集合
     * @param productId
     * @param costnumber
     * @return    
     * @return List<Tag>    返回类型
     * @author xiejin
     */
    List<Video> findByIdCostnumber(@Param("productId")String productId,
    		@Param("costnumber")String costnumber);
}