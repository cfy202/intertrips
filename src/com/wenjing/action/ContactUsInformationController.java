package com.wenjing.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wenjing.entity.ContactUs;
import com.wenjing.service.ContactUsService;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;

/**
 * 
 * @author Jared
 *
 */
@Controller
@RequestMapping("/admin/master")
public class ContactUsInformationController {
	@Autowired
	private ContactUsService contactUsService;
	
	/**
	 * 
	 * @param model
	 * @param reqeust
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model,HttpServletRequest request,HttpServletResponse response){
		
		String costId = request.getParameter("costId");
		List<String> coList = new ArrayList<String>();
		//分销售中心查询
		if (costId == null || "".equals(costId)) {
			costId = WebUtils.getCookie(request, "contactUsCostId");
			if (costId == null || "".equals(costId)) {
				coList = Tools.getCostNumber(request);
			}else {
				coList.add(costId);
			}
		} else {
			coList.add(costId);
			WebUtils.addCookie(request, response, "contactUsCostId", costId);
		}		
		List<ContactUs> contactUsList = contactUsService.list(coList);
		
		model.addAttribute("contactUsList", contactUsList);
		model.addAttribute("costNumList",coList);
		return "/admin/manage/contactus/list.ftl";
	}
	
	/**
	 * 
	 * @param contactUsId
	 * @param model
	 * @return
	 */
	@RequestMapping("/view")
	public String view(String id,Model model){
		ContactUs contactus = contactUsService.findById(id);
		model.addAttribute("contactus", contactus);
		return "/admin/manage/contactus/views.ftl";
	}
	
	/**
	 * 删除contactus
	 * 
	 * @param contactUsId
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(String id){
		contactUsService.delete(id);
		return "redirect:list.do";
	}
}
