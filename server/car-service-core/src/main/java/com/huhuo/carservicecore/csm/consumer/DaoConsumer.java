package com.huhuo.carservicecore.csm.consumer;

import org.springframework.stereotype.Repository;

import com.huhuo.carservicecore.db.GenericBaseExtenseDao;

@Repository("carservicecoreDaoConsumer")
public class DaoConsumer extends GenericBaseExtenseDao<ModelConsumer> implements IDaoConsumer {

	@Override
	public String getTableName() {
		return "csm_consumer";
	}

	@Override
	public Class<ModelConsumer> getModelClazz() {
		return ModelConsumer.class;
	}

}
