
package com.wenjing.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.ProductvideoMapper;
import com.wenjing.dao.VideoMapper;
import com.wenjing.entity.Video;
import com.wenjing.util.Tools;

/**
 * 产品标签后台管理
 * @author sevens
 *
 */
@Service
public class VideoService {

	
	@Resource
	private VideoMapper videoMapper;
	@Resource
	private ProductvideoMapper productvideoMapper;
	
	/**
	 * 查询所有产品标签 
	 * 
	 * @return
	 * sevens
	 */
	@Transactional(readOnly=true)
	public List<Video> findAll(String costnumber,Integer type){
		return videoMapper.findAll(costnumber,type);
	}
	
	/**
	 * 根据id删除video
	 * @param id
	 * @return
	 */
	@Transactional
	public boolean delete(String id) {
		boolean flag = false;
		int tag = productvideoMapper.findByTagIdCount(id);
		if(tag == 0){
			videoMapper.deleteByPrimaryKey(id);
			flag = true;
		}
		return flag;
	}

	/**
	 * 根据id查询video
	 * @param id
	 * @return
	 */
	@Transactional
	public Video findById(String id) {
		return videoMapper.selectByPrimaryKey(id);
	}

	/**
	 * 保存video
	 * @param video
	 * @return
	 */
	@Transactional
	public int save(Video video) {
		return videoMapper.insert(video);
	}
	
	/**
	 * 修改video
	 * @param video
	 * @return
	 * xiejin
	 */
	@Transactional
	public int update(Video video) {
		return videoMapper.updateByPrimaryKeySelective(video);
	}
	
    /**
     * @Title: findRegionShow
     * @Description: 查询线路标签
     * @param region
     * @param costnumber
     * @param time
     * @return    
     * @return List<Tag>    返回类型
     * @author xiejin
     */
	@Transactional(readOnly=true)
    public List<Video> findRegionShow(String region,String costnumber){
    	int time = Tools.getDtimestemp(Tools.getDtime()); //当前时间戳
    	return videoMapper.findRegionShow(region, costnumber, time);
	};
	
    /**
     * @Title: findByIdCostnumber
     * @Description: 查询线路相关标签对象集合
     * @param productId
     * @param costnumber
     * @return    
     * @return List<Tag>    返回类型
     * @author sevens
     */
	@Transactional(readOnly=true)
    public List<Video> findByIdCostnumber(String productId ,String costnumber){
		return videoMapper.findByIdCostnumber(productId, costnumber);
	};
}
