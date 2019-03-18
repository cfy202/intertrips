/**
 * 
 */
package com.wenjing.dao;

import java.util.List;

import com.wenjing.entity.AttractionLevel;


/**
 * 类说明
 * @author xiejin
 * @date 2015-9-25 
 * @date 2015-9-25 下午2:13:19
 */
public interface AttractionLevelMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    int insert(AttractionLevel record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    int insertSelective(AttractionLevel record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    AttractionLevel selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    int updateByPrimaryKeySelective(AttractionLevel record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    int updateByPrimaryKey(AttractionLevel record);
    
    /**
     * 查询所有景点类型
     * @return
     * xiejin
     */
    List<AttractionLevel> findAll();
    
}
