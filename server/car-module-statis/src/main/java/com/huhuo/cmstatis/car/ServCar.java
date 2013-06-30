package com.huhuo.cmstatis.car;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huhuo.carservicecore.cust.car.IDaoCar;
import com.huhuo.carservicecore.cust.car.IDaoCarType;
import com.huhuo.carservicecore.cust.car.ModelCar;
import com.huhuo.carservicecore.cust.car.ModelCarType;
import com.huhuo.carservicecore.cust.store.ModelStore;
import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.cmsystem.store.IServStore;
import com.huhuo.integration.base.IBaseExtenseDao;
import com.huhuo.integration.exception.ServException;
import com.huhuo.integration.util.BeanUtils;
import com.huhuo.integration.util.TimeUtils;

@Service("cmstatisServCar")
@Transactional
public class ServCar extends GenericBaseExtenseServ<ModelCar> implements IServCar {

	@Resource(name = "carservicecoreDaoCar")
	private IDaoCar idaoCar;
	@Resource(name = "carservicecoreDaoCarType")
	private IDaoCarType iDaoCarType;
	@Resource(name = "cmsystemServStore")
	private IServStore iServStore;
	
	@Override
	public IBaseExtenseDao<ModelCar> getDao() {
		return idaoCar;
	}

	@Override
	public void inject(ModelCar t) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * inject external object
	 * @param list
	 */
	private void injectCartypeAndStore(List<Map<String, Object>> list) {
		try {
			Set<Long> cartypeIds = new HashSet<Long>();
			Set<Long> storeIds = new HashSet<Long>();
			// inject car type and store
			for(Map<String,Object> map : list) {
				if(map.get("carTypeId") != null) {
					cartypeIds.add((Long) map.get("carTypeId"));
				}
				if(map.get("storeId") != null) {
					storeIds.add((Long) map.get("storeId"));
				}
			}
			if(!cartypeIds.isEmpty()) {
				List<ModelCarType> carTypes = iDaoCarType.findByIds(new ArrayList<Long>(cartypeIds));
				for(Map<String,Object> map : list) {
					for(ModelCarType carType : carTypes) {
						if(carType.getId().equals(map.get("carTypeId"))) {
							ModelCarType dest = new ModelCarType();
							BeanUtils.copyProperties(dest, carType);
							map.put("cartype", dest);
						}
					}
				}
			}
			if(!storeIds.isEmpty()) {
				List<ModelStore> stores = iServStore.findByIds(new ArrayList<Long>(storeIds));
				for(Map<String,Object> map : list) {
					for(ModelStore store : stores) {
						if(store.getId().equals(map.get("storeId"))) {
							ModelStore dest = new ModelStore();
							BeanUtils.copyProperties(dest, store);
							map.put("store", dest);
						}
					}
				}
			}
			
		} catch (IllegalAccessException e) {
			String msg = ExceptionUtils.getFullStackTrace(e);
			logger.error(msg);
			throw new ServException(e);
		} catch (InvocationTargetException e) {
			String msg = ExceptionUtils.getFullStackTrace(e);
			logger.error(msg);
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
	public List<Map<String, Object>> getCountTrendMonthCartype(Date begin,
			Date end) {
		ajustage(begin, end);
		String sql = "SELECT carTypeId, DATE_FORMAT(createTime, '%Y/%m')  AS countTime, COUNT(id) AS num FROM cust_car WHERE createTime BETWEEN ? AND ? GROUP BY carTypeId ASC, countTime ASC";
		List<Map<String,Object>> list = getDao().queryForMapList(sql, begin, end);
		injectCartypeAndStore(list);
		return list;
	}

	@Override
	public List<Map<String, Object>> getCountTrendMonthStore(Date begin,
			Date end) {
		ajustage(begin, end);
		String sql = "SELECT storeId, DATE_FORMAT(createTime, '%Y/%m') AS countTime, COUNT(id) AS num FROM cust_car WHERE createTime BETWEEN ? AND ? GROUP BY storeId ASC, countTime ASC";
		List<Map<String,Object>> list = getDao().queryForMapList(sql, begin, end);
		injectCartypeAndStore(list);
		return list;
	}

}
