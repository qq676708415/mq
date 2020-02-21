package com.hailiang.common.redis.db;

import com.hailiang.common.consts.RedisConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * redis dao服务
 */
@Component
public class RedisDao {
	
	/** 日志处理 **/
	private static Logger logger = LoggerFactory.getLogger(RedisDao.class);
	
	
	/**
	 * 注入存储微信相关信息专用dao服务
	 */
	@Autowired
	private RedisOnigiriWeixinDao weixinDao;
	
	/**
	 * 注入存储手机验证码专用dao服务
	 */
	@Autowired
	private RedisOnigiriVcodeDao vcodeDao;
	
	/**
	 * 注入存储业务参数专用dao服务
	 */
	@Autowired
	private RedisOnigiriBusparaDao busParaDao;
		
	/**
	 * 注入存储投资相关信息专用dao服务
	 */
	@Autowired
	private RedisOnigiriInvestDao investDao;
	
	/**
	 * 注入存储提现相关信息专用dao服务
	 */
	@Autowired
	private RedisOnigiriCashDao cashDao;
	
	/**
	 * 注入存储q消息专用dao服务
	 */
	@Autowired
	private RedisOnigiriQueueDao queueDao;
	
	/**
	 * 注入Q监控消息专用dao服务
	 */
	@Autowired
	private RedisOnigiriMonitorDao monitorDao;
	
	
	/**
	 * key - value
	 * @param key
	 * @param value
	 * @param type weixin:微信
	 *             vcode：手机验证码
	 *             buspara：业务参数
	 *             invest：投资相关
	 *             cash：提现相关
	 *             queue：消费queue
	 *             monitor： Q监控
	 */
	public void setKeyAndValue(String key , String value, String type){
		
		if(RedisConst.REDIS_DB_TYPE_WEIXIN.equals(type)){
			weixinDao.setKeyAndValue(key, value);
		}else if(RedisConst.REDIS_DB_TYPE_VCODE.equals(type)){
			vcodeDao.setKeyAndValue(key, value);
		}else if(RedisConst.REDIS_DB_TYPE_BUSPARA.equals(type)){
			busParaDao.setKeyAndValue(key, value);
		}else if(RedisConst.REDIS_DB_TYPE_INVEST.equals(type)){
			investDao.setKeyAndValue(key, value);
		}else if(RedisConst.REDIS_DB_TYPE_CASH.equals(type)){
			cashDao.setKeyAndValue(key, value);
		}else if(RedisConst.REDIS_DB_TYPE_QUEUE.equals(type)){
			queueDao.setKeyAndValue(key, value);
		}else if(RedisConst.REDIS_DB_TYPE_MONITOR.equals(type)){
			monitorDao.setKeyAndValue(key, value);
		}
	}
	
	/**
	 * key - value  - timeout(单位：秒)
	 * @param key
	 * @param value
	 * @param type weixin:微信
	 *             vcode：手机验证码
	 *             buspara：业务参数
	 *             invest：投资相关
	 *             cash：提现相关
	 *             queue：消费queue
	 *             monitor： Q监控
	 */
	public void setKeyAndValueTimeout(String key , String value,long timeout, String type){
		
		if(RedisConst.REDIS_DB_TYPE_WEIXIN.equals(type)){
			weixinDao.setKeyAndValueTimeout(key, value, timeout);
		}else if(RedisConst.REDIS_DB_TYPE_VCODE.equals(type)){
			vcodeDao.setKeyAndValueTimeout(key, value, timeout);
		}else if(RedisConst.REDIS_DB_TYPE_BUSPARA.equals(type)){
			busParaDao.setKeyAndValueTimeout(key, value, timeout);
		}else if(RedisConst.REDIS_DB_TYPE_INVEST.equals(type)){
			investDao.setKeyAndValueTimeout(key, value, timeout);
		}else if(RedisConst.REDIS_DB_TYPE_CASH.equals(type)){
			cashDao.setKeyAndValueTimeout(key, value, timeout);
		}else if(RedisConst.REDIS_DB_TYPE_QUEUE.equals(type)){
			queueDao.setKeyAndValueTimeout(key, value, timeout);
		}else if(RedisConst.REDIS_DB_TYPE_MONITOR.equals(type)){
			monitorDao.setKeyAndValue(key, value);
		}
	}
	
