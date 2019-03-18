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

import com.wenjing.entity.AttractionLevel;
import com.wenjing.service.AttractionLevelService;
import com.wenjing.util.Tools;

/**
 * 类说明
 * @author xiejin
 * @date 2015-9-25 
 * @date 2015-9-25 下午2:53:03
 */
@Controller
@RequestMapping("/admin/attractionLevel")
public class AttractionLevelController {

	@Resource
	private AttractionLevelService attractionLevelService;
	
	/**
	 * 查询所有attractiontype 
	 * @return
	 * xiejin
	 */
	@RequestMapping("/list")
	public String findAll(Model model){
		List<AttractionLevel> attractionLevel = attractionLevelService.findAll();		
		model.addAttribute("attractionLevel", attractionLevel);
		return "/admin/manage/attractionLevel/attractionLevel.ftl";
	}
	
	/**
	 * 根据id删除attractiontype
	 * @param id
	 * @return
	 * xiejin
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id) {
		attractionLevelService.delete(id);
		return "redirect:/admin/attractionLevel/list.do";
	}

	/**
	 * 保存attractiontype
	 * @param attractiontype
	 * @param id
	 * @return
	 * xiejin
	 */
	@RequestMapping("/save")
	public String save(AttractionLevel attractionLevel,@RequestParam("id") String id) {
		attractionLevel.setId(id);
		AttractionLevel attractionLevel2 = attractionLevelService.findById(id);
		if (attractionLevel2 != null && !attractionLevel2.equals("")) {
			attractionLevelService.update(attractionLevel);
		} else {
			attractionLevelService.save(attractionLevel);
		}
		return "redirect:/admin/attractionLevel/list.do";
		
	}

	/**
	 * 修改attractiontype
	 * @param attractiontype
	 * @param model
	 * @return
	 * xiejin
	 */
	@RequestMapping("/update")
	public String update(AttractionLevel attractionLevel, Model model) {
		String id = attractionLevel.getId();			
		attractionLevel = attractionLevelService.findById(id);
		model.addAttribute("attractionLevel", attractionLevel);
		return "/admin/manage/attractionLevel/attractionLevelUpdate.ftl";
	}
	
	/**
	 * 添加attractiontype
	 * @param attractiontype
	 * @param model
	 * @return
	 * xiejin
	 */
	@RequestMapping("/add")
	public String add(AttractionLevel attractionLevel, Model model){
		String id = Tools.getUUID();
		attractionLevel.setId(id);
		model.addAttribute("attractionLevel", attractionLevel);
		return "/admin/manage/attractionLevel/attractionLevelAdd.ftl";
	}


}
