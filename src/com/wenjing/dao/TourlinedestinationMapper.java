package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Tourlinedestination;

public interface TourlinedestinationMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:32
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:32
     */
    int insert(Tourlinedestination record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:32
     */
    int insertSelective(Tourlinedestination record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:32
     */
    Tourlinedestination selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:32
     */
    int updateByPrimaryKeySelective(Tourlinedestination record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:32
     */
    int updateByPrimaryKey(Tourlinedestination record);
    
    /**
     * @author Sevens
     * 时间2015-5-18
     * 根据线路ID查询目的地
     * 
     */
    List<Tourlinedestination> selectByTourlineid(String tourLineId);
    
    /**
     * @author Sevens
     * @param tourlineId
     * @return
     * 时间2015-5-20
     */
    int deleteByTourlineId(String tourlineId);
    
    /**
     * @Title: deleteByDestinationId
     * @Description: 根据destinationId删除关系表
     * @param destinationId
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
    int deleteByDestinationId(String destinationId);
    
    /**
     * @author Sevens
     * 时间2015-9-28
     * @param destinationId
     * @return
     */
    List<Tourlinedestination> selectByDestinationid(String destinationId);
    
    /**
     * @Title: replaceByDestinationId
     * @Description: 替换目的地id
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
    int replaceByDestinationId(@Param("destinationId")String destinationId,
    		@Param("replaceId")String replaceId);
}