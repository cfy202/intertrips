package com.wenjing.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.Pages;
import com.wenjing.dao.CouponseproductMapper;
import com.wenjing.dao.ProductMapper;
import com.wenjing.dao.ProducttagMapper;
import com.wenjing.dao.ProductvideoMapper;
import com.wenjing.dao.PromotionProductMapper;
import com.wenjing.entity.Couponseproduct;
import com.wenjing.entity.Product;
import com.wenjing.entity.Producttag;
import com.wenjing.entity.Productvideo;
import com.wenjing.entity.PromotionProduct;
import com.wenjing.util.FreemarkerUtils;
import com.wenjing.util.Tools;
import com.wenjing.webservice.BookErpOrdersService;
import com.wenjing.webservice.ErpProductAddService;


/**
 * @author 作者 E-mail: bowden
 * @version 创建时间：2015-5-14 下午3:15:22
 * 类说明 ：产品  Service
 */
@Service
public class ProductService {
	@Resource
	private ProductMapper productMapper;
	@Resource
	private PromotionProductMapper promotionProductMapper;
	@Resource
	private ProducttagMapper produtcttagMapper;
	@Resource
	private ProductvideoMapper productvideoMapper;
	@Resource
	private CouponseproductMapper couponseproductMapper;
	@Autowired
	private BookErpOrdersService bookErpOrdersService;
	@Autowired
	private ErpProductAddService erpProductAddService;
	/**
	 * 查询所有产品列表
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Product> findAll() {
		List<Product> products = productMapper.findAll();
		return products;
	}
	
	/**
	 * 根据线路ID查找产品
	 * @param tourlineId
	 * @return
	 */
	@Transactional(readOnly=true)
	public Product findByTourlineId(String tourlineId){
		return productMapper.selectByTourlineId(tourlineId);
	}

	
	/**
	 * 根据线路ID查找产品
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public Product findById(String id){
		return productMapper.selectByPrimaryKey(id);
	}

	
	/**
	 * 根据promotionid查询参与活动列表
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Product> findByPromotionId(String promotionid) {
		List<PromotionProduct> promotioProducts = promotionProductMapper.selectByPromotionid(promotionid);
		List<String> productidList = new ArrayList<String>();
		List<Product> products = new ArrayList<Product>() ;
		if(promotioProducts.size()>0){
			 for (PromotionProduct promotionProduct : promotioProducts) {
		        	productidList.add(promotionProduct.getProductid());
				}
			 products = productMapper.findByProductidList(productidList);
		}
		return products;
	}
	
	/**
	 * 根据优惠券ID查询参与活动列表
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Product> findByCouponseId(String couponseid) {
		List<Couponseproduct> couponseproducts = couponseproductMapper.selectByCouponseid(couponseid);
		List<String> productidList = new ArrayList<String>();
		List<Product> products = new ArrayList<Product>() ;
		if(couponseproducts.size()>0){
			 for (Couponseproduct couponseproduct : couponseproducts) {
		        	productidList.add(couponseproduct.getProductid());
				}
			 products = productMapper.findByProductidList(productidList);
		}
		return products;
	}

	/**
	 * 根据costnumber获取产品列表
	 *  bowden
	 * @param costnumber
	 * @return
	 */
	public List<Product> findAllByCostnumber(String costnumber) {
		String timeString = Tools.getTime();
		int time = Tools.getTimestemp(timeString);
		List<Product> products = productMapper.findAllByCostnumber(costnumber,time);
		return products;
	}
   /**
    * @author Sevens
    * @param product
    * @return
    */
	public int updateByPrimaryKeySelective(Product product){
		return productMapper.updateByPrimaryKeySelective(product);
	}
	/**
	 * @author Sevens
	 * @param isshow
	 * @param id
	 * @return
	 * 时间2015-5-20
	 */
	@Transactional
	public int updateWithShow(String id,Integer isshow){
		System.out.println(isshow+"==============");
	   return productMapper.updateWithShow(id,isshow);	
	}
	/**
	 * @author Sevens
	 * 时间2015-5-29
	 * @param id
	 * @param ishot
	 * @return
	 */
	@Transactional
	public int updateWithHot(String id,Integer ishot){
		System.out.println(ishot+"==============");
		return productMapper.updateWithHot(id, ishot);
		
	}
	/**
	 * @author Sevens
	 * 时间2015-5-29
	 * @param id
	 * @param indexShow
	 * @return
	 */
	@Transactional
	public int updateWithIndexshow(String id,Integer indexShow){
		System.out.println(indexShow+"==============");
		return productMapper.updateWithIndexshow(id, indexShow);
	}
	@Transactional(readOnly=true)
	public List<Product> findIshotVisa(Integer type,String costnumber ){
		return productMapper.findIshotVisa(type, costnumber);
	}
 @Transactional(readOnly=true)
 /**
  * @author Sevens
  * 时间2015-6-25
  * @param code
  * @return
  */
 public boolean isExistUserCode(String code){
	 boolean validate = true;
	 List<Product> product = productMapper.isExistUserCode(code);
	 if(product!=null&&product.size()>0){
		 validate =false;
	 }
	 return validate;
 }
 @Transactional(readOnly=true)
 public boolean isExistProductNo(String proudctNo,String costnumber){
	 List<Product> productList = productMapper.isExistProductNo(proudctNo, costnumber);
	 if(productList != null && productList.size() > 0){
		 return true;
	 }	
	 return false;
 }
 
@Transactional
 public int updateProductTags(String tagnames,List<String>  productIds){
	return productMapper.updateProductTags(tagnames, productIds);
}
@Transactional
public int deleteWithProductIdAndCostnumber(String productId,String costnumber){
	return produtcttagMapper.deleteWithProductIdAndCostnumber(productId, costnumber);
}

@Transactional
public int updateProductVideos(String tagnames,List<String>  productIds){
	return productMapper.updateProductTags(tagnames, productIds);
}
@Transactional
public int deleteWithProductIdAndCostnumberV(String productId,String costnumber){
	return productvideoMapper.deleteWithProductIdAndCostnumber(productId, costnumber);
}
@Transactional
/**
 * @author Sevens
 * 时间2015-9-7
 * @param productId
 * @param tagId
 * @param costnumber
 */
public void addTags(String productId,String tagId,String costnumber){
	Producttag ptag = new Producttag();
	ptag.setId(Tools.getUUID());
	ptag.setProductid(productId);
	ptag.setTagid(tagId);
	ptag.setCostnumber(costnumber);
	produtcttagMapper.insert(ptag);
}

/**
 * @author Sevens
 * 时间2015-9-7
 * @param productId
 * @param tagId
 * @param costnumber
 */
public void addVideo(String productId,String videoid,String costnumber){
	Productvideo pvideo = new Productvideo();
	pvideo.setId(Tools.getUUID());
	pvideo.setProductid(productId);
	pvideo.setVideoid(videoid);
	pvideo.setCostnumber(costnumber);
	productvideoMapper.insert(pvideo);
}
	/**
	 * @Title: findByTourlineId
	 * @Description: 根据线路id查询产品code
	 * @return    
	 * @return List<Product>    返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly=true)
	public Product findProductByTourlineId(String tourlineId){
		return productMapper.findByTourlineId(tourlineId);
	};
	
	/**
	 * 根据costnumber获取产品列表
	 *  bowden
	 * @param costnumber
	 * @return
	 */
	public Map<String, Object> findProductByCondition(Map<String, Object> root,String costnumber,String tourCode,
			String tourName,String regionName,int size,HttpServletRequest request) {
		String pageNow = request.getParameter("pageNow");
		Pages page = null;
		List<Product> productList = new ArrayList<Product>();
		String timeString = Tools.getTime();
		int time = Tools.getTimestemp(timeString); // 当前系统时间戳
		int totalCount = productMapper.getProductCount(regionName, costnumber, tourName, tourCode, time);
		if (pageNow != null) {
			page = new Pages(totalCount, Integer.parseInt(pageNow));
		}else {
			page = new Pages(totalCount, 1);
		}
		//判断一页全部显示size大小
		if (size == 0) {
			size = totalCount;
		}
		page.setPageSize(size);
		int startPos = page.getStartPos();
		int pageSize = page.getPageSize();
		productList= productMapper.findProductByCondition(regionName, costnumber, tourName, tourCode, time, startPos, pageSize);
		String pageContent = FreemarkerUtils.getPageContent(request
				.getSession().getServletContext(), page);
		String productContent = FreemarkerUtils.getProductContent(request
				.getSession().getServletContext(), productList, request);
		root.put("productContent", productContent);
		root.put("pageContent", pageContent);
		return root;
	}
	
