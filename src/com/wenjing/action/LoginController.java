package com.wenjing.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenjing.dao.AttractionMapper;
import com.wenjing.dao.MemberMapper;
import com.wenjing.dao.OrdersMapper;
import com.wenjing.dao.TourlineMapper;
import com.wenjing.entity.Admin;
import com.wenjing.entity.Cost;
import com.wenjing.entity.ResultDTO;
import com.wenjing.entity.Tourline;
import com.wenjing.entity.Tree;
import com.wenjing.service.AdminService;
import com.wenjing.service.CostService;
import com.wenjing.service.RoleService;
import com.wenjing.service.TreeService;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;

@RequestMapping("/admin")
@Controller
public class LoginController {
	public static String sysname = "wj";
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private AdminService adminService;
	@Autowired
	private CostService costService;
	@Autowired
	private TreeService treeService;
	@Autowired
	private RoleService roleService;
	@Resource
	private MemberMapper memberMapper;
	@Resource
	private TourlineMapper tourlineMapper;
	@Resource
	private OrdersMapper ordersMapper;
	@Resource
	private AttractionMapper attractionMapper;
	@RequestMapping(value = "/list")
	public String findAll(Model map) {
		String mesge = request.getParameter("message");
		if(mesge!=null){
			request.getSession().setAttribute("Competence", "你没有"+mesge+"的访问权限，请与管理员联系！");
			
		}
		request.getSession().removeAttribute("dids");
		request.getSession().removeAttribute("tid");
		int members = memberMapper.findCount();
		map.addAttribute("members",members);
		int ordersCount = ordersMapper.findOrderWithNow(Tools.getCostNumber(request), Tools.getTime());
		map.addAttribute("ordersCount",ordersCount);
		int tourlineCount =0;
		List<String> costnumbers = Tools.getCostNumber(request);
		if(costnumbers.size()!=0&&costnumbers.size()==1){
			tourlineCount=tourlineMapper.getSTourlineCount(costnumbers.get(0), null, null, null, null);
		}else{
			tourlineCount=tourlineMapper.getSTourlineCount(null, null, null, null, null);
		}
		map.addAttribute("tourlineCount",tourlineCount);
		List<Tourline> tourlist = tourlineMapper.findTourlineWithTourdate(Tools.getCostNumber(request),Tools.getTimestemp( Tools.date(Tools.getTime(), 7)));
		map.addAttribute("tourlist",tourlist);
		List<Tourline> tourlineNoprice = tourlineMapper.findTourlineWithNoprice(Tools.getCostNumber(request));
		map.addAttribute("tourlineNoprice", tourlineNoprice);
		int attrCount = attractionMapper.getMaxSort();
		map.addAttribute("attrCount",attrCount);
		return "/admin/index.ftl";
	}

	/**
	 * 获得文件列表
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody
	boolean login(HttpServletResponse response) throws IOException {
		boolean status = true;
		String username = request.getParameter("username");
		String passwd = Tools.MD5(request.getParameter("passwd"));

		Admin admin = adminService.findByUsername(username);
		if (admin != null && !admin.equals("")) {
			System.out.println(passwd);
			if (admin.getPassword().equals(passwd)) {
				String roleId = admin.getRoleid();
				List<Cost> costlist = costService.findAllByRoleId(roleId);
				List<Tree> treelist = treeService.findAllByRole(roleId);
				request.getSession().setAttribute("admin", admin);
				request.getSession().setAttribute("tree", treelist);
				request.getSession().setAttribute("cost", costlist);
				status = true;
			} else {
				status = false;
			}
		} else {
			status = false;
		}
		WebUtils.removeCookie(request, response, "orderNo");
		WebUtils.removeCookie(request, response, "productNoOrCode");
		WebUtils.removeCookie(request, response, "contacterName");
		WebUtils.removeCookie(request, response, "orderStatusId");
		WebUtils.removeCookie(request, response, "departureDateBeforeLimit");
		WebUtils.removeCookie(request, response, "departureDateAfterLimit");
		WebUtils.removeCookie(request, response, "bookingTimeBeforeLimit");
		WebUtils.removeCookie(request, response, "bookingTimeAfterLimit");
		WebUtils.removeCookie(request, response, "orderTpageSize");
		WebUtils.removeCookie(request, response, "orderTpageNow");
		return status;
	}

	/*
	 * @RequestMapping(value="/login") public String login(){
	 * 
	 * System.out.println("login");
	 * 
	 * return "/admin/login.html"; }
	 */

