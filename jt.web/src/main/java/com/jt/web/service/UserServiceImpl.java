package com.jt.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private HttpClientService httpClient;
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	
	@Override
	public void saveUser(User user) {
		String url = "http://sso.jt.com/user/register";
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("username",user.getUsername());
		params.put("password",user.getPassword());
		params.put("phone",user.getPhone());
		params.put("email",user.getEmail());
		
		String jsonData = httpClient.doPost(url, params);
		
		//判断返回数据是否正确,如果返回数据不正确则抛出异常
		try {
			SysResult  sysResult = objectMapper.readValue(jsonData, SysResult.class);
			if(sysResult.getStatus() != 200){
	
				throw new RuntimeException();
			}
		} catch (Exception e) {
			e.getMessage();
			throw new RuntimeException();
		}
	}


	@Override
	public String findUserByUP(String username, String password) {
		//1.定义url
		String url = "http://sso.jt.com/user/login";
		
		//2.封装数据
		Map<String,String> params = new HashMap<String,String>();
		params.put("username", username);
		params.put("password", password);
		
		//3.发起请求
		String jsonData = httpClient.doPost(url, params);
		
		//4.将JSON转化为对象
		try {
			SysResult sysResult = objectMapper.readValue(jsonData, SysResult.class);
			
			if(sysResult.getStatus() == 200){

				String token = (String) sysResult.getData();
				return token;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	

}
