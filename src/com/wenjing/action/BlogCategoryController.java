package com.wenjing.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/admin/blogCategory")
public class BlogCategoryController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private BlogCategoryService blogCategoryService;
	
	@Autowired
	private PageService pageService;
	
	@Autowired
	private BlogTagService blogTagService;

	/**
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(){
		return "/admin/manage/blogCategory/add.ftl";
	}
	
	/**
	 * 保存
	 * 
	 * @param blogCategory
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(BlogCategory blogCategory){
		Page page = blogCategory.getPage();
		page.setId(Tools.getUUID());
		page.setType(BlogController.PAGE_TYPE);
		page.setCostnumber(BlogController.DEFAULT_COST_ID);
		pageService.save(page);
		blogCategory.setId(Tools.getUUID());
		blogCategory.setPageId(page.getId());
		blogCategoryService.save(blogCategory);
		return "redirect:list.do";	
	}
	
	/**
	 * 编辑
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String loadBlogCategory(String id,Model model){
		BlogCategory blogCategory = blogCategoryService.findById(id);		
		model.addAttribute("blogCategory",blogCategory);
		return "/admin/manage/blogCategory/edit.ftl";	
	}

	/**
	 * 更新
	 * 
	 * @param blogCategory
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(BlogCategory blogCategory){
		blogCategory.setIsCreate(0);
		Page page = blogCategory.getPage();
		page.setCostnumber(BlogController.DEFAULT_COST_ID);
		if(StringUtils.isEmpty(page.getId())){
			page.setId(Tools.getUUID());
			page.setType(BlogController.PAGE_TYPE);
			pageService.save(page);
			blogCategory.setPageId(page.getId());
		}else{
			pageService.update(page);
		}
		blogCategoryService.update(blogCategory);
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
		List<BlogCategory> list = blogCategoryService.findAll();
		model.addAttribute("list", list);
		return "/admin/manage/blogCategory/list.ftl";
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
		//获取上，下导航
		pageService.getNavigation(model, BlogController.DEFAULT_COST_ID);
		Page navigationPage = pageService.findBlogNavigationPage();
		
		BlogCategory blogCategory = blogCategoryService.findById(id);
		List<Blog> blogList = blogService.findRecentBlogByCategory(blogCategory.getId());
		List<BlogCategory> blogCategoryList = blogCategoryService.findAllShowCategory();
		List<BlogTag> blogTagList = blogTagService.findAllShowBlogTag();
		if(navigationPage == null){
			navigationPage = blogCategory.getPage();
		}
		model.addAttribute("blogCategory", blogCategory);
		model.addAttribute("navigationPage", navigationPage);
		model.addAttribute("page", blogCategory.getPage());
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
		//获取上，下导航
		pageService.getNavigationCreate(parameterMap, BlogController.DEFAULT_COST_ID);
		Page navigationPage = pageService.findBlogNavigationPage();
		
		BlogCategory blogCategory = blogCategoryService.findById(id);
		List<Blog> blogList = blogService.findRecentBlogByCategory(blogCategory.getId());
		List<BlogCategory> blogCategoryList = blogCategoryService.findAllShowCategory();
		List<BlogTag> blogTagList = blogTagService.findAllShowBlogTag();
		if(navigationPage == null){
			navigationPage = blogCategory.getPage();
		}
		parameterMap.put("request", request);
		parameterMap.put("blogCategory", blogCategory);
		parameterMap.put("navigationPage", navigationPage);
		parameterMap.put("page", blogCategory.getPage());
		parameterMap.put("recentBlogList", blogList);
		parameterMap.put("blogCategoryList", blogCategoryList);
		parameterMap.put("blogTagList", blogTagList);
		// 静态页面的完整路径
		String str = request.getSession().getServletContext().getRealPath("/") + blogCategory.getPage().getFilepath();
		if (str.indexOf("/") != -1) {
			String folder = str.substring(0, str.lastIndexOf("/"));
			File f = new File(folder);
			if (!f.exists() && !f.isDirectory()) { // 是文件夹，且文件夹不存在则创建文件夹
				f.mkdir();
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
			FreemarkerUtils.createHTML(request.getSession().getServletContext(), parameterMap,
					"/front/blog/blogList.ftl", blogCategory.getPage().getFilepath());
		}
		blogCategoryService.updateCreate(id);
		return "redirect:list.do";
	}
	
	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(String id){
		BlogCategory blogCategory = blogCategoryService.findById(id);
		pageService.delete(blogCategory.getPageId());
		blogCategoryService.deleteById(id);
		return "redirect:list.do";
	}
}
