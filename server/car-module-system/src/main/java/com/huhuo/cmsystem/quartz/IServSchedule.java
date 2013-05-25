package com.huhuo.cmsystem.quartz;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;

/**
 * schedule tool bean for schedule temporary task, 
 * not tasks with a specified <a href="http://www.manpagez.com/man/5/crontab/">Crontab pattern</a>
 * @author root
 */
public interface IServSchedule<T> {
	
	/**
	 * Schedule the given {@link Runnable}, invoking it whenever the trigger indicates a next execution time.
	 * Execution will end once the scheduler shuts down or the returned {@link ScheduledFuture} gets cancelled.
	 * @see {@link TaskScheduler#schedule(Runnable, Trigger)}
	 */
	ScheduledFuture<T> schedule(Runnable task, Trigger trigger);
	/**
	 * Schedule the given {@link Runnable}, invoking it at the specified execution time.
	 * Execution will end once the scheduler shuts down or the returned {@link ScheduledFuture} gets cancelled.
	 * @see {@link TaskScheduler#schedule(Runnable, Date)}
	 */
	ScheduledFuture<T> schedule(Runnable task, Date startTime);
	/**
	 * Schedule the given {@link Runnable}, starting as soon as possible and invoking it with the given period. 
	 * Execution will end once the scheduler shuts down or the returned {@link ScheduledFuture} gets cancelled.
	 * @see {@link TaskScheduler#scheduleAtFixedRate(Runnable, Date, long)}
	 */
	ScheduledFuture<T> scheduleAtFixedRate(Runnable task, Date startTime, long period);
	/**
	 * Schedule the given {@link Runnable}, invoking it at the specified execution time and subsequently with the given period. 
	 * Execution will end once the scheduler shuts down or the returned {@link ScheduledFuture} gets cancelled.
	 * @see {@link TaskScheduler#scheduleAtFixedRate(Runnable, long)}
	 */
	ScheduledFuture<T> scheduleAtFixedRate(Runnable task, long period);
	/**
	 * Schedule the given {@link Runnable}, 
	 * invoking it at the specified execution time and subsequently with the given delay between the completion of one execution and the start of the next. 
	 * Execution will end once the scheduler shuts down or the returned {@link ScheduledFuture} gets cancelled.
	 * @see {@link TaskScheduler#scheduleWithFixedDelay(Runnable, Date, long)}
	 */
	ScheduledFuture<T> scheduleWithFixedDelay(Runnable task, Date startTime, long delay);
	/**
	 * Schedule the given {@link Runnable}, starting as soon as possible and invoking it with the given delay between the completion of one execution and the start of the next. 
	 * Execution will end once the scheduler shuts down or the returned {@link ScheduledFuture} gets cancelled.
	 * @see {@link TaskScheduler#scheduleWithFixedDelay(Runnable, long)}
	 */
	ScheduledFuture<T> scheduleWithFixedDelay(Runnable task, long delay);
}
