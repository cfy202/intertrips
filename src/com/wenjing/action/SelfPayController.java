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

import com.alibaba.fastjson.JSON;
import com.wenjing.entity.Cost;
import com.wenjing.entity.Destination;
import com.wenjing.entity.Selfpay;
import com.wenjing.entity.Selfpaycurrency;
import com.wenjing.service.CostService;
import com.wenjing.service.CurrencyService;
import com.wenjing.service.DestinationService;
import com.wenjing.service.SelfPayService;
import com.wenjing.util.Tools;

/**
 * @author 作者 E-mail: bowden
 * @version 创建时间：2015-5-4 下午6:19:34
 *  类说明 ：自费项目 Controller
 */
@Controller
@RequestMapping("/admin/selfpay")
public class SelfPayController {

	@Resource
	private SelfPayService selfPayService;
	@Resource
	private DestinationService destinationService;
	@Resource
	private HttpServletRequest request;
	@Resource
	private CostService costService;
	@Resource
	private CurrencyService currencyService;

	/**
	 * 列表展示
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		List<String> cosList = Tools.getCostNumber(request);
		List<Selfpay> selfpays = selfPayService.findAllByCostNumber(cosList);
		//List<Selfpay> selfpays = selfPayService.findAll();
		model.addAttribute("selfpays", selfpays);
		return "/admin/manage/selfpay/list.ftl";
	}

	/**
	 * 新增
	 */
	@RequestMapping("/add")
	public String add(Model model) {
		List<String> coList = Tools.getCostNumber(request);
		int maxSort = selfPayService.getMaxSort(coList.get(0));
		List<Destination> destinations = destinationService.findCityByCostNumber();
		// 获取运营中心列表
		List<Cost> costs = costService.findAllCostByCostNumber(request);
		// 获取币种列表
		
		model.addAttribute("maxSort", maxSort + 1);
		model.addAttribute("destinations", destinations);
		model.addAttribute("costs", costs);
		
		return "/admin/manage/selfpay/add.ftl";
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public String update(@RequestParam("id") String id, Model model) {
		if (id != null && !"".equals(id)) {
			Selfpay selfpay = selfPayService.findById(id);
			List<Destination> destinations = destinationService.findCityByCostNumber();
			//Cost costs = costService.findById(selfpay.getCostnumber());
			List<Selfpaycurrency> selfpaycurrencies = selfPayService.findPriceBySelfpayId(id);// 价格
			model.addAttribute("selfpay", selfpay);
			model.addAttribute("destinations", destinations);
			//model.addAttribute("costs", costs);
			model.addAttribute("selfpaycurrencies", selfpaycurrencies);
		}
		return "/admin/manage/selfpay/update.ftl";
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public String save(Selfpay selfpay) {
		String id = selfpay.getId();
		if (id != null && !"".equals(id)) {
			selfPayService.update(selfpay);
		} else {
			selfPayService.insert(selfpay);
		}
		return "redirect:/admin/selfpay/list.do";
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id) {
		selfPayService.delete(id);
		return "redirect:/admin/selfpay/list.do";
	}

	/**
	 * 异步获取orderid最大值和上级导航
	 */
	@RequestMapping("/getsort")
	@ResponseBody
	public Map<String, Object> getSort(
			@RequestParam("costnumber") String costnumber) {
		Map<String, Object> root = new HashMap<String, Object>();
		List<Destination> destinations = destinationService.findCityByCostNumber();
		int maxSort = selfPayService.getMaxSort(costnumber);
		root.put("destinations", destinations);
		root.put("maxSort", maxSort + 1);
		return root;
	}
	
	/**
	 * 同步自选项
	 */
	@RequestMapping("/sychronizeSelfpay")
	public String sychronizeSelfpay(String[] selfPayIds) {
		System.out.println(JSON.toJSONString(selfPayIds));
		return "redirect:/admin/selfpay/list.do";
	}
}
