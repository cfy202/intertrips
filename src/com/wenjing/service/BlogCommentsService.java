package com.wenjing.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.Pages;
import com.wenjing.dao.CommentsToBlogMapper;
import com.wenjing.entity.CommentsToBlog;

/**
 * Service - 博客
 * 
 * @author Jared
 *
 */
@Service
public class BlogCommentsService {

	@Autowired
	private CommentsToBlogMapper commentsToBlogMapper;
	
	/**
	 * 查询评论数量
	 * @return
	 */
	@Transactional(readOnly=true)
	public int countCommentsToBlogByCondition(String blogName,String status){
		Integer statusInt = null;
		if(!StringUtils.isEmpty(status)){
			statusInt = Integer.parseInt(status);
		}
		return commentsToBlogMapper.countAllByCondition(blogName,statusInt);
	}
	
	/**
	 * 根据分页信息查询评论信息
	 */
	@Transactional(readOnly=true)
	public List<CommentsToBlog> commentsList(Pages page,String blogName,String status){
		Integer statusInt = null;
		if(!StringUtils.isEmpty(status)){
			statusInt = Integer.parseInt(status);
		}
		return commentsToBlogMapper.findByPage(page.getStartPos(), page.getPageSize(),blogName,statusInt);
	}
	
	/**
	 * 获取博客评论列表
	 * 
	 * @param blogId
	 * @param page
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<CommentsToBlog> findPageByBlogId(String blogId,Pages page){
		return commentsToBlogMapper.findPages(blogId, page.getStartPos(), page.getPageSize());
	}
	
	@Transactional(readOnly=true)
	public CommentsToBlog findAllInformationById(String id){
		return commentsToBlogMapper.findAllInformationById(id);
	}
	
	/**
	 * 根据Id调整是否显示
	 */
	@Transactional
	public void changeIsShow(String id,Integer isShow){
		commentsToBlogMapper.changeIsShow(id, isShow);
	}
	
	/**
	 * 删除评论以及子评论
	 * @param id
	 */
	@Transactional
	public void deleteById(String id){
		List<String> childrenIds = commentsToBlogMapper.findChildrenId(id);
		for(String childId:childrenIds){
			deleteById(childId);
		}
		commentsToBlogMapper.deleteById(id);
	}
	
	/**
	 * 获取评论的总条数
	 * @return
	 */
	@Transactional(readOnly=true)
	public int getTotalNumber(String blogId){
		return commentsToBlogMapper.countAllByBlogId(blogId);
	}
	
	/**
	
	 */
	@Transactional(readOnly=true)
	public int getChildrenTotalNumber(String blogId){
		return commentsToBlogMapper.countChildrenCommentsNumber(blogId);
	}
	
	@Transactional
	public void save(CommentsToBlog commentsToBlog){
		commentsToBlogMapper.insertSelective(commentsToBlog);
	}
	
	//更新评论的是否显示和审核状态
	@Transactional
	public void updateComments(CommentsToBlog commentsToBlog){
		commentsToBlogMapper.updateIsShowAndStatus(commentsToBlog.getId(), commentsToBlog.getIsShow(), commentsToBlog.getStatus());
	}
	
	@Transactional(readOnly=true)
	public int findLevel(String id){
		return commentsToBlogMapper.findLevel(id);
	} 
}
