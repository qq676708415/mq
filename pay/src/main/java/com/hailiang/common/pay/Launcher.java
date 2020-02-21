package com.hailiang.common.pay;

import com.hailiang.common.pay.service.InvestPayService;
import com.hailiang.common.pay.util.BeanFactoryUtil;
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
           InvestPayService investPayService = (InvestPayService)BeanFactoryUtil.getContext().getBean("investPayService");
           investPayService.ListenInvestPayMessages();
           
		} catch (Exception e) {
			log.error("pay 异常结束", e);
		}
    }  
}