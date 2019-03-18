package com.wenjing.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenjing.entity.Admin;
import com.wenjing.entity.Cost;
import com.wenjing.entity.Image;
import com.wenjing.entity.Page;
import com.wenjing.entity.PageImage;
import com.wenjing.service.CostService;
import com.wenjing.service.ImageService;
import com.wenjing.service.OfficeContactsService;
import com.wenjing.service.PageImageService;
import com.wenjing.service.PageService;
import com.wenjing.util.Tools;

/**
 * 类说明		page后台管理controller
 * @author xiejin
 * @date 2015-4-23
 */
@Controller
@RequestMapping("/admin/single")
public class SinglePageController {

	@Resource
	private PageService pageService;
	@Resource 
	private ImageService imageService;
	@Resource
	private PageImageService pageImageService;
	@Resource
	private CostService costService;
	@Resource
	private OfficeContactsService officeContactsService;
	@Resource
	private HttpServletRequest request;
	
	/**
	 * 查询所有page信息
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String findAll(Model model){
//		List<String> costnumber = Tools.getCostNumber(request);
		List<Page> page = pageService.findAll(Tools.getCostNumber(request),9);		
		model.addAttribute("page", page);
		return "/admin/manage/single/page.ftl";
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
		return "redirect:/admin/single/list.do";
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
		page.setType(9);
		Page page2 = pageService.findById(id);
		//保存单页面表
		if (page2 != null && !page2.equals("")) {
			pageService.update(page);
		} else {
			pageService.save(page);
		}
		//保存page图片联系表
		if(imageid !=null && !imageid.equals("")){ 				//判断是否选择或上传图片
			for (int i = 0; i < imageid.length; i++) {			
				if(!imageid[i].equals("")){						//去除为""的imageid
					String id1 = Tools.getUUID();
					PageImage pageImage = new PageImage();
					pageImage.setId(id1);
					pageImage.setPageId(id);
					pageImage.setImageId(imageid[i]);
					pageImageService.save(pageImage);
				}
			}		
		}
		return "redirect:/admin/single/list.do";
		
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
		model.addAttribute("page", page);
		Cost costlist = costService.findById(page.getCostnumber());
		model.addAttribute("costlist", costlist);
		String imageurl = page.getImageurl();
		if(imageurl!=null && !imageurl.equals("")){
			imageurl = imageurl+",";
			String[] imgurl = imageurl.split(",");
			model.addAttribute("imgurl", imgurl);
			model.addAttribute("img", 1);
		}else{
			model.addAttribute("img", 0);
		}
		return "/admin/manage/single/pageUpdate.ftl";
	}
	
	/**
	 * 添加page
	 * @param page
	 * @param model
	 * @return
	 * xiejin
	 */
	@RequestMapping("/add")
	public String add(Page page, Model model){
		String id = Tools.getUUID();
		page.setId(id);
		model.addAttribute("page", page);
		List<Cost> costlist = new ArrayList<Cost>();
		List<String> costnumber = Tools.getCostNumber(request);
		for (String costnumber2 : costnumber) {
			Cost cost = costService.findById(costnumber2);
			costlist.add(cost);
		}
		model.addAttribute("costlist", costlist);
		return "/admin/manage/single/pageAdd.ftl";
	}
	
	/**
	 * 单页面图片上传保存到图库
	 * @param picaddress
	 * @return
	 * xiejin
	 */
	@RequestMapping("/savepic")
	@ResponseBody
	public Image savepictoria(@RequestParam("picaddress") String picaddress,@Param("costnumber")String costnumber){
		//保存image
		String id = Tools.getUUID();
		Image image = new Image();
		image.setId(id);
		image.setUrl(picaddress);
		String[] name = picaddress.split("images/");
		image.setName(name[1]);
		String createtime = Tools.getTime();
		image.setCreatetime(createtime);
		image.setUsetype("page");
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		image.setOpuser(admin.getUsername());
		image.setCostnumber(costnumber);
		imageService.save(image);
		return image;
	}
	
	/**
	 * 根据imageid和pageid删除景点图片联系表
	 * @param url
	 * @param pageid
	 */
	@RequestMapping("/deletebyimageid")
	@ResponseBody
	public String deletePageImageByImageId(@RequestParam("url") String url,@RequestParam("pageid") String pageid){
		Image image = imageService.selectByUrl(url);		//根据url查询图片
		String imageId = image.getId();		//获取图片id
		pageImageService.deleteByImageId(imageId,pageid);		//根据imageid和pageid删除单页面图片联系表信息
		return null;
	}

	/**
	 * @author Sevens	首页后台预览
	 * 时间2015-6-23
	 * @param model
	 * @param costnumber
	 * @return
	 */
	@RequestMapping("/view")
	public String index(Model model,@RequestParam("costnumber")String costnumber,@RequestParam("id")String id){
		pageService.index(model, costnumber);
		Page page = pageService.findById(id);
		String imageUrl = page.getImageurl();
		if(imageUrl!=null && !imageUrl.equals("")){
			String[] url = (imageUrl+",").split(",");
			page.setImageurl(url[0]);
		}else{
			page.setImageurl("/assets-web/images/banner.jpg");
		}
		model.addAttribute("page",page);
		return page.getTemplateUrl();
	}

	/**
	 * @author Sevens	首页生成静态页面
	 * 时间2015-7-30	
	 * @param costnumber
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/create")
	public String createhome(@RequestParam("costnumber")String costnumber,@RequestParam("id")String id,Model model) throws Exception{
		Page page = pageService.findById(id);
		String imageUrl = page.getImageurl();
		if(imageUrl!=null && !imageUrl.equals("")){
			String[] url = (imageUrl+",").split(",");
			page.setImageurl(url[0]);
		}else{
			page.setImageurl("/assets-web/images/banner.jpg");
		}
		pageService.create(costnumber, page, request);
		return "redirect:/admin/single/list.do";
	}
	
	
}
