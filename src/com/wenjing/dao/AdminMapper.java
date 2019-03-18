package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Admin;

public interface AdminMapper {
    int deleteByPrimaryKey(String id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(String id);
    
    Admin findByUsername(String username);
    
    int update(Admin admin);
    
    boolean usernameExists(String username);
    
    List<?> findByIdForRole(String id);
    
    List<Admin> findAll();
    

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    
    Admin selectByRoelId(String id);

	int modifyPassword(@Param("id")String id,@Param("newpassword") String newpassword);
    
}