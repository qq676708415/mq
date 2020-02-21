package com.hailiang.common.workorder.service;


import com.hailiang.common.consts.RedisQueueConst;
import com.hailiang.common.mq.thread.IMqConsumer;
import com.hailiang.common.mq.thread.QbBlockQueue;
import com.hailiang.common.redis.biz.QueueBiz;
import com.hailiang.common.redis.biz.RedisBatchBiz;
import com.hailiang.common.workorder.util.BeanFactoryUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 买入 - 预处理消息 - 阻塞消息队列消费
 *
 */
@Slf4j
public class WorkOrderMqImpl implements IMqConsumer {

	// 注入消息队列备份处理biz
	private RedisBatchBiz redisBatchBiz;

	// 注入redis消息队列biz
	private QueueBiz queueBiz;

	public void execute(QbBlockQueue queue) {
		try{
			redisBatchBiz = BeanFactoryUtil.getContext().getBean(RedisBatchBiz.class);
			queueBiz = BeanFactoryUtil.getContext().getBean(QueueBiz.class);
			String payErrorValue = queue.ConverToObject(String.class);
			log.info("判断此工单是否已经被记录，如果是则更新，否则插入");
			log.info("消息备份移除...");
			redisBatchBiz.hdel(RedisQueueConst.REDIS_QUEUE_FW_INVEST_PAYERROR_BACKUP_MAP_KEY, payErrorValue);

		}catch (Exception e) {

		}
	}
}