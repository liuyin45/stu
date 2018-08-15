package com.jt.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jt.dubbo.pojo.Item;
import com.jt.dubbo.service.DubboSearchService;

/**
 * 全文检索
 * 接受关键字，返回关键字相关商品列表
 * query
 * itemList
 * @author soft01
 *
 */
@Controller
public class SerchController {

	
	@Autowired
	private DubboSearchService dubboSearchService;
	
	@RequestMapping("/search")
	public String searchItem(
			@RequestParam("q") String keyWord,Model model) throws Exception{
		//处理乱码
		keyWord = new String(keyWord.getBytes("ISO-8859-1"),"utf-8");
		List<Item> itemList =  dubboSearchService.findItemsByKeyWord(keyWord);
		
		model.addAttribute("query", keyWord);
		model.addAttribute("itemList", itemList);
		return "search";
		
	}
	
	
}
