package com.hailiang.common.accountreward.service;


import com.alibaba.fastjson.JSON;
import com.hailiang.common.accountreward.util.BeanFactoryUtil;
import com.hailiang.common.consts.RedisQueueConst;
import com.hailiang.common.mq.thread.IMqConsumer;
import com.hailiang.common.mq.thread.QbBlockQueue;
import com.hailiang.common.redis.biz.QueueBiz;
import com.hailiang.common.redis.biz.RedisBatchBiz;
import lombok.extern.slf4j.Slf4j;

/**
 * 记账管理 - 奖励金账户记账- 阻塞消息队列消费
 *
 */
@Slf4j
public class AccountingRewardMqImpl implements IMqConsumer {

	// 注入消息队列备份处理biz
	private RedisBatchBiz redisBatchBiz;

	// 注入redis消息队列biz
	private QueueBiz queueBiz;

	public void execute(QbBlockQueue queue) {
		try{
			redisBatchBiz = BeanFactoryUtil.getContext().getBean(RedisBatchBiz.class);
			queueBiz = BeanFactoryUtil.getContext().getBean(QueueBiz.class);
			String advanceValue = queue.ConverToObject(String.class);
			log.info("奖励金记账 消息 消费 {}", advanceValue);
			log.info("1、使用奖金转出流水入库处理...");
			log.info("使用奖金转出记账更新DB...");
			log.info("解冻投资使用奖金...");
			log.info("推送投资买入使用奖金 - 余额转入记账队列消息 ...");
//			queueBiz.leftPush(RedisQueueConst.REDIS_QUEUE_ACCOUNT_BAL_LIST, advanceValue);
			log.info("活动相关。。。。。。");
			log.info("2、邀请奖金转入...");
			log.info("奖金账户转入流水入库处理...");
			log.info("邀请奖金转入记账更新DB...");

			log.info("3、送奖励金活动转入...");
			log.info("奖金账户转入流水入库处理...");
			log.info("送奖金转入记账更新DB...");

			log.info("消息备份移除...");
			redisBatchBiz.hdel(RedisQueueConst.REDIS_QUEUE_ACCOUNT_REWARD_BACKUP_MAP_KEY, advanceValue);

		}catch (Exception e) {

		}
	}
}