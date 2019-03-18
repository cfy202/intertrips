package com.wenjing.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wenjing.dao.CostMapper;
import com.wenjing.dao.CurrencyMapper;
import com.wenjing.dao.ProducttagMapper;
import com.wenjing.dao.PromotionMapper;
import com.wenjing.dao.PromotionProductMapper;
import com.wenjing.dao.TagMapper;
import com.wenjing.entity.Cost;
import com.wenjing.entity.Promotion;
import com.wenjing.entity.PromotionProduct;
import com.wenjing.util.FreemarkerUtils;
import com.wenjing.util.Tools;

/**
 * @author 作者 E-mail: bowden
 * @version 创建时间：2015-4-23 下午7:04:57
 *  类说明: Service - 促销
 */
@Service
public class PromotionService {
	@Resource
	private PromotionMapper promotionMapper;
	@Resource
	private CostMapper costMapper;
	@Resource
	private CurrencyMapper currencyMapper;
	@Resource
	private PromotionProductMapper promotionProductMapper;
	@Resource
	private TagMapper tagMapper;
	@Resource
	private ProducttagMapper producttagMapper;
	@Resource
	private HttpServletRequest request;
	@Resource
	private TourlineService tourlineService;
	@Resource
	private PageService pageService;

	/**
	 * 查询全部活动列表
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Promotion> findAllByCostNumber(HttpServletRequest request) {
		List<Cost> costlist = (List<Cost>) request.getSession().getAttribute("cost");
		List<String> costnumberlist = new ArrayList<String>();
		for (Cost cost : costlist) {
			costnumberlist.add(cost.getId());
		}
		List<Promotion> promotions = promotionMapper.findAllByCostNumber(costnumberlist);
		for (Promotion promotion : promotions) {
			String starttime = Tools.getDtime(promotion.getStarttime());
			String endtime = Tools.getDtime(promotion.getEndtime());
			promotion.setBeginDate(starttime);
			promotion.setEndDate(endtime);
		}
		return promotions;
	}

	/**
	 * 根据运营中心costnumber,查询此运营中心orderid最大值
	 * @return
	 */
	@Transactional(readOnly = true)
	public Integer getMaxSort(String costnumber) {
		return promotionMapper.getMaxSort(costnumber);
	}
	
	/**
	 * 修改保存
	 * 
	 * @param promotion
	 */
	@Transactional
	public void update(Promotion promotion,HttpServletRequest request) {
		String[] productIdArr = request.getParameterValues("productIds");
		promotion.setModifydate(new Date());
		String starttime = promotion.getBeginDate();
		String endtime = promotion.getEndDate();
		if(starttime!=null && endtime!=null){
			promotion.setStarttime(Tools.getDtimestemp(starttime));
			promotion.setEndtime(Tools.getDtimestemp(endtime));
		}
		if (promotion.getIsshow() == null) {
			promotion.setIsshow((byte) 0);
		}
		promotion.setIsCreate(0);
		int success = promotionMapper.updateByPrimaryKeySelective(promotion);	//修改促销活动
		if(productIdArr!=null && productIdArr.length>0){
			String[] newArr = Tools.arrRepetition(productIdArr);  //去重
			List<PromotionProduct> pList = new ArrayList<PromotionProduct>();
			for (String string : newArr) {
				PromotionProduct promotioProduct = new PromotionProduct();
				promotioProduct.setId(Tools.getUUID());
				promotioProduct.setPromotionid(promotion.getId());
				promotioProduct.setProductid(string);
				pList.add(promotioProduct);
			}
			if(success>0){	//促销活动修改成功
				promotionProductMapper.deleteBypromotionid(promotion.getId()); // 根据promotionid删除关联表数据
		        promotionProductMapper.batchAdd(pList); // 批量插入参加活动产品
		       
		       
//				//***********促销活动标题作为标签name*****开始*******
//				String tagId = Tools.getUUID();
//				Tag tag = new Tag();
//				tag.setId(tagId);
//				tag.setName(promotion.getTitle());
//				tag.setBgcolor("ff5500");
//				tag.setTextcolor("ffffff");
//				int tagSuccess = tagMapper.insert(tag);	//插入标签
//				if (tagSuccess >0) {
//					for (String string : newArr) {
//						Producttag producttag = new Producttag();
//						producttag.setId(Tools.getUUID());
//						producttag.setTagid(tagId);
//						producttag.setProductid(string);
//						producttag.setCostnumber(promotion.getCostnumber());
//						producttagMapper.insert(producttag);	//插入产品标签关系表
//					}
//				}
//				//*************结束*****************************
		       
			}
		}else {
			promotionProductMapper.deleteBypromotionid(promotion.getId()); // 根据promotionid删除关联表数据
		}
	}

