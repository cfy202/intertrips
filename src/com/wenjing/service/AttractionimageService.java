package com.wenjing.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.AttractionimageMapper;
import com.wenjing.entity.Attractionimage;

/**
 * 类说明   	景点图片联系表管理
 * @author xiejin
 * @date 2015-4-27
 */
@Service
public class AttractionimageService {
	
	@Resource
	private AttractionimageMapper attractionimageMapper;
	
	/**
	 * 保存景点图片联系信息
	 * @param attractionimage
	 * @return
	 * xiejin
	 */
	@Transactional
	public int save(Attractionimage attractionimage){
		return attractionimageMapper.insert(attractionimage);
	}
	
	/**
	 * 根据imageid删除Attractionimage信息
	 * @param imageId
	 * @param attractionid
	 * @return
	 * xiejin
	 */
	@Transactional
	public int deleteByImageId(String imageId,String attractionid){
		return attractionimageMapper.deleteByImageId(imageId,attractionid);
	}
	
    /**
     * @Title: deleteByAttractionId
     * @Description: 根据景点id删除景点图片关联信息
     * @param attractionId
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
	@Transactional
    public int deleteByAttractionId(String attractionId){
    	return attractionimageMapper.deleteByAttractionId(attractionId);
    };
    
    /**
     * @Title: deleteByImageId
     * @Description: 根据图片id删除关联关系
     * @param imageId
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
    @Transactional
    public int deleteByImageId2(String imageId){
    	return attractionimageMapper.deleteByImageId2(imageId);
    };
}
