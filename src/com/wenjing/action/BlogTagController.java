package com.wenjing.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wenjing.entity.Blog;
import com.wenjing.entity.BlogCategory;
import com.wenjing.entity.BlogTag;
import com.wenjing.entity.Page;
import com.wenjing.service.BlogCategoryService;
import com.wenjing.service.BlogService;
import com.wenjing.service.BlogTagService;
import com.wenjing.service.PageService;
import com.wenjing.util.FreemarkerUtils;
import com.wenjing.util.Tools;

/**
 * 博客类型
 * 
 * @author Jared
 *
 */
@Controller
@RequestMapping("/admin/blogTag")
public class BlogTagController {
	
	@Autowired
	private BlogTagService blogTagService;
	
	@Autowired
	private PageService pageService;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private BlogCategoryService blogCategoryService;
	
	/**
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(){
		return "/admin/manage/blogTag/add.ftl";
	}
	
	/**
	 * 保存
	 * 
	 * @param blogCategory
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(BlogTag blogTag){
		Page page = blogTag.getPage();
		page.setId(Tools.getUUID());
		page.setType(BlogController.PAGE_TYPE);
		page.setCostnumber(BlogController.DEFAULT_COST_ID);
		pageService.save(page);
		blogTag.setId(Tools.getUUID());
		blogTag.setPageId(page.getId());
		blogTagService.save(blogTag);
		return "redirect:list.do";	
	}
	
	/**
	 * 编辑
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(String id,Model model){
		BlogTag blogTag = blogTagService.findById(id);		
		model.addAttribute("blogTag",blogTag);
		return "/admin/manage/blogTag/edit.ftl";	
	}

	/**
	 * 更新
	 * 
	 * @param blogTag
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(BlogTag blogTag){
		blogTag.setIsCreate(0);
		Page page = pageService.findById(blogTag.getPage().getId());
		page.setTemplateUrl(blogTag.getPage().getTemplateUrl());
		page.setFilepath(blogTag.getPage().getFilepath());
		page.setMetatitle(blogTag.getPage().getMetatitle());
		page.setMetakeywords(blogTag.getPage().getMetakeywords());
		page.setMetadescription(blogTag.getPage().getMetadescription());
		pageService.update(page);		
		blogTagService.update(blogTag);
		return "redirect:list.do";
	}
	
	/**
	 * 列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model){
		List<BlogTag> list = blogTagService.findAll();
		model.addAttribute("list", list);
		return "/admin/manage/blogTag/list.ftl";
	}
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(String id){
		BlogTag blogTag = blogTagService.findById(id);
		pageService.delete(blogTag.getId());
		blogTagService.deleteById(id);
		return "redirect:list.do";
	}
	
	
	/**
	 * blog导航预览
	 * 
	 * @param model
	 * @param costnumber
	 * @param id
	 * @return
	 */
	@RequestMapping("/view")
	public String view(Model model,@RequestParam("id")String id){
		pageService.getNavigation(model, BlogController.DEFAULT_COST_ID);
		Page page = pageService.findBlogNavigationPage();
		BlogTag blogTag = blogTagService.findById(id);
		//获取上，下导航
		List<Blog> blogList = blogService.findRecentBlogByTag(id);
		List<BlogTag> blogTagList = blogTagService.findAllShowBlogTag();
		List<BlogCategory> blogCategoryList = blogCategoryService.findAllShowCategory();
		model.addAttribute("blogTag",blogTag);
		model.addAttribute("navigationPage", page);
		model.addAttribute("page", blogTag.getPage());
		model.addAttribute("recentBlogList", blogList);
		model.addAttribute("blogCategoryList", blogCategoryList);
		model.addAttribute("blogTagList", blogTagList);
		return"/front/blog/blogList.ftl";
	}
	
	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/create")
	public String create(HttpServletRequest request,String id){
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		pageService.getNavigationCreate(parameterMap, BlogController.DEFAULT_COST_ID);
		Page page = pageService.findBlogNavigationPage();
		BlogTag blogTag = blogTagService.findById(id);
		//获取上，下导航
		List<Blog> blogList = blogService.findRecentBlogByTag(id);
		List<BlogTag> blogTagList = blogTagService.findAllShowBlogTag();
		List<BlogCategory> blogCategoryList = blogCategoryService.findAllShowCategory();
		parameterMap.put("request", request);
		parameterMap.put("blogTag",blogTag);
		parameterMap.put("navigationPage", page);
		parameterMap.put("page", blogTag.getPage());
		parameterMap.put("recentBlogList", blogList);
		parameterMap.put("blogCategoryList", blogCategoryList);
		parameterMap.put("blogTagList", blogTagList);
		// 静态页面的完整路径
		String str = request.getSession().getServletContext().getRealPath("/") + page.getFilepath();
		if (str.indexOf("/") != -1) {
			String folder = str.substring(0, str.lastIndexOf("/"));
			File f = new File(folder);
			if (!f.exists() && !f.isDirectory()) { // 是文件夹，且文件夹不存在则创建文件夹
				f.mkdirs();
			}
			File file = new File(str);
			// 如果静态文件存在，则删除之前的静态页面，重新生成新的静态页面
			if (file.isFile() && file.exists()) {
				file.delete();
			}
			str = null;// 释放资源
			if (file != null) {
				file = null;
			}
			FreemarkerUtils.createHTML(
					request.getSession().getServletContext(), parameterMap,
					"/front/blog/blogList.ftl",blogTag.getPage().getFilepath());
		}
		blogTagService.changeIsCreate(id);
		return "redirect:list.do";
	}
}
