package com.hailiang.common.investrecord.service;


import com.hailiang.common.consts.RedisQueueConst;
import com.hailiang.common.investrecord.util.BeanFactoryUtil;
import com.hailiang.common.mq.thread.IMqConsumer;
import com.hailiang.common.mq.thread.QbBlockQueue;
import com.hailiang.common.redis.biz.QueueBiz;
import com.hailiang.common.redis.biz.RedisBatchBiz;
import lombok.extern.slf4j.Slf4j;

/**
 * 投资记录 - 阻塞消息队列消费
 *
 */
@Slf4j
public class InvestRecordMqImpl implements IMqConsumer {

	// 注入消息队列备份处理biz
	private RedisBatchBiz redisBatchBiz;

	// 注入redis消息队列biz
	private QueueBiz queueBiz;

	public void execute(QbBlockQueue queue) {
		try{
			redisBatchBiz = BeanFactoryUtil.getContext().getBean(RedisBatchBiz.class);
			queueBiz = BeanFactoryUtil.getContext().getBean(QueueBiz.class);
			String advanceValue = queue.ConverToObject(String.class);
			log.info("1、新增 投资记录 预处理消息 消费 {}", advanceValue);
			log.info("有加息票，推送活动加息收益明细消息队列");
			// 队列省略
			log.info("推送生成还款计划消息队列");

			log.info("推送债权匹配队列消息");
			log.info("推送投资成功 - 邀请收益 - 活动部分");
			log.info("推送投资成功 - 成为核心会员 - 活动部分");



			log.info("消息备份移除...");
			redisBatchBiz.hdel(RedisQueueConst.REDIS_QUEUE_FW_INVEST_RECORD_MANAGE_BACKUP_MAP_KEY, advanceValue);

		}catch (Exception e) {

		}
	}
}