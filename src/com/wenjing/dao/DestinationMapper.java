package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Destination;

public interface DestinationMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    int insert(Destination record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    int insertSelective(Destination record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    Destination selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    int updateByPrimaryKeySelective(Destination record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:30
     */
    int updateByPrimaryKey(Destination record);
    
    /**
     * 根据costnumber查询所有destination
     * @param costnumber
     * @return
     * xiejin
     */
    List<Destination> findAllByCostNumber();
    
    /**
     * 根据costnumber查询除城市外的destination 
     * @param costnumber
     * @return
     * xiejin
     */
    List<Destination> findByCostNumber();
    
    /**
     * 根据costnumber查询城市 
     * @param costnumber
     * @return
     * xiejin
     */
    List<Destination> findCityByCostNumber();
    
    /**
     * 根据costnumber查询最大sort值 
     * @return
     * xiejin
     */
    int getMaxSort();
    
    /**
     * @Title: findCityAndProvinceByCostNumber
     * @Description: 查询所有州和城市
     * @param costnumber
     * @return    
     * @return List<Destination>    返回类型
     * @author xiejin
     */
    List<Destination> findCityAndProvinceByCostNumber();
    
    /**
     * @Title: findByNameNamacn
     * @Description: 根据中文名或者英文名查询目的地
     * @param name
     * @param costnumber
     * @return    
     * @return List<Destination>    返回类型
     * @author xiejin
     */
    List<Destination> findByNameNamacn(String name);
    
    /**
     * @author Sevens
     * 时间2015-6-21
     * @param costnumber
     * @return
     */
    int getSDestionCount(@Param("search")String search);
    /**
     * @author Sevens
     * 时间2015-7-8
     * @param startPos
     * @param pageSize
     * @param costnumber
     * @param search
     * @return
     */
    List<Destination> selectAllByPage(@Param(value="startPos") Integer startPos,
			@Param(value="pageSize") Integer pageSize,@Param("search")String search);
    /**
     * @author Sevens
     * 时间2015-8-14
     * @param desIds
     * @return
     */
    List<Destination> selectWithTourline(List<String> desIds);
    
    /**
     * @Title: updateNamepy
     * @Description: 修改拼音
     * @param id
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
    int updateNamepy(Destination destination);
    
    /**
     * @Title: findNamecnAndNamepy
     * @Description: 根据id查询中文名字和拼音
     * @param id
     * @return    
     * @return Destination    返回类型
     * @author xiejin
     */
    Destination findNamecnAndNamepy(String id);
    
    /**
     * @Title: findRegionShow
     * @Description: 查询二级页面显示的游玩地区所有目的地
     * @return    
     * @return List<Attraction>    返回类型
     * @author xiejin
     */
    List<Destination> findRegionShow(@Param("regionid")String regionid,
    		@Param("costnumber")String costnumber ,
    		@Param("time")int time);
    
    /**
     * @Title: findRegionShow
     * @Description: 查询二级页面显示的游玩地区所有目的地
     * @return    
     * @return List<Attraction>    返回类型
     * @author xiejin
     */
    List<Destination> searchRegionShow(@Param("keyword")String keyword,
    		@Param("costnumber")String costnumber ,
    		@Param("time")int time);
    
    /**
     * @Title: seachShowDestination
     * @Description: 查询首页显示的目的地
     * @param costnumber
     * @param showType
     * @return    
     * @return List<Destination>    返回类型
     * @author xiejin
     */
    List<Destination> searchShowDestination(@Param("costnumber")String costnumber,
    		@Param("showType")Integer showType);
    
    /**
     * @Title: findDesNav
     * @Description: 查询目的地导航
     * @param costnumber
     * @return    
     * @return List<Destination>    返回类型
     * @author xiejin
     */
    List<Destination> findDesNav(String costnumber);
    
    List<Destination> findWithSuoYin();
    
    /**
     * @author Sevens
     * 时间2015-8-14
     * @param desIds
     * @return
     */
    List<Destination> selectByids(List<String> ids);
    
    /**
     * 根据目的地和运营中心查询景点门票和美食
     * 
     * @param ids
     * @return
     */
    List<Destination> selectWithAttractionAndFood(@Param("ids")List<String> ids,@Param("costnumber")String costnumber);
    
    /**
     * 根据目的地查询自选项
     * 
     * @param ids
     * @return
     */
    List<Destination> selectWithSelfPay(@Param("ids")List<String> ids,@Param("selfPayIds")String selfPayIds);

    /**
     *根据线路查询目的地
     * @param tourlineId
     * @return
     */
    List<Destination> selectByTourlineId(String tourlineId);
}