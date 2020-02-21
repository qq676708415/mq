package com.hailiang.common.pay.service;

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
 * 买入 - 支付消息队列监听，将队列中取到的值放入阻塞队列里
 *
 */
@Service("investPayService")
@Slf4j
public class InvestPayService {

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


	/**
	 * 监听买入 - 支付消息
	 * 
	 * @throws Exception
	 */
	public void ListenInvestPayMessages() {
		log.info("开始监听...");
		for (;;) {
			//  投资预处理 - 队列监控的key
			String monitor_key = MqMonitorConst.FF_MQ_MONITOR + MqMonitorConst.MQ_INVEST_MANAGE_FW_PAY + Thread.currentThread().getId();

			try {
				try {
					Thread.sleep(8);
					cacheMonotorBiz.recordMqMonitorInfo(monitor_key);
				} catch (Exception e) {
					log.error("队列监控记录异常 :" + monitor_key, e);
				}
				String payValue = queueBiz.rightPop(RedisQueueConst.REDIS_QUEUE_FW_INVEST_PAY_LIST, String.class);
				if (null == payValue) {
					continue;
				}
				log.info("投资支付消息队列出队 => {}", payValue);

				log.info("消息备份添加...");
				redisBatchBiz.hset(RedisQueueConst.REDIS_QUEUE_FW_INVEST_PAY_BACKUP_MAP_KEY, payValue, payValue);

				//放入阻塞消息队列
				taskProducer.addRedisRecord(payValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}