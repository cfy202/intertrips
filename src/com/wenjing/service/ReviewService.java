package com.wenjing.service;

import java.io.UnsupportedEncodingException;
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
import com.wenjing.dao.MemberinformationMapper;
import com.wenjing.dao.ProductMapper;
import com.wenjing.dao.ReviewMapper;
import com.wenjing.entity.Member;
import com.wenjing.entity.Memberinformation;
import com.wenjing.entity.Product;
import com.wenjing.entity.Review;
import com.wenjing.util.FreemarkerUtils;
import com.wenjing.util.IPUtils;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;

/** 
 * @author 作者 E-mail: Bowden
 * @version 创建时间：2015-9-15 下午3:10:48 
 * 类说明 :用户评价service
 */
@Service
public class ReviewService {
	@Resource
	private ReviewMapper reviewMapper;
	@Resource
	private ProductMapper productMapper;
	@Resource
	private HttpServletRequest request;
	@Resource
	private MemberinformationMapper memberinformationMapper;
	
	private  final static int SIZE = 4;		//定义分页显示评论数量

	/**
	 * @Title findByCostNumAndPage
	 * @Description 后台分页显示评论列表
	 * @Author Bowden
	 * @CreateDate 2015-9-15 下午3:13:44
	 */
	@Transactional(readOnly=true)
	public List<Review> findByCostNumAndPage(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pageNow = request.getParameter("pageNow");
		String pageSize = request.getParameter("pageSize");
		String statustr = request.getParameter("status");
		String costId = request.getParameter("costId");
		String search = request.getParameter("reviewSearch");
		List<String> costNumList = new ArrayList<String>();
		//分销售中心查询
		if (costId == null || "".equals(costId)) {
			costId = WebUtils.getCookie(request, "reviewCostId");
			if (costId == null || "".equals(costId)) {
				costNumList = Tools.getCostNumber(request);
			}else {
				costNumList.add(costId);
			}
		} else {
			costNumList.add(costId);
			WebUtils.addCookie(request, response, "reviewCostId", costId);
			pageNow = "1";
		}
		
		//分状态查询
		if(statustr == null || "".equals(statustr)){
			statustr = WebUtils.getCookie(request, "reviewStatus");
			if(statustr == null || "".equals(statustr)){
				statustr = "2";
			}
		}else {
			WebUtils.addCookie(request, response, "reviewStatus", statustr);
			pageNow = "1";
		}
		
		// 根据搜索条件
		if (search == null || "".equals(search)) {
			search = WebUtils.getCookie(request, "reviewSearch");
		} else {
			WebUtils.addCookie(request, response, "reviewSearch", search);
			pageNow = "1";
		}
		
		 // 如果每页显示记录的条数为null，则在cookie里查找，如果不存在，则默认显示10条
		if(pageSize == null || "".equals(pageSize)){
			pageSize = WebUtils.getCookie(request, "reviewPageSize");
	        if(pageSize == null || "".equals(pageSize)){
	        	pageSize = "10";
	        }
		}else{
			WebUtils.addCookie(request, response, "reviewPageSize", pageSize);
			pageNow = "1";
		}
		
		//如果当前页为null，则在cookie里查找，如果不存在，则默认显示第1页
		if (pageNow == null || "".equals(pageNow)) {
			pageNow = WebUtils.getCookie(request, "reviewPageNow");
		} else {
			WebUtils.addCookie(request, response, "reviewPageNow", pageNow);
		}
		
		Pages page = null;
		int totalCount = reviewMapper.getCountByCostNum(costNumList, search);
		if (pageNow != null && !"".equals(pageNow)) {
			page = new Pages(totalCount, Integer.parseInt(pageNow));
			page.setPageSize(Integer.parseInt(pageSize));
		}else{
			page = new Pages(totalCount,1);
			page.setPageSize(Integer.parseInt(pageSize));
		} 
		List<Review> reviews = reviewMapper.findByCostNumAndPage(costNumList, page.getStartPos(), page.getPageSize(), Integer.parseInt(statustr), search);
		model.addAttribute("page", page);
		model.addAttribute("status", Integer.parseInt(statustr));
		model.addAttribute("costNumList", costNumList);
		model.addAttribute("reviewSearch", search);
		return reviews;
	}
	
	/**
	 * @Title editIsshow
	 * @Description 编辑是否显示
	 * @Author Bowden
	 * @CreateDate 2015-9-8 下午2:39:10
	 */
	@Transactional
	public void editIsshow(Review review) {
		reviewMapper.editIsshow(review);
	}
	
	/**
	 * @Title updataQStatus
	 * @Description 修改处理状态
	 * @Author Bowden
	 * @CreateDate 2015-9-9 上午10:33:25
	 */
	@Transactional
	public void updateStatus(String id) {
		reviewMapper.updateStatus(id, 1);
	}

