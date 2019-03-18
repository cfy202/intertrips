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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenjing.entity.Blog;
import com.wenjing.entity.BlogCategory;
import com.wenjing.entity.BlogTag;
import com.wenjing.entity.Page;
import com.wenjing.service.BlogCategoryService;
import com.wenjing.service.BlogService;
import com.wenjing.service.BlogTagService;
import com.wenjing.service.CostService;
import com.wenjing.service.ImageService;
import com.wenjing.service.PageImageService;
import com.wenjing.service.PageService;
import com.wenjing.util.FreemarkerUtils;
import com.wenjing.util.Tools;

@Controller
@RequestMapping("/admin/blogPage")
public class BlogPageController {
	
	@Autowired
	private PageService pageService;
	
	@Autowired
	private PageImageService pageImageService;
	
	@Autowired
	private CostService costService;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private BlogTagService blogTagService;
	
	@Autowired
	private BlogCategoryService blogCategoryService;
	
	@Autowired
	private BlogService blogService;
	
	/**
	 * 查询所有blogPage信息
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String findAll(Model model,HttpServletRequest request){
		List<Page> blogPageList = pageService.findAllBlogList(Tools.getCostNumber(request));		
		model.addAttribute("blogList", blogPageList);
		return "/admin/manage/blogNavigation/list.ftl";
	}
	
	/**
	 * 根据id删除page
	 * @param id
	 * @return
	 * xiejin
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id) {
		pageImageService.deleteByPageId(id);	//删除关联信息
		pageService.delete(id);
		return "redirect:/admin/blogPage/list.do";
	}

	/**
	 * 保存page
	 * @param page
	 * @param id
	 * @return
	 * xiejin
	 */
	@RequestMapping("/save")
	public String save(Page page,@RequestParam("id") String id,@RequestParam("imageid") String[] imageid) {
		page.setId(id);	
		page.setType(BlogController.PAGE_TYPE);
		page.setCostnumber(BlogController.DEFAULT_COST_ID);
		Page page2 = pageService.findById(id);
		//保存单页面表
		if (page2 != null && !page2.equals("")) {
			pageService.update(page);
		} else {
			pageService.save(page);
		}
		return "redirect:/admin/blogPage/list.do";
	}

	/**
	 * 修改page
	 * @param page
	 * @param model
	 * @return
	 * xiejin
	 */
	@RequestMapping("/update")
	public String update(Page page, Model model) {
		String id = page.getId();					
		page = pageService.findById(id);
		String imageurl = page.getImageurl();
		if(imageurl!=null && !imageurl.equals("")){//判断景点是否有图片，并读出图片url
			imageurl = imageurl+",";
			String[] imgurl = imageurl.split(",");
			model.addAttribute("imgurl", imgurl);
			model.addAttribute("img", 1);
		}else{
			model.addAttribute("img", 0);
		}
		model.addAttribute("page", page);
		return "/admin/manage/blogNavigation/edit.ftl";
	}
	
	/**
	 * 添加page
	 * @param page
	 * @param model
	 * @return
	 * xiejin
	 */
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Page page, Model model){
		String id = Tools.getUUID();
		page.setId(id);
		return "/admin/manage/blogNavigation/add.ftl";
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
	public String view(Model model,@RequestParam("costnumber")String costnumber,@RequestParam("id")String id){
		Page page = pageService.findById(id);
		//获取上，下导航
		pageService.getNavigation(model, costnumber);
		List<Blog> blogList = blogService.findRecentBlog();
		List<BlogTag> blogTagList = blogTagService.findAllShowBlogTag();
		List<BlogCategory> blogCategoryList = blogCategoryService.findAllShowCategory();
		model.addAttribute("navigationPage", page);
		model.addAttribute("page", page);
		model.addAttribute("recentBlogList", blogList);
		model.addAttribute("blogTagList", blogTagList);
		model.addAttribute("blogCategoryList", blogCategoryList);
		return"/front/blog/blogList.ftl";
	}
	
	/**
	 * 检查blog的页面是否存在
	 */
	@RequestMapping("/checkFilePath")
	public @ResponseBody String checkFilePath(String filePath){
		int pageNumber = pageService.getPageNumberWithFilePath(filePath);
		if(pageNumber == 0){
			return "yes";
		}else{
			return "no";
		}
	}
	
	/**
	 * 检查blog的页面是否存在(修改时)
	 * 
	 * @param filePath
	 * @return
	 */
	@RequestMapping("/checkFilePathWithExistPage")
	public String checkFilePath(String filePath,String existPageId){
		int pageNumber = pageService.getPageNumberWithFilePathExceptPage(filePath,existPageId);
		if(pageNumber == 0){
			return "yes";
		}else{
			return "no";
		}
	}

	/**
	 * blog导航生成页面
	 * 
	 * @param request
	 * @param costnumber
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/create")
	public String create(HttpServletRequest request,@RequestParam("costnumber")String costnumber,@RequestParam("id")String id) throws Exception{
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		Page page = pageService.findById(id);
		//获取上，下导航
		pageService.getNavigationCreate(parameterMap, costnumber);
		List<Blog> blogList = blogService.findRecentBlog();
		List<BlogTag> blogTagList = blogTagService.findAllShowBlogTag();
		List<BlogCategory> blogCategoryList = blogCategoryService.findAllShowCategory();
		parameterMap.put("request", request);
		parameterMap.put("page", page);
		parameterMap.put("navigationPage", page);
		parameterMap.put("recentBlogList", blogList);
		parameterMap.put("blogTagList", blogTagList);
		parameterMap.put("blogCategoryList", blogCategoryList);
		
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
					"/front/blog/blogList.ftl", page.getFilepath());
		}
		return "redirect:/admin/blogPage/list.do";
	}
}
