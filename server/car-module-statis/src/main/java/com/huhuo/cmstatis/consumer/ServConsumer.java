package com.huhuo.cmstatis.consumer;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huhuo.carservicecore.csm.consumer.IDaoConsumer;
import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.integration.base.IBaseExtenseDao;

@Service("cmstatisServConsumer")
@Transactional
public class ServConsumer extends GenericBaseExtenseServ<ModelConsumer> implements IServConsumer {

	@Resource(name = "carservicecoreDaoConsumer")
	private IDaoConsumer iDaoConsumer;
	
	@Override
	public IBaseExtenseDao<ModelConsumer> getDao() {
		// TODO Auto-generated method stub
		return iDaoConsumer;
	}

	@Override
	public void inject(ModelConsumer t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Map<String, Object>> getAmountByDay(Date begin, Date end) {
		String sql = "SELECT COUNT(id) AS totalNum,DATE_FORMAT(createTime,'本周第%w天') AS day FROM csm_consumer WHERE createTime BETWEEN ? AND ? GROUP BY day;";
		List<Map<String,Object>> list = iDaoConsumer.queryForMapList(sql, begin, end);
		System.out.println(list);
		return list;
	}
	@Override
	public List<Map<String, Object>> getAmountByDate(Date begin, Date end) {
		String sql = "SELECT COUNT(id) AS totalNum,DATE_FORMAT(createTime,'%c/%d') AS DATE FROM csm_consumer WHERE createTime BETWEEN ? AND ? GROUP BY DATE;";
		List<Map<String,Object>> list = iDaoConsumer.queryForMapList(sql, begin, end);
		System.out.println(list);
		return list;
	}
	@Override
	public List<Map<String, Object>> getAmountByWeek(Date begin, Date end) {
		String sql = "SELECT COUNT(id) AS totalNum,DATE_FORMAT(createTime,'%y年%u周') AS week FROM csm_consumer WHERE createTime BETWEEN ? AND ? GROUP BY week;";
		List<Map<String,Object>> list = iDaoConsumer.queryForMapList(sql, begin, end);
		System.out.println(list);
		return list;
	}
	@Override
	public List<Map<String, Object>> getAmountByMonth(Date begin, Date end) {
		String sql = "SELECT COUNT(id) AS totalNum,DATE_FORMAT(createTime,'%y年%m月') AS month FROM csm_consumer WHERE createTime BETWEEN ? AND ? GROUP BY month;";
		List<Map<String,Object>> list = iDaoConsumer.queryForMapList(sql, begin, end);
		System.out.println(list);
		return list;
	}

}
