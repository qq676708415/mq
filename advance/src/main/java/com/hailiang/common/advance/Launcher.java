package com.hailiang.common.advance;

import com.hailiang.common.advance.util.BeanFactoryUtil;
import com.hailiang.common.advance.service.InvestAdvanceService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
           InvestAdvanceService investAdvanceService = (InvestAdvanceService)BeanFactoryUtil.getContext().getBean("investAdvanceService");
           investAdvanceService.ListenInvestAdvanceMessages();
           
		} catch (Exception e) {
			log.error("advance 异常结束", e);
		}
    }  
}