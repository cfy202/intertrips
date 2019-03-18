package com.wenjing.dao;

import java.util.List;

import com.wenjing.entity.Roletree;

public interface RoletreeMapper {
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
    int insert(Roletree record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:32
     */
    int insertSelective(Roletree record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:32
     */
    Roletree selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:32
     */
    int updateByPrimaryKeySelective(Roletree record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:32
     */
    int updateByPrimaryKey(Roletree record);
    
    /**
     * 根据角色iD删除对应的目录
     * @param roleId
     * @return
     */
    int deleteByroleId(String roleId);
    
    
    /**
     * 根据角色ID查询对应的目录
     * @author Sevens
     * @param roleId
     * @return
     * 
     */
    
    List<Roletree> selectByRoleid(String roleId);
    
    /**
     * 批量插入
     * @author Sevens
     * 时间5015-5-18
     * 
     */
    
    int batchAddRoleTree(List<Roletree> roletrees);
}