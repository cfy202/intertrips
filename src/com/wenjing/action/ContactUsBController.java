package com.wenjing.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wenjing.entity.Cost;
import com.wenjing.entity.OfficeContacts;
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
@RequestMapping("/admin/contactus")
public class ContactUsBController {

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
	 * contact us的list页面
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public String contactUsList(Model model){
		List<Page> pageList = pageService.findAllContactUs(Tools.getCostNumber(request));		
		model.addAttribute("pageList", pageList);
		return "/admin/manage/page/contactUsList.ftl";
	}
	
	/**
	 * 添加contact us
	 * @param page
	 * @param model
	 * @return
	 * xiejin
	 */
	@RequestMapping("/add")
	public String addContactUs(Page page, Model model){
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
		return "/admin/manage/page/contactUsAdd.ftl";
	}

	/**
	 * 保存contact us
	 * @param page
	 * @param id
	 * @return
	 * xiejin
	 */
	@RequestMapping("/save")
	public String saveContactUs(Page page,@RequestParam("id") String id,@RequestParam("imageid") String[] imageid) {
		page.setId(id);	
		page.setType(8);
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
		return "redirect:/admin/contactus/list.do";
	}
	
	/**
	 * 根据id删除contact us
	 * @param id
	 * @return
	 * xiejin
	 */
	@RequestMapping("/delete")
	public String contactUsDelete(@RequestParam("id") String id) {
		pageImageService.deleteByPageId(id);	//删除关联信息
		pageService.delete(id);
		return "redirect:/admin/contactus/list.do";
	}
	
	/**
	 * 编辑contact us
	 * @param page
	 * @param model
	 * @return
	 * xiejin
	 */
	@RequestMapping("/update")
	public String editContactUs(Page page, Model model) {
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
		return "/admin/manage/page/contactUsEdit.ftl";
	}
	
	/**
	 * @author Sevens	contact us后台预览
	 * 时间2015-6-23
	 * @param model
	 * @param costnumber
	 * @return
	 */
	@RequestMapping("/view")
	public String contactUs(Model model,@RequestParam("costnumber")String costnumber,@RequestParam("id")String id){
		//获取上，下导航
		pageService.getNavigation(model, costnumber);	
		Page page = pageService.findById(id);
		List<OfficeContacts> officeContactsList = officeContactsService.findByCostnumber(costnumber);
		model.addAttribute("officeContactsList", officeContactsList);
		model.addAttribute("page",page);
		model.addAttribute("costnumber", costnumber);
		return"/front/about/contactus.ftl";
	}
	
	/**
	 * @author Sevens	contact us生成静态页面
	 * 时间2015-7-30	
	 * @param costnumber
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/create")
	public String createContactUs(@RequestParam("costnumber")String costnumber,@RequestParam("id")String id,Model model) throws Exception{
		Page page = pageService.findById(id);
		pageService.createContactUs(costnumber, page, request);
		return "redirect:/admin/contactus/list.do";
	}
}
