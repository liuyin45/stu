package com.jt.dubbo.service;


import java.util.List;

import com.jt.dubbo.pojo.Cart;

public interface DubboCartService {

	List<Cart> findCartById(Long userId);
	
	void saveCart(Cart cart);

	void updateItemCatNum(Cart cart);

	void deleteCart(Cart cart);


}