	/**
	 * @Title getReviewById
	 * @Description 根据id查询
	 * @Author Bowden
	 * @CreateDate 2015-9-15 下午4:17:22
	 */
	@Transactional(readOnly=true)
	public Review getReviewById(String id) {
		Review review = reviewMapper.getReviewByQid(id);
		String ip = review.getIp();
		if(ip != null && !"".equals(ip)){
			String path = request.getSession().getServletContext().getRealPath("/");
			IPUtils.load(path + "/ip/17monipdb.dat");
			String[] location = IPUtils.find(ip);
			review.setIp(Tools.stringArrToString(location));
		}
		return review;
	}

	/**
	 * @Title delete
	 * @Description 删除
	 * @Author Bowden
	 * @CreateDate 2015-9-16 上午11:16:55
	 */
	@Transactional
	public void delete(String id) {
		reviewMapper.deleteByPrimaryKey(id);
	}

	/**
	 * @Title addReview
	 * @Description 前台添加保存评论
	 * @Author Bowden
	 * @CreateDate 2015-9-23 上午11:42:01
	 */
	@Transactional
	public int addReview(Review review) {
		String costnumber = (String) request.getSession().getAttribute("costnumber");
		if(costnumber!=null && !"".equals(costnumber)){
			review.setCostNumber(costnumber);
		}
		review.setId(Tools.getUUID());
		review.setIp(Tools.getRemortIP(request));
		review.setCreateDate(new Date());
		review.setIsshow(0);
		review.setStatus(0);
		Member member = Tools.getMember(request);
		if(member != null){
			review.setMemberId(member.getId());
		}
		int success = reviewMapper.insert(review);
		return success;
	}
	
	/**
	 * 保存评价，并返回
	 * 
	 * @param review
	 * @return
	 */
	@Transactional
	public int submitReview(Review review){
		Product product = productMapper.selectByPrimaryKey(review.getProductId());
		review.setId(Tools.getUUID());
		review.setIp(Tools.getRemortIP(request));
		review.setCreateDate(new Date());
		review.setCostNumber(product.getCostnumber());
		review.setIsshow(0);
		review.setStatus(0);
		Member member = Tools.getMember(request);
		if(member != null){
			review.setMemberId(member.getId());
		}
		int success = reviewMapper.insert(review);
		if(success > 0){
			return success;
		}else{
			return 0;
		}
	}
	
	/**
	 * @Title: insert
	 * @Description: 后台保存评论
	 * @param review
	 * @return    
	 * @return int    返回类型
	 * @author xiejin
	 */
	@Transactional
	public int insert(Review review){
		return reviewMapper.insert(review);
	}
	
	/**
	 * @Title: getReview
	 * @Description: 前台分页查询线路评论
	 * @param tourlineId
	 * @param costnumber
	 * @return    
	 * @return Map<String,Object>    返回类型
	 * @author xiejin
	 */
	@Transactional
	public List<String> getReview(String tourlineId,String costnumber){
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String pageNow = request.getParameter("pageNow");
		Pages page = null;
		List<Review> reviewList = new ArrayList<Review>();
		int totalCount = reviewMapper.getReviewCount(tourlineId, costnumber);
		if (pageNow != null) {
			page = new Pages(totalCount, Integer.parseInt(pageNow));
		} else {
			page = new Pages(totalCount, 1);
		}
		page.setPageSize(SIZE);
		int startPos = page.getStartPos();
		int pageSize = page.getPageSize();
		reviewList = reviewMapper.getReview(tourlineId, costnumber,startPos, pageSize);
		for (Review review : reviewList) {
			 //August 21, 2015
			review.setCreateDateString(Tools.getEnglishShow(review.getCreateDate()));
			
			Member member = review.getMember();
			if (member !=null) {
				String account = member.getAccount();
				Memberinformation memberinformation = memberinformationMapper.findInfoByMemberId(member.getId());
				member.setMemberinformation(memberinformation);
				String[] accountString = account.split("@");
				String account2 = account.charAt(0)+"**@"+accountString[1];
				member.setAccount(account2);
			}
		}
		String reviewPageContent = "";
		String reviewContent = "";
		if(totalCount > 0){
			reviewPageContent = FreemarkerUtils.getReviewPage(request
					.getSession().getServletContext(), page);
			reviewContent = FreemarkerUtils.getReviewContent(request
					.getSession().getServletContext(), reviewList, request);
		}
		List<String> root = new ArrayList<String>();
		root.add(reviewContent);
		root.add(reviewPageContent);
		return root;
	}
	
	/**
	 * 
	 * @param tourlineId
	 * @param costNumber
	 * @return
	 */
	@Transactional(readOnly=true)
	public int getReviewNumber(String tourlineId, String costNumber){
	    return reviewMapper.getReviewCount(tourlineId, costNumber);
	}
}
