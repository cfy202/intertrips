package com.wenjing.action.front;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenjing.entity.QnaAnswer;
import com.wenjing.entity.QnaQuestion;
import com.wenjing.service.QnaService;
import com.wenjing.util.FreemarkerUtils;

/** 
 * @author 作者 E-mail: Bowden
 * @version 创建时间：2015-9-6 下午3:39:21 
 * 类说明 : 常见问题 Controller
 */
@Controller("frontQnaController")
@RequestMapping("/front/qna")
public class QnaController {
	@Resource
	private HttpServletRequest request;
	@Resource
	private QnaService qnaService;
	
	/**
	 * @Title questionSubmit
	 * @Description 添加咨询问题
	 * @Author Bowden
	 * @CreateDate 2015-9-9 下午3:53:52
	 */
	@RequestMapping(value = "/qsubmit", method = RequestMethod.POST)
	@ResponseBody
	public boolean questionSubmit(QnaQuestion question){
		boolean flag = false;
		int success = qnaService.addQuestion(question);
		if(success>0){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * @Title answerSubmit
	 * @Description 添加回复
	 * @Author Bowden
	 * @CreateDate 2015-9-10 下午4:21:13
	 */
	@RequestMapping("/answerSbumit")
	@ResponseBody
	public boolean answerSubmit(QnaAnswer answer){
		boolean flag = false;
		int success = qnaService.addAnswer(answer);
		if(success>0){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * @Title: getqna
	 * @Description: 线路详情页问答显示
	 * @param id
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 */
	@RequestMapping(value = "/getqna",  produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getqna(@RequestParam("productId") String productId){
		List<QnaQuestion> qList = qnaService.findByproductId(productId);
		String qaContent = FreemarkerUtils.getQAContent(request.getSession().getServletContext(), qList, request);
		return qaContent;
	}

}
