package com.hailiang.common.accountreward;

import com.hailiang.common.accountreward.service.AccountingRewardService;
import com.hailiang.common.accountreward.util.BeanFactoryUtil;
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
           AccountingRewardService investAdvanceService = (AccountingRewardService)BeanFactoryUtil.getContext().getBean("accountingRewardService");
           investAdvanceService.ListenAccountRewardMessages();
           
		} catch (Exception e) {
			log.error("account reward 异常结束", e);
		}
    }  
}