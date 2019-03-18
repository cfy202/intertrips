package com.wenjing.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wenjing.dao.CostMapper;
import com.wenjing.dao.RolecostMapper;
import com.wenjing.dao.RoletreeMapper;
import com.wenjing.entity.Admin;
import com.wenjing.entity.Cost;
import com.wenjing.entity.Role;
import com.wenjing.entity.Rolecost;
import com.wenjing.entity.Roletree;
import com.wenjing.entity.Setoperater;
import com.wenjing.entity.Tree;
import com.wenjing.service.RoleService;
import com.wenjing.service.SetoperaterService;
import com.wenjing.service.TreeService;
import com.wenjing.util.Tools;

/**
 * 类说明 后台角色管理controller
 * 
 * @author sevens
 * @date 2015-4-23
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController {

	@Resource
	private RoleService roleService;
	@Resource
	private SetoperaterService setoperaterService;
	@Resource
	private TreeService treeService;
	@Resource
	private RolecostMapper rolecostMapper;
	@Resource
	private CostMapper costMapper;
	@Autowired
	private HttpServletRequest request;
	@Resource
	private RoletreeMapper roletreeMapper;

	/**
	 * 查询所有role
	 * 
	 * @return sevens
	 */
	@RequestMapping("/list")
	public String findAll(Model model, String costnumber) {
		List<Role> role = roleService.findAll();
		model.addAttribute("role", role);
		return "/admin/manage/role/role.ftl";
	}

	/**
	 * 根据id删除role
	 * 
	 * @param id
	 * @return sevens
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id) {
		roleService.delete(id);
		return "redirect:/admin/role/list.do";
	}

	/**
	 * 保存role
	 * 
	 * @param role
	 * @param id
	 * @return sevens
	 */
	@RequestMapping("/save")
	public String save(Role role, @RequestParam("id") String id) {
		role.setId(id);
		int actions = 0;
		String[] treeids = request.getParameterValues("treeids");
		String[] costids = request.getParameterValues("costids");
		Role role2 = roleService.findById(id);
		if (role2 != null && !role2.equals("")) {
			roleService.update(role);
		} else {
			actions = 1;
			roleService.save(role);
		}
		roleService.updateregest(role, treeids, costids, actions,request);
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		if(admin!=null){
			request.getSession().removeAttribute("tree");
			request.getSession().removeAttribute("cost");
			List<Cost> costlist = costMapper.findAllByRoleId(admin.getRoleid());
			List<Tree> treelist = treeService.findAllByRole(admin.getRoleid());
			request.getSession().setAttribute("tree", treelist);
			request.getSession().setAttribute("cost", costlist);
		}
		return "redirect:/admin/role/list.do";

	}

	/**
	 * 添加，修改tree
	 * 
	 * @param region
	 * @param model
	 * @return sevens
	 */
	@RequestMapping("/update")
	public String update(Role role, Model model) {

		String id = role.getId();

		role = roleService.findById(id);
		model.addAttribute("role", role);

		// System.out.println(role);

		List<Roletree> roletree = roletreeMapper.selectByRoleid(id);
		model.addAttribute("roletree", roletree);

		// System.out.println(roletree);

		List<Rolecost> rolecost = rolecostMapper.selectByRoleid(id);
		model.addAttribute("rolecost", rolecost);

		// System.out.println(rolecost);

		List<Tree> treelist = treeService.findAll();
		
		for (Tree tree : treelist) {
			if(tree.getUrl()!=null&&!"".equals(tree.getUrl())){
				tree.setUrl(tree.getUrl().substring(0,tree.getUrl().lastIndexOf("/")));
			}
		}
		model.addAttribute("treelist", treelist);

		// System.out.println(treelist);

		List<Setoperater> setoperater = setoperaterService.findAll();
		model.addAttribute("setoperater", setoperater);

		List<Cost> costlist = costMapper.findAll();
		model.addAttribute("costlist", costlist);
        
		List<Setoperater> operaterlist = setoperaterService.findAll();
		model.addAttribute("operaterlist", operaterlist);
		// System.out.println("fred");

		return "/admin/manage/role/roleUpdate.ftl";
	}

	/**
	 * 添加role
	 * 
	 * @param role
	 * @param model
	 * 
	 * @return sevens
	 */
	@RequestMapping("/add")
	public String add(Role role, Model model) {
		String id =Tools.getUUID();// 产生uuid
		
		role.setId(id);

		model.addAttribute("role", role);
		List<Tree> treelist = treeService.findAll();
		for (Tree tree : treelist) {
			if(tree.getUrl()!=null&&!"".equals(tree.getUrl())){
				tree.setUrl(tree.getUrl().substring(0,tree.getUrl().lastIndexOf("/")));
			}
			
		}
		model.addAttribute("treelist", treelist);
		List<Setoperater> operaterlist = setoperaterService.findAll();
		model.addAttribute("operaterlist", operaterlist);
		List<Cost> costlist = costMapper.findAll();
		model.addAttribute("costlist",costlist);
		return "/admin/manage/role/roleAdd.ftl";
	}
}
