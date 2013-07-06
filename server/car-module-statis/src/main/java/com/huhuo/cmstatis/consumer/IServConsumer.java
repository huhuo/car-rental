package com.huhuo.cmstatis.consumer;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
import com.huhuo.integration.base.IBaseExtenseServ;


public interface IServConsumer extends IBaseExtenseServ<ModelConsumer> {
	
	/**
	 * get total amount between time begin and end, and result will group by date in simple format,
	 * for example 2013-06-20, 2013-06-21, 2013-06-23
	 * @param begin begin time, including
	 * @param end end time, including
	 * @return
	 */
	List<Map<String, Object>> getAmountByDate(Date begin, Date end);

	List<Map<String, Object>> getAmountByWeek(Date begin, Date end);

	List<Map<String, Object>> getAmountByMonth(Date begin, Date end);

	List<Map<String, Object>> getAmountByDay(Date begin, Date end);
	
	
}
