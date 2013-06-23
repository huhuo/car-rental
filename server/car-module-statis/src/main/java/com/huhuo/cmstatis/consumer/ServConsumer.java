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
	public List<Map<String, Object>> getAmountByDate(Date begin, Date end) {
		// TODO Auto-generated method stub
		return null;
	}

}
