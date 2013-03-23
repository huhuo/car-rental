package com.huhuo.carservicecore.mid;

import org.springframework.stereotype.Repository;

import com.huhuo.carservicecore.db.GenericBaseExtenseDao;

@Repository("carservicecoreDaoCarConsumer")
public class DaoCarConsumer extends GenericBaseExtenseDao<ModelCarConsumer> implements IDaoCarConsumer {

	@Override
	public String getTableName() {
		return "mid_car_consumer";
	}

	@Override
	public Class<ModelCarConsumer> getModelClazz() {
		return ModelCarConsumer.class;
	}

}
