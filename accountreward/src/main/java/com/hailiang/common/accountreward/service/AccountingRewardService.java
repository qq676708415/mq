package com.hailiang.common.accountreward.service;

import com.hailiang.common.consts.MqMonitorConst;
import com.hailiang.common.consts.RedisQueueConst;
import com.hailiang.common.mq.monitor.biz.CacheMonitorBiz;
import com.hailiang.common.mq.thread.TaskProducer;
import com.hailiang.common.redis.biz.QueueBiz;
import com.hailiang.common.redis.biz.RedisBatchBiz;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 记账管理 - 奖励金账户记账 消息队列监听，将队列中取到的值放入阻塞队列里
 *
 */
@Service("accountingRewardService")
@Slf4j
public class AccountingRewardService {

	/**
	 * 注入redis消息队列biz
	 */
	@Autowired
	private QueueBiz queueBiz;

	/**
	 * 注入队列监控biz服务
	 */
	@Autowired
	private CacheMonitorBiz cacheMonotorBiz;

	/**
	 * 注入阻塞消息队列管理类
	 */
	@Autowired
	private TaskProducer taskProducer;

	/**
	 * 注入消息队列备份处理biz
	 */
	@Autowired
	private RedisBatchBiz redisBatchBiz;

	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * 奖励金账户 - 预处理消息
	 * 
	 * @throws Exception
	 */
	public void ListenAccountRewardMessages() {
		log.info("开始监听...");
		for (;;) {
			//  奖励金账户 - 队列监控的key
			String monitor_key = MqMonitorConst.FF_MQ_MONITOR + MqMonitorConst.MQ_ACCOUNTING_MANAGE_REWARD + Thread.currentThread().getId();

			try {
				try {
					Thread.sleep(8);
					cacheMonotorBiz.recordMqMonitorInfo(monitor_key);
				} catch (Exception e) {
					log.error("队列监控记录异常 :" + monitor_key, e);
				}
				String advanceValue = queueBiz.rightPop(RedisQueueConst.REDIS_QUEUE_ACCOUNT_REWARD_LIST, String.class);
				if (null == advanceValue) {
					continue;
				}
				log.info("奖励金账户队列出队 => {}", advanceValue);

				log.info("消息备份添加...");
				redisBatchBiz.hset(RedisQueueConst.REDIS_QUEUE_ACCOUNT_REWARD_BACKUP_MAP_KEY, advanceValue, advanceValue);

				//放入阻塞消息队列
				taskProducer.addRedisRecord(advanceValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}