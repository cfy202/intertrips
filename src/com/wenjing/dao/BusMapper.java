package com.wenjing.dao;

import java.util.List;

import com.wenjing.entity.Bus;

public interface BusMapper {

	/**
	 * 新增bus
	 * 
	 * @param record
	 * @return
	 */
    int insert(Bus record);

    /**
     * 
     * @param record
     * @return
     */
    int insertSelective(Bus record);
    
    /**
     * 查出所有的bus
     * @return
     */
    List<Bus> findAll();
    
    /**
     * 根据Id查出bus
     * @param id
     * @return
     */
    Bus selectById(String id);
   
    /**
     * 根据id删除bus
     * @param id
     */
    void deleteById(String id);
    
    /**
     * 更新bus
     * @param bus
     * @return
     */
    int update(Bus bus);
}