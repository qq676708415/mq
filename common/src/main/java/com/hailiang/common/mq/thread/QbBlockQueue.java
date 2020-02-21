package com.hailiang.common.mq.thread;

import com.hailiang.common.mq.queue.AbstractTaskQueue;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 自定义 block
 *
 *
 */
public class QbBlockQueue {
	
	private static Logger log = LoggerFactory.getLogger(QbBlockQueue.class);
		
    private static ObjectMapper mapper = new ObjectMapper();
    
    /**
     * 阻塞消息队列
     */
    private AbstractTaskQueue queue;
    
	public QbBlockQueue(AbstractTaskQueue queue){
    	this.queue = queue;
    }
	
	/**
	 * 消息jsonstring转化为object
	 * @param classPathStr  IMqConsumer实现类完整路径
	 * @return  mqInfoObject  mq消息对象
	 */
	@SuppressWarnings("unchecked")
	public  <T> T ConverToObject(String classPathStr){
		
		String queueStr = null;
		Object mqInfoObject = null;
		try {
			mqInfoObject = Class.forName(classPathStr).newInstance();
		    queueStr =  queue.take();
			mqInfoObject = mapper.readValue(queueStr,  mqInfoObject.getClass());
		} catch (Exception e) {
			log.error("mq下游阻塞消息队列出队消息类型转换异常："+classPathStr, e);
		} 
		return (T) mqInfoObject;
	}
	
	/**
	 * 消息jsonstring转化为object
	 * @param clazz  IMqConsumer实现类完整路径
	 * @return mq消息对象
	 */
	public <T> T ConverToObject(Class<T> clazz){
		
		String queueStr = null;
		try {
		    queueStr =  queue.take();
		    if (clazz == String.class) {
		    	return (T) queueStr;
			}
			return mapper.readValue(queueStr,  clazz);
		} catch (Exception e) {
			log.error("mq下游阻塞消息队列出队数据类型转换异常，出队数据:{}， 转换类型：{}", queueStr, clazz.getName(), e);
		} 
		return null;
	}
	
}