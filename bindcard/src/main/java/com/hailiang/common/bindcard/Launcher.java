package com.hailiang.common.bindcard;

import com.hailiang.common.bindcard.service.BindCardService;
import com.hailiang.common.bindcard.util.BeanFactoryUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务入口
 */

@Slf4j
public class Launcher
{

    public static void main(String[] args) {
       try {
    	   // 初始化spring
           BeanFactoryUtil.init();
           log.info("初始化spring");
           BindCardService bindCardService = (BindCardService)BeanFactoryUtil.getContext().getBean("bindCardService");
           bindCardService.ListenBindCardMessages();
           
		} catch (Exception e) {
			log.error("bindCard 异常结束", e);
		}
    }  
}