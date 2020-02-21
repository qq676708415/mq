package com.hailiang.common.mq.thread;

/**
 * 阻塞消息队列消费端统一接口
 *
 *
 */
public interface IMqConsumer {
	
	/**
	 * 阻塞消息队列出队执行
	 * @param queue
	 */
	public void execute(QbBlockQueue queue);

}