	/**
	 * 根据key查询value
	 * @param key
	 * @param type weixin:微信
	 *             vcode：手机验证码
	 *             buspara：业务参数
	 *             invest：投资相关
	 *             cash：提现相关
	 *             queue：消费queue
	 *             monitor： Q监控
	 * @return
	 */
	public String getValueByKey(String key , String type){
		
		if(RedisConst.REDIS_DB_TYPE_WEIXIN.equals(type)){
			return weixinDao.getValueByKey(key);
		}else if(RedisConst.REDIS_DB_TYPE_VCODE.equals(type)){
			return vcodeDao.getValueByKey(key);
		}else if(RedisConst.REDIS_DB_TYPE_BUSPARA.equals(type)){
			return busParaDao.getValueByKey(key);
		}else if(RedisConst.REDIS_DB_TYPE_INVEST.equals(type)){
			return investDao.getValueByKey(key);
		}else if(RedisConst.REDIS_DB_TYPE_CASH.equals(type)){
			return cashDao.getValueByKey(key);
		}else if(RedisConst.REDIS_DB_TYPE_QUEUE.equals(type)){
			return queueDao.getValueByKey(key);
		}else if(RedisConst.REDIS_DB_TYPE_MONITOR.equals(type)){
			return monitorDao.getValueByKey(key);
		}
		return null;
	}
	
	/**
	 * 根据key删除value
	 * @param key
	 * @param type weixin:微信
	 *             vcode：手机验证码
	 *             buspara：业务参数
	 *             invest：投资相关
	 *             cash：提现相关
	 *             queue：消费queue
	 *             monitor： Q监控
	 */
	public void removeValueByKey(String key , String type){
		
		if(RedisConst.REDIS_DB_TYPE_WEIXIN.equals(type)){
			weixinDao.removeValueByKey(key);
		}else if(RedisConst.REDIS_DB_TYPE_VCODE.equals(type)){
			vcodeDao.removeValueByKey(key);
		}else if(RedisConst.REDIS_DB_TYPE_BUSPARA.equals(type)){
			busParaDao.removeValueByKey(key);
		}else if(RedisConst.REDIS_DB_TYPE_INVEST.equals(type)){
			investDao.removeValueByKey(key);
		}else if(RedisConst.REDIS_DB_TYPE_CASH.equals(type)){
			cashDao.removeValueByKey(key);
		}else if(RedisConst.REDIS_DB_TYPE_QUEUE.equals(type)){
			queueDao.removeValueByKey(key);
		}else if(RedisConst.REDIS_DB_TYPE_MONITOR.equals(type)){
			monitorDao.removeValueByKey(key);
		}
	}
	
	
	/**
	 * 推送消息
	 * @param key
	 * @param value
	 * @param type weixin:微信
	 *             vcode：手机验证码
	 *             buspara：业务参数
	 *             invest：投资相关
	 *             cash：提现相关
	 *             queue：消费queue
	 *             monitor： Q监控
	 */
	public void leftPush(String key,String value, String type){
		
		if(RedisConst.REDIS_DB_TYPE_WEIXIN.equals(type)){
			weixinDao.leftPush(key,value);
		}else if(RedisConst.REDIS_DB_TYPE_VCODE.equals(type)){
			vcodeDao.leftPush(key,value);
		}else if(RedisConst.REDIS_DB_TYPE_BUSPARA.equals(type)){
			busParaDao.leftPush(key,value);
		}else if(RedisConst.REDIS_DB_TYPE_INVEST.equals(type)){
			investDao.leftPush(key,value);
		}else if(RedisConst.REDIS_DB_TYPE_CASH.equals(type)){
			cashDao.leftPush(key,value);
		}else if(RedisConst.REDIS_DB_TYPE_QUEUE.equals(type)){
			queueDao.leftPush(key,value);
		}else if(RedisConst.REDIS_DB_TYPE_MONITOR.equals(type)){
			monitorDao.leftPush(key,value);
		}
	}
		
