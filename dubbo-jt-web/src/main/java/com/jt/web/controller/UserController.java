package com.jt.web.controller;

import java.nio.file.attribute.UserPrincipalLookupService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;
import com.jt.web.service.UserService;

import redis.clients.jedis.JedisCluster;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JedisCluster jedisCluster;
	
	@RequestMapping("/{module}")
	public String module(@PathVariable String module){
		
		return module;
	}
	
	@RequestMapping("/doRegister")
	@ResponseBody
	public SysResult saveUser(User user){
		try {
			userService.saveUser(user);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201,"用户新增失败");
	}
	
	//使用用户登陆操作
	@RequestMapping("/doLogin")
	@ResponseBody
	public SysResult findUserByUP(String username,String password,HttpServletResponse response){
		try {
			
			String token = userService.findUserByUP(username,password);
			
			//保证数据不能为null
			if(StringUtils.isEmpty(token)){
				
				return SysResult.build(201, "用户登陆失败");
			}
			
			//表示数据不为null,需要将数据保存到Cookie中.
			Cookie cookie = new Cookie("JT_TICKET", token);
			//设定cookie声明周期   0 表示Cookie立即销毁  -1 表示会话关闭后销毁  大于0 存活声明周期
			cookie.setMaxAge(5 * 24 * 3600);  //单位是秒
			cookie.setPath("/"); //表示Cookie的所属权限.一般都为/
			response.addCookie(cookie);
			
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SysResult.build(201,"用户登陆失败");
	}
	
	/**
	 * 1.先获取Cookie信息之后删除Cookie
	 * 2.删除redis缓存数据
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response){
		//获取Cookie
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			
			if("JT_TICKET".equals(cookie.getName())){
				String token = cookie.getValue();
				jedisCluster.del(token); //删除缓存数据
				Cookie cookie2 = new Cookie("JT_TICKET", "");
				cookie2.setMaxAge(0);
				cookie2.setPath("/");
				response.addCookie(cookie2);
				//删除原有的Cookie
				break;
			}
		}
		
		//重定向到商城首页
		return "redirect:/index.html";
	}
	
	
	
	
	
}
