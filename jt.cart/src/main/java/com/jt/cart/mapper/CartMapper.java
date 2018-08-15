package com.jt.cart.mapper;

import org.apache.ibatis.annotations.Param;

import com.jt.cart.pojo.Cart;
import com.jt.common.mapper.SysMapper;

public interface CartMapper extends SysMapper<Cart>{
	
	//根据UserId和ItemId查询购物车数据
	Cart findCartByUI(Cart cart);

	void updateCartByUserIdAndItemId(
			@Param("userId") Long userId,
			@Param("itemId")Long itemId, 
			@Param("num")Integer num);

	
	
	
}
