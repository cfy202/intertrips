package com.wenjing.action.front;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenjing.Pages;
import com.wenjing.entity.Blog;
import com.wenjing.entity.CommentsToBlog;
import com.wenjing.entity.Member;
import com.wenjing.service.BlogCommentsService;
import com.wenjing.service.BlogService;
import com.wenjing.service.MemberService;
import com.wenjing.service.PageService;
import com.wenjing.util.FreemarkerUtils;
import com.wenjing.util.Tools;

@Controller("fronBlog")
@RequestMapping("/front/blog")
public class BlogController {
	
	private static final int PAGE_SIZE = 2;
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private BlogCommentsService blogCommentsService;
	@Autowired
	private PageService pageService;
	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/getBlogContent",  produces = "application/json; charset=utf-8")
	public @ResponseBody String getBlogList(HttpServletRequest request) {
		String blogCategoryId = request.getParameter("blogCategoryId");
		String blogTagId = request.getParameter("blogTagId");
		String search = request.getParameter("search");
		String pageNow = request.getParameter("pageNow");
		
		if(StringUtils.isEmpty(search)){
			search = null;
		}
		if(StringUtils.isEmpty(blogCategoryId)){
			blogCategoryId = null;
		}
		if(StringUtils.isEmpty(pageNow)){
			pageNow = "1";
		}
		
		int totalNumber;
		
		//默认的导航
		if(StringUtils.isEmpty(blogTagId) && StringUtils.isEmpty(blogCategoryId)){
			totalNumber = blogService.findTotalNumber(search);
		//博客分类	
		}else if(StringUtils.isEmpty(blogTagId)){
			totalNumber = blogService.findTotalNumberByCategoryId(blogCategoryId, search);
		//博客标签	
		}else{
			totalNumber = blogService.findTotalNumberByTagId(blogTagId, search);
		}
		
		Pages page = new Pages(totalNumber,Integer.parseInt(pageNow));
		page.setPageSize(PAGE_SIZE);
		
		List<Blog> blogList;
		if(StringUtils.isEmpty(blogTagId) && StringUtils.isEmpty(blogCategoryId)){
			blogList = blogService.findRecentBlogByPage(page,search);
		}else if(StringUtils.isEmpty(blogTagId)){
			blogList = blogService.findRecentBlogWithCategoryByPage(page, blogCategoryId, search);
		}else{
			blogList = blogService.findRecentBlogWithTagByPage(page,blogTagId,search);
		}
		
		for(Blog blog:blogList){
			blog.setReleaseTimeString(Tools.getEnglishShow(blog.getReleaseTime()));
		}
		
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("blogList", blogList);
		parameterMap.put("request", request);
		parameterMap.put("page",page);
		String returnHtml = FreemarkerUtils.getBlogContent(request.getSession().getServletContext(),parameterMap);
		return returnHtml;
	}
	
	/**
	 * 根据blogId查询blog的评论
	 * 
	 * @param blogId
	 * @param request
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	@RequestMapping(value="/getBlogComments")
	public @ResponseBody Map<String,String> getBlogComments(String blogId,Integer pageNow,HttpServletRequest request) throws IOException, ClassNotFoundException{
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		Map<String,String> result = new HashMap<String,String>();
		int childrenCommentsNumber = blogCommentsService.getChildrenTotalNumber(blogId);
		int totalNumber = blogCommentsService.getTotalNumber(blogId);
		if(pageNow == null){
			pageNow = new Integer(1);
		}
		Pages page = new Pages(childrenCommentsNumber,pageNow);
		List<CommentsToBlog> commentsList = blogCommentsService.findPageByBlogId(blogId, page);
		for(CommentsToBlog commentsToBlog:commentsList){
			commentsToBlog.setEnglishCreateTime(Tools.getEnglishShow(commentsToBlog.getCreateTime()));
			commentsToBlog = Tools.clearGrandchild(commentsToBlog);
			for(CommentsToBlog commentsToBlo : commentsToBlog.getCommentsToBlogList()){
				commentsToBlo.setEnglishCreateTime(Tools.getEnglishShow(commentsToBlo.getCreateTime()));
			}
		}
		parameterMap.put("totalNumber", totalNumber);
		parameterMap.put("commentsList", commentsList);
		parameterMap.put("request", request);
		String blogCommentsHtml = FreemarkerUtils.getBlogComments(request.getSession().getServletContext(),parameterMap);
		result.put("html", blogCommentsHtml);
		return result;
	}
	
	/**
	 * 提交博客评论
	 * 
	 * @return
	 */
	@RequestMapping(value="/submitBlogComments")
	public @ResponseBody String submitBlogComments(HttpServletRequest request,CommentsToBlog commentsToBlog){
		int level = 0;
		Member parentMember = null;
		if(!StringUtils.isEmpty(commentsToBlog.getParentId())){
			level = blogCommentsService.findLevel(commentsToBlog.getParentId()) + 1;
			parentMember = memberService.findByCommentsId(commentsToBlog.getParentId());
		}
		Member member = Tools.getMember(request);
		commentsToBlog.setId(Tools.getUUID());
		commentsToBlog.setCommentMemberId(member.getId());
		commentsToBlog.setCreateTime(new Date());
		commentsToBlog.setLevel(level);
		if(parentMember != null){
			commentsToBlog.setParentAdminName(parentMember.getAccount());
		}
		blogCommentsService.save(commentsToBlog);
		return "success";
	}
}
