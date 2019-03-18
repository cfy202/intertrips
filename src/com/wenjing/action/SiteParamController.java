package com.wenjing.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wenjing.entity.Sitparam;
import com.wenjing.service.SiteParamService;

/**
 * @author 作者 E-mail:bowden
 * @version 创建时间：2015-4-27 下午1:09:47
 *  类说明 : 页面系统参数 Controller
 */
@Controller
@RequestMapping("/admin/siteparam")
public class SiteParamController {
	@Resource
	private SiteParamService siteParamService;

	/**
	 * 列表展示
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		List<Sitparam> siteparams = siteParamService.findAll();
		model.addAttribute("siteparams", siteparams);
		return "/admin/manage/siteparam/siteparamlist.ftl";
	}

	/**
	 * 新增
	 */
	@RequestMapping("/add")
	public String add(Model model) {

		return "/admin/manage/siteparam/siteparamadd.ftl";
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public String update(@RequestParam("id") String id, Model model) {
		if (id != null && !"".equals(id)) {
			Sitparam siteparam = siteParamService.findById(id);
			model.addAttribute("siteparam", siteparam);
		}
		return "/admin/manage/siteparam/siteparamupdate.ftl";
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public String save(Sitparam siteparam) {
		String id = siteparam.getId();
		if (id != null && !"".equals(id)) {
			// update
			siteParamService.update(siteparam);
		} else {
			// insert
			siteParamService.insert(siteparam);
		}
		return "redirect:/admin/siteparam/list.do";
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id) {
		if (id != null && !"".equals(id)) {
			siteParamService.delete(id);
		}
		return "redirect:/admin/siteparam/list.do";
	}
}
