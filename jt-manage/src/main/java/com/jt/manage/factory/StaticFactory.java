package com.jt.manage.factory;

import java.util.Calendar;

public class StaticFactory {
	
	//静态工厂方式
	public static Calendar getCalender(){
		
		return Calendar.getInstance();
	}
}
