package com.wenjing.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.Pages;
import com.wenjing.dao.BlogCategoryMapper;
import com.wenjing.dao.BlogMapper;
import com.wenjing.dao.BlogTagAssociationMapper;
import com.wenjing.dao.BlogTagMapper;
import com.wenjing.entity.Blog;
import com.wenjing.entity.BlogTagAssociation;
import com.wenjing.util.Tools;

/**
 * Service - 博客
 * 
 * @author Jared
 *
 */
@Service
public class BlogService {
	
	@Autowired
	private BlogMapper blogMapper;
	
	@Autowired
	private BlogTagMapper blogTagMapper;
	
	@Autowired
	private BlogCategoryMapper blogCategoryMapper;
	
	@Autowired
	private BlogTagAssociationMapper blogTagAssociationMapper;

	/**
	 * 查出所有的博客
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Blog> findAllByPage(Pages page){
		return blogMapper.findAllByPage(page.getStartPos(), page.getPageSize());
	}
	
	/**
	 * 获取最新发布的5条博客
	 * 
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Blog> findRecentBlog(){
		return blogMapper.findRecentBlog();
	}
	
	/**
	 * 根据发布者Id查询最近的Blog
	 * 
	 * @param adminId
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Blog> findRecentBlogByAdminId(String adminId){
		return blogMapper.findRecentBlogByAdminId(adminId);
	}
	
	/**
	 * 根据分页查询博客信息
	 * 
	 * @param page
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Blog> findRecentBlogByPage(Pages page,String searchTitle){
		return blogMapper.findRecentBlogByPage(page.getStartPos(),page.getPageSize(),searchTitle);
	}

	/**
	 * 
	 * @param categoryId
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Blog> findRecentBlogByCategory(String categoryId){
		return blogMapper.findRecentBlogByCategoryId(categoryId);
	}
	
	/**
	 * 根据标签ID查询最近发布的博客
	 * 
	 * @return
	 */
	public List<Blog> findRecentBlogByTag(String bagTagId){
		return blogMapper.findRecentBlogByTagId(bagTagId);
	}
	
	/**
	 * 根据分页信息查询带标签的博客信息
	 * 
	 * @param page
	 * @param blogTagId
	 * @param searchTitle
	 * @return
	 */
	public List<Blog> findRecentBlogWithTagByPage(Pages page,String blogTagId,String searchTitle){
		return blogMapper.findRecentBlogWithTagByPage(page.getStartPos(),page.getPageSize(),blogTagId,searchTitle);
	}
	
	/**
	 * 根据分页和博客分类信息查询博客
	 * 
	 * @param page
	 * @param blogCategoryId
	 * @param searchTitle
	 * @return
	 */
	public List<Blog> findRecentBlogWithCategoryByPage(Pages page,String blogCategoryId,String searchTitle){
		return blogMapper.findRecentBlogWithCategoryByPage(page.getStartPos(),page.getPageSize(), blogCategoryId, searchTitle);
	}
	
	/**
	 * 根据内容检索博客的标题查询总数
	 * @return
	 */
	@Transactional(readOnly=true)
	public int findTotalNumber(String seachTitle){
		return blogMapper.countAll(seachTitle);
	}
	
	/**
	 * 根据标签ID和博客标题查询总数
	 */
	@Transactional(readOnly=true)
    public int findTotalNumberByTagId(String blogTagId,String seachTitle){
		return blogMapper.countAllByTagId(blogTagId, seachTitle);
    }
	
	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly=true)
	public int findTotalNumberByCategoryId(String categoryId,String searchTitle){
		return blogMapper.countAllByCategoryId(categoryId, searchTitle);
	}
	
	/**
	 * 查出所有博客的数目
	 * 
	 * @return
	 */
	@Transactional(readOnly=true)
	public int countAll(){
		return blogMapper.countAll(null);
	}
	
	/**
	 * 根据Id查出博客
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public Blog findById(String id){
		return blogMapper.selectWithPageById(id);
	}
	
	/**
	 * 显示blog的详情
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public Blog showBlogDetail(String id){
		return blogMapper.selectAllById(id);
	}
	
	/**
	 * 保存博客
	 */
	@Transactional
	public void save(Blog blog){
		blogMapper.insertSelective(blog);
	}
	
	/**
	 * 更新博客
	 * 
	 * @param blog
	 */
	@Transactional
	public void update(Blog blog){
		blog.setIsCreate(0);
		blogMapper.updateByPrimaryKeySelective(blog);
	}
	
	/**
	 * 根据id删除博客
	 */
	@Transactional
	public void deleteById(String id){
		blogMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 给博客添加标记
	 * 
	 * @param blogId
	 * @param blogTagId
	 */
	@Transactional
	public void addTag(String[] blogIds,List<String> blogTagIds){
		List<BlogTagAssociation> blogTagAssociationList = new ArrayList<BlogTagAssociation>();
		BlogTagAssociation blogTagAssociation = null;
		for(String blogId:blogIds){
			for(String blogTagId:blogTagIds){
				blogTagAssociation = new BlogTagAssociation();
				blogTagAssociation.setId(Tools.getUUID());
				blogTagAssociation.setBlogId(blogId);
				blogTagAssociation.setTagId(blogTagId);
				blogTagAssociationList.add(blogTagAssociation);
			}
		}
		blogTagAssociationMapper.insertBatch(blogTagAssociationList);
	}
	
	/**
	 * 根据博客ID删除博客和标签的关联表
	 * 
	 * @param blogIds
	 */
	public void deleteAssociationByBlogIds(String[] blogIds){
		blogTagAssociationMapper.deleteByBlogIds(blogIds);
	}
	
	/**
	 * 设置blog页面已生成
	 */
	public void updateCreate(String id){
		blogMapper.updateIsCreateById(1, id);
	}
	
	/**
	 * 更新blog的评论数
	 */
	public void updateCommmentsNumber(String commentsId){
		blogMapper.updateCommentsNumber(commentsId);
	}
}
