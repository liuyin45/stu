package com.jt.cart.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.cart.mapper.CartMapper;
import com.jt.cart.pojo.Cart;
import com.jt.common.vo.SysResult;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartMapper cartMapper;

	@Override
	public List<Cart> findCartByUserId(Long userId) {
		Cart cart = new Cart();
		cart.setUserId(userId);
		return cartMapper.select(cart);
	}

	/**
	 * 1.根据userid和ItemId查询购物车数据 如果购物车中有数据,则应该做购物车的更新商品数量 如果购物车中没有数据,则做购物车入库操作
	 */
	@Override
	public void saveCart(Cart cart) {
		// 首先根据userId和CartId判断购物车信息是否存在,
		// 如果存在则数量求和.如果不存则在进行入库操作
		Cart cartDB = cartMapper.findCartByUI(cart);
		Date date = new Date();
		if (cartDB == null) {
			cart.setCreated(date);
			cart.setUpdated(date);
			cartMapper.insert(cart);
		} else {
			int num = cartDB.getNum() + cart.getNum();
			cartDB.setNum(num);
			cartDB.setUpdated(date);
			cartMapper.updateByPrimaryKeySelective(cartDB);
		}

	}

	/**
	 * 
	 */
	@Override
	public SysResult updateCartNum(Long userId, Long itemId, Integer num) {

		try {
			cartMapper.updateCartByUserIdAndItemId(userId, itemId, num);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "商品修改失败");
		}

	}

	//根据用户id和商品id删除购物车
	@Override
	public void deleteCart(Long userId, Long itemId) {
		Cart cart = new Cart();
		cart.setUserId(userId);
		cart.setItemId(itemId);
		cartMapper.delete(cart);

	}

}
