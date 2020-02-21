package com.hailiang.common.accountbal.service;


import com.alibaba.fastjson.JSON;
import com.hailiang.common.accountbal.util.BeanFactoryUtil;
import com.hailiang.common.consts.RedisQueueConst;
import com.hailiang.common.mq.thread.IMqConsumer;
import com.hailiang.common.mq.thread.QbBlockQueue;
import com.hailiang.common.redis.biz.QueueBiz;
import com.hailiang.common.redis.biz.RedisBatchBiz;
import lombok.extern.slf4j.Slf4j;

/**
 * 余额记账 - 阻塞消息队列消费
 *
 */
@Slf4j
public class AccountingBalMqImpl implements IMqConsumer {

	// 注入消息队列备份处理biz
	private RedisBatchBiz redisBatchBiz;

	// 注入redis消息队列biz
	private QueueBiz queueBiz;

	public void execute(QbBlockQueue queue) {
		try{
			redisBatchBiz = BeanFactoryUtil.getContext().getBean(RedisBatchBiz.class);
			queueBiz = BeanFactoryUtil.getContext().getBean(QueueBiz.class);
			String advanceValue = queue.ConverToObject(String.class);
			log.info("基础账户余额记账 消息 消费 {}", advanceValue);
			log.info("记账校验- 记账前根据用户号、交易uuid、交易类型判断该流水信息已记账");
			log.info("操作类型：invest投资、recharge充值");
			log.info("查询查询账户信息...");
			log.info("判断充值或买入记账");
			log.info("1、是否余额买入：否");
			log.info("充值流水入库处理...");
			log.info("记账更新账户余额");
			log.info("充值推送站内信...");


			log.info("判断投资是否使用奖励金：是...");
			log.info("推送奖金账户消息队列记账...");
			queueBiz.leftPush(RedisQueueConst.REDIS_QUEUE_ACCOUNT_REWARD_LIST, advanceValue+"奖励金使用记账");
			log.info("更新redis中的投资记录信息...");

			log.info("推送工单队列消息：更新工单信息 ");
			queueBiz.leftPush(RedisQueueConst.REDIS_QUEUE_FW_INVEST_WORKORDER_LIST, advanceValue+"投资工单类型");

			log.info("买入使用红包转入流水入库处理...");
			log.info("解冻买入使用余额金额...");

			log.info("推送定期产品买入记账队列消息...");
			queueBiz.leftPush(RedisQueueConst.REDIS_QUEUE_ACCOUNT_FW_LIST, advanceValue + "定期产品账户记账");

			log.info("2、定期产品赎回记账。。");
			log.info("赎回到余额账户流水入库");
			log.info("赎回本金， 余额入账。。更新DB。");
			log.info("利息转到余额账户流水入库");
			log.info("赎回利息， 余额入账。。更新DB。");

			log.info("4、提现记账。。");
			log.info("提现流水入库。");
			log.info("赎回记账更新DB。");


			log.info("消息备份移除...");
			redisBatchBiz.hdel(RedisQueueConst.REDIS_QUEUE_ACCOUNT_BAL_BACKUP_MAP_KEY, advanceValue);

		}catch (Exception e) {

		}
	}
}