	/**
	 * 新增
	 * 
	 * @param promotion
	 */
	@Transactional
	public void insert(Promotion promotion,HttpServletRequest request) {
		String[] productIdArr = request.getParameterValues("productIds");
		String id = Tools.getUUID();// 产生uuid
		promotion.setId(id);
		promotion.setCreatedate(new Date());
		promotion.setModifydate(new Date());
		String startTime = promotion.getBeginDate();
		String endTime = promotion.getEndDate();
		if(startTime!=null && endTime!=null){
			promotion.setStarttime(Tools.getDtimestemp(startTime));
			promotion.setEndtime(Tools.getDtimestemp(endTime));
		}
		if (promotion.getIsshow() == null) {
			promotion.setIsshow((byte) 0);
		}
		promotion.setIsCreate(0);
		int success = promotionMapper.insertSelective(promotion);	//插入促销活动
		if(productIdArr!=null && productIdArr.length>0){
			String[] newArr = Tools.arrRepetition(productIdArr); //去重
			List<PromotionProduct> pList = new ArrayList<PromotionProduct>();
			for (String string : newArr) {
				PromotionProduct promotioProduct = new PromotionProduct();
				promotioProduct.setId(Tools.getUUID());
				promotioProduct.setPromotionid(promotion.getId());
				promotioProduct.setProductid(string);
				pList.add(promotioProduct);
			}
			if(success>0){
				promotionProductMapper.batchAdd(pList);	//插入促销活动产品关系表
//				//***********促销活动标题作为标签name*****开始*******
//				String tagId = Tools.getUUID();
//				Tag tag = new Tag();
//				tag.setId(tagId);
//				tag.setName(promotion.getTitle());
//				tag.setBgcolor("ff5500");
//				tag.setTextcolor("ffffff");
//				int tagSuccess = tagMapper.insert(tag);	//插入标签
//				if (tagSuccess >0) {
//					for (String string : newArr) {
//						Producttag producttag = new Producttag();
//						producttag.setId(Tools.getUUID());
//						producttag.setTagid(tagId);
//						producttag.setProductid(string);
//						producttag.setCostnumber(promotion.getCostnumber());
//						producttagMapper.insert(producttag);	//插入产品标签关系表
//					}
//				}
//				//*************结束*****************************
			}
		}
	}

	/**
	 * 根据promotion的id查询
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Promotion findPromotionById(String id) {
		Promotion promotion = promotionMapper.selectByPrimaryKey(id);
		String starttime = Tools.getDtime(promotion.getStarttime());
		String endtime = Tools.getDtime(promotion.getEndtime());
		promotion.setBeginDate(starttime);
		promotion.setEndDate(endtime);
		return promotion;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@Transactional
	public void delete(String id) {
		promotionProductMapper.deleteBypromotionid(id); // 根据promotionid删除关联表数据
		promotionMapper.deleteByPrimaryKey(id);
	}
	
	
	/**
	 * @Title: findBytourlineId
	 * @Description: 根据线路id查询促销活动
	 * @param tourlineId
	 * @return    
	 * @return List<Promotion>    返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly = true)
	public List<Promotion> findBytourlineId(String tourlineId,String costnumber){
		int timeNow = Tools.getDtimestemp(Tools.getDtime()); //当前时间戳
		return promotionMapper.findBytourlineId(tourlineId, costnumber, timeNow);
	}
	
	/**
	 * @Title: findByProductIdCostnumber
	 * @Description: 根据产品id和costnumber查询促销活动
	 * @param tourlineId
	 * @return    
	 * @return List<Promotion>    返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly = true)
	public List<Promotion> findByProductIdCostnumber(String productId,String costnumber){
		int timeNow = Tools.getDtimestemp(Tools.getDtime()); //当前时间戳
		return promotionMapper.findByProductIdCostnumber(productId, costnumber,timeNow);
	}

	@Transactional
	public int updateByPrimaryKeySelective(Promotion record){
		return promotionMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * @Title: getByCostnumber
	 * @Description: 前台根据销售中心查询促销活动id,title
	 * @param costnumber
	 * @param timeNow
	 * @return    
	 * @return List<Promotion>    返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly=true)
	public List<Promotion> getPartByCostnumber(String costnumber,int timeNow){
		return promotionMapper.getPartByCostnumber(costnumber, timeNow);
	}
	/**
	 * 查询所有促销活动
	 * 时间：2015-12-30
	 * @author Sevens
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Promotion> findAllNotByCostNumber(){
		return promotionMapper.findAllNotByCostNumber();
	}
	
	/**
	 * @Title: viewTourlinePromotion
	 * @Description: 促销活动线路展示页面生成
	 * @param costnumber
	 * @param id
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 */
	
	public void createTourlinePromotion(Promotion promotion,HttpServletRequest request){
		
		Map<String, Object> root = new HashMap<String, Object>(); 
		pageService.getNavigationCreate(root, promotion.getCostnumber());								//获取上，下导航
		tourlineService.findByPromotionIdCreate(promotion.getCostnumber(), promotion.getId(), root);	//查询参与某一促销活动的线路
		root.put("now",Tools.getTime());
		root.put("promotion",promotion);
		root.put("request", request); 
		if (promotion.getFilePath()!= null && !"".equals(promotion.getFilePath())) {
			// 静态页面的完整路径 
			String str = request.getSession().getServletContext().getRealPath("/") + promotion.getFilePath(); 
			File file = new File(str);
			// 如果静态文件存在，则删除静态页面之后重新生成 
			if (file.isFile() && file.exists()) {
				file.delete(); 
			}
			str = null;
			// 释放资源 if (file != null) file = null;
			FreemarkerUtils.createHTML(request.getSession().getServletContext(),
					root, "/front/promotionTourline.ftl",promotion.getFilePath());
			promotion.setIsCreate(1);
			this.updateByPrimaryKeySelective(promotion);	//修改生成状态
		}
	
	}
}
