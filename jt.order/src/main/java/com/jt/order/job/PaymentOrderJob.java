package com.jt.order.job;

import java.util.Date;

import org.joda.time.DateTime;
import org.quartz.JobExecutionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.jt.order.mapper.OrderMapper;

public class PaymentOrderJob extends QuartzJobBean{

	//定时任务具体逻辑
	@Override
	protected void executeInternal(JobExecutionContext context)  {
		
		//获取spring容器对象
		ApplicationContext applicationContext = 
				(ApplicationContext) context.getJobDetail().getJobDataMap().get("applicationContext");
	    
		//Order接口对象
		OrderMapper orderMapper = applicationContext.getBean(OrderMapper.class);
		
		//3.如果用户订单已经超时,则应该将状态从1改为6.规定2天超时
	    
		//超时时间
		Date timeOut = new DateTime().minusDays(2).toDate();
		
		orderMapper.updateStatus(timeOut);
				System.out.println("定时任务执行完成");

	    
	
	
	}
	
	
}
