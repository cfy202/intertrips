package com.wenjing.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wenjing.entity.CharterService;
import com.wenjing.service.CharterServiceService;
import com.wenjing.util.Tools;

@Controller
@RequestMapping("/admin/charterService")
public class CharterServiceController {
	
	@Autowired
	private CharterServiceService charterServiceService;
	
	/**
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(){
		return "/admin/manage/charterService/add.ftl";
	}
	
	/**
	 * 保存
	 * 
	 * @param blogCategory
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(CharterService charterService){
		charterService.setId(Tools.getUUID());
		charterServiceService.save(charterService);
		return "redirect:list.do";	
	}
	
	/**
	 * 编辑
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(String id,Model model){
		CharterService charterService = charterServiceService.findById(id);		
		model.addAttribute("charterService",charterService);
		return "/admin/manage/charterService/edit.ftl";	
	}

	/**
	 * 更新
	 * 
	 * @param CharterService
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(CharterService charterService){
		charterServiceService.update(charterService);
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
		List<CharterService> list = charterServiceService.findAll();
		model.addAttribute("list", list);
		return "/admin/manage/charterService/list.ftl";
	}
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(String id){
		charterServiceService.deleteById(id);
		return "redirect:list.do";
	}
}
