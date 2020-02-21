package com.hailiang.common.borrow.service;


import com.hailiang.common.borrow.util.BeanFactoryUtil;
import com.hailiang.common.consts.RedisQueueConst;
import com.hailiang.common.mq.thread.IMqConsumer;
import com.hailiang.common.mq.thread.QbBlockQueue;
import com.hailiang.common.redis.biz.QueueBiz;
import com.hailiang.common.redis.biz.RedisBatchBiz;
import lombok.extern.slf4j.Slf4j;

/**
 * 债券匹配 - 阻塞消息队列消费
 *
 */
@Slf4j
public class BorrowManageMqImpl implements IMqConsumer {

	// 注入消息队列备份处理biz
	private RedisBatchBiz redisBatchBiz;

	// 注入redis消息队列biz
	private QueueBiz queueBiz;

	public void execute(QbBlockQueue queue) {
		try{
			redisBatchBiz = BeanFactoryUtil.getContext().getBean(RedisBatchBiz.class);
			queueBiz = BeanFactoryUtil.getContext().getBean(QueueBiz.class);
			String advanceValue = queue.ConverToObject(String.class);
			log.info("获取债券池中债券列表，按照审核时间升序排列");
			log.info("1、投资匹配 - 债权 -for 循环");
			log.info("债券包含借贷金额，以及以融金额，债券余额 = 借贷金额 - 以融金额");
			log.info("债券余额 > 投资金额，完全匹配，更新债券池");
			log.info("债券余额 <= 投资金额，直接匹配，债券做满标处理，更新债券池，剩余投资金额进入下一次循环再匹");
			log.info("推送债匹结果队列。。。");
			log.info("2、债券到期复匹  - 匹配 ");
			log.info("3、默认债权重匹  - ");


			log.info("赎回释放");
			log.info("到期释放");
			log.info("默认债权释放");




			log.info("消息备份移除...");
			redisBatchBiz.hdel(RedisQueueConst.REDIS_QUEUE_FW_INVEST_RECORD_MANAGE_BACKUP_MAP_KEY, advanceValue);

		}catch (Exception e) {

		}
	}
}