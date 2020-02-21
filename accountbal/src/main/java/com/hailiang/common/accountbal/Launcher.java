package com.hailiang.common.accountbal;

import com.hailiang.common.accountbal.service.AccountingBalService;
import com.hailiang.common.accountbal.util.BeanFactoryUtil;
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
           AccountingBalService accountingBalService = (AccountingBalService)BeanFactoryUtil.getContext().getBean("accountingBalService");
           accountingBalService.ListenAccountBalMessages();
           
		} catch (Exception e) {
			log.error("advance 异常结束", e);
		}
    }  
}