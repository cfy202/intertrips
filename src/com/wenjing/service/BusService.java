package com.wenjing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.BusMapper;
import com.wenjing.entity.Bus;

/**
 * Service - 车
 * 
 * @author Jared
 *
 */
@Service
public class BusService {
	
	@Autowired
	private BusMapper busMapper;
	
	/**
	 * 查出所有的车辆
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Bus> findAll(){
		return busMapper.findAll();
	}
	
	/**
	 * 根据Id查出车辆
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public Bus findById(String id){
		return busMapper.selectById(id);
	}
	
	/**
	 * 保存车辆
	 */
	@Transactional
	public void save(Bus bus){
		busMapper.insertSelective(bus);
	}
	
	/**
	 * 更新车辆
	 * 
	 * @param blog
	 */
	@Transactional
	public void update(Bus bus){
		busMapper.update(bus);
	}
	
	/**
	 * 根据id删除车辆
	 */
	@Transactional
	public void deleteById(String id){
		busMapper.deleteById(id);
	}
}
