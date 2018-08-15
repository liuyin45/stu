package com.jt.testRedis;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class TestRedis {
	
	//redis的入门案例
	@Test
	public void test01(){
		//1.通过ip:端口链接redis 获取jedis对象
		Jedis jedis = new Jedis("192.168.139.140", 7000);
		
		//2.通过jedis对象实现get/set操作
		String result = jedis.set("1802", "1802班今天终于不热了");
		System.out.println("结果:"+result+" 获取数据:"+jedis.get("1802"));
	}
	
	//通过jedis客户端实现redis分片操作
	//注意:虽然redis分片以后是多台节点(node),但是在操作时看成一台
	@Test
	public void testShard(){
		
		//定义链接池的大小
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(1000);
		poolConfig.setMaxIdle(10);//最大空闲数量
		poolConfig.setTestOnBorrow(true);//是否检测链接有效
	
		//定义redis分片详细信息
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		
		JedisShardInfo info1 = new JedisShardInfo("192.168.139.140", 7000);
		JedisShardInfo info2 = new JedisShardInfo("192.168.139.140", 7003);
		JedisShardInfo info3 = new JedisShardInfo("192.168.139.140", 7004);
		shards.add(info1);
		shards.add(info2);
		shards.add(info3);
		
		//定义操作redis分片的API
		ShardedJedisPool pool = 
				new ShardedJedisPool(poolConfig, shards);
		
		//实现redis分片操作
		ShardedJedis shardedJedis = pool.getResource();
		
		shardedJedis.set("tom", "redis分片");
		System.out.println("获取数据:"+shardedJedis.get("tom"));
	}
	
	
	//哨兵的测试案例
	@Test
	public void test03(){
		String masterName = "mymaster";//获取主机地址的变量名称
		
		Set<String> sentinels = new HashSet<String>();
		//添加哨兵IP:端口  new HostAndPort(host, port).toString()
		sentinels.add("192.168.126.166:26379");
		sentinels.add("192.168.126.166:26380");
		sentinels.add("192.168.126.166:26381");
		
		//1.定义哨兵的链接池
		JedisSentinelPool pool = 
				new JedisSentinelPool(masterName, sentinels);
		//2.获取链接
		Jedis jedis = pool.getResource();
		
		//3.为redis赋值
		jedis.set("sentinel", "哨兵测试完成");
		
		System.out.println("取值:"+jedis.get("sentinel"));
	}
	
	
	@Test
	public void test04(){
		
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		nodes.add(new HostAndPort("192.168.126.166", 7000));
		nodes.add(new HostAndPort("192.168.126.166", 7001));
		nodes.add(new HostAndPort("192.168.126.166", 7002));
		nodes.add(new HostAndPort("192.168.126.166", 7003));
		nodes.add(new HostAndPort("192.168.126.166", 7004));
		nodes.add(new HostAndPort("192.168.126.166", 7005));
		nodes.add(new HostAndPort("192.168.126.166", 7006));
		nodes.add(new HostAndPort("192.168.126.166", 7007));
		nodes.add(new HostAndPort("192.168.126.166", 7008));
		JedisCluster jedisCluster = new JedisCluster(nodes);
		
		jedisCluster.set("name","tomcat猫");
		String result = jedisCluster.get("name");
		System.out.println("获取集群的数据:"+result);
	}
	
	//测试工厂模式
	@Test
	public void test05(){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("/spring/factory.xml");
		
		Calendar calendar1 = 
				(Calendar) context.getBean("calendar1");
		System.out.println("时间:"+calendar1.getTime());
		Calendar calendar2 = 
				(Calendar) context.getBean("calendar2");
		System.out.println("时间:"+calendar2.getTime());
		
		Calendar calendar3 = 
				(Calendar) context.getBean("calendar3");
		System.out.println("时间:"+calendar3.getTime());
	}
	
	
	
	
	
	
	
	
	
	
}
