package com.jt.web.service;

import java.util.List;

import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;
import com.jt.web.pojo.OrderItem;

public interface CartService {

	List<Cart> findCartListByUserId(Long userId);

	void saveCart(Cart cart);

	SysResult updateItemCatNum(Long userId, Long itemId, Integer num);

	void deleteCart(Long userId, Long itemId);
	
	//删除订单商品
	void deleteCart(Long userId, List<OrderItem> orderItems);

}
