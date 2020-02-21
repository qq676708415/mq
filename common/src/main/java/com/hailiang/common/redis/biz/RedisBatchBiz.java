package com.hailiang.common.redis.biz;

import com.hailiang.common.redis.db.RedisOnigiriMonitorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RedisBatchBiz {

	@Autowired
	RedisOnigiriMonitorDao monitorDao;
	
	public void putAll(String key, Map<String, String> map){
		monitorDao.hmset(key, map);
	}
	
	/**
	 * 根据key、hkey 新增 map中的hvalue
	 * @param key
	 * @param hashKey
	 * @param haskValue
	 */
	public void hset(String key, String hashKey, String haskValue) {
		monitorDao.hset(key, hashKey, haskValue); 
	}
	
	/**
	 * 根据key、hkey删除map中的hvalue
	 * @param key
	 * @param hasKey
	 */
	public void hdel(String key,String hashKey){
		monitorDao.hdel(key, hashKey);
	}
	
	public Long size(String key){
		return monitorDao.hlen(key);
	}
	
	public void delete(String key, String... hashKeys){
		monitorDao.hdel(key, hashKeys);
	}
	
	
	/**
	 *  移除某个key值
	 * @author  fengruiqi
	 * 创建时间 2017年4月26日 下午4:06:02
	 * @param key
	 */
	public void removeValueByKey(String key){
		monitorDao.removeValueByKey(key);
	}
	
}
