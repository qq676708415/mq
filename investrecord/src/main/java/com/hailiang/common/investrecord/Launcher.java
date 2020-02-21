package com.hailiang.common.investrecord;

import com.hailiang.common.investrecord.service.InvestRecordService;
import com.hailiang.common.investrecord.util.BeanFactoryUtil;
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
           InvestRecordService investRecordService = (InvestRecordService) BeanFactoryUtil.getContext().getBean("investRecordService");
           investRecordService.ListenInvestRecordMessages();
           
		} catch (Exception e) {
			log.error("advance 异常结束", e);
		}
    }  
}