package com.jt.web.intercept;

import javax.net.ssl.HandshakeCompletedListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.web.pojo.User;
import com.jt.web.thread.UserThreadLocal;

import redis.clients.jedis.JedisCluster;

public class UserInterceptor implements HandlerInterceptor{
	
	@Autowired
	private JedisCluster jedisCluster;
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * 在真正执行业务逻辑之前拦截
	 * 1.return false; 表示请求拦截,需要重定向到另外的页面中
	 *   return true;  放行用户请求资源
	 * 2.用户拦截器业务逻辑
	 * 	 1.获取Cookie信息
	 *   2.获取ticket值
	 *   3.从redis中获取缓存数据(json)
	 *   4.将userJSON转化为User对象
	 *   5.如果用户没有Cookie或者json那么需要用户重定向到登陆页面
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//1.获取Cookie信息
		Cookie[] cookies = request.getCookies();
		
		String token = null;
		
		for (Cookie cookie : cookies) {
			
			if("JT_TICKET".equals(cookie.getName())){
				token = cookie.getValue();
				break;
			}
		}
		
		//表示token数据不为null
		if(!StringUtils.isEmpty(token)){
			
			String userJSON = jedisCluster.get(token);
			
			if(!StringUtils.isEmpty(userJSON)){
				
				User user = objectMapper.readValue(userJSON,User.class);
				
				UserThreadLocal.setUser(user);
				//获取用户信息后需要传递给服务器程序????
				return true;//表示拦截放行
			}
			
		}
		
		//重定向到登陆页面
		response.sendRedirect("/user/login.html");
		return false;//进行拦截
	}

	//在请求完成后在返回给用户之前
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	//用户展现页面之前
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		//关闭ThreadLocal防止内存泄漏
		UserThreadLocal.remove();
	}

}
