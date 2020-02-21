package com.hailiang.common.workorder;

import com.hailiang.common.workorder.service.WorkOrderService;
import com.hailiang.common.workorder.util.BeanFactoryUtil;
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
           WorkOrderService workOrderService = (WorkOrderService)BeanFactoryUtil.getContext().getBean("workOrderService");
           workOrderService.ListenWorkOrderMessages();
           
		} catch (Exception e) {
			log.error("payError 异常结束", e);
		}
    }  
}