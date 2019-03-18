package com.wenjing.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenjing.entity.Cost;
import com.wenjing.entity.Navigation;
import com.wenjing.service.CostService;
import com.wenjing.service.NavigationService;
import com.wenjing.util.Tools;

/**
 * @author 作者 E-mail: bowden
 * @version 创建时间：2015-5-4 上午9:22:55 
 * 类说明 : 导航管理 Controller
 */
@Controller
@RequestMapping("/admin/navigation")
public class NavigationController {

	@Resource
	private NavigationService navigationService;
	@Resource
	private CostService costService;
	@Resource
	private HttpServletRequest request;

	/**
	 * 导航列表展示
	 */
	@RequestMapping("/list")
	public String list(Model model,HttpServletResponse response) {
		List<Navigation> navigations = navigationService.findAllByCostNumber( request, response, model);
		model.addAttribute("navigations", navigations);
		return "/admin/manage/navigation/list.ftl";
	}

	/**
	 * 新增
	 */
	@RequestMapping("/add")
	public String add(Model model) {
		List<String> coList = Tools.getCostNumber(request);
		String costnumber = coList.get(0);
		Integer maxOrderId = navigationService.getMaxOrderId(costnumber);// 根据运营中心costnumber,默认查询第一个运营中心orderid最大值
		List<Navigation> navigation2 = navigationService.findByUpIdAndCostid(
				costnumber, 1);// 默认查询upid=root, 导航类型为1(上导航)
		// 获取运营中心列表
		List<Cost> costs = costService.findAllCostByCostNumber(request);
		model.addAttribute("navigation2", navigation2);
		model.addAttribute("maxOrderId", maxOrderId + 1);
		model.addAttribute("costs", costs);
		return "/admin/manage/navigation/add.ftl";
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public String update(@RequestParam("id") String id, Model model) {
		Navigation navigation = navigationService.findById(id);
		if (navigation.getType() == null) {
			navigation.setType(0);
		}
		List<Navigation> navigation2 = navigationService.findNotContainSon(navigation);
		// 获取运营中心
		Cost costs = costService.findById(navigation.getCostnumber());
		model.addAttribute("navigation2", navigation2);
		model.addAttribute("navigation", navigation);
		model.addAttribute("costs", costs);
		return "/admin/manage/navigation/update.ftl";
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public String save(Navigation navigation) {
		String id = navigation.getId();
		Integer isShow = navigation.getIsShow();
		if (isShow==null) {
			navigation.setIsShow(0);
		}
		if (id != null && !"".equals(id)) {
			Navigation oldnavigation = navigationService.findById(id);
			navigationService.update(navigation, oldnavigation);
		} else {
			navigationService.insert(navigation);
		}
		return "redirect:/admin/navigation/list.do";
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id) {
		navigationService.delete(id);
		return "redirect:/admin/navigation/list.do";
	}

	/**
	 * 异步获取orderid最大值和上级导航
	 */
	@RequestMapping("/getsort")
	@ResponseBody
	public Map<String, Object> getSort(
			@RequestParam("costnumber") String costnumber,
			@RequestParam("type") Integer type) {
		Map<String, Object> root = new HashMap<String, Object>();
		List<Navigation> navigations = null;
		String id = request.getParameter("id");
		if (id != null && !"".equals(id)) {
			Navigation navigation = navigationService.findById(id);
			navigation.setType(type);
			navigations = navigationService.findNotContainSon(navigation);
		} else {
			navigations = navigationService.findByUpIdAndCostid(costnumber,
					type);
		}
		// int maxOrderId = navigationService.getMaxOrderId(costnumber);
		root.put("navigations", navigations);
		// root.put("maxOrderId", maxOrderId + 1);
		return root;
	}
}
