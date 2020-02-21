package com.hailiang.common.accountproduct.service;


import com.alibaba.fastjson.JSON;
import com.hailiang.common.accountproduct.util.BeanFactoryUtil;
import com.hailiang.common.consts.RedisQueueConst;
import com.hailiang.common.mq.thread.IMqConsumer;
import com.hailiang.common.mq.thread.QbBlockQueue;
import com.hailiang.common.redis.biz.QueueBiz;
import com.hailiang.common.redis.biz.RedisBatchBiz;
import lombok.extern.slf4j.Slf4j;

/**
 * 记账管理 - 产品账户记账 - 阻塞消息队列消费
 *
 */
@Slf4j
public class AccountingProductMqImpl implements IMqConsumer {

	// 注入消息队列备份处理biz
	private RedisBatchBiz redisBatchBiz;

	// 注入redis消息队列biz
	private QueueBiz queueBiz;

	public void execute(QbBlockQueue queue) {
		try{
			redisBatchBiz = BeanFactoryUtil.getContext().getBean(RedisBatchBiz.class);
			queueBiz = BeanFactoryUtil.getContext().getBean(QueueBiz.class);
			String advanceValue = queue.ConverToObject(String.class);
			log.info("1、产品买入记账 预处理消息 消费 {}", advanceValue);
			log.info("买入流水入库处理...");
			log.info("买入记账更新DB...");
			log.info("更新投资状态...");
			log.info("清除用户投资锁。。。");
			log.info("更新redis中的投资记录信息...");
			log.info("产品记账成功 - 推送工单消息队列：更新工单表信息...");
			queueBiz.leftPush(RedisQueueConst.REDIS_QUEUE_FW_INVEST_WORKORDER_LIST, advanceValue + "产品记账成功");

			log.info("推送投资成功消息队列消息...");
			queueBiz.leftPush(RedisQueueConst.REDIS_QUEUE_FW_INVEST_RECORD_MANAGE_LIST, advanceValue + "开始生成投资记录");

			log.info("投资使用加息劵处理...");
			log.info("更新户加息券，红包，为已使用...");

			log.info("2、产品赎回记账 预处理消息 消费 {}", advanceValue);
			log.info("赎回记录流水");
			log.info("赎回消息到余额记账队列");

//			queueBiz.leftPush(RedisQueueConst.REDIS_QUEUE_ACCOUNT_BAL_LIST, advanceValue + "产品赎回，进入账户余额记账队列");
			log.info("赎回发送站内信");

			log.info("消息备份移除...");
			redisBatchBiz.hdel(RedisQueueConst.REDIS_QUEUE_ACCOUNT_FW_BACKUP_MAP_KEY, advanceValue);

		}catch (Exception e) {

		}
	}
}