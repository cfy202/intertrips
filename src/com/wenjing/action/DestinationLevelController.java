/**
 * 
 */
package com.wenjing.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wenjing.entity.DestinationLevel;
import com.wenjing.service.DestinationLevelService;
import com.wenjing.util.Tools;

/**
 * 类说明
 * @author xiejin
 * @date 2015-9-25 
 * @date 2015-9-25 下午3:45:15
 */
@Controller
@RequestMapping("/admin/destinationLevel")
public class DestinationLevelController {

	@Resource
	private DestinationLevelService destinationLevelService;
	
	/**
	 * 查询所有destinationLevel 
	 * @return
	 * xiejin
	 */
	@RequestMapping("/list")
	public String findAll(Model model){
		List<DestinationLevel> destinationLevel = destinationLevelService.findAll();		
		model.addAttribute("destinationLevel", destinationLevel);
		return "/admin/manage/destinationLevel/destinationLevel.ftl";
	}
	
	/**
	 * 根据id删除destinationLevel
	 * @param id
	 * @return
	 * xiejin
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id) {
		destinationLevelService.delete(id);
		return "redirect:/admin/destinationLevel/list.do";
	}

	/**
	 * 保存destinationLevel
	 * @param destinationLevel
	 * @param id
	 * @return
	 * xiejin
	 */
	@RequestMapping("/save")
	public String save(DestinationLevel destinationLevel,@RequestParam("id") String id) {
		destinationLevel.setId(id);
		DestinationLevel destinationLevel2 = destinationLevelService.findById(id);
		if (destinationLevel2 != null && !destinationLevel2.equals("")) {
			destinationLevelService.update(destinationLevel);
		} else {
			destinationLevelService.save(destinationLevel);
		}
		return "redirect:/admin/destinationLevel/list.do";
		
	}

	/**
	 * 修改destinationLevel
	 * @param destinationLevel
	 * @param model
	 * @return
	 * xiejin
	 */
	@RequestMapping("/update")
	public String update(DestinationLevel destinationLevel, Model model) {
		String id = destinationLevel.getId();			
		destinationLevel = destinationLevelService.findById(id);
		model.addAttribute("destinationLevel", destinationLevel);
		return "/admin/manage/destinationLevel/destinationLevelUpdate.ftl";
	}
	
	/**
	 * 添加destinationLevel
	 * @param destinationLevel
	 * @param model
	 * @return
	 * xiejin
	 */
	@RequestMapping("/add")
	public String add(DestinationLevel destinationLevel, Model model){
		String id = Tools.getUUID();
		destinationLevel.setId(id);
		model.addAttribute("destinationLevel", destinationLevel);
		return "/admin/manage/destinationLevel/destinationLevelAdd.ftl";
	}
}
