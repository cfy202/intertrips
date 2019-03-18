package com.wenjing.action.front;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wenjing.entity.Cost;
import com.wenjing.entity.Currency;
import com.wenjing.entity.Navigation;
import com.wenjing.entity.Region;
import com.wenjing.entity.Tag;
import com.wenjing.service.CostService;
import com.wenjing.service.CurrencyService;
import com.wenjing.service.NavigationService;
import com.wenjing.service.RegionService;
import com.wenjing.service.TagService;

/** 
 * @author 作者 E-mail: Bowden
 * @version 创建时间：2015-8-6 下午3:14:56 
 * 类说明  :  帮助中心
 */
@Controller
@RequestMapping("/front/help")
public class HelpCenterController {
	@Resource
	private RegionService regionService;
	@Resource
	private NavigationService navigationService;
	@Resource
	private HttpServletRequest request;
	@Resource
	private CurrencyService currencyService;
	@Resource
	private CostService costService;
	@Resource 
	private TagService tagService;
	
	/**
	 * @Title faq
	 * @Description 常见问题
	 * @Author Bowden
	 * @CreateDate 2015-8-6 下午3:17:34
	 */
	@RequestMapping("/faq")
	public String faq(Model model){
		findNavAndRegion(request, model);
		return "/front/help-center/problem.ftl";
	}
	
	/**
	 * @Title privacy
	 * @Description 隐私中心
	 * @Author Bowden
	 * @CreateDate 2015-8-6 下午3:51:05
	 */
	@RequestMapping("/privacy")
	public String privacy(Model model){
		findNavAndRegion(request, model);
		return "/front/tos/privacy.ftl";
	}
	
	/**
	 * @Title agreement
	 * @Description 客户协议
	 * @Author Bowden
	 * @CreateDate 2015-8-6 下午3:51:05
	 */
	@RequestMapping("/agreement")
	public String agreement(Model model){
		findNavAndRegion(request, model);
		return "/front/tos/agreement.ftl";
	}
	
	
	/**
	 * 查询上下导航及产品分类
	 */
	public void findNavAndRegion(HttpServletRequest request, Model model) {
		String costnumber = (String) request.getSession().getAttribute("costnumber");
		List<Navigation> navigation = navigationService.findByType(1, costnumber); // 查找上导航
		for (Navigation navigation2 : navigation) {
			String regionId = regionService.findIdByUrl(navigation2.getUrl());	//查询导航对应的分类
			if (regionId !=null) {
				List<Tag> tagList = tagService.findRegionShow(regionId, costnumber);	//查询分类对应的标签集合
				navigation2.setTagList(tagList);
			}
		}
		model.addAttribute("navigation", navigation);
		List<Navigation> navigation2 = navigationService.findByType(2, costnumber); // 查找下导航
		model.addAttribute("navigation2", navigation2);
		List<Region> region = regionService.findByCostnumber(1,costnumber); // 查找产品分类
		model.addAttribute("region", region);
		model.addAttribute("costnumber", costnumber);
		List<Currency> currencies = currencyService.findAll(); //获取币种列表
		model.addAttribute("currencies", currencies);
		Cost cost = costService.findById(costnumber);
		model.addAttribute("frontCode", cost.getCode());
	}
	
}
