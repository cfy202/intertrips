package com.wenjing.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.TourlineimageMapper;
@Service
public class TourlineImageService {
	@Resource
	private TourlineimageMapper tourlineimageMapper;
	/**
	 * 根据imageid删除tourlineimage信息
	 * @param imageId
	 * @param attractionid
	 * @return
	 * @author Sevens
	 */
	@Transactional
	public int deleteByImageId(String imageId,String tourlineId){
		return tourlineimageMapper.deleteByImageId(imageId,tourlineId);
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
		return tourlineimageMapper.deleteByImageId2(imageId);
	};
}
