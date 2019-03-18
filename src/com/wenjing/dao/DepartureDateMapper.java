package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.DepartureDate;

public interface DepartureDateMapper {
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
    int insert(DepartureDate record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    int insertSelective(DepartureDate record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    DepartureDate selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    int updateByPrimaryKeySelective(DepartureDate record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    int updateByPrimaryKey(DepartureDate record);
    
    /**
	 * 根据tourdateid查询
	 * @param id
	 * @return
	 */
	List<DepartureDate> findByTourDateId(String tourdateid);

	/**
	 * 根据tourdateid删除
	 * @param tourdateid
	 * @return
	 */
	int deletebytourdateid(String tourdateid);

	/**
	 * 批量插入
	 * @param departureDateList
	 */
	void batchAdd(List<DepartureDate> departureDateList);
	/**
	 * 根据线路的Id删除出发地和出发日关系数据
	 * @author Sevens
	 * 时间2015-8-17
	 * @param tourlineId
	 * @return
	 */
	int deleteWithProductid(String productId);

	/**
	 * @Title findByDepartureId
	 * @Description 根据出发地ID查询出发地和出发日期关联表
	 * @Author Bowden
	 * @CreateDate 2015-10-9 下午2:37:52
	 */
	int findByDepartureId(String id);

	/**
	 * @Title replaceDeparture
	 * @Description 替换出发地id
	 * @Author Bowden
	 * @CreateDate 2015-10-23 下午4:18:24
	 */
	int replaceDeparture(@Param("departureId")String departureId, @Param("replaceId")String replaceId);
}