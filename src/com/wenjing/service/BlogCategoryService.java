package com.wenjing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.BlogCategoryMapper;
import com.wenjing.entity.BlogCategory;

/**
 * Service - 博客
 * 
 * @author Jared
 *
 */
@Service
public class BlogCategoryService {
	
	
	@Autowired
	private BlogCategoryMapper blogCategoryMapper;
	
	/**
	 * 查出所有的博客类型
	 * 
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<BlogCategory> findAll(){
		return blogCategoryMapper.findAll();
	}

	/**
	 * 根据id查询出博客类型
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public BlogCategory findById(String id){
		return blogCategoryMapper.selectWithPageById(id);
	}
	
	/**
	 * 保存博客类型
	 */
	@Transactional
	public void save(BlogCategory blogCategory){
		blogCategoryMapper.insertSelective(blogCategory);
	}
	
	/**
	 * 更新博客类型
	 */
	@Transactional
	public void update(BlogCategory blogCategory){
		blogCategory.setIsCreate(0);
		blogCategoryMapper.updateByPrimaryKeySelective(blogCategory);
	}
	
	/**
	 * 删除博客类型
	 * 
	 * @param id
	 */
	@Transactional
	public void deleteById(String id){
		blogCategoryMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 博客种类变动博客数量
	 * 
	 * @param id
	 */
	@Transactional(readOnly=true)
	public void changeBlogNumberById(Integer changedValue,String id){
		blogCategoryMapper.changeBlogNumberById(changedValue,id);
	}
	
	/**
	 * 查询所有的博客种类信息
	 * 
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<BlogCategory> findAllShowCategory(){
		return blogCategoryMapper.findAllShowCategory();
	}
	
	/**
	 * 更新博客类型的生成状态
	 * 
	 * @param id
	 * @param isCreate
	 */
	@Transactional
	public void updateCreate(String id){
		blogCategoryMapper.updateIsCreateById(1,id);
	}
}
