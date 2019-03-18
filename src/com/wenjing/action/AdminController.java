package com.wenjing.action;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wenjing.dao.RoleMapper;
import com.wenjing.entity.Admin;
import com.wenjing.entity.Role;
import com.wenjing.service.AdminService;
import com.wenjing.util.Tools;

/**
 * 类说明 后台用户管理controller
 * 
 * @author sevens
 * @date 2015-4-23
 */
@Controller
@RequestMapping("/admin/manager")
public class AdminController {

	@Resource
	private AdminService adminService;
	@Resource
	private RoleMapper roleMapper;
	@Resource
	private HttpServletRequest request;

	/**
	 * 查询所有admin
	 * 
	 * @return sevens
	 */
	@RequestMapping("/list")
	public String findAll(Model model) {
		List<Admin> adminlist = adminService.findAll();
		model.addAttribute("adminlist", adminlist);
		return "/admin/manage/admin/admin.ftl";
	}

	/**
	 * 根据id删除admin
	 * 
	 * @param id
	 * @return sevens
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id) {
		adminService.delete(id);
		return "redirect:/admin/manager/list.do";
	}

	/**
	 * 保存admin
	 * 
	 * @param admin
	 * @param id
	 * @return sevens
	 */
	@RequestMapping("/save")
	public String save(Admin adminup, @RequestParam("id") String id) {
		Admin admin2 = null;
		if (id != null && !"".equals(id)) {
			adminup.setId(id);
			admin2 = adminService.findById(id);
		}
		if (admin2 != null && !admin2.equals("")) {
			adminService.update(adminup);
		} else {
			adminup.setId(Tools.getUUID());
			adminup.setPassword(Tools.MD5(adminup.getPassword()));
			adminService.save(adminup);
		}
		return "redirect:/admin/manager/list.do";

	}

	/**
	 * 添加，修改admin
	 * 
	 * @param region
	 * @param model
	 * @return sevens
	 */
	@RequestMapping("/update")
	public String update(@RequestParam("id") String id, Model model) {

		Admin adminup = adminService.findById(id);
		model.addAttribute("adminup", adminup);
		List<Role> rolelist = roleMapper.findAll();
		model.addAttribute("rolelist", rolelist);
		return "/admin/manage/admin/adminUpdate.ftl";
	}

	/**
	 * 添加admin
	 * 
	 * @param admin
	 * @param model
	 * 
	 * @return sevens
	 */
	@RequestMapping("/add")
	public String add(Admin admin, Model model) {
		String id = UUID.randomUUID().toString();// 产生uuid
		if (null != id && id.contains("-")) {
			id = id.replaceAll("-", "");
		}
		admin.setId(id);

		model.addAttribute("admin", admin);
		List<Role> rolelist = roleMapper.findAll();
		model.addAttribute("rolelist", rolelist);

		return "/admin/manage/admin/adminAdd.ftl";
	}

}
