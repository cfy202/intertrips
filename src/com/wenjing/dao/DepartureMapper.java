package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Departure;

public interface DepartureMapper {
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
    int insert(Departure record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    int insertSelective(Departure record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    Departure selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    int updateByPrimaryKeySelective(Departure record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    int updateByPrimaryKey(Departure record);

    /**
     * 查询全部自费项目
     * @return
     */
	List<Departure> findAllByCostNumber(List<String> coList);

	/**
	 * 获取当前sort最大值
	 * @return
	 */
	int getMaxSort(String costnumber);

	/**
	 * 根据costnumber查询对应出发地
	 * bowden
	 * @param costnumber
	 * @return
	 */
	List<Departure> findByCostid(String costnumber);
	
    /**
     * 根据tourDateId查询出departure
     * @param tourDateId
     * @return
     */
	List<Departure> findByTourDateId(String tourDateId);

	/**
	 * 查询所有出发地
	 * @return
	 */
	List<Departure> findAll();
	/**
	 * 
	 * @param departureIds
	 * @return
	 */
	List<Departure> findByDepartureIds(@Param("departureIds")String departureIds);
	/**
	 * 
	 * @param productId
	 * @return
	 */
	List<Departure> findByProductId(@Param("productId")String productId);
}
