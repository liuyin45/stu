package com.jt.cart.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jt.cart.mapper.CartMapper;
import com.jt.dubbo.pojo.Cart;
import com.jt.dubbo.service.DubboCartService;

//在配置文件中已经配置了bean
public class DubboCartServiceImpl implements DubboCartService {

	
	
	
	@Autowired
	private CartMapper cartMapper;

	@Override
	public List<Cart> findCartById(Long userId) {
		Cart cart = new Cart(); 
		cart.setUserId(userId);
		return cartMapper.select(cart);
	}

	/**
	 * 1.查询数据库
	 * 2.判断 如果数据已存在,更新商品数量
	 *  如果不存在,新增购物车数据
	 */
	@Override
	public void saveCart(Cart cart) {
		Cart cartDB = cartMapper.findCartByUI(cart);
		
		if(cartDB==null){
			cart.setCreated(new Date());
			cart.setUpdated(cart.getCreated());
			cartMapper.insert(cart);
		}else{
			int num = cartDB.getNum() + cart.getNum();
			cartDB.setNum(num);
			cartDB.setUpdated(new Date());
			cartMapper.selectByPrimaryKey(cartDB);
		}
		
	}
	

	@Override
	public void updateItemCatNum(Cart cart) {
		
	    cartMapper.updateByPrimaryKeySelective(cart);
	    
	}

	@Override
	public void deleteCart(Cart cart) {
		
		cartMapper.delete(cart);
		
	}

	

	
	

}
