package com.wenjing.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenjing.entity.Cost;
import com.wenjing.entity.Review;
import com.wenjing.entity.Tourline;
import com.wenjing.service.ReviewService;
import com.wenjing.service.TourlineService;
import com.wenjing.util.Tools;

/** 
 * @author 作者 E-mail: Bowden
 * @version 创建时间：2015-9-11 上午10:01:27 
 * 类说明 :用户评价后台管理
 */
@Controller("adminReview")
@RequestMapping("/admin/review")
public class ReviewController {
	@Resource
	private HttpServletRequest request;
	@Resource
	private ReviewService reviewService;
	@Resource
	private TourlineService tourlineService;
	
	/**
	 * @Title list
	 * @Description 用户评价展示列表
	 * @Author Bowden
	 * @CreateDate 2015-9-15 下午3:06:53
	 */
	@RequestMapping("/list")
	public String list(Model model, HttpServletResponse response){
		List<Review> reviews = reviewService.findByCostNumAndPage(request, response, model);
		model.addAttribute("reviews", reviews);
		return "/admin/manage/review/list.ftl";
	}
	
	/**
	 * @Title details
	 * @Description 进入评论详情页
	 * @Author Bowden
	 * @CreateDate 2015-9-8 下午2:33:58
	 */
	@RequestMapping("/details")
	public String details (@RequestParam("id") String id, Model model){
		Review review = reviewService.getReviewById(id);
		String picture = review.getPicture();
		if(picture != null && !"".equals(picture)){
			model.addAttribute("pictureArr", picture.split(","));
		}
		model.addAttribute("review", review);
		return "/admin/manage/review/details.ftl";
	}
	
	/**
	 * @Title add
	 * @Description 线路添加评论
	 * @Author xiejin
	 */
	@RequestMapping("/add")
	public String add(Model model){
		String id = Tools.getUUID();
		model.addAttribute("id",id);
		List<Cost> costlist = (List<Cost>) request.getSession().getAttribute("cost");
		model.addAttribute("costlist", costlist);
		if (costlist.size() != 0) {
			List<Tourline> tourline = tourlineService.getByCostNumber(costlist.get(0).getId());
			model.addAttribute("tourline", tourline);
		}
		return "/admin/manage/review/add.ftl";
	}
	
	/**
	 * 保存review
	 * @param review
	 * @param id
	 * @return
	 * xiejin
	 */
	@RequestMapping("/addSave")
	public String save(Review review,@RequestParam("id") String id) {
		review.setId(id);
		Integer isshow = review.getIsshow();
		if (isshow==null) {
			review.setIsshow(0);
		}
		Integer tourGroupScore = review.getTourGroupScore();
		if (tourGroupScore==null || "".equals(tourGroupScore)) {
			review.setTourGroupScore(0);
		}
		Integer wenjingScore = review.getWenjingScore();
		if (wenjingScore==null || "".equals(wenjingScore)) {
			review.setWenjingScore(0);
		}
		reviewService.insert(review);
		return "redirect:/admin/review/list.do";		
	}
	
	/**
	 * @Title delete
	 * @Description 删除
	 * @Author Bowden
	 * @CreateDate 2015-9-16 上午11:16:31
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id){
		reviewService.delete(id);
		return "redirect:/admin/review/list.do";
	}
	
	/**
	 * @Title save
	 * @Description 详情页处理此问题是否显示
	 * @Author Bowden
	 * @CreateDate 2015-9-8 下午2:35:15
	 */
	@RequestMapping("/save")
	public String save(Review review, Model model){
		reviewService.editIsshow(review);
		tourlineService.setAvgReviewScore(reviewService.getReviewById(review.getId()).getProductId());
		return "redirect:/admin/review/list.do";
	}
	
	/**
	 * 异步修改review否显示
	 */
	@RequestMapping("/isshow")
	@ResponseBody
	public int updateIsshow(@RequestParam("isshow") Integer isshow, @RequestParam("id") String id) {
		if (isshow != null && isshow == 1) {
			isshow = 0;
		} else {
			isshow = 1;
		}
		Review review = new Review(id, isshow, 1);
		reviewService.editIsshow(review);
		tourlineService.setAvgReviewScore(reviewService.getReviewById(id).getProductId());
		return isshow;
	}

	/**
	 * 异步修改review处理状态
	 */
	@RequestMapping("/status")
	@ResponseBody
	public void updateStatus(@RequestParam("id") String id) {
		reviewService.updateStatus(id);
	}
	
	/**
	 * 选择销售中心时异步查询
	 * @param conum
	 * @return
	 * xiejin
	 */
	@RequestMapping("/selectCost")
	@ResponseBody
	public List<Tourline> addbycost(@RequestParam("conum") String conum){
		List<Tourline> tourline = tourlineService.getByCostNumber(conum);
		return tourline;
	}
}
