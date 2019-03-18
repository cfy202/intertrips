
package com.wenjing.action;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wenjing.dao.RoleMapper;
import com.wenjing.entity.Cost;
import com.wenjing.service.CostService;

/**
 * 类说明		后台运营中心管理controller
 * @author sevens
 * @date 2015-4-23 
 */
@Controller
@RequestMapping("/admin/cost")
public class CostController {

	@Resource
	private CostService costService;
	@Resource
	private RoleMapper roleMapper;
	
	/**
	 * 查询所有cost
	 * @return
	 * sevens
	 */
	@RequestMapping("/list")
	public String findAll(Model model){
		List<Cost> costs = costService.findAll();		
		model.addAttribute("costs", costs);
		return "/admin/manage/cost/cost.ftl";
	}
	
	/**
	 * 根据id删除cost
	 * @param id
	 * @return
	 * sevens
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id) {
		costService.delete(id);
		return "redirect:/admin/cost/list.do";
	}

	/**
	 * 保存cost
	 * @param cost
	 * @param id
	 * @return
	 * sevens
	 */
	@RequestMapping("/save")
	public String save(Cost costs,@RequestParam("id") String id) {
		costs.setId(id);
		Cost cost2 = costService.findById(id);
		if (cost2 != null && !cost2.equals("")) {
			costService.update(costs);
		} else {
			costService.save(costs);
		}
		return "redirect:/admin/cost/list.do";
		
	}

	/**
	 * 添加，修改cost
	 * @param cost
	 * @param model
	 * @return
	 * sevens
	 */
	@RequestMapping("/update")
	public String update(Cost costs, Model model) {
		String id = costs.getId();			
		costs = costService.findById(id);
		model.addAttribute("costs", costs);
		return "/admin/manage/cost/costUpdate.ftl";
	}
	
	/**
	 * 添加cost
	 * @param cost
	 * @param model
	 * 
	 * @return
	 * sevens
	 */
	@RequestMapping("/add")
	public String add(Cost costs, Model model){
		String id = UUID.randomUUID().toString();//产生uuid
		if (null!=id && id.contains("-")){  
			id = id.replaceAll("-", "");  
        }
		costs.setId(id);
		model.addAttribute("costs", costs);
		return "/admin/manage/cost/costAdd.ftl";
	}
}
