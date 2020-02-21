package com.hailiang.common.redis;

import com.hailiang.common.consts.RedisConst;
import com.hailiang.common.redis.db.RedisDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 订单号索引值处理
 *
 * @version 1.0
 * @since 1.0
 */
@Component
public class RedisUuidUtil {
	
	private static Logger log = LoggerFactory.getLogger(RedisUuidUtil.class);
	
	/** 订单号索引redis key **/
	private final static String UUID_KEY = "redis_queue_seqno_list";
	
	/** 订单号索引 最小阈值 **/
	private final static int QUEUE_MIN_NUM = 10000;
	
	/** 订单号索引 最大阈值 **/
	private final static int QUEUE_MAX_NUM = 100000;
	
	@Autowired
	RedisDao redisDao;
	
	/**
	 * 出队右出
	 * 
	 * @param key
	 * @return
	 */
	public synchronized String getSeqNoIndex() {
		
		try {
			
			String index = redisDao.rightPop(UUID_KEY, RedisConst.REDIS_DB_TYPE_QUEUE);
			if(null == index){
				autoAdd(QUEUE_MAX_NUM - 1);
				index = String.valueOf(QUEUE_MAX_NUM);
			}	
			
			if(Integer.parseInt(index) == QUEUE_MIN_NUM ){
				autoAdd(QUEUE_MAX_NUM);
			}
			
			/**
			 * 不足六位左侧用0填充
			 */
			int length = index.length();
		    if(length < 6){
		    	 for(int i= 1; i <= (6 - length); i++){
		    		 index = "0"+index;
		    	 }
		     }
			return index;
		} catch (Exception e) {
			log.error("获取订单号redis索引值异常",e);
		}
		return null;
	}
	
	/**
	 * 异步自增长队列
	 */
	private void autoAdd(final int maxNum){
		
		new Thread(new Runnable() {
			
			public void run() {
				 try {
					 for (int i = maxNum; i >= 1; i--) {
							put(String.valueOf(i));
						}
				} catch (Exception e) {
					log.error("redis入队生成订单号索引异常", e);
				}
			}
		}).start();
	}
	
	/**
	 * 入队左进
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	private void put(String value) {
		redisDao.leftPush(UUID_KEY, value,RedisConst.REDIS_DB_TYPE_QUEUE);
	}
}