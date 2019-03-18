package com.wenjing.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wenjing.entity.Slider;
import com.wenjing.service.CostService;
import com.wenjing.service.SliderService;
import com.wenjing.util.Tools;

/**
 * @author 作者 E-mail:bowden
 * @version 创建时间：2015-4-24 下午6:49:23 
 * 类说明 ：幻灯片管理 - Controller
 */
@Controller
@RequestMapping("/admin/slider")
public class SliderController {
	@Resource
	private SliderService sliderService;
	@Resource
	private HttpServletRequest request;
	@Resource
	private CostService costService;

	/**
	 * 列表展示
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		List<String> cosList = Tools.getCostNumber(request);
		List<Slider> sliders = sliderService.findAllByCostNumber(cosList);
		model.addAttribute("sliders", sliders);
		return "/admin/manage/slider/sliderlist.ftl";
	}

	/**
	 * 新增
	 */
	@RequestMapping("/add")
	public String add(Model model) {
		List<String> coList = Tools.getCostNumber(request);
		String costnumber = coList.get(0);
		Integer maxSort = sliderService.getMaxSort(costnumber, 1); //默认查询首页幻灯片最大排序号
		model.addAttribute("maxSort", maxSort+1);
		return "/admin/manage/slider/slideradd.ftl";
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public String update(@RequestParam("id") String id, Model model) {
		if (id != null && !"".equals(id)) {
			Slider slider = sliderService.findById(id);
			model.addAttribute("slider", slider);
		}
		return "/admin/manage/slider/sliderupdate.ftl";
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public String save(Slider slider) {
		String id = slider.getId();
		if (id != null && !"".equals(id)) {
			// update
			sliderService.update(slider);
		} else {
			// insert
			sliderService.insert(slider);
		}
		return "redirect:/admin/slider/list.do";
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id) {
		if (id != null && !"".equals(id)) {
			sliderService.delete(id);
		}
		return "redirect:/admin/slider/list.do";
	}
	
	/**
	 * 幻灯片图片分页显示
	 */
	@ResponseBody
	@RequestMapping("/sliderimage")
	public Map<String, Object> sliderImage(HttpServletRequest request, Model model) {
		Map<String, Object> root = sliderService.sliderImage(request, model);
		return root;
	}

	/**
	 * 图库图片分页显示
	 */
	@ResponseBody
	@RequestMapping("/image")
	public Map<String, Object> showImage(HttpServletRequest request,
			Model model,@Param("usetype") String usetype,@Param("usetype1")String usetype1) {
		Map<String, Object> root = sliderService.showImageByPage(request,
				model,usetype,usetype1);
		return root;
	}
	
	/**
	 * 图库图片分页显示
	 */
	@ResponseBody
	@RequestMapping("/imageTourlineUp")
	public Map<String, Object> imageTourlineUp(HttpServletRequest request,
			Model model) {
		Map<String, Object> root = sliderService.imageTourlineUp(request, model);
		return root;
	}
	
	/**
	 * 异步获取orderid最大值和上级导航
	 */
	@RequestMapping("/getsort")
	@ResponseBody
	public int getSort(
	        @RequestParam("costnumber") String costnumber,
			@RequestParam("type") Integer type) {
		int maxSort = sliderService.getMaxSort(costnumber, type);
		return maxSort + 1;
	}
}
