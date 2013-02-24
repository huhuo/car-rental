package com.huhuo.carservicecore.consumer;

import org.springframework.stereotype.Repository;

import com.huhuo.carservicecore.db.GenericBaseExtenseDao;

@Repository("cmconsumerDaoConsumer")
public class DaoConsumer extends GenericBaseExtenseDao<ModelConsumer> implements IDaoConsumer {

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "csm_consumer";
	}

}
