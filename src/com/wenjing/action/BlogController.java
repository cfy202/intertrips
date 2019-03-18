package com.wenjing.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenjing.DateEditor;
import com.wenjing.HtmlCleanEditor;
import com.wenjing.Pages;
import com.wenjing.entity.Admin;
import com.wenjing.entity.Blog;
import com.wenjing.entity.BlogCategory;
import com.wenjing.entity.BlogTag;
import com.wenjing.entity.Image;
import com.wenjing.entity.Page;
import com.wenjing.service.BlogCategoryService;
import com.wenjing.service.BlogService;
import com.wenjing.service.BlogTagService;
import com.wenjing.service.ImageService;
import com.wenjing.service.PageService;
import com.wenjing.util.FreemarkerUtils;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;

/**
 * 博客类型
 * 
 * @author Jared
 *
 */
@Controller
@RequestMapping("/admin/blog")
public class BlogController {
	
	private static final boolean String = false;
	
	public static final String DEFAULT_COST_ID = "d8fe5ef1de7747ab86588f9880f1aa77";
	
	public static final int PAGE_TYPE = 7;

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private BlogCategoryService blogCategoryService;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private BlogTagService blogTagService;
	
	@Autowired
	private PageService pageService;
	
	/**
	 * 数据绑定
	 * 
	 * @param binder
	 *            WebDataBinder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateEditor(true));
	}
	
	/**
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		List<BlogCategory> blogCategoryList = blogCategoryService.findAll();
		model.addAttribute("blogCategoryList", blogCategoryList);
		model.addAttribute("currentDate",new Date());
		return "/admin/manage/blog/add.ftl";
	}
	
	/**
	 * 保存
	 * 
	 * @param blogCategory
	 * @return
	 */
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(Blog blog,HttpServletRequest request){
		Page page = blog.getPage();
		page.setId(Tools.getUUID());
		page.setType(PAGE_TYPE);
		page.setCostnumber(DEFAULT_COST_ID);
		pageService.save(page);
		Admin admin = Tools.getAdmin(request);
		blog.setId(Tools.getUUID());
		String name = blog.getTittle().replace(" ","-");
		blog.setName(name);
		Date currentDate = new Date();
		blog.setCreateTime(currentDate);
		blog.setLastUpdateTime(currentDate);
		blog.setReleaseAdminId(admin.getId());
		blog.setPageId(page.getId());
		blogService.save(blog);
		blogCategoryService.changeBlogNumberById(1, blog.getCategoryId());
		return "redirect:list.do";	
	}
	
	/**
	 * 编辑
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(String id,Model model){
		Blog blog = blogService.findById(id);		
		List<BlogCategory> blogCategoryList = blogCategoryService.findAll();
		model.addAttribute("blog",blog);
		model.addAttribute("blogCategoryList",blogCategoryList);
		return "/admin/manage/blog/edit.ftl";	
	}

	/**
	 * 更新
	 * 
	 * @param blogCategory
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(Blog blog){
		blog.setIsCreate(0);
		Page page = pageService.findById(blog.getPage().getId());
		page.setTemplateUrl(blog.getPage().getTemplateUrl());
		page.setFilepath(blog.getPage().getFilepath());
		page.setMetatitle(blog.getPage().getMetatitle());
		page.setMetakeywords(blog.getPage().getMetakeywords());
		page.setMetadescription(blog.getPage().getMetadescription());
		pageService.update(page);
		blogService.update(blog);
		return "redirect:list.do";
	}
	
	/**
	 * 列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model,HttpServletRequest request,HttpServletResponse response){
		String pageNow = request.getParameter("TpageNow");
		String pageSize = request.getParameter("pageSize");
		
		if (StringUtils.isEmpty(pageSize)){
			pageSize = WebUtils.getCookie(request, "blogPageSize");
		}else{
			WebUtils.addCookie(request, response, "blogPageSize", pageSize);
			pageNow = 1 + "";
		}
		if (StringUtils.isEmpty(pageNow)) {
			pageNow = WebUtils.getCookie(request, "blogPageNow");
		} else {
			WebUtils.addCookie(request, response, "blogPageNow", pageNow);
		}
		
		if(StringUtils.isEmpty(pageSize)){
			pageSize = 10 + "";
		}
		if(StringUtils.isEmpty(pageNow)){
			pageNow = 1 + "";
		}
		int totalNumber = blogService.countAll();
		Pages page = new Pages(totalNumber,Integer.parseInt(pageNow));
		page.setPageSize(Integer.parseInt(pageSize));
		List<Blog> list = blogService.findAllByPage(page);
		List<BlogTag> blogTagList = blogTagService.findAll();
		model.addAttribute("page", page);
		model.addAttribute("blogList", list);
		model.addAttribute("blogTagList", blogTagList);
		return "/admin/manage/blog/list.ftl";
	}
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(String id){
		String[] blogIdArray = new String[]{id};
		blogService.deleteAssociationByBlogIds(blogIdArray);
		Blog blog = blogService.findById(id);
		pageService.delete(blog.getPageId());
		blogService.deleteById(id);
		blogCategoryService.changeBlogNumberById(-1, blog.getCategoryId());
		return "redirect:list.do";
	}
	
	/**
	 * 批量为博客添加或删除标签
	 * 
	 * @param blogId
	 * @param blogTagId
	 * @return
	 */
	@RequestMapping(value="/addTags")
	public String addTag(String[] blogIds,String[] blogTagIds){
		if(blogIds != null && blogIds.length > 0){
			blogService.deleteAssociationByBlogIds(blogIds);
		}
		List<String> blogTagList = Arrays.asList(blogTagIds);
		blogTagList = new ArrayList<String>(blogTagList);
		for(Iterator<String> iterator = blogTagList.iterator();iterator.hasNext();){
			String blogTagId = iterator.next();
			if("remove".equals(blogTagId)){
				iterator.remove();
			}
		}
		if(blogTagList.size() > 0){
			blogService.addTag(blogIds, blogTagList);
		}
		return "redirect:list.do";
	}
	
