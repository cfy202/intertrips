
package com.wenjing.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.wenjing.dao.CostMapper;
import com.wenjing.dao.PriceMapper;
import com.wenjing.dao.ProductMapper;
import com.wenjing.dao.VisaMapper;
import com.wenjing.entity.Cost;
import com.wenjing.entity.Price;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;

/**
 * 其他产品的价格管理
 * @author sevens
 *
 */
@Service
public class PriceService {

	
	@Resource
	private PriceMapper priceMapper;
	@Resource
	private ProductMapper productMapper;
	@Resource
	private VisaMapper visaMapper;
	@Resource
	private CostMapper costMapper;
	
	
	/**
	 * 查询所有产品标签 
	 * 
	 * @return
	 * sevens
	 */
	@Transactional(readOnly=true)
	public List<Price> findAll(String productId, HttpServletRequest request, HttpServletResponse response, Model model){
		List<Price> pricelist = priceMapper.findByCostnumberOrProdcutId(productId);
		return pricelist;
	}
	
	/**
	 * 根据id删除price
	 * @param id
	 * @return
	 */
	@Transactional
	public int delete(String id) {
		Price price = priceMapper.selectByPrimaryKey(id);
		priceMapper.deleteByPrimaryKey(id);
		if(price!=null){
			this.addCostNumToTourline( price.getProductid());
		}
		return 0;
	}

	/**
	 * 根据id查询price
	 * @param id
	 * @return
	 */
	@Transactional
	public Price findById(String id) {
		return priceMapper.selectByPrimaryKey(id);
	}

	/**
	 * 保存price
	 * @param price
	 * @return
	 */
	@Transactional
	public int save(Price price) {
		return priceMapper.insert(price);
	}
	
	/**
	 * 修改price
	 * @param price
	 * @return
	 * xiejin
	 */
	@Transactional
	public int update(Price price) {
		return priceMapper.updateByPrimaryKeySelective(price);
	}
	/**
	 * 将价格对应的costnumid添加到线路表
	 * @param tourlineid
	 * @param costnumber
	 */
	@Transactional
	private void addCostNumToTourline(String productid) {
		List<String> costNumIdList = priceMapper.getPriceCostNumByProductId(productid);
		String costNumIds =  "";
		if(costNumIdList!=null && costNumIdList.size()>0){
		   costNumIds = Tools.ListToString(costNumIdList);
		}
		visaMapper.updateCostNumIds(productid, costNumIds);
		productMapper.updateCostNumIds(productid, costNumIds);
	}
	/**
	 * @author Administrator
	 * 时间：2015-11-15
	 * @param productid
	 * @param costNum
	 * @return
	 */
   public boolean copyPrice(String productid, String costNum){
	   boolean flag = false;
	   if(productid !=null && !"".equals(productid) && costNum!=null && !"".equals(costNum)){
			List<String> costNumList = new ArrayList<String>();
			costNumList.add(costNum);
			//删除costnum之外销售中心日期及价格
			priceMapper.deleteNotCostnumber(costNum, productid);
			//查询此复制日期和价格
			List<Price> pricelist = priceMapper.findByCostnumberOrProdcutId2(costNum, productid);
			if(pricelist == null || pricelist.size()==0){
				return false;
			}
			//如果不是美国销售中心，先复制为美国价格，然后在已美国价格复制给其他销售中心
			if(!"d8fe5ef1de7747ab86588f9880f1aa77".equals(costNum)){
				copyToUSD(costNum, pricelist);
				costNum = "d8fe5ef1de7747ab86588f9880f1aa77";
				costNumList.add(costNum);
				pricelist = priceMapper.findByCostnumberOrProdcutId2(costNum, productid);
			}
			//查询不是此costnum的销售中心集合
			Cost coList = costMapper.getIsNoThisCostNum(costNum);
				List<Price> pricelist2 = new ArrayList<Price>();
				BigDecimal exchangeRate = coList.getExchangerate(); // 美元兑换汇率
				 
				for (Price price : pricelist) {
					  BigDecimal sellingPrice = price.getPrice();
					  Price pricenew = new Price();
					  if(sellingPrice!=null){
						  pricenew.setId(Tools.getUUID());
						  pricenew.setPrice(sellingPrice.multiply(exchangeRate).setScale(0, BigDecimal.ROUND_CEILING));
						  pricenew.setCostnumber(coList.getId());
						  pricenew.setTitle(price.getTitle());
						  pricenew.setProductid(productid);
						  pricenew.setServiceType(price.getServiceType());
					  }
					  pricelist2.add(pricenew); 
				}
				priceMapper.batchAddPrice(pricelist2);
			}
			flag = true;
	   
	   return flag;
   }
   
   public void copyToUSD(String costNum,List<Price> pricelist){
	    Cost cost = costMapper.selectByPrimaryKey(costNum);
		String copyCostnum = "d8fe5ef1de7747ab86588f9880f1aa77"; // 要复制的销售中心id------美元
		String currencyId = "e897e21a556442a583ee14e9c22b0817"; // 美元
		BigDecimal exchangeRate = cost.getExchangerate(); // 美元兑换汇率 
		List<Price> pricelist1 = new ArrayList<Price>();
		for (Price price : pricelist) {
			  Price pricenew = new Price();
			  BigDecimal sellingPrice = price.getPrice();
			  if(sellingPrice!=null){
				  pricenew.setId(Tools.getUUID());
				  pricenew.setPrice(sellingPrice.divide(exchangeRate, 0, BigDecimal.ROUND_CEILING));
				  pricenew.setCostnumber(copyCostnum);
				  pricenew.setTitle(price.getTitle());
				  pricenew.setProductid(price.getProductid());
				  pricenew.setServiceType(price.getServiceType());
			  }
			  pricelist1.add(pricenew); 
		}
		priceMapper.batchAddPrice(pricelist1);
   }
   
   /**
    * 根据产品ID和销售中心ID加载服务费
    * 
    * @param productId
    * @return
    */
   public List<Price> findByProduct(String productId){
	   return priceMapper.findByCostnumberOrProdcutId(productId);
   }
   
}
