package com.hailiang.common.backsearch.service;


import com.alibaba.fastjson.JSON;
import com.hailiang.common.backsearch.util.BeanFactoryUtil;
import com.hailiang.common.consts.RedisQueueConst;
import com.hailiang.common.mq.thread.IMqConsumer;
import com.hailiang.common.mq.thread.QbBlockQueue;
import com.hailiang.common.redis.biz.QueueBiz;
import com.hailiang.common.redis.biz.RedisBatchBiz;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * 买入 - 预处理消息 - 阻塞消息队列消费
 *
 */
@Slf4j
public class InvestBackSearchMqImpl implements IMqConsumer {

	// 注入消息队列备份处理biz
	private RedisBatchBiz redisBatchBiz;

	// 注入redis消息队列biz
	private QueueBiz queueBiz;

	public void execute(QbBlockQueue queue) {
		try{
			redisBatchBiz = BeanFactoryUtil.getContext().getBean(RedisBatchBiz.class);
			queueBiz = BeanFactoryUtil.getContext().getBean(QueueBiz.class);
			String advanceValue = queue.ConverToObject(String.class);
			log.info("订单支付状态反查 消息队列 消费 {}", advanceValue);
			log.info("投资金额 - 校验");
			log.info("投资信息变更前先查询下，如没有直接break返回投资无效的信息");
			// 余额买入 直接走记账队列，更新工单
			log.info("1、判断是否为余额买入：是");
			log.info("校验 - 根据订单号检查数据库是否已经记账");
			log.info("推送余额记账队列。。。");
			queueBiz.leftPush(RedisQueueConst.REDIS_QUEUE_ACCOUNT_BAL_LIST, advanceValue + "余额记账");
			log.info("更新债权池金额。。。");
			log.info("解冻产品投资金额。。。");
			log.info("支付成功待记账 - 推送工单消息队列：更新工单表信息 ...");
			queueBiz.leftPush(RedisQueueConst.REDIS_QUEUE_FW_INVEST_WORKORDER_LIST, advanceValue + "更新工单信息");
			// 充值买入，需反查后，走记账，更新工单
			log.info("2、判断是否为余额买入：否 (需查询充值交易是否成功)");
			log.info("调用支付查询接口。。。");
			log.info("第一次没有查到则，创建Timer，定时调度任务，轮训调用支付查询接口....轮训10次");
			// 模拟反查结果
			boolean flag = new Random().nextBoolean();
			String str = "";
			if (!flag) {
				str = "支付失败";
				log.info("根据返回码封装展示给用户的返回信息");
				log.info("反查失败，推送支付失败队列。。。");
				log.info("更新投资状态为支付失败...");
				queueBiz.leftPush(RedisQueueConst.REDIS_QUEUE_FW_INVEST_PAYERROR_LIST, advanceValue + "支付失败");

			} else {
				str = "支付成功";
				log.info("反查成功，推送余额记账队列。。。");
				queueBiz.leftPush(RedisQueueConst.REDIS_QUEUE_ACCOUNT_BAL_LIST, advanceValue + "余额记账");
				log.info("更新债权池金额。。。");
				log.info("解冻产品投资金额。。。");
				log.info("支付成功待记账 - 推送工单消息队列：更新工单表信息 ...");
				queueBiz.leftPush(RedisQueueConst.REDIS_QUEUE_FW_INVEST_WORKORDER_LIST, advanceValue + "更新工单信息");
			}
			// 推送绑卡结果
			log.info("绑卡结果，推送绑卡消息队列 ...");
			queueBiz.leftPush(RedisQueueConst.REDIS_QUEUE_INVEST_BINDCARD_LIST, advanceValue + str);


			log.info("消息备份移除...");
			redisBatchBiz.hdel(RedisQueueConst.REDIS_QUEUE_FW_INVEST_BACKSEARCH_BACKUP_MAP_KEY, advanceValue);

		}catch (Exception e) {

		}
	}
}