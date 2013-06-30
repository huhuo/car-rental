package com.huhuo.cmstatis.car;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.huhuo.carservicecore.cust.car.ModelCar;
import com.huhuo.integration.base.IBaseExtenseServ;


public interface IServCar extends IBaseExtenseServ<ModelCar> {
	
	/**
	 * get trend by month in format such as 2013-01, 2013-02, 2013-03, and group by car type
	 * @param begin
	 * @param end
	 * @return
	 */
	List<Map<String, Object>> getCountTrendMonthCartype(Date begin, Date end);
	/**
	 * group by store
	 * @see #getCountTrendMonthCartype(Date, Date)
	 */
	List<Map<String, Object>> getCountTrendMonthStore(Date begin, Date end);
	
	
}
