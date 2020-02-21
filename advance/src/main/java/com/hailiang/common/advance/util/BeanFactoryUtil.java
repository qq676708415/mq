package com.hailiang.common.advance.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * spring xml init
 */
public class BeanFactoryUtil {
	
private static ApplicationContext ctx_producer = null;
	
	public static synchronized void init() {
		if(ctx_producer == null) {
			ctx_producer = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-config.xml","classpath:/config/spring-init.xml"});
		}
	}
	
	public static ApplicationContext getContext() {
		if(ctx_producer == null) {
			init();
		}
		return ctx_producer;
	}
}
