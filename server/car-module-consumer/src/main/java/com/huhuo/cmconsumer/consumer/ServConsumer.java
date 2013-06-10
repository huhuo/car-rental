package com.huhuo.cmconsumer.consumer;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huhuo.carservicecore.csm.consumer.IDaoConsumer;
import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.integration.base.IBaseExtenseDao;

@Service("cmconsumerServConsumer")
public class ServConsumer extends GenericBaseExtenseServ<ModelConsumer> implements IServConsumer {

	@Resource(name = "carservicecoreDaoConsumer")
	private IDaoConsumer idaoConsumer;

	@Override
	public IBaseExtenseDao<ModelConsumer> getDao() {
		// TODO Auto-generated method stub
		return idaoConsumer;
	}

	@Override
	public void inject(ModelConsumer t) {
		// TODO Auto-generated method stub
		
	}

}