	/**
	 * 消费消息
	 * @param key
	 * @param type weixin:微信
	 *             vcode：手机验证码
	 *             buspara：业务参数
	 *             invest：投资相关
	 *             cash：提现相关
	 *             queue：消费queue
	 * @return
	 */
	public String rightPop(String key, String type){
		
		String value = null;		
		if(RedisConst.REDIS_DB_TYPE_WEIXIN.equals(type)){
			value = weixinDao.rightPop(key);
		}else if(RedisConst.REDIS_DB_TYPE_VCODE.equals(type)){
			value = vcodeDao.rightPop(key);
		}else if(RedisConst.REDIS_DB_TYPE_BUSPARA.equals(type)){
			value = busParaDao.rightPop(key);
		}else if(RedisConst.REDIS_DB_TYPE_INVEST.equals(type)){
			value = investDao.rightPop(key);
		}else if(RedisConst.REDIS_DB_TYPE_CASH.equals(type)){
			value = cashDao.rightPop(key);
		}else if(RedisConst.REDIS_DB_TYPE_QUEUE.equals(type)){
			value = queueDao.rightPop(key);
		}else if(RedisConst.REDIS_DB_TYPE_MONITOR.equals(type)){
			value = monitorDao.rightPop(key);
		}
		
		if(null == value || "".equals(value))
			return null;
		return value;
	}
	
	/**
	 * 消费消息
	 * @param key
	 * @param type weixin:微信
	 *             vcode：手机验证码
	 *             buspara：业务参数
	 *             invest：投资相关
	 *             cash：提现相关
	 *             queue：消费queue
	 * @return
	 */
	public String rightPopAndLeftPush(String key,String backupKey, String type){
		
		String value = null;		
		if(RedisConst.REDIS_DB_TYPE_WEIXIN.equals(type)){
			value = weixinDao.rightPopAndLeftPush(key,backupKey);
		}else if(RedisConst.REDIS_DB_TYPE_VCODE.equals(type)){
			value = vcodeDao.rightPopAndLeftPush(key,backupKey);
		}else if(RedisConst.REDIS_DB_TYPE_BUSPARA.equals(type)){
			value = busParaDao.rightPopAndLeftPush(key,backupKey);
		}else if(RedisConst.REDIS_DB_TYPE_INVEST.equals(type)){
			value = investDao.rightPopAndLeftPush(key,backupKey);
		}else if(RedisConst.REDIS_DB_TYPE_CASH.equals(type)){
			value = cashDao.rightPopAndLeftPush(key,backupKey);
		}else if(RedisConst.REDIS_DB_TYPE_QUEUE.equals(type)){
			value = queueDao.rightPopAndLeftPush(key,backupKey);
		}else if(RedisConst.REDIS_DB_TYPE_MONITOR.equals(type)){
			value = monitorDao.rightPopAndLeftPush(key,backupKey);
		}
		
		if(null == value || "".equals(value))
			return null;
		return value;
	}
	
	/**
	 * 根据key进行模糊匹配查询
	 * @param key
	 * @param type weixin:微信
	 *             vcode：手机验证码
	 *             buspara：业务参数
	 *             invest：投资相关
	 *             cash：提现相关
	 *             queue：消费queue
	 *             monitor： Q监控
	 * @return
	 */
	public Set<String> getKeys(String key, String type){
		
		if(RedisConst.REDIS_DB_TYPE_WEIXIN.equals(type)){
			return weixinDao.getKeys(key);
		}else if(RedisConst.REDIS_DB_TYPE_VCODE.equals(type)){
			return vcodeDao.getKeys(key);
		}else if(RedisConst.REDIS_DB_TYPE_BUSPARA.equals(type)){
			return busParaDao.getKeys(key);
		}else if(RedisConst.REDIS_DB_TYPE_INVEST.equals(type)){
			return investDao.getKeys(key);
		}else if(RedisConst.REDIS_DB_TYPE_CASH.equals(type)){
			return cashDao.getKeys(key);
		}else if(RedisConst.REDIS_DB_TYPE_QUEUE.equals(type)){
			return queueDao.getKeys(key);
		}else if(RedisConst.REDIS_DB_TYPE_MONITOR.equals(type)){
			return monitorDao.getKeys(key);
		}
		
		return null;
	}
	
