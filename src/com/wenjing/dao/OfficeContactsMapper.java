package com.wenjing.dao;

import java.util.List;

import com.wenjing.entity.OfficeContacts;


public interface OfficeContactsMapper {
	
	/**
	 * 增加
	 * 
	 * @param record
	 * @return
	 */
    int insert(OfficeContacts record);

    /**
     * 动态增加
     * 
     * @param record
     * @return
     */
    int insertSelective(OfficeContacts record);
    
    /**
     * 根据主键查询出结果
     * @param id
     * @return
     */
    OfficeContacts selectByPrimaryKey(String id);
    
    /**
     * 根据条件查询出结果
     * 
     * @param officeContacts
     * @return
     */
    List<OfficeContacts> selectByConditions(OfficeContacts officeContacts);
    
    /**
     * 根据costnumber加载办公室信息
     * 
     * @param costnumber
     * @return
     */
    List<OfficeContacts> selectByCostnumber(String costnumber);
    
    /**
     * 查询所有
     * @return
     */
    List<OfficeContacts> selectAll();
    
    /**
     * 
     * @param officeContacts
     */
    void updateSelective(OfficeContacts officeContacts); 
    
    /**
     * 
     * @param id
     */
    void deleteByPrimaryKey(String id);
}