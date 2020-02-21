package com.hailiang.common.redis.biz;

import com.alibaba.fastjson.JSON;
import com.hailiang.common.consts.RedisConst;
import com.hailiang.common.redis.db.RedisDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 消息队列业务类
 *
 *
 */
@Service("queueBiz")
public class QueueBiz {

	private static Logger log = LoggerFactory.getLogger(QueueBiz.class);
	
	@Autowired
	RedisDao redisDao;
	
	/**
	 * 推送消息队列
	 */
	public void leftPush(String key, String value){
		redisDao.leftPush(key, value, RedisConst.REDIS_DB_TYPE_QUEUE);
	}
	
	/**
	 * 出队
	 */
	public String rightPop(String key){
		return redisDao.rightPop(key, RedisConst.REDIS_DB_TYPE_QUEUE);
	}
	
	/**
	 * 出队
	 */
	public <T> T rightPop(String key, Class<T> clazz){
		String value = redisDao.rightPop(key, RedisConst.REDIS_DB_TYPE_QUEUE);
		if(StringUtils.isEmpty(value)){
			return null;
		}
		try{
			if (clazz == String.class) {
				return (T)value;
			}
			return JSON.parseObject(value, clazz);
		} catch(Exception e){
			log.info("队列出队消息类型转换异常: 出队消息:{}, 转换类型:{}", value, clazz.getName());
			return null;
		}
	}
	
	/**
	 * 消息出队并进行备份
	 */
	public <T> T rightPopAndLeftPush(String key, String backupKey,Class<T> clazz){
		String value = redisDao.rightPopAndLeftPush(key, backupKey, RedisConst.REDIS_DB_TYPE_QUEUE);
		if(StringUtils.isEmpty(value)){
			return null;
		}
		try{
			return JSON.parseObject(value, clazz);
		} catch(Exception e){
			log.info("队列出队消息类型转换异常: 出队消息:{}, 转换类型:{}", value, clazz.getName());
			return null;
		}
	}
}
