package com.hailiang.common.payerror.service;

import com.hailiang.common.consts.MqMonitorConst;
import com.hailiang.common.consts.RedisQueueConst;
import com.hailiang.common.mq.monitor.biz.CacheMonitorBiz;
import com.hailiang.common.mq.thread.TaskProducer;
import com.hailiang.common.redis.biz.QueueBiz;
import com.hailiang.common.redis.biz.RedisBatchBiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 支付失败消息队列监听，将队列中取到的值放入阻塞队列里
 *
 */
@Service("investPayErrorService")
@Slf4j
public class InvestPayErrorService {

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
	 * 监听支付失败消息
	 * 
	 * @throws Exception
	 */
	public void ListenInvestPayErrorMessages() {
		log.info("开始监听...");
		for (;;) {
			//  支付失败 - 队列监控的key
			String monitor_key = MqMonitorConst.FF_MQ_MONITOR + MqMonitorConst.MQ_INVEST_MANAGE_FW_PAYERROR+ Thread.currentThread().getId();

			try {
				try {
					Thread.sleep(8);
					cacheMonotorBiz.recordMqMonitorInfo(monitor_key);
				} catch (Exception e) {
					log.error("队列监控记录异常 :" + monitor_key, e);
				}
				String payErrorValue = queueBiz.rightPop(RedisQueueConst.REDIS_QUEUE_FW_INVEST_PAYERROR_LIST, String.class);
				if (null == payErrorValue) {
					continue;
				}
				log.info("支付失败消息队列出队 => {}", payErrorValue);

				log.info("消息备份添加...");
				redisBatchBiz.hset(RedisQueueConst.REDIS_QUEUE_FW_INVEST_PAYERROR_BACKUP_MAP_KEY, payErrorValue, payErrorValue);

				//放入阻塞消息队列
				taskProducer.addRedisRecord(payErrorValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}