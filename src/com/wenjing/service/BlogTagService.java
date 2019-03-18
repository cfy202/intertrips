package com.wenjing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.BlogTagMapper;
import com.wenjing.entity.BlogTag;

/**
 * Service - 博客
 * 
 * @author Jared
 *
 */
@Service
public class BlogTagService {
	
	@Autowired
	private BlogTagMapper blogTagMapper;
	
	
	/**
	 * 查出所有的博客
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<BlogTag> findAll(){
		return blogTagMapper.findAll();
	}
	
	/**
	 * 根据Id查出博客
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public BlogTag findById(String id){
		return blogTagMapper.selectWithPageById(id);
	}
	
	/**
	 * 保存博客
	 */
	@Transactional
	public void save(BlogTag blogTag){
		blogTagMapper.insert(blogTag);
	}
	
	/**
	 * 更新博客
	 * 
	 * @param blog
	 */
	@Transactional
	public void update(BlogTag blogTag){
		blogTag.setIsCreate(0);
		blogTagMapper.updateByPrimaryKeySelective(blogTag);
	}
	
	/**
	 * 根据id删除博客
	 */
	@Transactional
	public void deleteById(String id){
		blogTagMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 查询所有显示的标签
	 * 
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<BlogTag> findAllShowBlogTag(){
		return blogTagMapper.findAllShow();
	}
	
	/**
	 * 更改标签的生成状态
	 * 
	 * @param id
	 */
	@Transactional
	public void changeIsCreate(String id){
		blogTagMapper.updateIsCreateById(1,id);
	}
}