	@RequestMapping(value = "/loginout")
	public String logout(Model model) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		String username = null;
		if (admin != null) {
			username = admin.getUsername();
			request.getSession().setAttribute("username", username);
			
		}
		request.getSession().removeAttribute("admin");
		request.getSession().removeAttribute("dids");
		request.getSession().removeAttribute("tid");
		request.getSession().removeAttribute("tree");
		request.getSession().removeAttribute("cost");
		request.getSession().removeAttribute("Competence");

		return "/admin/loginout.ftl";
	}

	/*
	 * public void createLeft(List<Tree> tree) throws Exception { //
	 * 将用到的变量存放到root if (tree.size() > 0) { root.put("tree", tree); }
	 * root.put("request", request); // 静态页面的完整路径 String str =
	 * request.getSession().getServletContext().getRealPath("/") +
	 * "/WEB-INF/template/admin/include/left-1.ftl"; File file = new File(str);
	 * // 如果静态文件存在，则删除静态页面之后重新生成 if (file.isFile() && file.exists()) {
	 * file.delete(); }
	 * 
	 * str = null;// 释放资源 if (file != null) file = null;
	 * FreemarkerUtils.createHTML(request.getSession().getServletContext(),
	 * root, "admin/include/left.ftl",
	 * "/WEB-INF/template/admin/include/left-1.ftl");
	 * 
	 * }
	 * 
	 * public void createMan(Admin admin) throws Exception { // 将用到的变量存放到root if
	 * (admin != null) { root.put("admin", admin); } root.put("request",
	 * request); // 静态页面的完整路径 String str =
	 * request.getSession().getServletContext().getRealPath("/") +
	 * "/WEB-INF/template/admin/include/man-1.ftl"; File file = new File(str);
	 * // 如果静态文件存在，则删除静态页面之后重新生成 if (file.isFile() && file.exists()) {
	 * file.delete(); }
	 * 
	 * str = null;// 释放资源 if (file != null) file = null;
	 * FreemarkerUtils.createHTML(request.getSession().getServletContext(),
	 * root, "admin/include/man.ftl",
	 * "/WEB-INF/template/admin/include/man-1.ftl");
	 * 
	 * }
	 */
	
	/**
	 * 后台用户登录密码修改
	 */
	@RequestMapping("/modifypassword")
	public String modifyPassword(Model model){
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		String username = admin.getUsername();
		model.addAttribute("username", username);
		return "/admin/manage/admin/modifyPassword.ftl";
	}
	
	/**
	 * 后台用户登录密码修改保存
	 */
	@RequestMapping("/passwordsave")
	@ResponseBody
	public ResultDTO passwordsave(Model model){
		ResultDTO result = new ResultDTO();
		String oldpassword = request.getParameter("oldpassword");
		String newpassword = request.getParameter("newpassword");
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		String password = admin.getPassword();
		String id = admin.getId();
		if(password.equals(Tools.MD5(oldpassword))){
			//修改密码
			int success = adminService.modifyPassword(id,Tools.MD5(newpassword));
			if(success>0){
				result.setSuccess(true);
				result.setMessage("密码修改成功");
			}else {
				result.setSuccess(false);
				result.setMessage("密码修改失败");
			}
			return result;
		}else {
			//原密码不正确
			
			result.setSuccess(false);
			result.setMessage("原密码不正确");
			return result;
		}
	}
}