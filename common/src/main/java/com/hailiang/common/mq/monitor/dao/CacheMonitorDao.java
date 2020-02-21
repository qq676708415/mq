package com.hailiang.common.mq.monitor.dao;

import com.hailiang.common.consts.RedisConst;
import com.hailiang.common.redis.db.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 *  队列监控biz
 */
@Component
public class CacheMonitorDao {
	
	/**
	 * 注入redis服务
	 */
	@Autowired
	private RedisDao redisDao;

	/**
	 * 
	 *  记录队列执行时间
	 */
	public void recordMqMonitorInfo(String key, String monitor_value) {
		redisDao.setKeyAndValue(key, monitor_value, RedisConst.REDIS_DB_TYPE_MONITOR);
	}
	
	

}
