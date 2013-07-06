package com.huhuo.cmstatis.turnover;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huhuo.carservicecore.csm.order.IDaoOrder;
import com.huhuo.carservicecore.csm.order.ModelOrder;
import com.huhuo.carservicecore.cust.store.ModelStore;
import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.cmsystem.store.IServStore;
import com.huhuo.integration.base.IBaseExtenseDao;
import com.huhuo.integration.exception.ServException;
import com.huhuo.integration.util.BeanUtils;
import com.huhuo.integration.util.TimeUtils;

@Service("cmstatisServTurnover")
@Transactional
public class ServTurnover extends GenericBaseExtenseServ<ModelOrder> implements IServTurnover {

	@Resource(name = "carservicecoreDaoOrder")
	private IDaoOrder iDaoOrder;
	@Resource(name = "cmsystemServStore")
	private IServStore iServStore;
	
	@Override
	public IBaseExtenseDao<ModelOrder> getDao() {
		return iDaoOrder;
	}

	@Override
	public void inject(ModelOrder t) {
		ModelStore store = iServStore.find(t.getStoreId());
		t.setStore(store);
	}


	@Override
	public List<Map<String, Object>> getSumTotalFeeByStore(Date begin, Date end) {
		ajustage(begin, end);
		String sql = "SELECT storeId, SUM(totalFee) AS totalFee FROM csm_order WHERE createTime BETWEEN ? AND ? GROUP BY storeId ASC";
		List<Map<String,Object>> list = getDao().queryForMapList(sql, begin, end);
		injectStore(list);
		return list;
	}

	@Override
	public List<Map<String, Object>> getSumTotalFeeByCarType(Date begin,
			Date end) {
		ajustage(begin, end);
		String sql = "SELECT custct.name, SUM(totalFee) AS totalFee FROM csm_order csmo LEFT JOIN cust_car custc ON csmo.carId=custc.id LEFT JOIN cust_car_type custct ON custc.carTypeId=custct.id WHERE csmo.createTime BETWEEN ? AND ? GROUP BY custct.id ASC";
		List<Map<String,Object>> list = getDao().queryForMapList(sql, begin, end);
		return list;
	}
	
	/**
	 * inject external object
	 * @param list
	 */
	private void injectStore(List<Map<String, Object>> list) {
		try {
			Set<Long> ids = new HashSet<Long>();
			for(Map<String,Object> map : list) {
				ids.add((Long) map.get("storeId"));
			}
			if(ids.isEmpty()) {
				logger.info("storeIds is empty --> {}", ids);
				return;
			}
			List<ModelStore> stores = iServStore.findByIds(new ArrayList<Long>(ids));
			for(Map<String,Object> map : list) {
				for(ModelStore store : stores) {
					if(store.getId().equals(map.get("storeId"))) {
						ModelStore dest = new ModelStore();
						BeanUtils.copyProperties(dest, store);
						map.put("store", dest);
					}
				}
			}
		} catch (IllegalAccessException e) {
			throw new ServException(e);
		} catch (InvocationTargetException e) {
			throw new ServException(e);
		}
	}
	/**
	 * justify begin time and end time
	 * @param begin
	 * @param end
	 */
	private void ajustage(Date begin, Date end) {
		Date beginConverted = TimeUtils.getDateBeginFor(begin);
		Date endConverted = TimeUtils.getDateEndFor(end);
		logger.info("convert date [begin: {} --> {}; end: {} --> {}]", begin, beginConverted, end, endConverted);
	}
	
	@Override
	public List<Map<String, Object>> getSumTotalFeeByDate(Date begin, Date end) {
		ajustage(begin, end);
		String sql = "SELECT storeId, DATE_FORMAT(createTime, '%m/%d') sumTime, SUM(totalFee) AS totalFee FROM csm_order WHERE createTime BETWEEN ? and ? GROUP BY storeId ASC, sumTime ASC";
		List<Map<String,Object>> list = getDao().queryForMapList(sql, begin, end);
		injectStore(list);
		return list;
	}

	@Override
	public List<Map<String, Object>> getSumTotalFeeByWeek(Date begin, Date end) {
		ajustage(begin, end);
		String sql = "SELECT storeId, DATE_FORMAT(createTime, '%y年%u周') sumTime, SUM(totalFee) AS totalFee FROM csm_order WHERE createTime BETWEEN ? and ? GROUP BY storeId ASC, sumTime ASC";
		List<Map<String,Object>> list = getDao().queryForMapList(sql, begin, end);
		injectStore(list);
		return list;
	}

	@Override
	public List<Map<String, Object>> getSumTotalFeeByMonth(Date begin, Date end) {
		ajustage(begin, end);
		String sql = "SELECT storeId, DATE_FORMAT(createTime, '%Y/%m') sumTime, SUM(totalFee) AS totalFee FROM csm_order WHERE createTime BETWEEN ? and ? GROUP BY storeId ASC, sumTime ASC";
		List<Map<String,Object>> list = getDao().queryForMapList(sql, begin, end);
		injectStore(list);
		return list;
	}
	
}
