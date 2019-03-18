package com.wenjing.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenjing.DateEditor;
import com.wenjing.HtmlCleanEditor;
import com.wenjing.Pages;
import com.wenjing.entity.CommentsToBlog;
import com.wenjing.service.BlogCategoryService;
import com.wenjing.service.BlogCommentsService;
import com.wenjing.service.BlogService;
import com.wenjing.service.BlogTagService;
import com.wenjing.service.ImageService;
import com.wenjing.util.WebUtils;

/**
 * 博客类型
 * 
 * @author Jared
 *
 */
@Controller
@RequestMapping("/admin/blogComments")
public class BlogCommentsController {
	
	private static String BLOG_NAME_COOKIE_NAME = "searchBlogName";
	private static String BLOGCOMMENTS_STATUS_COOKIE_NAME = "blogCommentsStatus";
	private static String PAGE_NOW_COOKIE_NAME = "blogCommentsPageNow";
	private static String PAGE_SIZE_COOKIE_NAME = "blogCommentsPageSize";
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private BlogCategoryService blogCategoryService;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private BlogTagService blogTagService;
	
	@Autowired
	private BlogCommentsService blogCommentsService;
	
	/**
	 * 数据绑定
	 * 
	 * @param binder
	 * WebDataBinder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new HtmlCleanEditor(true, true));
		binder.registerCustomEditor(Date.class, new DateEditor(true));
	}
	
	/**
	 * 评论list
	 * 
	 * @return
	 */
	@RequestMapping(value="/list")
	public String commentsList(HttpServletRequest request,HttpServletResponse response,Model model){
		String pageNow = request.getParameter("pageNow");
		String pageSize = request.getParameter("pageSize");
		String blogName = request.getParameter("blogName");
		String status = request.getParameter("status");
		
		String cookiePageNow = WebUtils.getCookie(request,PAGE_NOW_COOKIE_NAME);
		String cookiePageSize = WebUtils.getCookie(request,PAGE_SIZE_COOKIE_NAME);
		String cookieBlogName = WebUtils.getCookie(request,BLOG_NAME_COOKIE_NAME);
		String cookieStatus = WebUtils.getCookie(request,BLOGCOMMENTS_STATUS_COOKIE_NAME);
		
		//获取或者设置博客名称
		if(StringUtils.isEmpty(blogName)){
			blogName = cookieBlogName;
		}else if(!blogName.equals(cookieBlogName)){
			WebUtils.addCookie(request,response,BLOG_NAME_COOKIE_NAME,blogName);
			pageNow = "1";
		}
		//获取或者设置博客的评论状态
		if(StringUtils.isEmpty(status)){
			status = cookieStatus;
		}else if(!status.equals(cookieStatus)){
			WebUtils.addCookie(request,response,BLOGCOMMENTS_STATUS_COOKIE_NAME,status);
			pageNow = "1";
		}
		//获取或者设置页面容量
		if (StringUtils.isEmpty(pageSize)){
			pageSize = cookiePageSize;
		}else if(!pageSize.equals(cookiePageSize)){
			WebUtils.addCookie(request,response,PAGE_SIZE_COOKIE_NAME,pageSize);
			pageNow = "1";
		}
		//获取或者设置页面的页数
		if (StringUtils.isEmpty(pageNow)) {
			pageNow = cookiePageNow;
		} else if (!pageNow.equals(cookiePageNow)) {
			WebUtils.addCookie(request,response,PAGE_NOW_COOKIE_NAME,pageNow);
		}
		if(StringUtils.isEmpty(pageSize)){
			pageSize = "10";
		}
		if(StringUtils.isEmpty(pageNow)){
			pageNow = "1";
		}
		int totalComments = blogCommentsService.countCommentsToBlogByCondition(blogName,status);
		Pages page = new Pages(totalComments,Integer.parseInt(pageNow));
		page.setPageSize(Integer.parseInt(pageSize));
		List<CommentsToBlog> commentsList = blogCommentsService.commentsList(page,blogName,status);
		model.addAttribute("commentsList", commentsList);
		model.addAttribute("page",page);
		model.addAttribute("blogName",blogName);
		model.addAttribute("status", status);
		return "/admin/manage/blogComments/list.ftl";
	}
	
	/**
	 * 查看评论
	 * @param id
	 * @return
	 */
	@RequestMapping("/view")
	public String details(Model model,String id){
		CommentsToBlog commentDetails = blogCommentsService.findAllInformationById(id);	
		model.addAttribute("commentDetails", commentDetails);
		return "/admin/manage/blogComments/detail.ftl";
	}
	
	/**
	 * 删除评论
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(String id){ 
		blogCommentsService.deleteById(id);
		return "redirect:list.do";
	}
	
	/**
	 * 更改评论的显示与否
	 * 
	 * @return
	 */
	@RequestMapping("/changeIsShow")
	public @ResponseBody String changeStatus(String id,Integer isShow){
		blogCommentsService.changeIsShow(id, isShow);
		return "success";
	}
	
	/**
 	 * 更新博客评论状态
 	 * 
	 * @return
	 */
	@RequestMapping("/updateComments")
	public @ResponseBody String updateComments(CommentsToBlog commentsToBlog){
		blogCommentsService.updateComments(commentsToBlog);
		blogService.updateCommmentsNumber(commentsToBlog.getId());
		return "success";
	}
}