package com.jt.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.dubbo.pojo.Cart;
import com.jt.dubbo.service.DubboCartService;
import com.jt.web.thread.UserThreadLocal;


@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private DubboCartService cartService; 
	
	@RequestMapping("/show")
	public String showCart(Model model){
		Long userId = UserThreadLocal.getUser().getId();
		List<Cart> cartList = cartService.findCartById(userId);
		model.addAttribute("cartList", cartList);
		return "cart";
	}
	
	@RequestMapping("add/{itemId}")
	public String saveCart(@PathVariable Long itemId,Cart cart){
		//获取用户id
		Long userId = UserThreadLocal.getUser().getId();
		cart.setItemId(itemId);
		cart.setUserId(userId);
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
					Cart cart = new Cart();
					cart.setUpdated(new Date());
					cart.setItemId(itemId);
					cart.setNum(num);
					cart.setUserId(userId);
					cartService.updateItemCatNum(cart);
					return SysResult.oK();
			} catch (Exception e) {
					e.printStackTrace();
					
			}
		return SysResult.build(201, "商品修改失败");
		}
		
		
		//cart/delete/1474391950.html
		@RequestMapping("/delete/{itemId}")
		public String deleteCart(@PathVariable Long itemId){
					
			Long userId =  UserThreadLocal.getUser().getId();
			Cart cart = new Cart();
			cart.setItemId(itemId);
			cart.setUserId(userId);
			//根据购物车ID删除购物车信息
			cartService.deleteCart(cart);
			//如果删除成功重定向到购物车页面
			return "redirect:/cart/show.html";
			}
		
}
