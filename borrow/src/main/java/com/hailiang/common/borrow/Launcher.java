package com.hailiang.common.borrow;

import com.hailiang.common.borrow.service.BorrowManageService;
import com.hailiang.common.borrow.util.BeanFactoryUtil;
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
           BorrowManageService borrowManageService = (BorrowManageService) BeanFactoryUtil.getContext().getBean("borrowManageService");
           borrowManageService.ListenBorrowMessages();
           
		} catch (Exception e) {
			log.error("advance 异常结束", e);
		}
    }  
}