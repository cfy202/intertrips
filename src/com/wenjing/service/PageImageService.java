
package com.wenjing.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.PageImageMapper;
import com.wenjing.entity.PageImage;

/**类说明		pageImage表管理service层
 * @author xiejin
 * @date 2015-5-6 
 */
@Service
public class PageImageService {
	
	@Resource
	private PageImageMapper pageImageMapper;
	
	/**
	 * 添加pageImage表
	 * @param pageImage
	 * @return
	 * xiejin
	 */
	@Transactional
	public int save(PageImage pageImage){
		return pageImageMapper.insert(pageImage);
		
	}
	
	/**
	 * 根据imageid和pageid删除pageimage信息
	 * @param imageId
	 * @param pageid
	 * @return
	 * xiejin
	 */
	@Transactional
	public int deleteByImageId(String imageId,String pageid){
		return pageImageMapper.deleteByImageId(imageId,pageid);
	}
		
    /**
     * @Title: deleteByPageId
     * @Description: 根据pageId删除单页面图片关联信息
     * @param pageId
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
	@Transactional
    public int deleteByPageId(String pageId){
		return pageImageMapper.deleteByPageId(pageId);
	};
    
    /**
     * @Title: deleteByImageId2
     * @Description: 根据图片id删除关联关系
     * @param imageId
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
	@Transactional
    public int deleteByImageId2(String imageId){
    	return pageImageMapper.deleteByImageId2(imageId);
    };
}
