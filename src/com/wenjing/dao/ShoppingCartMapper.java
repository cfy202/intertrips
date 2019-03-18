package com.wenjing.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.ShoppingCart;

/**
 * 购物车的mapper
 * 
 * @author Jared
 *
 * Jun 12, 2015
 */
public interface ShoppingCartMapper {

	ShoppingCart selectByPrimaryKey(String id);
	
	ShoppingCart selectByMemberId(@Param("memberId")String memberId,@Param("costNumber")String costNumber);
	
    int deleteByPrimaryKey(String id);

    int insertSelective(ShoppingCart shoppingCart);
	
    int insert(ShoppingCart shoppingCart);
 
    int updateByPrimaryKeySelective(ShoppingCart shoppingCart);
    
    int updateByPrimaryKey(ShoppingCart shoppingCart);

    int updateShoppingCartMemberId(@Param("shoppingCartId")String shoppingCartId, @Param("memberId")String memberId);
    
    List<ShoppingCart> findAll();
    
    List<ShoppingCart> findByUserIdAndCostNumber(@Param("userId")String userId,@Param("costNumber")String costNumber);
    
    //根据用户ID和销售中心ID查询购物车中的条数
    int countNumberByUserId(@Param("userId")String userId,@Param("costNumber")String costNumber);
    
    //清楚购物车中过期的记录
    int evictExpired(Date expiredDate);
    
    //根据memberId删除购物车记录
    int deleteByMemberId(String memberId);
}
