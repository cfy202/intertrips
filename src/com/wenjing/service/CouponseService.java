package com.wenjing.service;

/**
 * Created by XuXuebin on 2014/7/9.
 */

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.Mail;
import com.wenjing.dao.ActivityrulesMapper;
import com.wenjing.dao.CouponsactivityMapper;
import com.wenjing.dao.CouponsduijiangMapper;
import com.wenjing.dao.CouponseMapper;
import com.wenjing.dao.CouponseproductMapper;
import com.wenjing.dao.CouponslevelMapper;
import com.wenjing.entity.Activityrules;
import com.wenjing.entity.Couponsactivity;
import com.wenjing.entity.Couponsduijiang;
import com.wenjing.entity.Couponse;
import com.wenjing.entity.Couponseproduct;
import com.wenjing.entity.Couponslevel;
import com.wenjing.entity.ExchangeCouponse;
import com.wenjing.util.FreemarkerUtils;
import com.wenjing.util.Tools;

/**
 * Service - 管理员
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service
public class CouponseService {

	@Autowired
	private CouponseMapper couponseMapper;
	@Resource
	private CouponsactivityMapper couponsactMapper;
	@Resource
	private CouponslevelMapper couponslevelMapper;
	@Resource
	private CouponsduijiangMapper couponsduijiangMapper;
	@Resource
	private CouponseproductMapper couponseproductMapper;
	@Resource
	private HttpServletRequest request;
	@Resource
	private ActivityrulesMapper activityrulesMapper;
	@Resource
	private TourlineService tourlineService;

    /**
     * @author Sevens	
     * @param coupons
     * 时间2015-5-21
     */
	@Transactional
	public void save(Couponse coupons,String [] productIdArr) {
		int success = couponseMapper.insertSelective(coupons);
		if(productIdArr!=null && productIdArr.length>0){
			String[] newArr = Tools.arrRepetition(productIdArr); //去重
			List<Couponseproduct> pList = new ArrayList<Couponseproduct>();
			for (String string : newArr) {
				Couponseproduct couponseProduct = new Couponseproduct();
				couponseProduct.setId(Tools.getUUID());
				couponseProduct.setCouponseid(coupons.getId());
				couponseProduct.setProductid(string);
				pList.add(couponseProduct);
			}
			if(success>0){
				couponseproductMapper.batchAdd(pList);	//插入促销活动产品关系表
			}
		}
	}
	/**
     * @author Sevens	
     * @param coupons
     * 时间2015-5-21
     */
	@Transactional
	public void update(Couponse coupons,String [] productIdArr) {
		int success = couponseMapper.updateByPrimaryKeySelective(coupons);
		if(productIdArr!=null && productIdArr.length>0){
			String[] newArr = Tools.arrRepetition(productIdArr); //去重
			List<Couponseproduct> pList = new ArrayList<Couponseproduct>();
			for (String string : newArr) {
				Couponseproduct couponseProduct = new Couponseproduct();
				couponseProduct.setId(Tools.getUUID());
				couponseProduct.setCouponseid(coupons.getId());
				couponseProduct.setProductid(string);
				pList.add(couponseProduct);
			}
			if(success>0){
				couponseproductMapper.deleteByCouponseid(coupons.getId());
				couponseproductMapper.batchAdd(pList);	//插入促销活动产品关系表

			}
		}
		
	}
	/**
     * @author Sevens	
     * @param coupons
     * 时间2015-5-21
     */
	@Transactional
	public void delete(String id) {
		couponseMapper.deleteByPrimaryKey(id);
	}
	/**
     * @author Sevens	
     * @param coupons
     * 时间2015-5-21
     */
	@Transactional(readOnly = true)
	public List<Couponse> findAll() {
		
		List<Couponse> couponseList = couponseMapper.findAllByCostNumber(Tools.getCostNumber(request));
		for (Couponse couponse : couponseList) {
			String starttime = Tools.getDtime(couponse.getStartTime());
			String endtime = Tools.getDtime(couponse.getEndTime());
			couponse.setBeginTime(starttime);
			couponse.setOverTime(endtime);
		}
		
		return couponseList;
	}
	/**
     * @author Sevens	
     * @param coupons
     * 时间2015-5-21
     */
	@Transactional(readOnly = true)
	public Couponse findById(String id) {
		Couponse couponse = couponseMapper.selectByPrimaryKey(id);
		String starttime = Tools.getDtime(couponse.getStartTime());
		String endtime = Tools.getDtime(couponse.getEndTime());
		couponse.setBeginTime(starttime);
		couponse.setOverTime(endtime);
		return couponse;
	}
	/**	
     * @author Sevens	
     * @param coupons
     * 时间2015-5-21
     */
   @Transactional(readOnly=true)
   public int getMaxSort(String costnumber){
	   return couponseMapper.getMaxSort(costnumber);
   }
  @Transactional
  public int give(Couponsactivity actd,String [] myselect,Integer place){
	  
	  Couponslevel couponlevel = couponslevelMapper.selectByPrimaryKey(actd.getLevelid());
	  Couponse couponse = couponseMapper.selectByPrimaryKey(actd.getCouponseid());
	  new ArrayList<Couponsactivity>();
	  new ArrayList<Couponsduijiang>();
	  
	  if(myselect!=null&&myselect.length>0&&myselect.length<place){
		  for (String string : myselect) {
			String [] obj = string.split("/"); 
			String code = Tools.getUUID(); 
			Couponsactivity act = new Couponsactivity();
			act.setId(Tools.getUUID());
			act.setCode(code);
			act.setLevelid(actd.getLevelid());
			act.setCouponseid(actd.getCouponseid());
			act.setCreatetime(Tools.getDtimestemp(Tools.getDtime()));
			act.setReleasestatus(0);
			act.setUsestatus(0);
			couponsactMapper.insert(act);
			Couponsduijiang couponsduij = new Couponsduijiang();
			couponsduij.setId(Tools.getUUID());
			couponsduij.setCode(code);
			couponsduij.setEmail(obj[0]);
			couponsduij.setMerberid(obj[1]);
			couponsduij.setWinningtime(Tools.getDtime());
			couponsduij.setExpirationdate(couponse.getExpirationdate());
			couponsduij.setEmailstatus(1);
			couponsduij.setReleasestatus(1);
			couponsduij.setUsestatus(0);
			couponsduij.setCouponsname(couponse.getName()+couponlevel.getName()+couponlevel.getDistype()+couponlevel.getDisvalue());
			couponsduij.setCouponseid(actd.getCouponseid());
			couponsduij.setLevelId(actd.getLevelid());
			couponsduijiangMapper.insert(couponsduij);
			sendMails(request.getServletContext(), couponsduij);
		}
	  }else{
		  for(int i = 0;i<place;i++){
			  String code = Tools.getUUID(); 
				Couponsactivity act = new Couponsactivity();
				act.setId(Tools.getUUID());
				act.setCode(code);
				act.setLevelid(actd.getLevelid());
				act.setCouponseid(actd.getCouponseid());
				act.setCreatetime(Tools.getDtimestemp(Tools.getDtime()));
				act.setReleasestatus(0);
				act.setUsestatus(0);
				couponsactMapper.insert(act);  
		  }
	  }
	  return 0;
  }
  /**
   * 给第三方合作平台生成优惠券
   * @param couponse
   * @return
   */
  @Transactional
  public int giveCompany(Couponse couponse,String levelId){
	  Couponsduijiang couponsduij = new Couponsduijiang();
		couponsduij.setId(Tools.getUUID());
		couponsduij.setCode(couponse.getCouponseCode());
		couponsduij.setWinningtime(Tools.getDtime());
		couponsduij.setExpirationdate(couponse.getExpirationdate());
		couponsduij.setEmailstatus(1);
		couponsduij.setReleasestatus(couponse.getRemaining());
		couponsduij.setUsestatus(0);
		couponsduij.setCouponsname(couponse.getName());
		couponsduij.setCouponseid(couponse.getId());
		couponsduij.setLevelId(levelId);
		this.update(couponse, null);
		return couponsduijiangMapper.insert(couponsduij);
  }
  
  
  
  public void create(String couponseid){
		 Couponse couponse = this.findById(couponseid);
		 if(couponse.getType()!=null&&couponse.getType()==2){
			 tourlineService.createWithCouponse(couponse.getCostnumber(), couponse); 
		 }else{
			 ownUsCreate(couponse);
		 }
		
  }
  
  public void ownUsCreate(Couponse couponse){
	  Map<String, Object> root = new HashMap<String, Object>();
		 root.put("couponse", couponse);
		 List<Activityrules> activityrules = activityrulesMapper.findByCouponseId(couponse.getId());
		 root.put("activityrules",activityrules);
		 root.put("request", request);
			
			
			// 静态页面的完整路径
			String str = request.getSession().getServletContext().getRealPath("/")+ couponse.getFilePath();
			if (str.indexOf("/") != -1) {

				String folder = str.substring(0, str.lastIndexOf("/"));
				File f = new File(folder);
				if (!f.exists() && !f.isDirectory()) { // 是文件夹，且文件夹不存在则创建文件夹
					f.mkdir();
				}
				File file = new File(str);
				// 如果静态文件存在，则删除之前的静态页面，重新生成新的静态页面
				if (file.isFile() && file.exists()) {
					file.delete();
				}
				str = null;// 释放资源
				if (file != null) {
					file = null;
				}
				FreemarkerUtils.createHTML(
						request.getSession().getServletContext(), root,
						"/admin/manage/couponse/acty.ftl", couponse.getFilePath());
			}
			couponse.setIsCreate(1);
			couponseMapper.updateByPrimaryKey(couponse);
	  
  }
  /**
   * 优惠券兑换方法
   * @param costnumber
   * @param totaleprice
   * @param code
   * @return
   */
  public ExchangeCouponse Exchange(String costnumber,Integer total,String code){
        Object object = request.getSession().getAttribute("couponsePrice");
        if(object!=null){
        	request.getSession().removeAttribute("couponsePrice");
        }
		ExchangeCouponse exchange = new ExchangeCouponse();
		Couponsduijiang couponsduij = couponsduijiangMapper.findWithExchange(costnumber, code);
		int nowtime = Tools.getTimestemp(Tools.getTime());
		if(couponsduij!=null){
			 if(couponsduij.getUsestatus()==0&&couponsduij.getReleasestatus()>=1){
				 Couponse couponse = this.findById(couponsduij.getCouponseid());
				 if(couponse.getRemaining() > 0&&Tools.getDtimestemp(couponse.getExpirationdate())>nowtime){
					 this.update(couponse, null);
					 Couponslevel couponslevel = couponslevelMapper.selectByPrimaryKey(couponsduij.getLevelId());
					 if(couponslevel.getDistype().equals("%")){
						 exchange.setReleaseprice(new BigDecimal(total*(couponslevel.getDisvalue()/100.0)).setScale(0, BigDecimal.ROUND_DOWN)); 
						 exchange.setTotalprice(new BigDecimal(total-exchange.getReleaseprice().intValue())); 
					 }
					 if(couponslevel.getDistype().equals("$")){
						 exchange.setReleaseprice(new BigDecimal(couponslevel.getDisvalue()));
						 exchange.setTotalprice(new BigDecimal(total-couponslevel.getDisvalue()));
					 }
					
					 request.getSession().setAttribute("couponsePrice", exchange.getReleaseprice());
				 }else{
					 exchange.setMeassage("Coupons have expired!"); 
				 }
			 }else{
				 exchange.setMeassage("Coupons have been used!"); 
			 }
		}else{
			exchange.setMeassage("Coupons do not exist.");
		}
		
		return exchange;

  }
  /**
   * 给派送的会员发送邮件
   * @param context
   * @param couponsduijing
   */
  public void sendMails (ServletContext context,Couponsduijiang couponsduijing){
	    Mail gmail = new Mail();
		gmail.setSendTo(new String[]{couponsduijing.getEmail()});
		gmail.setEmailSubjectTxt(couponsduijing.getCouponsname());
		String content = FreemarkerUtils.getCouponsePage(request.getServletContext(), couponsduijing);
		gmail.setEmailMsgTxt(content);
		
		try {
			gmail.sendSSLMessage(); // 发送邮件
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }
}