package com.jt.manage.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.RedisService;
import com.jt.common.vo.ItemCatData;
import com.jt.common.vo.ItemCatResult;
import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemCat;
import com.jt.manage.vo.ItemCatTree;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private ItemCatMapper itemCatMapper;
	
	//通过spring依赖注入jedis对象
	@Autowired
	private JedisCluster jedisCluster;
	//private Jedis jedis;
	//private RedisService redisService;
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	
	/**
	 * 1.根据父级Id查询商品分类列表
	 * 2.将分类列表数据封装为ItemCatTree
	 */
	@Override
	public List<ItemCatTree> findItemCatById(Long parentId) {
		//ItemCat itemCat = new ItemCat();
		//itemCat.setParentId(parentId);
		//1.查询数据 根据其中不为null的属性充当where条件
		//List<ItemCat> itemCats = itemCatMapper.select(itemCat);
		//通过缓存查询数据信息
		List<ItemCat> itemCats = findItemCatCache(parentId);
		//2.进行数据封装
		List<ItemCatTree> itemCatTreeList = new ArrayList<ItemCatTree>();
		for (ItemCat itemCatTemp : itemCats) {
			ItemCatTree itemCatTree = new ItemCatTree();
			itemCatTree.setId(itemCatTemp.getId());
			itemCatTree.setText(itemCatTemp.getName());
			//是父级则closed 如果不是则open
			String state = 
			itemCatTemp.getIsParent() 
			== true ? "closed" : "open";
			itemCatTree.setState(state);
			itemCatTreeList.add(itemCatTree);
		}
		return itemCatTreeList;
	}
	
	
	/**
	 * 通过parentId查询itemCats数据
	 * 1.先查询缓存
	 * @param parentId
	 * @return
	 */
	
	public List<ItemCat> findItemCatCache(Long parentId){
		List<ItemCat> itemCats = new ArrayList<ItemCat>();
		String key = "ITEM_CAT"+parentId;
		
		String jsonData = jedisCluster.get(key);
		
		try {
			if(StringUtils.isEmpty(jsonData)){
				//缓存中没有数据,则查询数据库
				ItemCat itemCat = new ItemCat();
				itemCat.setParentId(parentId);
				itemCats = itemCatMapper.select(itemCat);
				
				//将查询的结果先转化为JSON后保存redis中
				String itemCatJSON = 
							objectMapper.writeValueAsString(itemCats);
				jedisCluster.set(key,itemCatJSON);	
			}else{
				//表示缓存中有数据,将ItemCatListJSON转化List<ItemCat对象>
				//[{},{},{},{}]
				ItemCat[] arrayitemCats = 
				objectMapper.readValue(jsonData, ItemCat[].class);
				itemCats = Arrays.asList(arrayitemCats);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return itemCats;
	}


	/**
	 * 步骤:
	 * 	1.创建需要返回的对象ItemCatResult
	 * 	2.主备一个合理的数据结构,封装子父级关系
	 * 	  Map<parentId,List<ItemCat>>
	 *  3.准备一级商品分类菜单
	 *  4.封装二级商品分类菜单
	 *  5.封装三级商品分类菜单
	 */
	@Override
	public ItemCatResult findItemCatAll() {
		//1.定义封装对象
		ItemCatResult itemCatResult = new ItemCatResult();
		
		ItemCat itemCatTemp = new ItemCat();
		itemCatTemp.setStatus(1);//表示状态启动
		//2.查询全部的商品分类信息
		List<ItemCat> itemCatDBList = 
				itemCatMapper.select(itemCatTemp);
		
		//3.封装子父级关系
		Map<Long,List<ItemCat>> map = new HashMap<Long,List<ItemCat>>();
		
		for (ItemCat itemCat : itemCatDBList) {
			if(map.containsKey(itemCat.getParentId())){
				//将该数据追加到List集合中
				map.get(itemCat.getParentId()).add(itemCat);
			}else{
				//代表第一个添加这个父级Id
				List<ItemCat> tempCatList = new ArrayList<ItemCat>();
				tempCatList.add(itemCat);
				map.put(itemCat.getParentId(), tempCatList);
			}
		}
		
		//4.准备一级商品分类菜单
		List<ItemCatData> itemCatList1 = new ArrayList<ItemCatData>();
		
		//4.1编辑一级商品分类菜单封装一级商品分类
		for (ItemCat itemCat1 : map.get(0L)) {
			ItemCatData itemCatData1 = new ItemCatData();
			itemCatData1.setUrl("/products/"+itemCat1.getId()+".html");
			itemCatData1.setName("<a href='"+itemCatData1.getUrl()+"'>"+itemCat1.getName()+"</a>");
			
			//封装2级商品分类菜单
			List<ItemCatData> itemCatList2 = new ArrayList<ItemCatData>();
			//通过一级商品分类,查询二级商品分类信息
			for (ItemCat itemCat2 : map.get(itemCat1.getId())) {
				ItemCatData itemCatData2 = new ItemCatData();
				itemCatData2.setUrl("/products/"+itemCat2.getId());
				itemCatData2.setName(itemCat2.getName());
				
				//封装3级商品分类菜单
				List<String> itemCatList3 = new ArrayList<String>();
				
				for (ItemCat itemCat3 : map.get(itemCat2.getId())) {
					
					itemCatList3.add("/products/"+itemCat3.getId()+"|"+itemCat3.getName());
				}
				
				itemCatData2.setItems(itemCatList3);
				itemCatList2.add(itemCatData2);	
				
			}
			itemCatData1.setItems(itemCatList2);
			//控制集合的长度
			if(itemCatList1.size()>13){
				break;
			}
			itemCatList1.add(itemCatData1);
		}
		
		
		itemCatResult.setItemCats(itemCatList1);
		return itemCatResult;
	}


	/**
	 * 1.用户查询时先查询缓存
	 * 2.如果缓存中有数据则将缓存数据转化为需要的对象
	 * 3.如果缓存中没有数据,则查询数据库,
	 *   之后将数据转化为JSON数据
	 *   保存到redis缓存中.方便下次使用
	 */
	@Override
	public ItemCatResult findItemCatCache() {
		String key = "ITEM_CAT_ALL";
		String jsonData = jedisCluster.get(key);
		ItemCatResult itemCatResult = null;
		try {
			if(StringUtils.isEmpty(jsonData)){
				//表示缓存数据为空
				itemCatResult = findItemCatAll();
				String jsonResult = 
				objectMapper.writeValueAsString(itemCatResult);
				jedisCluster.set(key, jsonResult);
				System.out.println("第一次查询");
			}else{
				//表示缓存有数据
				itemCatResult = 
				objectMapper.readValue(jsonData, ItemCatResult.class);
				System.out.println("第二次查询");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return itemCatResult;
	}
	
	
	
	
	
	
	
	
	
}
