package com.wenjing.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wenjing.dao.CostMapper;
import com.wenjing.dao.TourdateMapper;
import com.wenjing.dao.TourpriceMapper;
import com.wenjing.entity.Tourdate;
import com.wenjing.entity.Tourprice;
import com.wenjing.service.TourDateService;
import com.wenjing.util.Tools;

/**
 * 批量复制日期和价格
 * @author bowden
 *
 */
@Controller
@RequestMapping("/admin")
public class CopyDateAndPriceController {

	@Resource
	private TourDateService tourDateService;
	@Resource
	private TourdateMapper tourdateMapper;
	@Resource
	private TourpriceMapper tourpriceMapper;
	@Resource
	private CostMapper costMapper;
	@Resource
	private HttpServletRequest request;
	
	/**
	 * @Title copyDateAndPrice
	 * @Description 复制价格、日期
	 * @Author Bowden
	 * @CreateDate 2015-8-6 下午4:24:07
	 */
	@RequestMapping("/copy_date_price")
    public String copyDateAndPrice(){
		String costnum = "d8fe5ef1de7747ab86588f9880f1aa77"; //----美国
		
//		String copyCostnum = "33e25a5f67274fab94b84e21adb95ef0"; //要复制的销售中心id------中国
//		String currencyId = "12b1c0a0a93d45a6a83ae9ef5e12dbb6"; //人民币
//		BigDecimal exchangeRate = new BigDecimal(6.2081); //美元兑换人民币汇率
		
//		String copyCostnum = "5de4d1a0ef24496292b620b4aa5fd061"; //要复制的销售中心id------加拿大
//		String currencyId = "75e41af1-2ac1-11e5-9e82-c4544401756d"; //加币
//		BigDecimal exchangeRate = new BigDecimal(1.2726); //美元兑换加币汇率
//		
//		String copyCostnum = "bbebc7de2fdf470c854620501fef4dd1"; //要复制的销售中心id------澳洲
//		String currencyId = "75f1a5b6-2ac1-11e5-9e82-c4544401756d"; //澳元
//		BigDecimal exchangeRate = new BigDecimal(1.3401); //美元兑换澳元汇率
		
		String copyCostnum = "a94b44b8ece14baf8812ede2de8d3701"; //要复制的销售中心id------欧洲
		String currencyId = "9209b534bc6049de9a273adb13df632b"; //欧元
		BigDecimal exchangeRate = new BigDecimal(0.8982); //美元兑换欧元元汇率
		
		List<Tourdate> dateAndPriceList = tourDateService.findAllDateAndPriceByCostnum(costnum);
		List<Tourdate> tourdateList = new ArrayList<Tourdate>();
		List<Tourprice> tourpriceList =new ArrayList<Tourprice>();
		for (Tourdate tourdate : dateAndPriceList) {
			BigDecimal markedPrice = tourdate.getTourprice().getMarkedprice();
			if(markedPrice != null ){
				markedPrice = markedPrice.multiply(exchangeRate).setScale(0, BigDecimal.ROUND_CEILING);
			}
			
			BigDecimal sellingPrice = tourdate.getTourprice().getSellingprice();
			if(sellingPrice != null ){
				sellingPrice = sellingPrice.multiply(exchangeRate).setScale(0, BigDecimal.ROUND_CEILING);
//				String sellStr = sellingPrice.toString();
//				if(!sellStr.endsWith("9")){
//					String b1 = sellStr.substring(0, sellStr.length()-1);
//					sellStr = b1 + "9";
//					sellingPrice = new BigDecimal(sellStr);
//				}
			}
			
			BigDecimal threesellingPrice = tourdate.getTourprice().getThreesellingprice();
			if(threesellingPrice != null){
				threesellingPrice = threesellingPrice.multiply(exchangeRate).setScale(0, BigDecimal.ROUND_CEILING);
			}
			
			BigDecimal foursellingPrice = tourdate.getTourprice().getFoursellingprice();
			if(foursellingPrice != null){
				foursellingPrice = foursellingPrice.multiply(exchangeRate).setScale(0, BigDecimal.ROUND_CEILING);
			}
			
			BigDecimal singleRoomPrice = tourdate.getTourprice().getSingleroomprice();
			if(singleRoomPrice != null){
				singleRoomPrice = singleRoomPrice.multiply(exchangeRate).setScale(0, BigDecimal.ROUND_CEILING);
			}
			
			BigDecimal extraBedPrice = tourdate.getTourprice().getExtrabedprice();
			if(extraBedPrice != null){
				extraBedPrice = extraBedPrice.multiply(exchangeRate).setScale(0, BigDecimal.ROUND_CEILING);
			}
			
			BigDecimal noBedPrice = tourdate.getTourprice().getNobedprice();
			if(noBedPrice != null){
				noBedPrice = noBedPrice.multiply(exchangeRate).setScale(0, BigDecimal.ROUND_CEILING);
			}
			
			BigDecimal babyPrice = tourdate.getTourprice().getBabyPrice();
			if(babyPrice != null){
				babyPrice = babyPrice.multiply(exchangeRate).setScale(0, BigDecimal.ROUND_CEILING);
			}
			
			BigDecimal childPrice = tourdate.getTourprice().getChildPrice();
			if(childPrice != null){
				childPrice = childPrice.multiply(exchangeRate).setScale(0, BigDecimal.ROUND_CEILING);
			}
			
			String tourdateid = Tools.getUUID();
			Tourdate tourdate2 = new Tourdate(tourdateid, 
											  tourdate.getStartdate(), 
											  tourdate.getTotalnum(), 
											  tourdate.getSoldnum(), 
											  tourdate.getRemainnum(), 
											  tourdate.getIsshow(), 
											  tourdate.getIshot(), 
											  tourdate.getIscall(), 
											  tourdate.getRemark(), 
											  tourdate.getUpdatetime(), 
											  tourdate.getEditor(), 
											  tourdate.getSort(), 
											  tourdate.getProductid(),
											  tourdate.getEnddate(), 
											  tourdate.getDateweek(),
											  copyCostnum,
											  tourdate.getDays(),
											  tourdate.getSealGroupDate());
			tourdateList.add(tourdate2);
			
			Tourprice tourprice = new Tourprice(Tools.getUUID(), 
					                            markedPrice, 
												sellingPrice, 
												singleRoomPrice, 
												extraBedPrice, 
												noBedPrice, 
												currencyId, 
												tourdateid, 
												copyCostnum, 
												threesellingPrice, 
												foursellingPrice, 
												babyPrice, 
												childPrice);
			tourpriceList.add(tourprice);
		}
//		int dateSuccess = tourdateMapper.batchAddDate(tourdateList);
//		int priceSuccess = tourpriceMapper.batchAddprice(tourpriceList);
//		System.out.println(dateSuccess + "-------------------------" + priceSuccess);
		return null;
	}
	
