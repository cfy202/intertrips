package com.wenjing.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.wenjing.Pages;
import com.wenjing.dao.QnaAnswerMapper;
import com.wenjing.dao.QnaQuestionMapper;
import com.wenjing.entity.Member;
import com.wenjing.entity.QnaAnswer;
import com.wenjing.entity.QnaQuestion;
import com.wenjing.util.IPUtils;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;

/** 
 * @author 作者 E-mail: Bowden
 * @version 创建时间：2015-9-6 下午3:40:08 
 * 类说明 : 常见问题 Service
 */
@Service
public class QnaService {
	@Resource
	private HttpServletRequest request;
	@Resource
	private QnaQuestionMapper qnaQuestionMapper;
	@Resource
	private QnaAnswerMapper qnaAnswerMapper;

	/**
	 * @Title addQuestion
	 * @Description 添加问题
	 * @Author Bowden
	 * @CreateDate 2015-9-7 下午2:49:57
	 */
	@Transactional
	public int addQuestion(QnaQuestion question) {
		String costnumber = (String) request.getSession().getAttribute("costnumber");
		if(costnumber!=null && !"".equals(costnumber)){
			question.setCostNumber(costnumber);
		}
		question.setId(Tools.getUUID());
		question.setIp(Tools.getRemortIP(request));
		question.setCreateDate(new Date());
		question.setIsshow(0);
		question.setStatus(0);
		Member member = Tools.getMember(request);
		if(member != null){
			question.setMemberId(member.getId());
		}
		int success = qnaQuestionMapper.insert(question);
		return success;
	}
	
	/**
	 * @Title addAnswer
	 * @Description 添加回复
	 * @Author Bowden
	 * @CreateDate 2015-9-7 下午2:49:57
	 */
	@Transactional
	public int addAnswer(QnaAnswer answer) {
		String costnumber = (String) request.getSession().getAttribute("costnumber");
		if(costnumber!=null && !"".equals(costnumber)){
			answer.setCostNumber(costnumber);
		}
		answer.setId(Tools.getUUID());
		answer.setIp(Tools.getRemortIP(request));
		answer.setCreateDate(new Date());
		answer.setIsshow(0);
		answer.setStatus(0);
		Member member = Tools.getMember(request);
		if(member != null){
			answer.setMemberId(member.getId());
		}
		int success = qnaAnswerMapper.insert(answer);
		return success;
	}

	/**
	 * @Title findByCostNum
	 * @Description 后台展示列表
	 * @Author Bowden
	 * @CreateDate 2015-9-7 下午2:50:14
	 */
	@Transactional(readOnly=true)
	public List<QnaQuestion> findByCostNumAndPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		String pageNow = request.getParameter("pageNow");
		String pageSize = request.getParameter("pageSize");
		String statustr = request.getParameter("status");
		String costId = request.getParameter("costId");
		String qnaSearchtitle = request.getParameter("qnaStitle");
		String qnaSearchtourname = request.getParameter("qnaStourname");
		String qnaSexrchcode = request.getParameter("qnaScode");
		List<String> costNumList = new ArrayList<String>();
		//分销售中心查询
		if (costId == null || "".equals(costId)) {
			costId = WebUtils.getCookie(request, "qnaCostId");
			if (costId == null || "".equals(costId)) {
				costNumList = Tools.getCostNumber(request);
			}else {
				costNumList.add(costId);
			}
		} else {
			costNumList.add(costId);
			WebUtils.addCookie(request, response, "qnaCostId", costId);
			pageNow = "1";
		}
		
		//分状态查询
		if(statustr == null || "".equals(statustr)){
			statustr = WebUtils.getCookie(request, "status");
			if(statustr == null || "".equals(statustr)){
				statustr = "2";
			}
		}else {
			WebUtils.addCookie(request, response, "status", statustr);
			pageNow = "1";
		}
		
		// 根据搜索条件---线路名称
		if (qnaSearchtourname == null || "".equals(qnaSearchtourname)) {
			qnaSearchtourname = WebUtils.getCookie(request, "qnaStourname");
		} else {
			WebUtils.addCookie(request, response, "qnaStourname", qnaSearchtourname);
			pageNow = "1";
		}
		// 根据搜索条件---线路编号
		if (qnaSexrchcode == null || "".equals(qnaSexrchcode)) {
			qnaSexrchcode = WebUtils.getCookie(request, "qnaScode");
		} else {
			WebUtils.addCookie(request, response, "qnaScode", qnaSexrchcode);
			pageNow = "1";
		}
		// 根据搜索条件---提问标题
		if (qnaSearchtitle == null || "".equals(qnaSearchtitle)) {
			qnaSearchtitle = WebUtils.getCookie(request, "qnaStitle");
		} else {
			WebUtils.addCookie(request, response, "qnaStitle", qnaSearchtitle);
			pageNow = "1";
		}
		
