package com.hailiang.common.redis.db;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis 存储投资相关信息专用dao服务
 * redis dao
 *
 *
 * @Date 2016年2月3日-下午2:52:40
 */
@Component
public class RedisOnigiriInvestDao {
	
	/**
	 * 注入template
	 */
	@Autowired
	@Qualifier("redisTemplate_onigiri_invest")  
    private RedisTemplate<String, String> template;
	
	/**
	 * key - value
	 * @param key
	 * @param value
	 */
	public void setKeyAndValue(String key , String value){
		template.opsForValue().set(key, value);
	}
	
	/**
	 * key - value  - timeout(单位：秒)
	 * @param key
	 * @param value
	 */
	public void setKeyAndValueTimeout(String key , String value,long timeout){
		template.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
	}
	
	/**
	 * 根据key查询value
	 * @param key
	 * @return
	 */
	public String getValueByKey(String key){
		return template.opsForValue().get(key);
	}
	
	/**
	 * 根据key删除value
	 * @param key
	 */
	public void removeValueByKey(String key){
		template.delete(key);
	}
	
	
	/**
	 * 推送消息
	 * @param key
	 * @param value
	 */
	public void leftPush(String key,String value){
		template.opsForList().leftPush(key, value);
	}
		
	/**
	 * 消费消息
	 * @param key
	 * @return
	 */
	public String rightPop(String key){		
		String value = template.opsForList().rightPop(key);
		if(null == value || "".equals(value))
			return null;
		return value;
	}
	
	/**
	 * 消费并备份消息
	 * @param key
	 * @return
	 */
	public String rightPopAndLeftPush(String key,String backupKey){		
		String value = template.opsForList().rightPopAndLeftPush(key, backupKey);
		if(null == value || "".equals(value))
			return null;
		return value;
	}
	
	/**
	 * 根据key进行模糊匹配查询
	 * @param key
	 * @return
	 */
	public Set<String> getKeys(String key){
		return template.keys(key);
	}
	
	/**
	 * 设置key在指定时间点失效
	 * @param key
	 * @param date
	 * @return
	 */
	public Boolean expireAt(String key, Date date){
		return template.expireAt(key, date);
	}
	
	/**
	 * redis中是否有key
	 * @param key
	 */
	public Boolean hasKey(String key){
		return template.hasKey(key);
	}
	
	/**
	 * @Desciption redis hash 操作 - 为哈希表中的字段赋值  
	 *
	 * @date 2016年8月8日 下午3:18:16
	 * @param key
	 * @param hashKey
	 * @param haskValue
	 */
	public void hset(String key, String hashKey, String haskValue) {
		template.opsForHash().put(key, hashKey, haskValue); 
	}
	
	/**
	 * @Desciption redis hash 操作-删除hash中指定的key 
	 *
	 * @date 2016年8月8日 下午3:20:26
	 * @param key
	 * @param hashKeys
	 */
	public void hdel(String key, String... hashKeys){
		template.opsForHash().delete(key, hashKeys);
	}
	
	/**
	 * @Desciption 判断哈希表中是否指定的字段 
	 *
	 * @date 2016年8月8日 下午3:24:23
	 * @param key
	 * @param hashKey
	 * @return
	 */
	public boolean hexists(String key, String hashKey){
		return template.opsForHash().hasKey(key, hashKey);
	}
	
	/**
	 * @Desciption 获得哈希表的元素数量 
	 *
	 * @date 2016年8月8日 下午3:28:27
	 * @param key
	 * @return
	 */
	public Long hlen(String key){
		return template.opsForHash().size(key);
	}
	
	/**
	 * @Desciption 同时将多个 field-value (域-值)对设置到哈希表 key 中。
	 *
	 * @date 2016年8月8日 下午3:29:43
	 * @param key
	 * @param map
	 */
	public void hmset(String key, Map<String, String> map){
		template.opsForHash().putAll(key, map);
	}
	
	/**
	 * key 不存在时，设置key value 返回true，如果存在直接返回false
	 * @param key
	 * @param value
	 * @param timeout
	 * @return
	 */
	public boolean setKeyAndValueIfAbsent(String key, String value){
		return template.opsForValue().setIfAbsent(key, value);
	}
	
	/**
	 * key 不存在时，设置key value 返回true，如果存在直接返回false
	 * @param key
	 * @param value
	 * @param timeout
	 * @return
	 */
	public boolean setKeyAndValueTimeountIfAbsent(String key, String value,long timeout){
		boolean result = template.opsForValue().setIfAbsent(key, value);
		template.expire(key, timeout, TimeUnit.SECONDS);
		return result;
	}

}