	/**
	 * 设置key在指定时间点失效
	 * @param key
	 * @param date
	 * @param type weixin:微信
	 *             vcode：手机验证码
	 *             buspara：业务参数
	 *             invest：投资相关
	 *             cash：提现相关
	 *             queue：消费queue
	 *             monitor： Q监控
	 * @return
	 */
	public Boolean expireAt(String key, Date date, String type){
		
		if(RedisConst.REDIS_DB_TYPE_WEIXIN.equals(type)){
			return weixinDao.expireAt(key, date);
		}else if(RedisConst.REDIS_DB_TYPE_VCODE.equals(type)){
			return vcodeDao.expireAt(key, date);
		}else if(RedisConst.REDIS_DB_TYPE_BUSPARA.equals(type)){
			return busParaDao.expireAt(key, date);
		}else if(RedisConst.REDIS_DB_TYPE_INVEST.equals(type)){
			return investDao.expireAt(key, date);
		}else if(RedisConst.REDIS_DB_TYPE_CASH.equals(type)){
			return cashDao.expireAt(key, date);
		}else if(RedisConst.REDIS_DB_TYPE_QUEUE.equals(type)){
			return queueDao.expireAt(key, date);
		}else if(RedisConst.REDIS_DB_TYPE_MONITOR.equals(type)){
			return monitorDao.expireAt(key, date);
		}
		return null;
	}
	
	/**
	 * redis中是否有key
	 * @param key
	 * @param type weixin:微信
	 *             vcode：手机验证码
	 *             buspara：业务参数
	 *             invest：投资相关
	 *             cash：提现相关
	 *             queue：消费queue
	 *             monitor： Q监控
	 */
	public Boolean hasKey(String key, String type){
		
		if(RedisConst.REDIS_DB_TYPE_WEIXIN.equals(type)){
			return weixinDao.hasKey(key);
		}else if(RedisConst.REDIS_DB_TYPE_VCODE.equals(type)){
			return vcodeDao.hasKey(key);
		}else if(RedisConst.REDIS_DB_TYPE_BUSPARA.equals(type)){
			return busParaDao.hasKey(key);
		}else if(RedisConst.REDIS_DB_TYPE_INVEST.equals(type)){
			return investDao.hasKey(key);
		}else if(RedisConst.REDIS_DB_TYPE_CASH.equals(type)){
			return cashDao.hasKey(key);
		}else if(RedisConst.REDIS_DB_TYPE_QUEUE.equals(type)){
			return queueDao.hasKey(key);
		}else if(RedisConst.REDIS_DB_TYPE_MONITOR.equals(type)){
			return monitorDao.hasKey(key);
		}
		return false;
	}
	
	/**
	 * @Desciption redis hash 操作 - 为哈希表中的字段赋值  
	 *
	 * @date 2016年8月8日 下午3:18:16
	 * @param key
	 * @param hashKey
	 * @param hashValue
	 */
	public void hset(String key, String hashKey, String hashValue, String type) {
		if (RedisConst.REDIS_DB_TYPE_WEIXIN.equals(type)) {
			weixinDao.hset(key, hashKey, hashValue);
		} else if (RedisConst.REDIS_DB_TYPE_VCODE.equals(type)) {
			vcodeDao.hset(key, hashKey, hashValue);
		} else if (RedisConst.REDIS_DB_TYPE_BUSPARA.equals(type)) {
			busParaDao.hset(key, hashKey, hashValue);
		} else if (RedisConst.REDIS_DB_TYPE_INVEST.equals(type)) {
			investDao.hset(key, hashKey, hashValue);
		} else if (RedisConst.REDIS_DB_TYPE_CASH.equals(type)) {
			cashDao.hset(key, hashKey, hashValue);
		} else if (RedisConst.REDIS_DB_TYPE_QUEUE.equals(type)) {
			queueDao.hset(key, hashKey, hashValue);
		} else if (RedisConst.REDIS_DB_TYPE_MONITOR.equals(type)) {
			monitorDao.hset(key, hashKey, hashValue);
		}
	}
	
