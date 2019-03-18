package com.wenjing.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenjing.entity.QnaAnswer;
import com.wenjing.entity.QnaQuestion;
import com.wenjing.service.QnaService;
import com.wenjing.util.Tools;

/** 
 * @author 作者 E-mail: Bowden
 * @version 创建时间：2015-9-6 下午3:39:21 
 * 类说明 : QA Controller
 */
@Controller("adminQnaController")
@RequestMapping("/admin/qna")
public class QnaController {
	@Resource
	private HttpServletRequest request;
	@Resource
	private QnaService qnaService;
	
	private final static String NAME = "文景假期";
	
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list")
	public String list(Model model, HttpServletResponse response) {
		List<QnaQuestion> qnaQuestions = qnaService.findByCostNumAndPage(request, response, model);
		model.addAttribute("qnaQuestions", qnaQuestions);
		return "/admin/manage/qna/list.ftl";
	}
	
	/**
	 * @Title question
	 * @Description 进入咨询问题详情页
	 * @Author Bowden
	 * @CreateDate 2015-9-8 下午2:33:58
	 */
	@RequestMapping("/question")
	public String question (@RequestParam("id") String id, Model model){
		QnaQuestion question = qnaService.getQuestionByQid(id);
		model.addAttribute("question", question);
		return "/admin/manage/qna/question.ftl";
	}
	
	/**
	 * @Title questionSave
	 * @Description 处理此问题是否显示
	 * @Author Bowden
	 * @CreateDate 2015-9-8 下午2:35:15
	 */
	@RequestMapping("/question/save")
	public String questionSave(QnaQuestion question, Model model){
		qnaService.editIsshow(question);
		return "redirect:/admin/qna/list.do";
	}
	
	/**
	 * 异步修改question否显示
	 */
	@RequestMapping("/question/isshow")
	@ResponseBody
	public int updateQIsshow(@RequestParam("isshow") Integer isshow, @RequestParam("id") String id) {
		if (isshow != null && isshow == 1) {
			isshow = 0;
		} else {
			isshow = 1;
		}
		QnaQuestion qnaQuestion = new QnaQuestion(id, isshow, 1);
		qnaService.editIsshow(qnaQuestion);
		return isshow;
	}

	/**
	 * 异步修改question处理状态
	 */
	@RequestMapping("/question/status")
	@ResponseBody
	public void updateQStatus(@RequestParam("id") String id) {
		qnaService.updateQStatus(id);
	}
	
	@RequestMapping("/question/delete")
	public String questionDelete(@RequestParam("id") String id){
		qnaService.deleteQuestionById(id);
		return "redirect:/admin/qna/list.do";
	}
	
	/**
	 * @Title answer
	 * @Description 查看回复
	 * @Author Bowden
	 * @CreateDate 2015-9-8 下午2:53:13
	 */
	@RequestMapping("/answer")
	public String answer(@RequestParam("id") String id, Model model){
		List<QnaAnswer> anList = qnaService.getAnswerByQid(id);
		model.addAttribute("anList", anList);
		return "/admin/manage/qna/answer.ftl";
	}

	/**
	 * @Title answerDetails
	 * @Description 查看回复详情
	 * @Author Bowden
	 * @CreateDate 2015-9-8 下午2:53:13
	 */
	@RequestMapping("/answer/details")
	public String answerDetails(@RequestParam("id") String id, Model model){
		QnaAnswer qnaAnswer = qnaService.getAnswerByAnswerId(id);
		model.addAttribute("answer", qnaAnswer);
		return "/admin/manage/qna/answerDetails.ftl";
	}
	
	/**
	 * @Title answerSave
	 * @Description 详情页处理此回复是否显示
	 * @Author Bowden
	 * @CreateDate 2015-9-8 下午2:35:15
	 */
	@RequestMapping("/answer/save")
	public String answerSave(QnaAnswer answer, Model model){
		qnaService.editIsshow(answer);
		return "redirect:/admin/qna/answer.do?id="+answer.getQuestionId();
	}

	
	/**
	 * 异步修改回复是否显示
	 */
	@RequestMapping("/answer/isshow")
	@ResponseBody
	public int updateAIsshow(@RequestParam("isshow") Integer isshow, @RequestParam("id") String id) {
		if (isshow != null && isshow == 1) {
			isshow = 0;
		} else {
			isshow = 1;
		}
		QnaAnswer answer = new QnaAnswer(id, isshow, 1);
		qnaService.editIsshow(answer);
		return isshow;
	}
	
	/**
	 * 异步修改answer处理状态
	 */
	@RequestMapping("/answer/status")
	@ResponseBody
	public void updateAStatus(@RequestParam("id") String id) {
		qnaService.updateAStatus(id);
	}
	
	@RequestMapping("/answer/delete")
	public String answerDelete(@RequestParam("id") String id, @RequestParam("qid") String qid){
		qnaService.deleteAnswerById(id);
		return "redirect:/admin/qna/answer.do?id="+qid;
	}
	
	/**
	 * @Title: addAnswer
	 * @Description: 添加问题回复
	 * @param id
	 * @param model
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 */
	@RequestMapping(value = "/addAnswer")
	public String addAnswer(@RequestParam("id") String id,Model model){
		model.addAttribute("id", id);
		return "/admin/manage/qna/addAnswer.ftl";
	}
	
	/**
	 * @Title: saveAnswer
	 * @Description: 后台添加问题回复后保存
	 * @param qnaAnswer
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 */
	@RequestMapping(value = "/saveAnswer")
	public String saveAnswer(QnaAnswer qnaAnswer){
		qnaAnswer.setId(Tools.getUUID());
		qnaAnswer.setName(NAME);
		qnaAnswer.setIp(Tools.getRemortIP(request));
		qnaAnswer.setCreateDate(new Date());
		qnaAnswer.setStatus(1);
		Integer isShow = qnaAnswer.getIsshow();
		if (isShow == null) {
			qnaAnswer.setIsshow(0);
		}
		qnaService.insert(qnaAnswer);
		return "redirect:/admin/qna/list.do";
	}
}
