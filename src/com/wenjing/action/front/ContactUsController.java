package com.wenjing.action.front;

import java.util.Date;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wenjing.Mail;
import com.wenjing.entity.ContactUs;
import com.wenjing.service.ContactUsService;
import com.wenjing.util.FreemarkerUtils;
import com.wenjing.util.Tools;

@Controller
@RequestMapping("/front/contact")
public class ContactUsController {
	
	@Autowired
	private ContactUsService contactUsService;
	
	/**
	 * 保存contact us
	 * 
	 * @param contactUs
	 * @return
	 */
	@RequestMapping("/save")
	public @ResponseBody String save(ContactUs contactUs,HttpServletRequest request){
		contactUs.setId(Tools.getUUID());
		contactUs.setIp(Tools.getRemortIP(request));
		contactUs.setCreateDate(new Date());
		contactUsService.save(contactUs);
		Mail gmail = new Mail();
		gmail.setSendTo(new String[]{"info@intertrips.com"});
		gmail.setEmailSubjectTxt(contactUs.getSubject());
		String content = FreemarkerUtils.getContactUsNotice(request,contactUs);
		gmail.setEmailMsgTxt(content);
		try {
			gmail.sendSSLMessage(); // 发送邮件
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return JSON.toJSONString("We have received your inquiry and one of our travel consultants will reply within 24 hours.");
	}
}
