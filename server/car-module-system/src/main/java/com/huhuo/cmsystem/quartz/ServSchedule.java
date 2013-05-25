package com.huhuo.cmsystem.quartz;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
@SuppressWarnings("unchecked")
@Component("cmsystemServSchedule")
public class ServSchedule<T> implements IServSchedule<T> {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource(name = "cmsystemScheduler")
	private TaskScheduler taskScheduler;
	
	@Override
	public ScheduledFuture<T> schedule(Runnable task, Trigger trigger) {
		// TODO Auto-generated method stub
		logger.info("schedule task={} with trigger={}", task, format(trigger));
		return taskScheduler.schedule(task, trigger);
	}

	@Override
	public ScheduledFuture<T> schedule(Runnable task, Date startTime) {
		// TODO Auto-generated method stub
		logger.info("schedule task={} at startTime={}", task, format(startTime));
		return taskScheduler.schedule(task, startTime);
	}

	@Override
	public ScheduledFuture<T> scheduleAtFixedRate(Runnable task,
			Date startTime, long period) {
		// TODO Auto-generated method stub
		logger.info("scheduleAtFixedRate task={} at startTime={} with period={}", task, format(startTime), period);
		return taskScheduler.scheduleAtFixedRate(task, startTime, period);
	}

	@Override
	public ScheduledFuture<T> scheduleAtFixedRate(Runnable task, long period) {
		// TODO Auto-generated method stub
		logger.info("scheduleAtFixedRate task={} immediately with period={}", task, period);
		return taskScheduler.scheduleAtFixedRate(task, period);
	}

	@Override
	public ScheduledFuture<T> scheduleWithFixedDelay(Runnable task,
			Date startTime, long delay) {
		// TODO Auto-generated method stub
		logger.info("scheduleWithFixedDelay task={} at startTime={} with delay={}", task, format(startTime), delay);
		return taskScheduler.scheduleWithFixedDelay(task, startTime, delay);
	}

	@Override
	public ScheduledFuture<T> scheduleWithFixedDelay(Runnable task, long delay) {
		// TODO Auto-generated method stub
		logger.info("scheduleWithFixedDelay task={} immediately with delay={}", task, delay);
		return taskScheduler.scheduleWithFixedDelay(task, delay);
	}

	/**
	 * format obj to standard JSON string
	 * @param obj object to be formatted
	 * @param dateFormat date format to use
	 * @param prettyFormat whether output with pretty format
	 * @return
	 */
	private String format(Object obj, String dateFormat, Boolean prettyFormat) {
		String ret = null;
		if(prettyFormat) {
			ret = JSON.toJSONStringWithDateFormat(obj, dateFormat, SerializerFeature.PrettyFormat);
		} else {
			ret = JSON.toJSONStringWithDateFormat(obj, dateFormat);
		}
		return ret;
	}
	
	private String format(Object obj, Boolean prettyFormat) {
		return format(obj, "yyyy-MM-dd HH:mm:ss", prettyFormat);
	}
	
	private String format(Object obj) {
		return format(obj, false);
	}
	
}
