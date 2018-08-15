package com.jt.manage.controller.web;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.manage.pojo.User;

@Controller
public class JSONPController {
	
	/*@RequestMapping("/web/testJSONP")
	@ResponseBody*/
	public String getJSONP(String callback){
		//编辑json数据
		/**
		 * 下列的格式是JSON的标准写法.建议以后这样写
		 * 规则:key和数字可以不添加""号
		 */
		String json = "{\"id\":\"100\",\"name\":\"tom\"}";
		
		return callback+"("+ json +")";
	}
	
	/**
	 * JSONP的API调用
	 * 1.返回特定的JSONP对象
	 * 2.接收callback参数
	 * 3.为对象赋值
	 * 4.直接返回特定的对象
	 */
	@RequestMapping("/web/testJSONP")
	@ResponseBody
	public MappingJacksonValue jsonp(String callback){
		User user = new User();
		user.setId(1000);
		user.setName("杯子");
		MappingJacksonValue jacksonValue = 
				new MappingJacksonValue(user);
		jacksonValue.setJsonpFunction(callback);
		return jacksonValue;
	}
}
