
package com.wenjing.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wenjing.entity.Cost;
import com.wenjing.entity.Costpayment;
import com.wenjing.service.CostService;
import com.wenjing.service.CostpaymentService;
import com.wenjing.util.Tools;

/**
 * 类说明		costpayment后台管理controller
 * @author xiejin
 * @date 2015-7-27 
 * @date 2015-7-27 下午4:36:30
 */
@Controller
@RequestMapping("/admin/costpayment")
public class CostpaymentController {
	@Resource
	private CostpaymentService costpaymentService;
	@Resource
	private CostService costService;
	
	/**
	 * 根据costnumber查询所有Costpayment
	 * @param model
	 * @param costnumber
	 * @return
	 * xiejin
	 */
	@RequestMapping("/list")
	public String findAll(Model model,@RequestParam("costnumber") String costnumber){
		List<Costpayment> costpaymentList = costpaymentService.findByCostnumber(costnumber);		
		Cost cost = costService.findById(costnumber);
		model.addAttribute("name", cost.getName());
		model.addAttribute("costpaymentList", costpaymentList);
		model.addAttribute("costnumber", costnumber);
		return "/admin/manage/costpayment/costpayment.ftl";
	}
	
	/**
	 * 根据id删除costpayment
	 * @param id
	 * @return
	 * xiejin
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id) {
		Costpayment costpayment = costpaymentService.findById(id);
		String costnumber = costpayment.getCostnumber();
		costpaymentService.delete(id);
		return "redirect:/admin/costpayment/list.do?costnumber="+costnumber;
	}
	
	/**
	 * 添加后保存
	 * @param costpayment
	 * @return
	 * xiejin
	 */
	@RequestMapping("/addSave")
	public String addSave(Costpayment costpayment) {
		String costnumber = costpayment.getCostnumber();
		costpaymentService.save(costpayment);
		return "redirect:/admin/costpayment/list.do?costnumber="+costnumber;
	}
	
	/**
	 * 修改后保存
	 * @param costpayment
	 * @return
	 * xiejin
	 */
	@RequestMapping("/updateSave")
	public String updateSave(Costpayment costpayment) {
		String costnumber = costpayment.getCostnumber();
		costpaymentService.update(costpayment);
		return "redirect:/admin/costpayment/list.do?costnumber="+costnumber;
	}

	/**
	 * 修改costpayment
	 * @param id
	 * @param model
	 * @return
	 * xiejin
	 */
	@RequestMapping("/update")
	public String update(@RequestParam("id") String id , Model model) {
		Costpayment costpayment = costpaymentService.findById(id);
		model.addAttribute("costpayment", costpayment);
		return "/admin/manage/costpayment/costpaymentUpdate.ftl";
	}
	
	/**
	 * 添加costpayment
	 * @param model
	 * @return
	 * xiejin
	 */
	@RequestMapping("/add")
	public String add(Model model,@RequestParam("costnumber")String costnumber){
		String id = Tools.getUUID();
		model.addAttribute("id", id);
		model.addAttribute("costnumber", costnumber);
		return "/admin/manage/costpayment/costpaymentAdd.ftl";
	}


}
