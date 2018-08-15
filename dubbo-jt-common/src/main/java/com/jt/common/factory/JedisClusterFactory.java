package com.jt.common.factory;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.io.Resource;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class JedisClusterFactory implements FactoryBean<JedisCluster>{



	private JedisPoolConfig poolConfig;
	
	private String redisNodePrefix; //配置文件前缀
	
	private Resource propertySource;//导入外部配置文件
	
	@Override
	public JedisCluster getObject() throws Exception {

		//获取
		Set<HostAndPort> nodes = getNodes();
		
        JedisCluster jedisCluster =
        		new JedisCluster(nodes, poolConfig);
		return jedisCluster;
	}

	public Set<HostAndPort> getNodes(){
		
		
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		
		//1.创建property对象,利用properties对象导入外部property文件
		Properties properties = new Properties();
		try{
		properties.load(propertySource.getInputStream());
        for (Object key :properties.keySet()) {
			 String rediesKey = (String)key;
			 if(!rediesKey.startsWith(redisNodePrefix)){
				 continue;
			 }
			 String redisNode = properties.getProperty(rediesKey);
			 String [] node = redisNode.split(":");
			 HostAndPort hostAndPort = new HostAndPort(node[0], Integer.parseInt(node[1]));
			 nodes.add(hostAndPort);
		}
		}catch(Exception e){
			e.printStackTrace();			
		}
		return nodes;

	}
	
	@Override
	public Class<?> getObjectType() {
		
		return JedisCluster.class;
	}

	@Override
	public boolean isSingleton() {
		
		return true;
	}

	
	public JedisPoolConfig getPoolConfig() {
		return poolConfig;
	}

	public void setPoolConfig(JedisPoolConfig poolConfig) {
		this.poolConfig = poolConfig;
	}

	public String getRedisNodePrefix() {
		return redisNodePrefix;
	}

	public void setRedisNodePrefix(String redisNodePrefix) {
		this.redisNodePrefix = redisNodePrefix;
	}

	public Resource getPropertySource() {
		return propertySource;
	}

	public void setPropertySource(Resource propertySource) {
		this.propertySource = propertySource;
	}
}
