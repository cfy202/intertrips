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

import com.wenjing.entity.Cost;
import com.wenjing.entity.Currency;
import com.wenjing.entity.Product;
import com.wenjing.entity.Promotion;
import com.wenjing.service.CostService;
import com.wenjing.service.CurrencyService;
import com.wenjing.service.NavigationService;
import com.wenjing.service.PageService;
import com.wenjing.service.ProductService;
import com.wenjing.service.PromotionService;
import com.wenjing.service.RegionService;
import com.wenjing.service.TagService;
import com.wenjing.service.TourDateService;
import com.wenjing.service.TourlineService;
import com.wenjing.util.Tools;

/**
 * @author 作者 E-mail: bowden
 * @version 创建时间：2015-4-23 下午2:01:15 
 * 类说明 : Controller - 促销
 */
@Controller
@RequestMapping("/admin/promotion")
public class PromotionController {
	@Resource
	private PromotionService promotionService;
	@Resource
	private ProductService productService;
	@Resource
	private CurrencyService currencyService;
	@Resource
	private CostService costService;
	@Resource
	private HttpServletRequest request;
	@Resource
	private NavigationService navigationService;
	@Resource
	private RegionService regionService;
	@Resource
	private TourlineService tourlineService;
	@Resource
	private TagService tagService;
	@Resource
	private TourDateService tourDateService;
	@Resource
	private PageService pageService;

	/**
	 * 促销活动列表展示
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		List<Promotion> promotions = promotionService
				.findAllByCostNumber(request);
		model.addAttribute("promotions", promotions);
		return "/admin/manage/promotion/promotionlist.ftl";
	}

	/**
	 * 添加促销活动
	 */
	@RequestMapping("/add")
	public String add(Model model) {
		List<String> coList = Tools.getCostNumber(request);
		String costnumber = coList.get(0);
		Integer sort = promotionService.getMaxSort(costnumber);// 根据运营中心costnumber,默认查询第一个运营中心orderid最大值
		// 获取运营中心列表
		List<Cost> costs = costService.findAllCostByCostNumber(request);
		// 获取币种列表
		List<Currency> currencies = currencyService.findAll();
		// 根据costnumber获取产品列表
		List<Product> products = productService.findAllByCostnumber(costnumber);
		String nowhour = Tools.getDtime();
		model.addAttribute("sort", sort + 1);
		model.addAttribute("costs", costs);
		model.addAttribute("currencies", currencies);
		model.addAttribute("products", products);
		model.addAttribute("nowhour", nowhour);
		return "/admin/manage/promotion/promotionadd.ftl";
	}

	/**
	 * 修改促销活动
	 */
	@RequestMapping("/update")
	public String update(Model model, @RequestParam("id") String id) {
		Promotion promotion = promotionService.findPromotionById(id);
		// 获取运营中心
		Cost costs = costService.findById(promotion.getCostnumber());
		// 获取币种列表
		List<Currency> currencies = currencyService.findAll();
		// 获取参与活动产品列表
		List<Product> promotionproduct = productService.findByPromotionId(id);
		// 根据costnumber获取产品列表
		List<Product> products = productService.findAllByCostnumber(promotion
				.getCostnumber());
		model.addAttribute("promotion", promotion);
		model.addAttribute("costs", costs);
		model.addAttribute("currencies", currencies);
		model.addAttribute("products", products);
		model.addAttribute("promotionproduct", promotionproduct);
		return "/admin/manage/promotion/promotionUpdate.ftl";
	}

	/**
	 * 保存
	 * 
	 */
	@RequestMapping("/save")
	public String save(Promotion promotion) {
		String id = promotion.getId();
		if (id != null && !"".equals(id)) {
			// update
			promotionService.update(promotion, request);
		} else {
			// insert
			promotionService.insert(promotion, request);
		}
		return "redirect:/admin/promotion/list.do";
	}

	/**
	 * 删除
	 * 
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id) {
		promotionService.delete(id);
		return "redirect:/admin/promotion/list.do";
	}

	/**
	 * 异步获取orderid最大值和上级导航
	 */
	@RequestMapping("/getsort")
	@ResponseBody
	public Map<String, Object> getSort(
			@RequestParam("costnumber") String costnumber) {
		Map<String, Object> root = new HashMap<String, Object>();
		List<Product> products = productService.findAllByCostnumber(costnumber);
		Integer sort = promotionService.getMaxSort(costnumber);// 根据运营中心costnumber,查询运营中心orderid最大值
		root.put("products", products);
		root.put("sort", sort + 1);
		return root;
	}
	
	/**
	 * @Title: viewTourlinePromotion
	 * @Description: 促销活动线路展示页面预览
	 * @param costnumber
	 * @param id
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 */
	@RequestMapping(value="/view")
	public String viewTourlinePromotion(@RequestParam("id") String id,Model model){
		Promotion promotion = promotionService.findPromotionById(id);
		pageService.getNavigation(model, promotion.getCostnumber());								//获取上，下导航
		tourlineService.findByPromotionId(promotion.getCostnumber(), id, model);	//查询参与某一促销活动的线路
		model.addAttribute("now",Tools.getTime());
		model.addAttribute("promotion",promotion);
		return "/front/promotionTourline.ftl";
	}
	
	/**
	 * @Title: viewTourlinePromotion
	 * @Description: 促销活动线路展示页面生成
	 * @param costnumber
	 * @param id
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 */
	@RequestMapping(value="/create")
	public String createTourlinePromotion(@RequestParam("id") String id,@RequestParam("filePath") String filePath,HttpServletRequest request){
		Promotion promotion = promotionService.findPromotionById(id);
		promotionService.createTourlinePromotion(promotion,request);
		return "redirect:/admin/promotion/list.do";
	}
	
	/**
	 * @Title: queryTourline
	 * @Description: 异步查询线路展示
	 * @return    
	 * @return Map<String,Object>    返回类型
	 * @author xiejin
	 */
	@RequestMapping("/queryTourline")
	@ResponseBody
	public Map<String, Object> queryTourline() {
		String costnumber = request.getParameter("costnumber");
		String tourCode = request.getParameter("tourCode");
		String tourName = request.getParameter("tourName");
		String regionName = request.getParameter("regionName");
		Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
		Map<String, Object> root = new HashMap<String, Object>();
		root = productService.findProductByCondition(root, costnumber, tourCode, tourName, regionName, pageSize, request);
		Integer sort = promotionService.getMaxSort(costnumber);// 根据运营中心costnumber,查询运营中心orderid最大值
		root.put("sort", sort + 1);
		return root;
	}
}
