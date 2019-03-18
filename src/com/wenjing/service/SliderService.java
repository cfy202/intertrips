package com.wenjing.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.wenjing.Pages;
import com.wenjing.dao.ImageMapper;
import com.wenjing.dao.SliderMapper;
import com.wenjing.entity.Image;
import com.wenjing.entity.Slider;
import com.wenjing.util.FreemarkerUtils;
import com.wenjing.util.Tools;

/**
 * @author 作者 E-mail:bowden
 * @version 创建时间：2015-4-24 下午6:51:32 类说明 : 幻灯片管理 - Service
 */
@Service
public class SliderService {
	@Resource
	private SliderMapper sliderMapper;

	@Resource
	private ImageMapper imageMapper;

	/**
	 * 查询全部列表
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Slider> findAllByCostNumber(List<String> costnumlist) {
		return sliderMapper.findAllByCostNumber(costnumlist);
	}

	/**
	 * 根据id查找
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Slider findById(String id) {
		return sliderMapper.selectByPrimaryKey(id);
	}

	/**
	 * 修改
	 * 
	 * @param slider
	 */
	@Transactional
	public void update(Slider slider) {
		if (slider.getIsshow() == null) {
			slider.setIsshow(0);
		}
		sliderMapper.updateByPrimaryKey(slider);
	}

	/**
	 * 新增
	 * 
	 * @param slider
	 */
	@Transactional
	public void insert(Slider slider) {
		String id = Tools.getUUID();// 产生uuid
		slider.setId(id);
		if (slider.getIsshow() == null) {
			slider.setIsshow(0);
		}
		sliderMapper.insertSelective(slider);
	}

	/**
	 * 删除
	 */
	@Transactional
	public void delete(String id) {
		sliderMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 分页展示图片列表
	 * 
	 * @param request
	 * @param model
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> showImageByPage(HttpServletRequest request,
			Model model,String usetype,String usetype1) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String, Object> root = new HashMap<String, Object>();
		String pageNow = request.getParameter("pageNow");
		String search = request.getParameter("search");
		if(search==""){
			search =null;
		}
		System.out.println(search);
		Pages page = null;
		List<Image> allImages = new ArrayList<Image>();
//		List<String> costnm = new ArrayList<String>();
//		costnm.add(costnumber);
		int totalCount = imageMapper.getImageCount(usetype,usetype1,search);

		if (pageNow != null) {
			page = new Pages(totalCount, Integer.parseInt(pageNow));
			page.setPageSize(8);
			allImages = this.imageMapper.selectImageByPage(page.getStartPos(),
					page.getPageSize(),usetype,usetype1,search);
		} else {
			page = new Pages(totalCount, 1);
			page.setPageSize(8);
			allImages = this.imageMapper.selectImageByPage(page.getStartPos(),
					page.getPageSize(), usetype,usetype1,search);
		}
		String pageContent = FreemarkerUtils.getPageContent(request
				.getSession().getServletContext(), page);
		root.put("allImages", allImages);
		root.put("pageContent", pageContent);
		return root;
	}

	/**
	 * 分页展示图片列表
	 * 
	 * @param request
	 * @param model
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> imageTourlineUp(HttpServletRequest request,
			Model model) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String, Object> root = new HashMap<String, Object>();
		String pageNow = request.getParameter("pageNow");
		String tourlineid = request.getParameter("tourlineid");
		Pages page = null;
		List<Image> allImages = new ArrayList<Image>();
//		List<String> costnm = new ArrayList<String>();
//		costnm.add(costnumber);
		int totalCount = imageMapper.getImageCountWithTourline(tourlineid);

		if (pageNow != null) {
			page = new Pages(totalCount, Integer.parseInt(pageNow));
			page.setPageSize(8);
			allImages = this.imageMapper.selectImageByPageWithTourline(page.getStartPos(),
					page.getPageSize(),tourlineid);
		} else {
			page = new Pages(totalCount, 1);
			page.setPageSize(8);
			allImages =this.imageMapper.selectImageByPageWithTourline(page.getStartPos(),
					page.getPageSize(),tourlineid);
		}
		String pageContent = FreemarkerUtils.getPageContent(request
				.getSession().getServletContext(), page);
		root.put("allImages", allImages);
		root.put("pageContent", pageContent);
		return root;
	}

	
	/**
	 * @Title: findByeType
	 * @Description: 根据type查询幻灯片图片
	 * @param type
	 * @return
	 * @return List<Slider> 返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly = true)
	public List<Slider> findByType(Integer type, String costnumber) {
		return sliderMapper.findByType(type, costnumber);
	}

	/**
	 * @Title getMaxSort
	 * @Description 获取运营中心下对应类型的幻灯片最大排序号
	 * @Author Bowden
	 * @CreateDate 2015-8-12 下午5:12:31
	 */
	public Integer getMaxSort(String costnumber, int type) {
		return sliderMapper.getMaxSort(costnumber, type);
	}
	/**
	 * 分页展示幻灯片图片
	 * 
	 * @param request
	 * @param model
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> sliderImage(HttpServletRequest request,
			Model model) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String, Object> root = new HashMap<String, Object>();
		String pageNow = request.getParameter("pageNow");
		Pages page = null;
		List<Slider> allImages = new ArrayList<Slider>();
		int totalCount = sliderMapper.getCount();
		if (pageNow != null) {
			page = new Pages(totalCount, Integer.parseInt(pageNow));
			page.setPageSize(4);
			allImages = this.sliderMapper.selectSliderPaging(page.getStartPos(), page.getPageSize());
		} else {
			page = new Pages(totalCount, 1);
			page.setPageSize(4);
			allImages =this.sliderMapper.selectSliderPaging(page.getStartPos(), page.getPageSize());
		}
		String pageContent = FreemarkerUtils.getPageContent(request.getSession().getServletContext(), page);
		root.put("allImages", allImages);
		root.put("pageContent", pageContent);
		return root;
	}
}
