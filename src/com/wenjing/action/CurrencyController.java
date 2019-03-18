package com.wenjing.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wenjing.entity.Currency;
import com.wenjing.service.CurrencyService;

/**
 * @author 作者 E-mail: bowden
 * @version 创建时间：2015-4-29 上午11:34:50
 *  类说明 : 后台币种管理 Controller
 */
@Controller
@RequestMapping("/admin/currency")
public class CurrencyController {

	@Resource
	private CurrencyService currencyService;

	/**
	 * 列表展示
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		List<Currency> currencies = currencyService.findAll();
		model.addAttribute("currencies", currencies);
		return "/admin/manage/currency/list.ftl";
	}

	/**
	 * 新增
	 */
	@RequestMapping("/add")
	public String add(Model model) {
		int sort = currencyService.getMaxSort();
		model.addAttribute("sort", sort + 1);
		return "/admin/manage/currency/add.ftl";
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public String update(@RequestParam("id") String id, Model model) {
		if (id != null && !"".equals(id)) {
			Currency currency = currencyService.findById(id);
			model.addAttribute("currency", currency);
		}
		return "/admin/manage/currency/update.ftl";
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public String save(Currency currency) {
		String id = currency.getId();
		if (id != null && !"".equals(id)) {
			currencyService.update(currency);
		} else {
			currencyService.insert(currency);
		}
		return "redirect:/admin/currency/list.do";
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id) {
		if (id != null && !"".equals(id)) {
			currencyService.delete(id);
		}
		return "redirect:/admin/currency/list.do";
	}
}
