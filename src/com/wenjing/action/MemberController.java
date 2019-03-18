package com.wenjing.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenjing.DateEditor;
import com.wenjing.entity.Member;
import com.wenjing.entity.Memberinformation;
import com.wenjing.service.MemberService;

/**
 * @author 作者 E-mail: bowden
 * @version 创建时间：2015-4-27 下午3:43:33 
 * 类说明 ：后台会员管理 Controller
 */
@Controller
@RequestMapping("/admin/member")
public class MemberController {

	@Resource
	private MemberService memberService;
	
	/**
	 * 数据绑定
	 * 
	 * @param binder
	 *            WebDataBinder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Date.class, new DateEditor(true));
	}

	/**
	 * 会员列表展示
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		List<Member> members = memberService.findAll();
		model.addAttribute("members", members);
		return "/admin/manage/member/list.ftl";
	}
	
	/**
	 * 后台修改会员资料保存
	 */
	@RequestMapping("/save")
	public String save(Memberinformation memberinformation){
		memberService.adminUpdateInfo(memberinformation);
		return "redirect:/admin/member/list.do";
	}

	/**
	 * 异步修改用户状态
	 */
	@RequestMapping("/updatestatus")
	@ResponseBody
	public int updateStatus(@RequestParam("userstatus") Integer userstatus, @RequestParam("id") String id) {
		if (userstatus != null && userstatus == 1) {
			userstatus = 0;
		} else {
			userstatus = 1;
		}
		memberService.updateStatus(id, userstatus);
		return userstatus;
	}

	/**
	 * 会员详细信息
	 */
	@RequestMapping("/info")
	public String info(@RequestParam("id") String id, Model model) {
		Memberinformation memberinformation = memberService.findInfoByMemberId(id);
		model.addAttribute("info", memberinformation);
		return "/admin/manage/member/info.ftl";
	}

	/**
	 * 删除会员
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id) {
		if (id != null && !"".equals(id)) {
			memberService.delete(id);
		}
		return "redirect:/admin/member/list.do";
	}
}
