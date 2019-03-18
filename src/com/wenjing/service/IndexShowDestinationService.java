/**
 * 
 */
package com.wenjing.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.IndexShowDestinationMapper;
import com.wenjing.entity.IndexShowDestination;

/**
 * 类说明
 * @author xiejin
 * @date 2015-11-17 
 * @date 2015-11-17 下午3:28:08
 */
@Service
public class IndexShowDestinationService {
	
	@Resource
	private IndexShowDestinationMapper indexShowDestinationMapper;
	
    /**
     * @Title: deleteByCondition
     * @Description: 删除关系表
     * @param costNumber
     * @param showType
     * @param destinationId
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
	@Transactional(readOnly=true)
    public int deleteByCondition(String costNumber,Integer showType,String destinationId){
    	return indexShowDestinationMapper.deleteByCondition(costNumber, showType, destinationId);
    }
    
    /**
     * @Title: Insert
     * @Description: 插入记录
     * @param indexShowDestination
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
	@Transactional
    public int insert(IndexShowDestination indexShowDestination){
    	return indexShowDestinationMapper.insert(indexShowDestination);
    }
}
