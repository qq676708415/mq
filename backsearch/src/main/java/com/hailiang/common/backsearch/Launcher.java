package com.hailiang.common.backsearch;

import com.hailiang.common.backsearch.service.InvestBackSearchService;
import com.hailiang.common.backsearch.util.BeanFactoryUtil;
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
           InvestBackSearchService investBackSearchService = (InvestBackSearchService)BeanFactoryUtil.getContext().getBean("investBackSearchService");
           investBackSearchService.ListenBackSearchMessages();
           
		} catch (Exception e) {
			log.error("backSearch 异常结束", e);
		}
    }  
}