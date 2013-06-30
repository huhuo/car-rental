package com.huhuo.cmstatis.turnover;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.huhuo.carservicecore.csm.order.ModelOrder;
import com.huhuo.integration.base.IBaseExtenseServ;


public interface IServTurnover extends IBaseExtenseServ<ModelOrder> {
	
	/**
	 * get summary of total fee between time begin and end,
	 * with result being grouped by fields storeId
	 * @param begin
	 * @param end
	 * @return
	 */
	List<Map<String, Object>> getSumTotalFeeByStore(Date begin, Date end);
	/**
	 * group by car type, return result in format --> [{name: '奥迪KK', totalFee: 39062.90833558981}, {name: '兰博基尼avatar', totalFee: 44142.1894699048}]
	 * @see #getSumTotalFeeByStore(Date, Date)
	 */
	List<Map<String, Object>> getSumTotalFeeByCarType(Date begin, Date end);
	
	/**
	 * get summary of total fee between time begin and end,
	 * and result will being grouped by fields storeId and createTime in simple format,
	 * for example 2013-06-20, 2013-06-21, 2013-06-23
	 * @param begin begin time, including
	 * @param end end time, including
	 * @return
	 */
	List<Map<String, Object>> getSumTotalFeeByDate(Date begin, Date end);
	/**
	 * week in format such as 2013第01周, 2013第02周, 2013第03周
	 * @see #getSumTotalFeeByDate(Date, Date)
	 */
	List<Map<String, Object>> getSumTotalFeeByWeek(Date begin, Date end);
	/**
	 * week in format such as 2013-01, 2013-02, 2013-03
	 * @see #getSumTotalFeeByDate(Date, Date)
	 */
	List<Map<String, Object>> getSumTotalFeeByMonth(Date begin, Date end);
	
}
