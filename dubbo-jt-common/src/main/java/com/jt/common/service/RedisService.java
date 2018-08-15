package com.jt.common.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

@Service
public class RedisService {


	//有的工程需要，有的工程不需要。设置required=false，有就注入，没有就不注入。
	//实现分片配置
//    @Autowired
//    private ShardedJedisPool jedisPool;

	 /**
	  * 实现哨兵的配置
	  * required=false 表示服务启动时暂时不检测依赖是否注入
	  * 当程序调用时检测注入
	  */
	 @Autowired(required=false)
	 private JedisSentinelPool jedisSentinelPool;

    /**
     * 保存数据到redis中
     * 
     * @param key
     * @param value
     * @return
     */
    public void set(String key, String value) {
       //ShardedJedis jedis = jedisPool.getResource();
       Jedis jedis = jedisSentinelPool.getResource();
       jedis.set(key, value);
       
       
       //将连接还给池
       jedisSentinelPool.returnResource(jedis);
            
     
    }


    public String get(String key) {
    	
    	//ShardedJedis jedis = jedisPool.getResource();
    	Jedis jedis = jedisSentinelPool.getResource();
    	
    	String result = jedis.get(key);
    	//将连接还给池
    	jedisSentinelPool.returnResource(jedis);
		return result;

  
    }
    
    //插入缓存定义超时时间
    public void set(String key,String value,Long expireTime){
    	
    	Jedis jedis = jedisSentinelPool.getResource();
    	
    	Integer seconds = (int)TimeUnit.MILLISECONDS.toSeconds(expireTime);
    	jedis.setex(key, seconds, value);
    	
    }

  
  
}
