package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Attraction;
import com.wenjing.entity.Destination;

public interface AttractionMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    int insert(Attraction record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    int insertSelective(Attraction record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    Attraction selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    int updateByPrimaryKeySelective(Attraction record);

    /**
     * 根据主键修改，空值条件会修改成null,支持大字段类型
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    int updateByPrimaryKeyWithBLOBs(Attraction record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    int updateByPrimaryKey(Attraction record);  
    
    /**
     * 根据costnumber查询所有Attraction
     * @return
     * xiejin
     */
    List<Attraction> findAllByCostNumber();
    
    /**
     * 查询最大sort值  
     * @return
     * xiejin
     */
    int getMaxSort();
    
    /**
     * 根据目的地ID查询
     */
    List<Attraction> selectByDestinationid(String destinationId);
    
    /**
     * @Title: findHotAttraction
     * @Description: 根据线路包含景点查询热门景点中文名
     * @return    
     * @return List<Attraction>    返回类型
     * @author xiejin
     */
    List<String> findHotAttraction(@Param("att")List<String> att,
    		@Param("num")Integer num);
    /**
     * @author Sevens
     * 时间2015-6-20
     * @return
     */
    int getSAttractionCount(@Param("search")String search);
    /**
     * @author Sevens
     * 时间2015-6-22
     * @param startPos
     * @param pageSize
     * @return
     */
    List<Attraction> selectAllByPage(@Param(value="startPos") Integer startPos,
			@Param(value="pageSize") Integer pageSize,@Param("search")String search);
    
    /**
     * @Title: updateNamepy
     * @Description: 修改拼音
     * @param id
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
    int updateNamepy(Attraction attraction);
    
    /**
     * @Title: findRegionShow
     * @Description: 查询二级页面显示的游玩地区所有景点
     * @return    
     * @return List<Attraction>    返回类型
     * @author xiejin
     */
    List<Attraction> findRegionShow(@Param("regionid")String region,
    		@Param("costnumber")String costnumber ,
    		@Param("time")int time);
    
    /**
     * @Title: findByNameNamacn
     * @Description: 根据中英文名查询景点
     * @return    
     * @return List<Attraction>    返回类型
     * @author xiejin
     */
    List<Attraction> findByNameNamacn(String name);
    
    /**
     * @Title: replaceByDestinationId
     * @Description: 替换目的地id
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
    int replaceByDestinationId(@Param("destinationId")String destinationId,
    		@Param("replaceId")String replaceId);
    /**
     * @author Sevens
     * 时间2015-8-14
     * @param desIds
     * @return
     */
    List<Attraction> selectByids(List<String> ids);
    
    List<Attraction> selectByDestinationids(@Param("destinationIds")List<String> destinationIds,@Param("tourlineId")String tourlineId);
}
