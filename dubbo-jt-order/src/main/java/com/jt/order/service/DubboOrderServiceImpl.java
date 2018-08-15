package com.jt.order.service;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.jt.dubbo.pojo.Order;
import com.jt.dubbo.service.DubboOrderService;
import com.jt.order.mapper.OrderMapper;


public class DubboOrderServiceImpl implements DubboOrderService{

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private OrderMapper orderMapper;

	
	
	//保存订单信息时,需要关联入库 同时操作三张表
	@Override
	public String saveOrder(Order order) {
		
		String orderId = order.getUserId() + "" + System.currentTimeMillis();
		
		order.setOrderId(orderId);
		
		String key = "save.order";
		
		//将order保存到消息队列中,加上路由key,表示路由模式
		rabbitTemplate.convertAndSend(key,order);
		System.out.println("消息队列发送成功");
	
		
		return orderId;
	}

	@Override
	public Order findOrderById(String orderId) {
		/**
		 * 中等难度
		 
        Order order = orderMapper.selectByPrimaryKey(orderId);
        System.out.println(order);
        
		OrderItem orderItem = new OrderItem();
		orderItem.setOrderId(orderId);
		System.out.println(orderId);
		
		List<OrderItem> orderItemList = orderItemMapper.select(orderItem);
		order.setOrderItems(orderItemList);
		System.out.println("orderItemList:"+orderItemList);
		
		OrderShipping orderShipping = orderShippingMapper.selectByPrimaryKey(orderId);
		order.setOrderShipping(orderShipping);
		
		
		return order;
		*/
		
		return orderMapper.findOrderById(orderId);
	}
	

}
