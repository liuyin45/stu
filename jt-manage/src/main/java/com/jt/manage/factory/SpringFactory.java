package com.jt.manage.factory;

import java.util.Calendar;

import org.springframework.beans.factory.FactoryBean;

public class SpringFactory implements FactoryBean<Calendar>{

	@Override
	public Calendar getObject() throws Exception {
		//spring工厂获取对象
		return Calendar.getInstance();
	}

	@Override
	public Class<?> getObjectType() {
		
		return Calendar.class;
	}
	
	//是否为单例
	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}
}
