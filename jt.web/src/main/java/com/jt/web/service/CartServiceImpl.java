package com.jt.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;
import com.jt.web.pojo.OrderItem;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private HttpClientService httpClient;
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public List<Cart> findCartListByUserId(Long userId) {
		String url = "http://cart.jt.com/cart/query/"+userId;
		
		String jsonData = httpClient.doGet(url);
		List<Cart> cartList = new ArrayList<Cart>();
		try {
			SysResult sysResult = 
			objectMapper.readValue(jsonData, SysResult.class);
			
			cartList = (List<Cart>) sysResult.getData();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return cartList;
	}

	@Override
	public void saveCart(Cart cart) {
		String url = "http://cart.jt.com/cart/save";
		Map<String,String> params = new HashMap<String,String>();
		params.put("userId", cart.getUserId()+"");
		params.put("itemId", cart.getItemId()+"");
		params.put("itemTitle",cart.getItemTitle());
		params.put("itemImage", cart.getItemImage());
		params.put("itemPrice", cart.getItemPrice()+"");
		params.put("num", cart.getNum()+"");
		httpClient.doPost(url,params);
	}

	@Override
	public SysResult updateItemCatNum(Long userId, Long itemId, Integer num) {
		//定义url地址
				String url = "http://cart.jt.com/cart/update/num/"+userId+"/"+itemId+"/"+num;
				Map<String,String> params = new HashMap<String,String>();
				params.put("userId", userId+"");
				params.put("itemId", itemId+"");
				params.put("num", num+"");
				
				
		      try {
				
				String jsonData = httpClient.doGet(url, params);
				
				//验证远程执行是否正确
				 SysResult sysReslt = objectMapper.readValue(jsonData, SysResult.class);
				
			
				if(sysReslt.getStatus()==200){
					
					//表示远程调用成功
					return SysResult.oK();
				}else{
					return SysResult.build(201, "数据修改失败");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				return SysResult.build(201, "数据修改失败");
			}
	
		
				
//				try {
//					String jsonData = httpClient.doGet(url, params);
//					
//					//验证远程执行是否正确
//					JsonNode jsonNode = objectMapper.readTree(jsonData);
//					
//					//获取返回值类型是否为200
//					String status = jsonNode.get("status").asText();
//					
//					if("200".equals(status)){
//						//表示远程调用成功
//						return SysResult.oK();
//					}else{
//						return SysResult.build(201, "数据修改失败");
//					}
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//					return SysResult.build(201, "数据修改失败");
//				}
				
	}

	@Override
	public void deleteCart(Long userId, Long itemId) {
		
		String url = "http://cart.jt.com/cart/delete/"+userId+"/"+itemId;
		try {
			String jsonData = httpClient.doGet(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	


	@Override
	public void deleteCart(Long userId, List<OrderItem> orderItems) {
		for (OrderItem orderItem : orderItems) {
			
			String itemId = orderItem.getItemId();
			deleteCart(userId,Long.parseLong(itemId));
		}
	}	
	
	
	
	
	
}
