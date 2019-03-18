package com.wenjing.dao;

import java.util.List;

import com.wenjing.entity.Attractionimage;
import com.wenjing.entity.Selfpaycurrency;

public interface SelfpaycurrencyMapper {
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
     * 批量插入数据
     * @param selfpaycurrencies
     * @return
     */
	int batchAdd(List<Selfpaycurrency> selfpaycurrencies);

	/**
	 * 根据selfpayid查询价格
	 * @param id
	 * @return
	 */
	List<Selfpaycurrency> findPriceBySelfpayId(String selfpayid);

	/**
	 * 根据selfpayid删除
	 * @param id
	 * @return
	 */
	int deleteBySelfpayId(String selfpayid);
}