package com.jt.manage.factory;

import java.util.HashSet;

import java.util.Properties;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
	
//获取Id    jedisClusterFactory 
			
@Component     
public class JedisClusterFactory implements BeanNameAware,BeanFactoryAware,ApplicationContextAware{

	@Override
	public void setBeanName(String name) {
		
		System.out.println("获取当前 bean的Id"+name);
	}
	/**
	 * 1.在Spring中默认的Id是首字母小写.
	 * 2.应该看类名的第二个字符,如果字符大写,则Id名称就是类名
	 * 	 如果第二个字符小写,则id首字母小写.
	 */

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		
	}
				
				
				
				
				
				
	
}
