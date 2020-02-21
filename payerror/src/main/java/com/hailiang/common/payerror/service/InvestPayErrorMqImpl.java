package com.hailiang.common.payerror.service;


import com.hailiang.common.consts.RedisQueueConst;
import com.hailiang.common.mq.thread.IMqConsumer;
import com.hailiang.common.mq.thread.QbBlockQueue;

import com.hailiang.common.payerror.util.BeanFactoryUtil;
import com.hailiang.common.redis.biz.QueueBiz;
import com.hailiang.common.redis.biz.RedisBatchBiz;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * 买入 - 预处理消息 - 阻塞消息队列消费
 *
 */
@Slf4j
public class InvestPayErrorMqImpl implements IMqConsumer {

	// 注入消息队列备份处理biz
	private RedisBatchBiz redisBatchBiz;

	// 注入redis消息队列biz
	private QueueBiz queueBiz;

	public void execute(QbBlockQueue queue) {
		try{
			redisBatchBiz = BeanFactoryUtil.getContext().getBean(RedisBatchBiz.class);
			queueBiz = BeanFactoryUtil.getContext().getBean(QueueBiz.class);
			String payErrorValue = queue.ConverToObject(String.class);
			log.info("区分投资失败处理，充值失败处理");
			log.info("投资失败处理。。。");
			log.info("解冻产品投资金额。。");
			log.info("回退 - 更新产品日剩余可售额");
			log.info("解冻投资使用余额账户金额");
			log.info("解冻投资使用奖金");
			log.info("回退日投资金额");
			log.info("回退日投资次数");
			log.info("解冻投资使用的活动加息");
			log.info("解冻投资使用的活动红包");
			log.info("清除用户投资锁");
			log.info("支付失败 - 推送工单消息队列：更新工单表信息");
			queueBiz.leftPush(RedisQueueConst.REDIS_QUEUE_FW_INVEST_WORKORDER_LIST, payErrorValue + "支付失败，更新工单");

			log.info("投资失败理结束===============...");
			log.info("消息备份移除...");
			redisBatchBiz.hdel(RedisQueueConst.REDIS_QUEUE_FW_INVEST_PAYERROR_BACKUP_MAP_KEY, payErrorValue);

		}catch (Exception e) {

		}
	}
}