	/**
	 * @Desciption redis hash 操作-删除hash中指定的key 
	 *
	 * @date 2016年8月8日 下午3:20:26
	 * @param key
	 * @param hashKeys
	 */
	public void hdel(String key, String hashKeys, String type){
		
		if (RedisConst.REDIS_DB_TYPE_WEIXIN.equals(type)) {
			weixinDao.hdel(key, hashKeys);
		} else if (RedisConst.REDIS_DB_TYPE_VCODE.equals(type)) {
			vcodeDao.hdel(key, hashKeys);
		} else if (RedisConst.REDIS_DB_TYPE_BUSPARA.equals(type)) {
			busParaDao.hdel(key, hashKeys);
		} else if (RedisConst.REDIS_DB_TYPE_INVEST.equals(type)) {
			investDao.hdel(key, hashKeys);
		} else if (RedisConst.REDIS_DB_TYPE_CASH.equals(type)) {
			cashDao.hdel(key, hashKeys);
		} else if (RedisConst.REDIS_DB_TYPE_QUEUE.equals(type)) {
			queueDao.hdel(key, hashKeys);
		} else if (RedisConst.REDIS_DB_TYPE_MONITOR.equals(type)) {
			monitorDao.hdel(key, hashKeys);
		} 
	}
	
	/**
	 * @Desciption 判断哈希表中是否指定的字段 
	 *
	 * @date 2016年8月8日 下午3:24:23
	 * @param key
	 * @param hashKey
	 * @return
	 */
	public boolean hexists(String key, String hashKey, String type){
		
		if (RedisConst.REDIS_DB_TYPE_WEIXIN.equals(type)) {
			return weixinDao.hexists(key, hashKey);
		} else if (RedisConst.REDIS_DB_TYPE_VCODE.equals(type)) {
			return vcodeDao.hexists(key, hashKey);
		} else if (RedisConst.REDIS_DB_TYPE_BUSPARA.equals(type)) {
			return busParaDao.hexists(key, hashKey);
		} else if (RedisConst.REDIS_DB_TYPE_INVEST.equals(type)) {
			return investDao.hexists(key, hashKey);
		} else if (RedisConst.REDIS_DB_TYPE_CASH.equals(type)) {
			return cashDao.hexists(key, hashKey);
		} else if (RedisConst.REDIS_DB_TYPE_QUEUE.equals(type)) {
			return queueDao.hexists(key, hashKey);
		} else if (RedisConst.REDIS_DB_TYPE_MONITOR.equals(type)) {
			return monitorDao.hexists(key, hashKey);
		}
		return false;
	}
	
	/**
	 * @Desciption 获得哈希表的元素数量 
	 *
	 * @date 2016年8月8日 下午3:28:27
	 * @param key
	 * @return
	 */
	public Long hlen(String key, String type){
		if (RedisConst.REDIS_DB_TYPE_WEIXIN.equals(type)) {
			return weixinDao.hlen(key);
		} else if (RedisConst.REDIS_DB_TYPE_VCODE.equals(type)) {
			return vcodeDao.hlen(key);
		} else if (RedisConst.REDIS_DB_TYPE_BUSPARA.equals(type)) {
			return busParaDao.hlen(key);
		} else if (RedisConst.REDIS_DB_TYPE_INVEST.equals(type)) {
			return investDao.hlen(key);
		} else if (RedisConst.REDIS_DB_TYPE_CASH.equals(type)) {
			return cashDao.hlen(key);
		} else if (RedisConst.REDIS_DB_TYPE_QUEUE.equals(type)) {
			return queueDao.hlen(key);
		} else if (RedisConst.REDIS_DB_TYPE_MONITOR.equals(type)) {
			return monitorDao.hlen(key);
		}
		return 0L;
		
	}
	
