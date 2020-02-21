package com.hailiang.common.mq.thread;

public abstract class AbstractTaskConsumer
{

	// 定义的线程数
    protected int consumer_thread_number;

    // 每个线程轮询队列的时间间隔
    protected int interval;
    
    //出队实现类 
    protected String thread_queue_out_implclass;

    /**
     * 从队列中拿出一个Task，调用Adapter发送
     */
    public abstract void startConsume();

    /**
     * Spring注入线程数
     * 
     * @param consumer_thread_number
     *            the consumer_thread_number to set
     */
    public void setConsumer_thread_number(int consumer_thread_number)
    {
        this.consumer_thread_number = consumer_thread_number;
    }

    /**
     * Spring注入轮询queue间隔
     * 
     * @param interval
     *            the interval to set
     */
    public void setInterval(int interval)
    {
        this.interval = interval;
    }

	public void setThread_queue_out_implclass(
			String thread_queue_out_implclass) {
		this.thread_queue_out_implclass = thread_queue_out_implclass;
	}
}
