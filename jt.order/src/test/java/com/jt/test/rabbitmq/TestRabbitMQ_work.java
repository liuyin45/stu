package com.jt.test.rabbitmq;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;
import com.rabbitmq.client.QueueingConsumer.Delivery;

public class TestRabbitMQ_work {

	
    private Connection connection;
	
	private String queueName ="work";
	
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
		
		String msg ="我是一个正在工作的模式";
		
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
	 * 第一个消费者
     */
	@Test
	public void consumerA() throws Exception{
		
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(queueName, false, false, false, null);
		
		//设定每个消费者每次消费个数,如果消息长时间没有接收到ACK消息,则消费失败,
		//如果队列连续3次没有接收到ACK返回消息,则不允许该消费者继续消费
		channel.basicQos(1);
		
		
		QueueingConsumer consumer = new QueueingConsumer(channel);
		
		//autoAck 自动应答
		//false 表示手动返回
		//将消息与对象进行绑定
		channel.basicConsume(queueName, false, consumer);
		System.out.println("消费者A启动");
		while (true) {
			
              Delivery delivery = consumer.nextDelivery();
              System.out.println("获取消息:"+new String(delivery.getBody()));
			  /**
			   * deliveryTag:返回数据的下标
			   * multiple: 是否批量返回
			   */
              channel.basicAck(delivery.getEnvelope().getDeliveryTag(), true);
		}
		
	}
	
	@Test
	public void consumerB() throws Exception{
		
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(queueName, false, false, false, null);
		
		//设定每个消费者每次消费个数,如果消息长时间没有接收到ACK消息则记录消费者信息,
		//如果队列连续3次没有接收到ACK返回消息,则不允许该消费者继续消费
		channel.basicQos(1);
		
		
		QueueingConsumer consumer = new QueueingConsumer(channel);
		
		//autoAck 自动应答
		//false 表示手动返回
		//将消息与对象进行绑定
		channel.basicConsume(queueName, false, consumer);
		System.out.println("消费者B启动");
		while (true) {
			
              Delivery delivery = consumer.nextDelivery();
              System.out.println("获取消息:"+new String(delivery.getBody()));
			  /**
			   * deliveryTag:返回数据的下标
			   * multiple: 是否批量返回
			   */
              channel.basicAck(delivery.getEnvelope().getDeliveryTag(), true);
		}
		
	}
	
	
	
}
