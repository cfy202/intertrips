package com.wenjing.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.ItineraryimageMapper;
import com.wenjing.entity.Itineraryimage;

/**
 * 类说明		行程图片联系表service
 * @author xiejin
 * @date 2015-5-8
 */
@Service
public class ItineraryImageService {
	
	@Resource
	private ItineraryimageMapper itineraryimageMapper;
	
	/**
	 * 保存景点图片联系信息
	 * @param itineraryimage
	 * @return
	 * xiejin
	 */
	@Transactional
	public int save(Itineraryimage itineraryimage){
		return itineraryimageMapper.insert(itineraryimage);
	}
	
	/**
	 * 根据imageid删除Itineraryimage信息
	 * @param imageId
	 * @param itineraryid
	 * @return
	 * xiejin
	 */
	@Transactional
	public int deleteByImageId(String imageId,String itineraryid){
		return itineraryimageMapper.deleteByImageId(imageId,itineraryid);
	}

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
		return itineraryimageMapper.deleteByImageId2(imageId);
	};
	
    /**
     * @Title: deleteByItineraryId
     * @Description: 根据行程id删除关系表
     * @param itineraryId
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
	@Transactional
    public int deleteByItineraryId(String itineraryId){
		return itineraryimageMapper.deleteByItineraryId(itineraryId);
	};
}
