package com.wenjing.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.wenjing.dao.ProductMapper;
import com.wenjing.dao.ShoppingCartMapper;
import com.wenjing.entity.Member;
import com.wenjing.entity.Product;
import com.wenjing.entity.ShoppingCart;
import com.wenjing.exception.OrderException;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;
import com.wenjing.vo.BookOrderVO;

/**
 * 购物车模块业务层
 * 
 * @author Jared
 *
 * May 25, 2015
 */
@Service
public class ShoppingCartService {
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private ShoppingCartMapper shoppingCartMapper;
	@Autowired
	private ProductMapper productMapper;
	
	/**
	 * 获取购物车的产品数量
	 * 
	 * @param request
	 * @return
	 */
	@Transactional(readOnly = true)
	public int getShoppingCartNumber(HttpServletRequest request){
		String costNumber = (String)request.getSession().getAttribute("costnumber");
		Member member = Tools.getMember(request);
		String memberId = "";
		
		//当用户没有登录时
		if(member == null){
			String currentMemberId = WebUtils.getCookie(request, "memberId");
			//如果当前用户没有购买过产品
			if(currentMemberId == null || "".equals(currentMemberId)){
				return 0;
			}else{
				memberId = currentMemberId;
			}
		}else{
			memberId = member.getId();
		}
		return shoppingCartMapper.countNumberByUserId(memberId,costNumber);
	}
	
	/**
	 * 根据用户ID和销售中心Id来获取购物车的产品
	 * 
	 * @param memberId
	 * @return
	 */
	@Transactional(readOnly=true)
	public ShoppingCart getShoppingCartByMemberId(String memberId,String costNumber){
		return shoppingCartMapper.selectByMemberId(memberId,costNumber);
	}
	
	/**
	 * 更新购物车的用户ID
	 * 
	 * @param shoppingCartId
	 * @param memberId
	 */
	@Transactional
	public void updateShoppingCartMemberId(String shoppingCartId,String memberId){
		shoppingCartMapper.updateShoppingCartMemberId(shoppingCartId, memberId);
	}
	
	/**
	 * 将产品添加到购物车中
	 * 
	 * @param bookOrderVO
	 * @return
	 */
	@Transactional
	public int addToShoppingCart(BookOrderVO bookOrderVO,HttpServletRequest request,HttpServletResponse response){
		Member member = Tools.getMember(request);
		String memberId = "";
		String costNumber = (String)request.getSession().getAttribute("costnumber");
		
		//当用户没有登录时
		if(member == null){
			String currentMemberId = WebUtils.getCookie(request, "memberId");
			if(currentMemberId == null || "".equals(currentMemberId)){
				memberId = Tools.getUUID();
			}else{
				memberId = currentMemberId;
			}
			WebUtils.addCookie(request, response, "memberId", memberId);
		}else{
			memberId = member.getId();
		}
		
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setId(Tools.getUUID());
		shoppingCart.setProductId(bookOrderVO.getProductId()); //存入产品ID
		shoppingCart.setProductCode(bookOrderVO.getProductCode()); //存入产品CODE
		shoppingCart.setDepartureId(bookOrderVO.getDepartureId()); //存入出发地ID
		shoppingCart.setAirportPickUpId(bookOrderVO.getAirportPickUpId()); //存入接送机Id
		shoppingCart.setAirTicketPriceId(bookOrderVO.getAirTicketPriceId()); //存入机票ID
		shoppingCart.setTourDateId(bookOrderVO.getTourDateId()); //存入出发日期所在的tourdate记录的ID
		shoppingCart.setCostNumber(costNumber);
		Date departureDate = null;
		try {
			 //将页面传递过来的出发日期转为Date类型,如果转换出错则抛出异常
			departureDate = dateFormat.parse(bookOrderVO.getDepartureDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new OrderException("incorrect_date_format");
		}
		shoppingCart.setDepartureDate(departureDate); //存入出发日期
		shoppingCart.setTotalNumber(bookOrderVO.getAdultNum() + bookOrderVO.getChildrenNum()); //存入总人数
		shoppingCart.setAdultNumber(bookOrderVO.getAdultNum()); //存入大人人数
		shoppingCart.setChildrenNumber(bookOrderVO.getChildrenNum()); //存入小孩人数
//		shoppingCart.setRoomInfo(JSON.toJSONString(bookOrderVO.getRoomInfoList()));	//存入房间住客信息
		
		shoppingCart.setUserId(memberId); //存入用户ID
		shoppingCartMapper.insert(shoppingCart);
		return shoppingCartMapper.countNumberByUserId(memberId,costNumber);
	}
	
	/**
	 * 订购产品
	 * 
	 * @param shoppingCart
	 * @param request
	 * @param response
	 */
//	@Transactional
//	public ShoppingCart bookTour(ShoppingCart shoppingCart,HttpServletRequest request,HttpServletResponse response){
//		Member member = Tools.getMember(request);
//		String memberId = "";
//		
//		Product product = productMapper.findById(shoppingCart.getProductId());
//		//当用户没有登录时
//		if(member == null){
//			String currentMemberId = WebUtils.getCookie(request, "memberId");
//			if(currentMemberId == null || "".equals(currentMemberId)){
//				memberId = Tools.getUUID();
//			}else{
//				memberId = currentMemberId;
//			}
//			WebUtils.addCookie(request, response, "memberId", memberId);
//		}else{
//			memberId = member.getId();
//		}
//		String additionalProductJson = JSON.toJSONString(shoppingCart.getAdditionalProductList());
//		if("null".equals(additionalProductJson)){
//			additionalProductJson = "";
//		}
//		shoppingCart.setId(Tools.getUUID());
//		shoppingCart.setUserId(memberId); //存入用户ID
//		shoppingCart.setCostNumber(product.getCostnumber());
//		shoppingCart.setAdditionalProducts(additionalProductJson);
//		shoppingCartMapper.insert(shoppingCart);
//		shoppingCart.setProduct(product);
//		return shoppingCart;
//	}
	
	/**
	 * 在购物车中取消产品
	 */
	@Transactional
	public void removeProductFromCart(String shoppingCartId){
		shoppingCartMapper.deleteByPrimaryKey(shoppingCartId);
	}
	
	/**
	 * 清除过期购物车
	 */
	@Transactional
	public void evictExpired(){
		shoppingCartMapper.evictExpired(DateUtils.addSeconds(new Date(), -ShoppingCart.TIMEOUT));
	}
}