	/**
	 * @Desciption 同时将多个 field-value (域-值)对设置到哈希表 key 中。
	 *
	 * @date 2016年8月8日 下午3:29:43
	 * @param key
	 * @param map
	 */
	public void hmset(String key, Map<String, String> map, String type){
		
		if (RedisConst.REDIS_DB_TYPE_WEIXIN.equals(type)) {
			weixinDao.hmset(key, map);
		} else if (RedisConst.REDIS_DB_TYPE_VCODE.equals(type)) {
			vcodeDao.hmset(key, map);
		} else if (RedisConst.REDIS_DB_TYPE_BUSPARA.equals(type)) {
			busParaDao.hmset(key, map);
		} else if (RedisConst.REDIS_DB_TYPE_INVEST.equals(type)) {
			investDao.hmset(key, map);
		} else if (RedisConst.REDIS_DB_TYPE_CASH.equals(type)) {
			cashDao.hmset(key, map);
		} else if (RedisConst.REDIS_DB_TYPE_QUEUE.equals(type)) {
			queueDao.hmset(key, map);
		} else if (RedisConst.REDIS_DB_TYPE_MONITOR.equals(type)) {
			monitorDao.hmset(key, map);
		}
	}
	
	/**
	 * key - value  - timeout(单位：秒)
	 * @param key
	 * @param value
	 * @param type weixin:微信
	 *             vcode：手机验证码
	 *             buspara：业务参数
	 *             invest：投资相关
	 *             cash：提现相关
	 *             queue：消费queue
	 *             monitor： Q监控
	 */
	public boolean setKeyAndValueIfAbsent(String key , String value, String type){
		if(RedisConst.REDIS_DB_TYPE_WEIXIN.equals(type)){
			return weixinDao.setKeyAndValueIfAbsent(key, value);
		}else if(RedisConst.REDIS_DB_TYPE_VCODE.equals(type)){
			return vcodeDao.setKeyAndValueIfAbsent(key, value);
		}else if(RedisConst.REDIS_DB_TYPE_BUSPARA.equals(type)){
			return busParaDao.setKeyAndValueIfAbsent(key, value);
		}else if(RedisConst.REDIS_DB_TYPE_INVEST.equals(type)){
			return investDao.setKeyAndValueIfAbsent(key, value);
		}else if(RedisConst.REDIS_DB_TYPE_CASH.equals(type)){
			return cashDao.setKeyAndValueIfAbsent(key, value);
		}else if(RedisConst.REDIS_DB_TYPE_QUEUE.equals(type)){
			return queueDao.setKeyAndValueIfAbsent(key, value);
		}else if(RedisConst.REDIS_DB_TYPE_MONITOR.equals(type)){
			return monitorDao.setKeyAndValueIfAbsent(key, value);
		}
		return false;
	}
	
	/**
	 * key - value  - timeout(单位：秒)
	 * @param key
	 * @param value
	 * @param type weixin:微信
	 *             vcode：手机验证码
	 *             buspara：业务参数
	 *             invest：投资相关
	 *             cash：提现相关
	 *             queue：消费queue
	 *             monitor： Q监控
	 */
	public boolean setKeyAndValueTimeountIfAbsent(String key , String value, long timeout, String type){
		if(RedisConst.REDIS_DB_TYPE_WEIXIN.equals(type)){
			return weixinDao.setKeyAndValueTimeountIfAbsent(key, value, timeout);
		}else if(RedisConst.REDIS_DB_TYPE_VCODE.equals(type)){
			return vcodeDao.setKeyAndValueTimeountIfAbsent(key, value, timeout);
		}else if(RedisConst.REDIS_DB_TYPE_BUSPARA.equals(type)){
			return busParaDao.setKeyAndValueTimeountIfAbsent(key, value, timeout);
		}else if(RedisConst.REDIS_DB_TYPE_INVEST.equals(type)){
			return investDao.setKeyAndValueTimeountIfAbsent(key, value, timeout);
		}else if(RedisConst.REDIS_DB_TYPE_CASH.equals(type)){
			return cashDao.setKeyAndValueTimeountIfAbsent(key, value, timeout);
		}else if(RedisConst.REDIS_DB_TYPE_QUEUE.equals(type)){
			return queueDao.setKeyAndValueTimeountIfAbsent(key, value, timeout);
		}else if(RedisConst.REDIS_DB_TYPE_MONITOR.equals(type)){
			return monitorDao.setKeyAndValueTimeountIfAbsent(key, value, timeout);
		}
		return false;
	}

}