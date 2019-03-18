package com.wenjing.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.wenjing.Pages;
import com.wenjing.dao.AttractionimageMapper;
import com.wenjing.dao.ItineraryMapper;
import com.wenjing.dao.ProductMapper;
import com.wenjing.entity.Attractionimage;
import com.wenjing.entity.Image;
import com.wenjing.entity.Itinerary;
import com.wenjing.util.FreemarkerUtils;

/**
 * 类说明		线路行程管理service
 * @author xiejin
 * @date 2015-5-8
 */
@Service
public class ItineraryService {
	
	@Resource
	private ItineraryMapper itineraryMapper;
	@Resource
	private AttractionimageMapper attractionimageMapper;
	@Autowired
	private ProductMapper productMapper;

	/**
	 * 根据tourlineid查询所有Itinerary 
	 * @param tourlineid
	 * @return
	 * xiejin
	 */
	@Transactional(readOnly=true)
	public List<Itinerary> findByTourlineId(String tourlineid){
		return itineraryMapper.findByTourlineId(tourlineid);
	}
	
	/**
	 * 删除Itinerary
	 * @param id
	 * @return
	 * xiejin
	 */
	@Transactional
	public int delete(String id) {
		return itineraryMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据id查询Itinerary
	 * @param id
	 * @return
	 * xiejin
	 */
	@Transactional
	public Itinerary findById(String id) {
		return itineraryMapper.selectByPrimaryKey(id);
	}

	/**
	 * 保存Itinerary
	 * @param itinerary
	 * @return
	 * xiejin
	 */
	@Transactional
	public int save(Itinerary itinerary) {
		return itineraryMapper.insert(itinerary);
	}
	
	/**
	 * 修改Itinerary
	 * @param itinerary
	 * @return
	 * xiejin
	 */
	@Transactional
	public int update(Itinerary itinerary) {
		return itineraryMapper.updateByPrimaryKeyWithBLOBs(itinerary);
	}
	
	/**
	 * 根据tourlineid查询最大day
	 * @param tourlineid
	 * @return
	 * xiejin
	 */
	@Transactional
	public int getMaxDay(String tourlineid){
		return itineraryMapper.getMaxDay(tourlineid);
	}
	
	/**
	 * @Title: showImageByPage
	 * @Description: 行程选择图片时异步查询相关图片
	 * @param request
	 * @param model
	 * @param attractionId
	 * @return    
	 * @return Map<String,Object>    返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> showImageByPage(HttpServletRequest request,
			Model model,List<String> attractionId) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String, Object> root = new HashMap<String, Object>();
		String pageNow = request.getParameter("pageNow");
		Pages page = null;
		List<Image> allImages = new ArrayList<Image>();		//图片集合
		int totalCount = attractionimageMapper.getTotalCount(attractionId);		//总数
		if (pageNow != null) {
			page = new Pages(totalCount, Integer.parseInt(pageNow));
		} else {
			page = new Pages(totalCount, 1);
		}
		page.setPageSize(8);
		List<Attractionimage> attractionimages = this.attractionimageMapper.findByAttractionId(attractionId, page.getStartPos(),
				page.getPageSize());	//分页查询Attractionimage
		for (Attractionimage attractionimage : attractionimages) {
			allImages.add(attractionimage.getImageImageid());		//添加Image
		}
		String pageContent = FreemarkerUtils.getPageContent(request
				.getSession().getServletContext(), page);
		root.put("allImages", allImages);
		root.put("pageContent", pageContent);
		return root;
	}
}
