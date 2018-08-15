package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.dubbo.pojo.Cart;
import com.jt.dubbo.pojo.Order;
import com.jt.dubbo.service.DubboCartService;
import com.jt.dubbo.service.DubboOrderService;
import com.jt.web.thread.UserThreadLocal;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private DubboCartService cartService;
	
	@Autowired
	private DubboOrderService orderService;
	
	@RequestMapping("/create")
	public String create(Model model){
		//查询当前登录用户的购物信息
		Long userId = UserThreadLocal.getUser().getId();
		List<Cart> carts = cartService.findCartById(userId);	
		model.addAttribute("carts", carts);
		return "order-cart";
	}
	
	@RequestMapping("/submit")
	@ResponseBody
	public SysResult saveOrder(Order order){
		try {
			//获取用户Id
			Long userId = UserThreadLocal.getUser().getId();
			order.setUserId(userId);
			
			String orderId = orderService.saveOrder(order);
			if(!StringUtils.isEmpty(orderId)){
				return SysResult.oK(orderId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201,"订单入库失败");
	}
	
	
	//根据orderId实现数据查询
	//http://www.jt.com/order/success.html?id=171531557991771
	@RequestMapping("/success")
	public String success(@RequestParam("id") String orderId,Model model){
		/**
		 * 根据orderId的值查询后台的数据库,订单的全部消息
		 * 要求查询三张表 
		 *  1.利用通用Mapper实现数据查询(中)
		 *  2.自己编辑Sql语句实现数据封装(难)
		 */
		Order order = orderService.findOrderById(orderId);
		model.addAttribute("order", order);
		
		return "success";
	}
	
	
	
	
	
	
}
;