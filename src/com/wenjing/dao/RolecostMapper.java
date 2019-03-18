package com.wenjing.dao;

import java.util.List;

import com.wenjing.entity.Rolecost;

public interface RolecostMapper {
    int deleteByPrimaryKey(String id);

    int insert(Rolecost record);

    int insertSelective(Rolecost record);

    Rolecost selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Rolecost record);

    int updateByPrimaryKey(Rolecost record);
    
    /**
     * 根据角色ID查询运营中心
     * @author Sevens
     * @param roleId
     * @return 
     */
    
   List<Rolecost> selectByRoleid(String roleId);
   /**
    * 根据角色Id删除与角色关联的运营中心
    * @author Sevens
    * 时间2015-5-18
    */
   int deleteByroleId(String roleId);
   
   /**
    * 批量插入
    * @author Sevens
    * 时间5015-5-18
    */
   
   int batchAddRoleCost(List<Rolecost> rolecosts);
}