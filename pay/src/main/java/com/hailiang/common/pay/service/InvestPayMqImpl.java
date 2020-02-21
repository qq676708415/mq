package com.hailiang.common.pay.service;


import com.alibaba.fastjson.JSON;
import com.hailiang.common.consts.RedisQueueConst;
import com.hailiang.common.mq.thread.IMqConsumer;
import com.hailiang.common.mq.thread.QbBlockQueue;
import com.hailiang.common.pay.util.BeanFactoryUtil;
import com.hailiang.common.redis.biz.QueueBiz;
import com.hailiang.common.redis.biz.RedisBatchBiz;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * 买入 - 预处理消息 - 阻塞消息队列消费
 *
 */
@Slf4j
public class InvestPayMqImpl implements IMqConsumer {

	// 注入消息队列备份处理biz
	private RedisBatchBiz redisBatchBiz;

	// 注入redis消息队列biz
	private QueueBiz queueBiz;

	public void execute(QbBlockQueue queue) {
		try{
			redisBatchBiz = BeanFactoryUtil.getContext().getBean(RedisBatchBiz.class);
			queueBiz = BeanFactoryUtil.getContext().getBean(QueueBiz.class);
			String advanceValue = queue.ConverToObject(String.class);
			log.info("买入 预处理消息 消费 {}", advanceValue);
			log.info("校验用户信息...");
			log.info("校验当前投资信息的进度...");
			log.info("区分投资支付，充值支付....");
			log.info("投资处理开始===============...");
			log.info("1、投资支付走投资处理逻辑。。。");
			log.info("校验产品开关...");
			log.info("校验投资金额大于0...");
			log.info("校验银联短信验证码，非余额支付...");
			log.info("校验用户单日买入金额...");
			log.info("校验用户投资锁...");
			log.info("校验分布式缓存中，更新该笔投资所处状态，事务控制");
			log.info("校验用户加息券，红包...");
			log.info("校验产品存量余额...");
			log.info("更新产品日剩余可售额");
			log.info("冻结产品投资金额。。。");
			log.info("冻结订单中使用的账户余额...");
			log.info("校验使用的奖励金是否够用。。。");
			log.info("否则，解冻加息券，红包，回退产品可售额度，解冻账户冻结的部分余额，解冻投资金额，清除用户锁，缓存当前支付状态");
			log.info("工单状态校验。。。。");
			log.info("调用支付确认接口前异步存储工单信息....");
			queueBiz.leftPush(RedisQueueConst.REDIS_QUEUE_FW_INVEST_WORKORDER_LIST, advanceValue + "初始化工单");
			log.info("判断是否为余额买入: 否...");
			log.info("确认支付...");
			boolean flag = new Random().nextBoolean();
			if (!flag) {
				log.info("失败，推送支付失败队列。。。");
				queueBiz.leftPush(RedisQueueConst.REDIS_QUEUE_FW_INVEST_PAYERROR_LIST, advanceValue + "支付失败");
			} else {
				log.info("成功，推送支付反查队列。。。");
				queueBiz.leftPush(RedisQueueConst.REDIS_QUEUE_FW_INVEST_BACKSEARCH_LIST, advanceValue + "进入反查队列");
			}


			log.info("投资预处理结束===============...");
			log.info("消息备份移除...");
			redisBatchBiz.hdel(RedisQueueConst.REDIS_QUEUE_FW_INVEST_ADVANCE_BACKUP_MAP_KEY, advanceValue);

		}catch (Exception e) {

		}
	}
}