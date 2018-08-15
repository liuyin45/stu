package com.jt.test.rabbitmq;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.rabbitmq.client.ShutdownSignalException;

public class TestRabbitMQ {

	/**
	 * 如何连接rabbitMQ
	 * 1.需要链接的Connection
	 * 2.利用工厂模式获取Connection
	 * 3.需要为工厂模式设定链接参数
	 * host/端口/用户名/密码/虚拟机
	 */
	private Connection connection;
	
	private String queueName ="simple";
	
	
	
	@Before
	public void init() throws IOException{
		ConnectionFactory factory = new ConnectionFactory(); 
		factory.setHost("192.168.139.136");
		factory.setPort(5672);
		factory.setUsername("jtadmin");
		factory.setPassword("jtadmin");
		factory.setVirtualHost("/jt");
		connection=factory.newConnection();
				
	}
	
	/**
	 * 定义消息提供者
	 * 步骤:
	 * 1.定义连接通道channel
	 * 2.定义队列
	 * 3.定义发送消息的内容
	 * 4.将消息发送到队列中
	 * @throws IOException 
	 */
	@Test
	public void provider() throws IOException{
		
		Channel channel = connection.createChannel();
		/**
		 * 参数介绍:
		 * queue:队列名称
		 * durable: 表示队列是否持久化
		 * exclusive: 是否由队列的提供者独有,若参数为true,那么消费者将不能消费
		 * autoDelete: 当消息队列中没有消息时,是否自动删除
		 * arguments: 传递的参数,如果没有一般为null
		 */
		channel.queueDeclare(queueName, false, false, false, null);
		
		String msg ="我是一个不一般的模式";
		
		//表示发送消息
		/**
		 * 参数说明:
		 * exchange: 交换机  作用:可以将消息发送到不同的队列中
		 * routingKey: 标识消息发往哪个队列
		 * props:消息队列中的配置文件properties 一般为null
		 * body: 代表消息的二进制文件
		 */
		channel.basicPublish("",queueName,null, msg.getBytes());
		
	    channel.close();
	    connection.close();
	    System.out.println("消息发送成功");
	}
	
	/**
	 * 1.创建通道
	 * 2.创建队列
	 * 3.创建消费者对象
	 * 4.将消费者与队列进行绑定
	 * 5.从队列中获取消息
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws ConsumerCancelledException 
	 * @throws ShutdownSignalException 
	 */
	@Test
	public void consumer() throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException{
		
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(queueName, false, false, false, null);
		
		QueueingConsumer consumer = new QueueingConsumer(channel);
		
		//autoAck 自动应答
		//将消息与对象进行绑定
		channel.basicConsume(queueName, true, consumer);
		
		while (true) {
			
              Delivery delivery = consumer.nextDelivery();
              System.out.println("获取消息:"+new String(delivery.getBody()));
			
		}
		
	}
	
	
	
	
	
}
