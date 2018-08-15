package com.jt.sso.service;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.sso.mapper.UserMapper;
import com.jt.sso.pojo.User;

import redis.clients.jedis.JedisCluster;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private JedisCluster jedisCluster;
	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 1.根据type类型的值 转化为具体的字段
	 * 	 1.1 可选参数1 username、2 phone、3 email
	 * 2.根据用户提供的信息count求和 
	 *   如果数据>=1 或者 ==0 判断用户信息是否存在
	 */
	@Override
	public boolean findCheckUser(String param, Integer type) {
		
		String cloumn = null;
		
		switch (type) {
			case 1:
				cloumn = "username"; break;
			case 2:
				cloumn = "phone"; break;
			case 3:
				cloumn = "email"; break;
		}
		
		//获取数据库查询数据
		int count = userMapper.findCheckUser(param,cloumn);
		
		return count ==0 ? false : true;
	}

	@Override
	public void saveUser(User user) {
		//准备后台数据
		String md5pass = DigestUtils.md5Hex(user.getPassword());
		user.setPassword(md5pass);//添加密文
		user.setEmail(user.getPhone());//为了防止数据库报错
		user.setCreated(new Date());
		user.setUpdated(user.getCreated());
		userMapper.insert(user);
	}

	//查询用户
	@Override
	public String findUserByUP(String username, String password) {
		
		User userTemp = new User();
		userTemp.setUsername(username);
		userTemp.setPassword(DigestUtils.md5Hex(password));
		
		User user = userMapper.findUserByUP(userTemp);
		String token = null;
		
		if(user == null){
			//如果查询结果为null,则表示用户名和密码不正确.
			throw new RuntimeException();
		}
		
		//如果数据不为null应该生成秘钥,之后转化为JSON数据
		try {
			user.setPassword(null);//敏感数据不应该保存第三方
			String userJSON  = 
					objectMapper.writeValueAsString(user);
			String ps = "JT_TICKET_"+System.currentTimeMillis()+user.getUsername();
			token = DigestUtils.md5Hex(ps);
			
			//将数据保存到redis中
			//jedisCluster.set(token, userJSON);//用不超时
			jedisCluster.setex(token, 5*24*3600, userJSON);//定义超时时间
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return token;
	}
	
	
	
	
	
	
	
	
}
