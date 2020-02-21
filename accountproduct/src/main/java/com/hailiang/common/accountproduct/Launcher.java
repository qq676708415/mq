package com.hailiang.common.accountproduct;

import com.hailiang.common.accountproduct.service.AccountingProductService;
import com.hailiang.common.accountproduct.util.BeanFactoryUtil;
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
           AccountingProductService accountingProductService = (AccountingProductService) BeanFactoryUtil.getContext().getBean("accountingProductService");
           accountingProductService.ListenAccountProductMessages();
           
		} catch (Exception e) {
			log.error("advance 异常结束", e);
		}
    }  
}