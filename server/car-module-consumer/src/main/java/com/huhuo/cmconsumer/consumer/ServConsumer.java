package com.huhuo.cmconsumer.consumer;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huhuo.carservicecore.consumer.IDaoConsumer;
import com.huhuo.carservicecore.consumer.ModelConsumer;
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
	public Class<ModelConsumer> getModelClazz() {
		// TODO Auto-generated method stub
		return ModelConsumer.class;
	}

	

}
