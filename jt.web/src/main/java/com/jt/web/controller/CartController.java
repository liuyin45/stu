package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;
import com.jt.web.service.CartService;
import com.jt.web.thread.UserThreadLocal;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	//查询当前用户的全部购物信息
	@RequestMapping("/show")
	public String show(Model model){
		//动态获取用户Id
		Long userId = UserThreadLocal.getUser().getId();
		List<Cart> cartList = 
				cartService.findCartListByUserId(userId);
		model.addAttribute("cartList", cartList);
		return "cart";
	}
	
	//商品新增到购物车中
	@RequestMapping("/add/{itemId}")
	public String saveCart(@PathVariable Long itemId,Cart cart){
		Long userId = UserThreadLocal.getUser().getId();
		cart.setItemId(itemId);
		cart.setUserId(userId);
		cartService.saveCart(cart);
		return "redirect:/cart/show.html";
	}
	
	//修改商品数量
	//http://cart.jt.com/cart/update/num/{userId}/{itemId}/{num}
	@RequestMapping("/update/num/{itemId}/{num}")
	@ResponseBody
	public SysResult updateCart(
				@PathVariable Long itemId,
				@PathVariable Integer num){
	try {
				Long userId =  UserThreadLocal.getUser().getId();
				return cartService.updateItemCatNum(userId,itemId,num);
		} catch (Exception e) {
				e.printStackTrace();
				return SysResult.build(201, "商品修改失败");
		}
	}
	
	
	//cart/delete/1474391950.html
	@RequestMapping("/delete/{itemId}")
	public String deleteCart(@PathVariable Long itemId){
				
				Long userId =  UserThreadLocal.getUser().getId();
				//根据购物车ID删除购物车信息
				cartService.deleteCart(userId,itemId);
				//如果删除成功重定向到购物车页面
				return "redirect:/cart/show.html";
			}
	
	
	
	
	
	
	
	
	
}
