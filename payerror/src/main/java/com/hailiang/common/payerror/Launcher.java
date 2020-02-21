package com.hailiang.common.payerror;

import com.hailiang.common.payerror.service.InvestPayErrorService;
import com.hailiang.common.payerror.util.BeanFactoryUtil;
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
           InvestPayErrorService investPayErrorService = (InvestPayErrorService)BeanFactoryUtil.getContext().getBean("investPayErrorService");
           investPayErrorService.ListenInvestPayErrorMessages();
           
		} catch (Exception e) {
			log.error("payError 异常结束", e);
		}
    }  
}