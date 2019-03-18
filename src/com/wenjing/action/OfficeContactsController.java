package com.wenjing.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wenjing.entity.Cost;
import com.wenjing.entity.OfficeContacts;
import com.wenjing.service.CostService;
import com.wenjing.service.OfficeContactsService;
import com.wenjing.util.Tools;

@Controller
@RequestMapping("/admin/officeContacts")
public class OfficeContactsController {
	
	@Autowired
	private OfficeContactsService officeContactsService;
	
	@Autowired
	private CostService costService;
	
	/**
	 * 列表
	 * 
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model,HttpServletResponse response){
		List<OfficeContacts> officeContactsList = officeContactsService.findAll();
		model.addAttribute("officeContactsList", officeContactsList);
		return "/admin/manage/officeContacts/list.ftl";
	}
	
	/**
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	public String add(Model model){
		List<Cost> costList = costService.findAll();
		model.addAttribute("costList", costList);
		return "/admin/manage/officeContacts/add.ftl";
	}
	
	/**
	 * 编辑
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/edit")
	public String load(Model model,String id){
		OfficeContacts officeContacts = officeContactsService.findById(id);
		List<Cost> costList = costService.findAll();
		model.addAttribute("costList", costList);
		model.addAttribute("officeContacts", officeContacts);
		return "/admin/manage/officeContacts/edit.ftl";
	}
	
	/**
	 * 更新
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/update")
	public String update(OfficeContacts officeContacts){
		officeContactsService.update(officeContacts);
		return "redirect:list.do";
	}
	
	/**
	 * 保存
	 * 
	 * @return
	 */
	@RequestMapping("/save")
	public String save(OfficeContacts officeContacts){
		officeContacts.setId(Tools.getUUID());
		officeContactsService.save(officeContacts);
		return "redirect:list.do";
	}
	
	/**
	 * 删除
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(String id,Model model){
		officeContactsService.deleteByPrimaryKey(id);
		return "redirect:list.do";
	}
}