	/**
	 * @Title copyUSDToCNY
	 * @Description 人民币价格复制成美元
	 * @Author Bowden
	 * @CreateDate 2015-8-10 下午3:37:51
	 */
	@RequestMapping("/copy_cny_usd")
    public String copyCnyToUsd(){
		String costnum = "33e25a5f67274fab94b84e21adb95ef0"; //----人民币
		
		String copyCostnum = "d8fe5ef1de7747ab86588f9880f1aa77"; //要复制的销售中心id------美元
		String currencyId = "e897e21a556442a583ee14e9c22b0817"; //美元
		BigDecimal exchangeRate = new BigDecimal(6.2081); //美元兑换澳元汇率
		
		List<Tourdate> dateAndPriceList = tourDateService.findAllDateAndPriceByCostnum2(costnum);
		List<Tourdate> tourdateList = new ArrayList<Tourdate>();
		List<Tourprice> tourpriceList =new ArrayList<Tourprice>();
		for (Tourdate tourdate : dateAndPriceList) {
			BigDecimal markedPrice = tourdate.getTourprice().getMarkedprice();
			if(markedPrice != null ){
				markedPrice = markedPrice.divide(exchangeRate, 0, BigDecimal.ROUND_CEILING);
			}
			
			BigDecimal sellingPrice = tourdate.getTourprice().getSellingprice();
			if(sellingPrice != null ){
				sellingPrice = sellingPrice.divide(exchangeRate, 0, BigDecimal.ROUND_CEILING);
//				String sellStr = sellingPrice.toString();
//				if(!sellStr.endsWith("9")){
//					String b1 = sellStr.substring(0, sellStr.length()-1);
//					sellStr = b1 + "9";
//					sellingPrice = new BigDecimal(sellStr);
//				}
			}
			
			BigDecimal threesellingPrice = tourdate.getTourprice().getThreesellingprice();
			if(threesellingPrice != null){
				threesellingPrice = threesellingPrice.divide(exchangeRate, 0, BigDecimal.ROUND_CEILING);
			}
			
			BigDecimal foursellingPrice = tourdate.getTourprice().getFoursellingprice();
			if(foursellingPrice != null){
				foursellingPrice = foursellingPrice.divide(exchangeRate, 0, BigDecimal.ROUND_CEILING);
			}
			
			BigDecimal singleRoomPrice = tourdate.getTourprice().getSingleroomprice();
			if(singleRoomPrice != null){
				singleRoomPrice = singleRoomPrice.divide(exchangeRate, 0, BigDecimal.ROUND_CEILING);
			}
			
			BigDecimal extraBedPrice = tourdate.getTourprice().getExtrabedprice();
			if(extraBedPrice != null){
				extraBedPrice = extraBedPrice.divide(exchangeRate, 0, BigDecimal.ROUND_CEILING);
			}
			
			BigDecimal noBedPrice = tourdate.getTourprice().getNobedprice();
			if(noBedPrice != null){
				noBedPrice = noBedPrice.divide(exchangeRate, 0, BigDecimal.ROUND_CEILING);
			}
			
			BigDecimal babyPrice = tourdate.getTourprice().getBabyPrice();
			if(babyPrice != null){
				babyPrice = babyPrice.divide(exchangeRate, 0, BigDecimal.ROUND_CEILING);
			}
			
			BigDecimal childPrice = tourdate.getTourprice().getChildPrice();
			if(childPrice != null){
				childPrice = childPrice.divide(exchangeRate, 0, BigDecimal.ROUND_CEILING);
			}
			
			String tourdateid = Tools.getUUID();
			Tourdate tourdate2 = new Tourdate(tourdateid, 
											  tourdate.getStartdate(), 
											  tourdate.getTotalnum(), 
											  tourdate.getSoldnum(), 
											  tourdate.getRemainnum(), 
											  tourdate.getIsshow(), 
											  tourdate.getIshot(), 
											  tourdate.getIscall(), 
											  tourdate.getRemark(), 
											  tourdate.getUpdatetime(), 
											  tourdate.getEditor(), 
											  tourdate.getSort(), 
											  tourdate.getProductid(), 
											  tourdate.getEnddate(), 
											  tourdate.getDateweek(),
											  copyCostnum,
											  tourdate.getDays(),
											  tourdate.getSealGroupDate());
			tourdateList.add(tourdate2);
			
			Tourprice tourprice = new Tourprice(Tools.getUUID(), 
					                            markedPrice, 
												sellingPrice, 
												singleRoomPrice, 
												extraBedPrice, 
												noBedPrice, 
												currencyId, 
												tourdateid, 
												copyCostnum, 
												threesellingPrice, 
												foursellingPrice, 
												babyPrice, 
												childPrice);
			tourpriceList.add(tourprice);
		}
//		int dateSuccess = tourdateMapper.batchAddDate(tourdateList);
//		int priceSuccess = tourpriceMapper.batchAddprice(tourpriceList);
//		System.out.println(dateSuccess + "-------------------------" + priceSuccess);
		return null;
	}
	
	
	@RequestMapping("/updateSellingPrice")
	public String updateSellingPrice(){
		
		List<Tourprice> tList = tourpriceMapper.findSellingPriceByCostNum(Tools.getCostNumber(request));
		List<Tourprice> tourprices = new ArrayList<Tourprice>();
		for (Tourprice tourprice : tList) {
			BigDecimal sellingPrice = tourprice.getSellingprice().setScale(0, BigDecimal.ROUND_CEILING);;
			String sellStr = sellingPrice.toString();
			if(!sellStr.endsWith("9")){
				String b1 = sellStr.substring(0, sellStr.length()-1);
				sellStr = b1 + "9";
				sellingPrice = new BigDecimal(sellStr);
				tourprice.setSellingprice(sellingPrice);
				tourprices.add(tourprice);
			}
		}
		if(tourprices != null && tourprices.size()>0){
//			int updateSuccess = tourpriceMapper.batchUpdateSellingPrice(tourprices);
//			System.out.println(updateSuccess);
		}
		return null;
	}
	
	
	public static void main(String[] args) {
		BigDecimal a = new BigDecimal(2161);
		BigDecimal b = new BigDecimal(6.2081);
		a = a.divide(b, 0, BigDecimal.ROUND_CEILING);
		System.out.println(a);
	}
}