		 // 如果每页显示记录的条数为null，则在cookie里查找，如果不存在，则默认显示10条
		if(pageSize == null || "".equals(pageSize)){
			pageSize = WebUtils.getCookie(request, "qnaPageSize");
	        if(pageSize == null || "".equals(pageSize)){
	        	pageSize = "10";
	        }
		}else{
			WebUtils.addCookie(request, response, "qnaPageSize", pageSize);
			pageNow = "1";
		}
		
		//如果当前页为null，则在cookie里查找，如果不存在，则默认显示第1页
		if (pageNow == null || "".equals(pageNow)) {
			pageNow = WebUtils.getCookie(request, "qnaPageNow");
		} else {
			WebUtils.addCookie(request, response, "qnaPageNow", pageNow);
		}
		
		Pages page = null;
		int totalCount = qnaQuestionMapper.getCountByCostNum(costNumList, qnaSearchtourname, qnaSearchtitle, qnaSexrchcode);
		if (pageNow != null && !"".equals(pageNow)) {
			page = new Pages(totalCount, Integer.parseInt(pageNow));
			page.setPageSize(Integer.parseInt(pageSize));
		}else{
			page = new Pages(totalCount,1);
			page.setPageSize(Integer.parseInt(pageSize));
		} 
		List<QnaQuestion> questions = qnaQuestionMapper.findByCostNumAndPage(costNumList, 
				                                                             page.getStartPos(), 
				                                                             page.getPageSize(), 
				                                                             Integer.parseInt(statustr), 
				                                                             qnaSearchtourname,
				                                                             qnaSearchtitle,
				                                                             qnaSexrchcode);
		model.addAttribute("page", page);
		model.addAttribute("status", Integer.parseInt(statustr));
		model.addAttribute("costNumList", costNumList);
		model.addAttribute("qnaStourname", qnaSearchtourname);
		model.addAttribute("qnaStitle", qnaSearchtitle);
		model.addAttribute("qnaScode", qnaSexrchcode);
		return questions;
	}

	/**
	 * @Title getQuestionByQid
	 * @Description 根据questionid 查询咨询问题详细信息
	 * @Author Bowden
	 * @CreateDate 2015-9-8 上午11:12:04
	 */
	@Transactional(readOnly=true)
	public QnaQuestion getQuestionByQid(String id) {
		QnaQuestion qnaQuestion = qnaQuestionMapper.getQuestionByQid(id);
		String ip = qnaQuestion.getIp();
		if(ip != null && !"".equals(ip)){
			String path = request.getSession().getServletContext().getRealPath("/");
			IPUtils.load(path + "/ip/17monipdb.dat");
			String[] location = IPUtils.find(ip);
			qnaQuestion.setIp(Tools.stringArrToString(location));
		}
		return qnaQuestion;
	}

	/**
	 * @Title editIsshow
	 * @Description 编辑是否显示
	 * @Author Bowden
	 * @CreateDate 2015-9-8 下午2:39:10
	 */
	@Transactional
	public void editIsshow(QnaQuestion question) {
		qnaQuestionMapper.editIsshow(question);
	}
	
	/**
	 * @Title editIsshow
	 * @Description 编辑是否显示
	 * @Author Bowden
	 * @CreateDate 2015-9-8 下午2:39:10
	 */
	@Transactional
	public void editIsshow(QnaAnswer qnaAnswer) {
		qnaAnswerMapper.editIsshow(qnaAnswer);
	}

	/**
	 * @Title getAnswerByQid
	 * @Description 查询回复内容
	 * @Author Bowden
	 * @CreateDate 2015-9-8 下午2:55:52
	 */
	@Transactional(readOnly=true)
	public List<QnaAnswer> getAnswerByQid(String id) {
		List<QnaAnswer> anList = qnaAnswerMapper.getAnswerByQid(id);
		String path = request.getSession().getServletContext().getRealPath("/");
		IPUtils.load(path + "/ip/17monipdb.dat");
		String ip = "";
		for (QnaAnswer qnaAnswer : anList) {
			ip = qnaAnswer.getIp();
			if(ip!=null && !"".equals(ip)){
				String[] location = IPUtils.find(ip);
				qnaAnswer.setIp(Tools.stringArrToString(location));
			}
		}
		return anList;
	}

	/**
	 * @Title updateAnswerIsshow
	 * @Description 修改answer回复是否显示
	 * @Author Bowden
	 * @CreateDate 2015-9-8 下午4:18:39
	 */
	@Transactional
	public void updateAnswerIsshow(String id, Integer isshow) {
       qnaAnswerMapper.updateAnswerIsshow(id, isshow, 1);
	}

	/**
	 * @Title updataQStatus
	 * @Description 修改处理状态
	 * @Author Bowden
	 * @CreateDate 2015-9-9 上午10:33:25
	 */
	@Transactional
	public void updateQStatus(String id) {
		qnaQuestionMapper.updateQStatus(id, 1);
	}
	
	/**
	 * @Title updataQStatus
	 * @Description 修改处理状态
	 * @Author Bowden
	 * @CreateDate 2015-9-9 上午10:33:25
	 */
	@Transactional
	public void updateAStatus(String id) {
		qnaAnswerMapper.updateAStatus(id, 1);
	}

	/**
	 * @Title getAnswerByAnswerId
	 * @Description 根据answerid查询回复详细内容
	 * @Author Bowden
	 * @CreateDate 2015-9-9 下午2:10:06
	 */
	@Transactional(readOnly=true)
	public QnaAnswer getAnswerByAnswerId(String id) {
		QnaAnswer qnaAnswer = qnaAnswerMapper.getAnswerByAnswerId(id);
		String ip = qnaAnswer.getIp();
		if(ip != null && !"".equals(ip)){
			String path = request.getSession().getServletContext().getRealPath("/");
			IPUtils.load(path + "/ip/17monipdb.dat");
			String[] location = IPUtils.find(ip);
			qnaAnswer.setIp(Tools.stringArrToString(location));
		}
		return qnaAnswer;
	}

	/**
	 * @Title deleteQuestionById
	 * @Description 根据id删除咨询问题
	 * @Author Bowden
	 * @CreateDate 2015-9-9 下午2:44:17
	 */
	@Transactional
	public void deleteQuestionById(String id) {
		// 删除此咨询问题对应的所有回复内容
		qnaAnswerMapper.deleteAByQid(id);
		//删除此咨询问题
		qnaQuestionMapper.deleteByPrimaryKey(id);
	}

	/**
	 * @Title deleteAnswerById
	 * @Description 根据id删除回复内容
	 * @Author Bowden
	 * @CreateDate 2015-9-9 下午2:50:26
	 */
	@Transactional
	public void deleteAnswerById(String id) {
        qnaAnswerMapper.deleteByPrimaryKey(id);		
	}

	/**
	 * @Title findByTourlineId
	 * @Description 根据产品id查询QA
	 * @Author Bowden
	 * @CreateDate 2015-9-9 下午4:02:35
	 */
	@Transactional
	public List<QnaQuestion> findByproductId(String productId) {
		String costnumber = (String) request.getSession().getAttribute("costnumber");
		List<QnaQuestion> qList = qnaQuestionMapper.findByproductId(productId, costnumber);
		String path = request.getSession().getServletContext().getRealPath("/");
		IPUtils.load(path + "/ip/17monipdb.dat");
		String ip = "";
		for (QnaQuestion question : qList) {
			ip = question.getIp();
			if(ip!=null && !"".equals(ip)){
				String[] location = IPUtils.find(ip);
				question.setIp(Tools.stringArrToString(location));
			}
			List<QnaAnswer> aList = question.getQnaAnswerList();
			for (QnaAnswer answer : aList) {
				ip = answer.getIp();
				if(ip!=null && !"".equals(ip)){
					String[] location = IPUtils.find(ip);
					answer.setIp(Tools.stringArrToString(location));
				}
			}
		}
		return qList;
	}
	
	/**
	 * @Title: insert
	 * @Description: 添加保存问题回复
	 * @param answer
	 * @return    
	 * @return int    返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly=true)
	public int insert(QnaAnswer answer){
		return qnaAnswerMapper.insert(answer);
	}

}
