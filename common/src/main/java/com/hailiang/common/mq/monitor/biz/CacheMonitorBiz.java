package com.hailiang.common.mq.monitor.biz;

import com.hailiang.common.mq.monitor.dao.CacheMonitorDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 *  队列监控biz
 */
@Component
public class CacheMonitorBiz {

	/**
	 * 
	 */
	@Autowired
	private CacheMonitorDao cacheMonotorDao;
	
	/**
	 * 
	 *  记录队列执行时间
	 *  */
	public void recordMqMonitorInfo(String key) {
		/** 获取用户当日已投资次数  **/
		String monitor_value = System.currentTimeMillis()/1000 + "";
		cacheMonotorDao.recordMqMonitorInfo(key, monitor_value);
	}


}
