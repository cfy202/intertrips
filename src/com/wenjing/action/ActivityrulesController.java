package com.wenjing.action;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wenjing.entity.Activityrules;
import com.wenjing.service.ActivityroulesService;

/**
 * 类说明后台优惠券抽奖活动规则管理ontroller
 * 
 * @author sevens
 * @date 2015-4-23
 */
@Controller
@RequestMapping("/admin/activityrules")
public class ActivityrulesController {

	@Resource
	private ActivityroulesService activityroulesService;
	
	
	/**
	 * 查询所有visa
	 * 
	 * @return sevens
	 */
	@RequestMapping("/list")
	public String findAll(Model model,@RequestParam("couponseid")String couponseid) {
		List<Activityrules> activityroles = activityroulesService.findByCouponseId(couponseid);
		model.addAttribute("activityroles", activityroles);
		model.addAttribute("couponseid",couponseid);
		return "/admin/manage/activityroules/activityroules.ftl";
	}

	/**
	 * 根据id删除规则
	 * 
	 * @param id
	 * @return sevens
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id")String id,@RequestParam("couponseid")String couponseid) {
		activityroulesService.delete(id);
		return "redirect:/admin/activityrules/list.do?couponseid="+couponseid;
	}


	/**
	 * 保存
	 * @param 规则
	 * @param id
	 * @return
	 */
	@RequestMapping("/save")
	public String save(Activityrules activityroules,@RequestParam("id") String id) {
		
		
		Activityrules activitys2 = activityroulesService.findById(id);
		if (activitys2 != null && !activitys2.equals("")) {
			activityroulesService.update(activityroules);
		} else {
			activityroules.setAddTime(new Date());
			activityroulesService.save(activityroules);
		}
		return "redirect:/admin/activityrules/list.do?couponseid="+activityroules.getCouponseid();
	}

	/**
	 * 添加，规则
	 * 
	 * @param region
	 * @param model
	 * @return sevens
	 */
	@RequestMapping("/update")
	public String update(Activityrules activityrules, Model model,@RequestParam("id")String id) {
		activityrules = activityroulesService.findById(id);
		model.addAttribute("activityrules", activityrules);
		return "/admin/manage/activityroules/activityroulesUpdate.ftl";
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
	public String add(Activityrules activityrules, Model model,@RequestParam("couponseid")String couponseid) {
		String id = UUID.randomUUID().toString();// 产生uuid
		if (null != id && id.contains("-")) {
			id = id.replaceAll("-", "");
		}
		activityrules.setId(id);
		model.addAttribute("couponseid",couponseid);
		model.addAttribute("activityrules", activityrules);
		

		return "/admin/manage/activityroules/activityroulesAdd.ftl";
	}
}
