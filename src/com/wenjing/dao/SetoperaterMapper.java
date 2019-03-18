package com.wenjing.dao;

import java.util.List;

import com.wenjing.entity.Setoperater;

public interface SetoperaterMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:21
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:21
     */
    int insert(Setoperater record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:21
     */
    int insertSelective(Setoperater record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:21
     */
    Setoperater selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:21
     */
    int updateByPrimaryKeySelective(Setoperater record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:21
     */
    int updateByPrimaryKey(Setoperater record);
    
    /**
     * 查询所有操作
     * @return
     */
    List<Setoperater> findAll();
    
    /**
     * 查询总记录数用于排序
     * @return
     */
    int getMaxSort();
}