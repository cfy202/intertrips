
package com.wenjing.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wenjing.entity.Cost;
import com.wenjing.entity.Email;
import com.wenjing.service.CostService;
import com.wenjing.service.EmailService;
import com.wenjing.util.Tools;

/**
 * 类说明		订阅邮件后台管理
 * @author xiejin
 * @date 2015-7-1 
 * @date 2015-7-1 上午11:21:10
 */
@Controller
@RequestMapping("/admin/email")
public class EmailController {

	@Resource
	private EmailService emailService;
	@Resource
	private HttpServletRequest request;
	@Resource
	private CostService costService;
	
	/**
	 * @Title: findAll
	 * @Description: 查询所有emai
	 * @param model
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 */
	@RequestMapping("/list")
	public String findAll(Model model){
		List<Email> emailList = emailService.findAll(Tools.getCostNumber(request));
		for (Email email : emailList) {
			String costnumber = email.getCostNumber();
			Cost cost = costService.findById(costnumber);
			//email.setCostName(cost.getName());		//set运营中心名称
			String addTimeString = Tools.getTime(email.getAddTime());
			email.setAddTimeString(addTimeString);
		}
		model.addAttribute("emailList", emailList);
		return "/admin/manage/email/email.ftl";
	}
	
	/**
	 * 根据id删除email
	 * @param id
	 * @return
	 * xiejin
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id) {
		emailService.deleteByPrimaryKey(id);
		return "redirect:/admin/email/list.do";
	}
	
	/**
	 * @Title: cancel
	 * @Description: 取消订阅邮件
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 */
	@RequestMapping("/cancel")
	public String cancel(){
		String id = request.getParameter("id");
		emailService.updateEmailStatus(id);
		return "/admin/manage/email/cancelOk.ftl";
	}
}
