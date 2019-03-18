package com.wenjing.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wenjing.entity.Cost;
import com.wenjing.entity.Departure;
import com.wenjing.service.CostService;
import com.wenjing.service.DepartureService;
import com.wenjing.util.Tools;

/**
 * @author 作者 E-mail: bowden
 * @version 创建时间：2015-5-12 上午11:31:20
 *  类说明 : 出发地管理
 */
@Controller
@RequestMapping("/admin/departure")
public class DepartureController {

	@Resource
	private DepartureService departureService;
	@Resource
	private CostService costService;
	@Resource
	private HttpServletRequest request;

	/**
	 * 列表展示
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		//List<String> coList = Tools.getCostNumber(request);
		List<Departure> departures = departureService.findAll();
		model.addAttribute("departures", departures);
		return "/admin/manage/departure/list.ftl";
	}

	/**
	 * 新增
	 */
	@RequestMapping("/add")
	public String add(Model model) {
		List<String> coList = Tools.getCostNumber(request);
		String costnumber = coList.get(0);
		int maxSort = departureService.getMaxSort(costnumber);// 根据运营中心costnumber,默认查询第一个运营中心orderid最大值
		// 获取运营中心列表
		List<Cost> costs = costService.findAllCostByCostNumber(request);
		model.addAttribute("maxSort", maxSort + 1);
		model.addAttribute("costs", costs);
		return "/admin/manage/departure/add.ftl";
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public String update(@RequestParam("id") String id, Model model) {
		if (id != null && !"".equals(id)) {
			Departure departure = departureService.findById(id);
			// 获取运营中心
			//Cost costs = costService.findById(departure.getCostnumber());
			model.addAttribute("departure", departure);
			//model.addAttribute("costs", costs);
		}
		return "/admin/manage/departure/update.ftl";
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public String save(Departure departure) {
		String id = departure.getId();
		if (id != null && !"".equals(id)) {
			departureService.update(departure);
		} else {
			departureService.insert(departure);
		}
		return "redirect:/admin/departure/list.do";
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id, final RedirectAttributes rAttributes) {
		boolean flag = departureService.delete(id);
		if(flag){
			rAttributes.addFlashAttribute("message", "删除成功！");
		}else {
			rAttributes.addFlashAttribute("message", "删除失败，该出发地正在使用，请在出发日期中取消后再删除！");
		}
		return "redirect:/admin/departure/list.do";
	}

	/**
	 * 异步获取orderid最大值和上级导航
	 */
	@RequestMapping("/getsort")
	@ResponseBody
	public int getSort(@RequestParam("costnumber") String costnumber) {
		int maxOrderId = departureService.getMaxSort(costnumber);
		return maxOrderId + 1;
	}
	
	/**
	 * 异步查询此出发地id是否使用
	 */
	@RequestMapping("/isUse")
	@ResponseBody
	public Map<String, Object> isUse(@RequestParam("departureId") String departureId) {
		Map<String, Object> root = new HashMap<String, Object>();
		boolean flag = false;
		if(departureId != null && !"".equals(departureId)){
			 flag = departureService.findByDepartureId(departureId);
			 if(flag){
				 List<Departure> departures = departureService.findAll();
				 root.put("departures", departures);
			 }
		}
		root.put("flag", flag);
		root.put("departureId", departureId);
		return root;
	}
	
	/**
	 * @Title replaceDeparture
	 * @Description 替换出发日期中的出发地
	 * @Author Bowden
	 * @CreateDate 2015-10-23 下午4:11:30
	 */
	@RequestMapping("/replaceDeparture")
	public String replaceDeparture(final RedirectAttributes rAttributes){
		boolean flag = departureService.replaceDeparture(request);
		if(flag){
			rAttributes.addFlashAttribute("message", "替换成功！");
		}else {
			rAttributes.addFlashAttribute("message", "替换失败，请从新选择！");
		}
		return "redirect:/admin/departure/list.do";
	}
}