	/**
	 * 线路图片上传保存到图库
	 * 
	 * @param picaddress
	 * @return
	 */
	@RequestMapping("/savepic")
	@ResponseBody
	public Image savepictoria(@RequestParam("picaddress") String picaddress,
			@Param("costnumber") String costnumber,HttpServletRequest request) {
		// 保存image
		String id = Tools.getUUID();
		Image image = new Image();
		image.setId(id);
		image.setUrl(picaddress);
		String[] name = picaddress.split("images/");
		image.setName(name[1]);
		String createtime = Tools.getTime();
		image.setCreatetime(createtime);
		image.setUsetype("blog");
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		image.setOpuser(admin.getUsername());
		image.setCostnumber(costnumber);
		imageService.save(image);
		return image;
	}
	
	/**
	 * 预览blog页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/view")
	public String view(Model model,String id){
		//获取上，下导航
		pageService.getNavigation(model, DEFAULT_COST_ID);
		Page page = pageService.findBlogNavigationPage();
		Blog blog = blogService.showBlogDetail(id);
		blog.setReleaseTimeString(Tools.getEnglishShow(blog.getReleaseTime()));
		
		List<Blog> blogList = blogService.findRecentBlogByAdminId(blog.getReleaseAdminId());
		List<BlogCategory> blogCategoryList = blogCategoryService.findAllShowCategory();
		List<BlogTag> blogTagList = blogTagService.findAllShowBlogTag();
		model.addAttribute("pageNavigationUrl", page.getFilepath());
		model.addAttribute("blog", blog);
		model.addAttribute("recentBlogList", blogList);
		model.addAttribute("blogCategoryList", blogCategoryList);
		model.addAttribute("blogTagList", blogTagList);
		return "/front/blog/blog.ftl";
	}
	
	/**
	 * 生成blog页面
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/create")
	public String create(HttpServletRequest request,String id){
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		pageService.getNavigationCreate(parameterMap, DEFAULT_COST_ID);
		Page page = pageService.findBlogNavigationPage();
		Blog blog = blogService.showBlogDetail(id);
		blog.setReleaseTimeString(Tools.getEnglishShow(blog.getReleaseTime()));
		
		List<Blog> blogList = blogService.findRecentBlogByAdminId(blog.getReleaseAdminId());
		List<BlogCategory> blogCategoryList = blogCategoryService.findAllShowCategory();
		List<BlogTag> blogTagList = blogTagService.findAllShowBlogTag();
		parameterMap.put("pageNavigationUrl", page.getFilepath());
		parameterMap.put("blog", blog);
		parameterMap.put("recentBlogList", blogList);
		parameterMap.put("blogCategoryList", blogCategoryList);
		parameterMap.put("blogTagList", blogTagList);
		parameterMap.put("request", request);
		
		String str = request.getSession().getServletContext().getRealPath("/") + blog.getPage().getFilepath();
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
			FreemarkerUtils.createHTML(request.getSession().getServletContext(), parameterMap,
					"/front/blog/blog.ftl", blog.getPage().getFilepath());
		}
		blogService.updateCreate(id);
		return "redirect:list.do";
	}
}