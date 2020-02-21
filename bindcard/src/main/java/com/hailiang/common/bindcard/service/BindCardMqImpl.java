package com.hailiang.common.bindcard.service;


import com.hailiang.common.bindcard.util.BeanFactoryUtil;
import com.hailiang.common.consts.RedisQueueConst;
import com.hailiang.common.mq.thread.IMqConsumer;
import com.hailiang.common.mq.thread.QbBlockQueue;
import com.hailiang.common.redis.biz.QueueBiz;
import com.hailiang.common.redis.biz.RedisBatchBiz;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * 绑卡- 阻塞消息队列消费
 *
 */
@Slf4j
public class BindCardMqImpl implements IMqConsumer {

	// 注入消息队列备份处理biz
	private RedisBatchBiz redisBatchBiz;

	// 注入redis消息队列biz
	private QueueBiz queueBiz;

	public void execute(QbBlockQueue queue) {
		try{
			redisBatchBiz = BeanFactoryUtil.getContext().getBean(RedisBatchBiz.class);
			queueBiz = BeanFactoryUtil.getContext().getBean(QueueBiz.class);
			String bindCardValue = queue.ConverToObject(String.class);
			log.info("绑卡 消费 {}", bindCardValue);
			log.info("绑卡基本信息处理...");
			log.info("更新用户信息表 用户真实姓名、用户证件号码...");
			log.info("更新账户信息表 用户真实姓名、用户证件号码...");
			log.info("更新绑卡信息前 - 把历史有效卡都置为无效...");


			log.info("消息备份移除...");
			redisBatchBiz.hdel(RedisQueueConst.REDIS_QUEUE_FW_INVEST_BINDCARD_BACKUP_MAP_KEY, bindCardValue);

		}catch (Exception e) {

		}
	}
}