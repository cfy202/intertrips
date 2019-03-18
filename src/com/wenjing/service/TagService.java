
package com.wenjing.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.ProducttagMapper;
import com.wenjing.dao.TagMapper;
import com.wenjing.entity.Tag;
import com.wenjing.util.Tools;

/**
 * 产品标签后台管理
 * @author sevens
 *
 */
@Service
public class TagService {

	
	@Resource
	private TagMapper tagMapper;
	@Resource
	private ProducttagMapper producttagMapper;
	
	/**
	 * 查询所有产品标签 
	 * 
	 * @return
	 * sevens
	 */
	@Transactional(readOnly=true)
	public List<Tag> findAll(String costnumber,Integer type){
		return tagMapper.findAll(costnumber,type);
	}
	
	/**
	 * 根据id删除tag
	 * @param id
	 * @return
	 */
	@Transactional
	public boolean delete(String id) {
		boolean flag = false;
		int tag = producttagMapper.findByTagIdCount(id);
		if(tag == 0){
			tagMapper.deleteByPrimaryKey(id);
			flag = true;
		}
		return flag;
	}

	/**
	 * 根据id查询tag
	 * @param id
	 * @return
	 */
	@Transactional
	public Tag findById(String id) {
		return tagMapper.selectByPrimaryKey(id);
	}

	/**
	 * 保存tag
	 * @param Tag
	 * @return
	 */
	@Transactional
	public int save(Tag tag) {
		return tagMapper.insert(tag);
	}
	
	/**
	 * 修改tag
	 * @param Tag
	 * @return
	 * xiejin
	 */
	@Transactional
	public int update(Tag tag) {
		return tagMapper.updateByPrimaryKeySelective(tag);
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
    public List<Tag> findRegionShow(String region,String costnumber){
    	int time = Tools.getDtimestemp(Tools.getDtime()); //当前时间戳
    	return tagMapper.findRegionShow(region, costnumber, time);
	};
	
    /**
     * @Title: findByIdCostnumber
     * @Description: 查询线路相关标签对象集合
     * @param productId
     * @param costnumber
     * @return    
     * @return List<Tag>    返回类型
     * @author xiejin
     */
	@Transactional(readOnly=true)
    public List<Tag> findByIdCostnumber(String productId ,String costnumber){
		return tagMapper.findByIdCostnumber(productId, costnumber);
	};
}
