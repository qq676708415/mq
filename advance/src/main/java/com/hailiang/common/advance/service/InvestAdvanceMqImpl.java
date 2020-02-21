package com.hailiang.common.advance.service;


import com.alibaba.fastjson.JSON;
import com.hailiang.common.advance.util.BeanFactoryUtil;
import com.hailiang.common.consts.RedisQueueConst;
import com.hailiang.common.mq.thread.QbBlockQueue;
import com.hailiang.common.mq.thread.IMqConsumer;
import com.hailiang.common.redis.biz.QueueBiz;
import com.hailiang.common.redis.biz.RedisBatchBiz;
import lombok.extern.slf4j.Slf4j;

/**
 * 买入 - 预处理消息 - 阻塞消息队列消费
 *
 */
@Slf4j
public class InvestAdvanceMqImpl implements IMqConsumer {

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
			log.info("投资处理开始===============...");
			log.info("查询用户权益信息..., 没有则投资失败返回。。。");
			log.info("校验产品开关...");
			log.info("校验用户投资金额，最低，最高，日限总额，日限次数...");
			log.info("校验产品存量余额...");
			log.info("校验用户投资锁...");
			log.info("校验用户加息券，红包...");
			log.info("校验分布式缓存中，更新该笔投资所处状态，事务控制");
			log.info("投资预处理结束===============...");
			log.info("1、余额买入，直接推送至投资支付队列...");
			queueBiz.leftPush(RedisQueueConst.REDIS_QUEUE_FW_INVEST_PAY_LIST, advanceValue + "待支付");
			log.info("2、充值买入，计算需要充值金额...");
			log.info("注意：单充值，与充值买入都是走的充值接口，将通过三方支付将用户的钱打到p2p公司在的第三方支付平台的账户中");

			log.info("第一次未绑卡，走银行卡签约接口。。。");
			log.info("已经绑卡，走银行卡鉴权接口。。。");
			log.info("保存卡信息到用户，初始化绑卡状态，推送至绑卡队列。。。等完成支付后再次修改绑卡状态为成功。。。");

			queueBiz.leftPush(RedisQueueConst.REDIS_QUEUE_INVEST_BINDCARD_LIST, advanceValue + "绑卡信息初始化");
			log.info("客户端等待：三方支付发送支付确认验证码，需要要用户端数据，才将投资信息推送到支付队列中");

			log.info("消息备份移除...");
			redisBatchBiz.hdel(RedisQueueConst.REDIS_QUEUE_FW_INVEST_ADVANCE_BACKUP_MAP_KEY, advanceValue);

		}catch (Exception e) {

		}
	}
}