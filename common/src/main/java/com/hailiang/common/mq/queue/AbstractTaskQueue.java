package com.hailiang.common.mq.queue;

import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 所有Queue的父类
 */
public abstract class AbstractTaskQueue extends LinkedBlockingQueue<String>
{

    private static final long serialVersionUID = -3645243155204152472L;    
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(AbstractTaskQueue.class);
    
    @Override
    public void put(String e) {
        try
        {
            super.put(e);
        }
        catch(InterruptedException ie)
        {
            logger.error("InterruptedException while put new task in", ie);
        }
    }

    /**
     * 取队列头部的一个Task对象
     */
    @Override
    public String take() {
        try
        {
            return super.take();
        }
        catch(InterruptedException ie)
        {
            logger.error("InterruptedException while put new task in", ie);
            return null;
        }
    }

    
}