package com.wenjing.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wenjing.entity.Attractiontype;
import com.wenjing.service.AttractiontypeService;
import com.wenjing.util.Tools;

/**
 * 类说明		景点类型管理controller
 * @author xiejin
 * @date 2015-4-24
 */
@Controller
@RequestMapping("/admin/attractiontype")
public class AttractiontypeController {

	@Resource
	private AttractiontypeService attractiontypeService;
	
	/**
	 * 查询所有attractiontype 
	 * @return
	 * xiejin
	 */
	@RequestMapping("/list")
	public String findAll(Model model){
		List<Attractiontype> attractiontype = attractiontypeService.findAll();		
		model.addAttribute("attractiontype", attractiontype);
		return "/admin/manage/attractiontype/attractiontype.ftl";
	}
	
	/**
	 * 根据id删除attractiontype
	 * @param id
	 * @return
	 * xiejin
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id) {
		attractiontypeService.delete(id);
		return "redirect:/admin/attractiontype/list.do";
	}

	/**
	 * 保存attractiontype
	 * @param attractiontype
	 * @param id
	 * @return
	 * xiejin
	 */
	@RequestMapping("/save")
	public String save(Attractiontype attractiontype,@RequestParam("id") String id) {
		attractiontype.setId(id);
		Attractiontype attractiontype2 = attractiontypeService.findById(id);
		if (attractiontype2 != null && !attractiontype2.equals("")) {
			attractiontypeService.update(attractiontype);
		} else {
			attractiontypeService.save(attractiontype);
		}
		return "redirect:/admin/attractiontype/list.do";
		
	}

	/**
	 * 修改attractiontype
	 * @param attractiontype
	 * @param model
	 * @return
	 * xiejin
	 */
	@RequestMapping("/update")
	public String update(Attractiontype attractiontype, Model model) {
		String id = attractiontype.getId();			
		attractiontype = attractiontypeService.findById(id);
		model.addAttribute("attractiontype", attractiontype);
		return "/admin/manage/attractiontype/attractiontypeUpdate.ftl";
	}
	
	/**
	 * 添加attractiontype
	 * @param attractiontype
	 * @param model
	 * @return
	 * xiejin
	 */
	@RequestMapping("/add")
	public String add(Attractiontype attractiontype, Model model){
		String id = Tools.getUUID();
		attractiontype.setId(id);
		attractiontype.setSort(attractiontypeService.getOrderId()+1);
		model.addAttribute("attractiontype", attractiontype);
		return "/admin/manage/attractiontype/attractiontypeAdd.ftl";
	}
}