	@Transactional
	public int synchronizeProduct(String productId){
		List<Product> productList = new ArrayList<Product>();
		Product product = productMapper.findPendingSynchronousTourlineById(productId);
		productList.add(product);
		List<Integer> resultList = erpProductAddService.addProduct(productList);
		if(resultList.get(0) == 1){
			List<String> ids = new ArrayList<String>();
			ids.add(productId);
			productMapper.synchronizeProduct(ids);
			return 1;
		}else{
			return 0;
		}
	} 	
	
	/**
	 * 
	 */
	@Transactional
	public void autoSynchronizeProductToERP(){
		Calendar calendar = Calendar.getInstance();
	    int cyear = calendar.get(Calendar.YEAR);
	    int cmonth = calendar.get(Calendar.MONTH);
	    int cdate = calendar.get(Calendar.DAY_OF_MONTH);
	    calendar.set(cyear, cmonth, cdate, 0, 0);
	    long endTime = calendar.getTime().getTime()/1000;
	    calendar.add(Calendar.YEAR, -1);
		long startTime = calendar.getTime().getTime()/1000;
		List<Product> productList = productMapper.findPendingSynchronousTourline(startTime,endTime);
		if(productList.size() > 0){
			List<Integer> resultList = erpProductAddService.addProduct(productList);
			List<String> synchronizedIds = new ArrayList<String>();
			for(int index=0;index<resultList.size();index++){
				if(resultList.get(index) == 1){
					synchronizedIds.add(productList.get(index).getId());
				}
			}
			productMapper.synchronizeProduct(synchronizedIds);
		}
	}
	/**
	 * 
	 * @return
	 */
	public int getTourlineTotalSize(){
		return productMapper.findTourlineSize();
	}
	
	/**
	 * 获取线路产品的name code和erp系统中的name信息
	 * 
	 * @return
	 */
	public List<Product> showProductInfo(Pages page){
		List<Product> productList = productMapper.findAllTourline(page.getStartPos(),page.getPageSize());
		for(Product product : productList){
			product = bookErpOrdersService.findErpProduct(product);
		}
		return productList;
	}
	
	public String findFilePathByProductId(String id){
		return productMapper.findFilePathByProductId(id);
	}
}
