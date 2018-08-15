package com.jt.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.cart.pojo.Cart;
import com.jt.cart.service.CartService;
import com.jt.common.vo.SysResult;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	private static final ObjectMapper objectMapper = new ObjectMapper();

	@RequestMapping("/query/{userId}")
	@ResponseBody
	public SysResult findCartByUserId(@PathVariable Long userId) {
		try {
			List<Cart> cartList = cartService.findCartByUserId(userId);
			return SysResult.oK(cartList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "获取购物车数据失败");
	}

	@RequestMapping("/save")
	@ResponseBody
	public SysResult saveCart(Cart cart) {
		try {
			cartService.saveCart(cart);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "新增购物车失败");
	}

	// url:"http://cart.jt.com/cart/update/num/"+userId+"/"+itemId+"/"+num;
	@RequestMapping("/update/num/{userId}/{itemId}/{num}")
	@ResponseBody
	public SysResult updateCartNum(Long userId, Long itemId, Integer num) {

		try {
			return cartService.updateCartNum(userId, itemId, num);
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "数据修改失败");
		}
	}

	// http://cart.jt.com/cart/delete/{userId}/{itemId}
	@RequestMapping("/delete/{userId}/{itemId}")
	@ResponseBody
	public SysResult deleteCart(@PathVariable Long userId, @PathVariable Long itemId) {

		try {
			cartService.deleteCart(userId, itemId);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "删除失败");
		}
	}

}
