package com.wenjing.action;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wenjing.entity.Visaoccupation;
import com.wenjing.service.VisaoccupationService;

/**
 * 类说明 后台目录管理controller
 * 
 * @author sevens
 * @date 2015-4-23
 */
@Controller
@RequestMapping("/admin/visaocc")
public class VisaoccupationController {

	@Resource
	private VisaoccupationService visaoccupationService;
	
	
	/**
	 * 查询所有visa
	 * 
	 * @return sevens
	 */
	@RequestMapping("/list")
	public String findAll(Model model,@RequestParam("visaId")String visaId) {
		List<Visaoccupation> visaocclist = visaoccupationService.findAll(visaId);
		model.addAttribute("visaocclist", visaocclist);
		model.addAttribute("visaId",visaId);
		return "/admin/manage/visaocc/visaocc.ftl";
	}

	/**
	 * 根据id删除visaoccupation
	 * 
	 * @param id
	 * @return sevens
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id")String id,@RequestParam("visaId")String visaId) {
		visaoccupationService.delete(id);
		return "redirect:/admin/visaocc/list.do?visaId="+visaId;
	}


	/**
	 * 保存
	 * @param visaoccupation
	 * @param id
	 * @return
	 */
	@RequestMapping("/save")
	public String save(Visaoccupation visaoccupation,@RequestParam("id") String id) {
		
		
		Visaoccupation visa2 = visaoccupationService.findById(id);
		if (visa2 != null && !visa2.equals("")) {
			visaoccupationService.update(visaoccupation);
		} else {
			visaoccupationService.save(visaoccupation);
		}
		return "redirect:/admin/visaocc/list.do?visaId="+visaoccupation.getVisaId();
	}

	/**
	 * 添加，修改tree
	 * 
	 * @param region
	 * @param model
	 * @return sevens
	 */
	@RequestMapping("/update")
	public String update(Visaoccupation visaoccupation, Model model) {
		String id = visaoccupation.getId();
		visaoccupation = visaoccupationService.findById(id);
		model.addAttribute("visaoccupation", visaoccupation);
		return "/admin/manage/visaocc/visaoccUpdate.ftl";
	}

	/**
	 * 添加Tree
	 * 
	 * @param Tree
	 * @param model
	 * 
	 * @return sevens
	 */
	@RequestMapping("/add")
	public String add(Visaoccupation visaoccupation, Model model,@RequestParam("visaId")String visaId) {
		String id = UUID.randomUUID().toString();// 产生uuid
		if (null != id && id.contains("-")) {
			id = id.replaceAll("-", "");
		}
		visaoccupation.setId(id);
		Integer sort = visaoccupationService.getMaxSort();
		visaoccupation.setSort(sort+1);
		model.addAttribute("visaId",visaId);
		model.addAttribute("visaoccupation", visaoccupation);
		

		return "/admin/manage/visaocc/visaoccAdd.ftl";
	}
}
