package com.wenjing.action.front;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenjing.entity.Review;
import com.wenjing.service.ReviewService;

/** 
 * @author 作者 E-mail: Bowden
 * @version 创建时间：2015-9-6 下午3:39:21 
 * 类说明 : 用户评价 Controller
 */
@Controller("frontReviewController")
@RequestMapping("/front/review")
public class ReviewController {
	@Resource
	private HttpServletRequest request;
	@Resource
	private ReviewService reviewService;

	/**
	 * @Title reviewSubmit
	 * @Description 评论提交
	 * @Author Bowden
	 * @CreateDate 2015-9-23 上午11:40:20
	 */
	@RequestMapping("/rSubmit")
	@ResponseBody
	public boolean reviewSubmit(Review review){
		boolean flag = false;
		int success = reviewService.addReview(review);
		if(success>0){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 提交评论
	 * 
	 * @param review
	 * @return
	 */
	@RequestMapping("/submitReview")
	public @ResponseBody int submitReview(Review review){
		return reviewService.submitReview(review);
	}
}
