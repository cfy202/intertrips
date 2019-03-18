package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;

import com.wenjing.entity.Selfpay;

public interface SelfpayMapper {
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
    int insert(Selfpay record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    int insertSelective(Selfpay record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:29findWithType
     */
    Selfpay selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    int updateByPrimaryKeySelective(Selfpay record);

    /**
     * 根据主键修改，空值条件会修改成null,支持大字段类型
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    int updateByPrimaryKeyWithBLOBs(Selfpay record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    int updateByPrimaryKey(Selfpay record);

    /**
     * 查询全部自费项目
     * @return
     */
	List<Selfpay> findAllByCostNumber(List<String> costnumlist);

	 /**
     * 查询自费项目
     * @return
     * @author Sevens
     */
	List<Selfpay> findByCostNumber(String costnumber);
	/**
	 * 获取当前sort最大值
	 * @return
	 */
	int getMaxSort(String costnumber);

	List<Selfpay> findAll();
	/**
	 * @author Sevens
	 * 时间2015-8-24
	 * @param names
	 * @return
	 */
	List<Selfpay> findWithD(List<String> names);
	
	List<Selfpay> findWithItintarySids(String itintarySids);
	
	/**
	 * 根据类型获取对应的自费项
	 * @param type
	 * @return
	 */
	List<Selfpay> findWithType(@Param("type")Integer type,@Param("costnumber")String costnumber);
	
	
	List<Selfpay>  selectByDestinationAndSelfPayIds(@Param("destinationId")String destinationId,@Param("sIds")List<String> sIds);
	
	List<Selfpay> selectByTourlineId(String tourlineId);
}