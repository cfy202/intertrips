package com.wenjing.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wenjing.entity.ServiceItem;
import com.wenjing.service.ServiceItemService;
import com.wenjing.util.Tools;

/**
 * 类说明		后台服务项管理controller
 * @author sevens
 * @date 2015-4-23 
 */
@Controller
@RequestMapping("/admin/serviceitem")
public class ServiceItemController {
	@Resource(name="serviceItemService")
	private ServiceItemService serviceItemService;
	
	/**
	 * 查询所有服务项列表
	 * @return
	 * sevens
	 */
	@RequestMapping("/list")
	public String findAll(Model model){
		List<ServiceItem> serviceItemList = serviceItemService.findAll();
		model.addAttribute("serviceItemList", serviceItemList);
		return "/admin/manage/serviceitem/list.ftl";
	}
	
	/**
	 * 根据id删除服务项
	 * @param id
	 * @return
	 * sevens
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id, final RedirectAttributes rAttributes) {
		boolean flag = serviceItemService.delete(id);
		if(flag){
			rAttributes.addFlashAttribute("message", "删除成功！");
		}else {
			rAttributes.addFlashAttribute("message", "删除失败，此服务项正在使用！");
		}
		return "redirect:/admin/serviceitem/list.do";
	}
	
	/**
	 * 添加服务项
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	public String add(){
		return "/admin/manage/serviceitem/add.ftl";
	}

	/**
	 * 保存服务项
	 * 
	 * @param tag
	 * @param id
	 * @return
	 * sevens
	 */
	@RequestMapping("/save")
	public String save(ServiceItem serviceItem,HttpServletRequest request) {
		serviceItem.setId(Tools.getUUID());
		serviceItemService.save(serviceItem);
		return "redirect:/admin/serviceitem/list.do";
	}
	
	/**
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(String id,Model model){
		ServiceItem serviceItem = serviceItemService.findById(id);
		model.addAttribute("serviceItem", serviceItem);
		return "/admin/manage/serviceitem/edit.ftl";
	}

	/**
	 * 更新服务项
	 * 
	 * @param serviceitem
	 * @param model
	 * @return
	 * sevens
	 */
	@RequestMapping("/update")
	public String update(ServiceItem serviceItem) {
		serviceItemService.update(serviceItem);
		return "redirect:/admin/serviceitem/list.do";
	}
}
