package com.wenjing.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wenjing.entity.Bus;
import com.wenjing.service.BusService;
import com.wenjing.util.Tools;

@Controller
@RequestMapping("/admin/bus")
public class BusController {
	
	@Autowired
	private BusService busService;
	
	/**
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(){
		return "/admin/manage/bus/add.ftl";
	}
	
	/**
	 * 保存
	 * 
	 * @param bus
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(Bus bus){
		bus.setId(Tools.getUUID());
		busService.save(bus);
		return "redirect:list.do";	
	}
	
	/**
	 * 编辑
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(String id,Model model){
		Bus bus = busService.findById(id);		
		model.addAttribute("bus",bus);
		return "/admin/manage/bus/edit.ftl";	
	}

	/**
	 * 更新
	 * 
	 * @param blogTag
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(Bus Bus){
		busService.update(Bus);
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
		List<Bus> list = busService.findAll();
		model.addAttribute("list", list);
		return "/admin/manage/bus/list.ftl";
	}
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(String id){
		busService.deleteById(id);
		return "redirect:list.do";
	}
}
