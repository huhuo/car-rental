package com.huhuo.carservicecore.cust.store;

import org.springframework.stereotype.Repository;

import com.huhuo.carservicecore.db.GenericBaseExtenseDao;

@Repository("carservicecoreDaoStore")
public class DaoStore extends GenericBaseExtenseDao<ModelStore> implements IDaoStore {

	@Override
	public String getTableName() {
		return "cust_store";
	}

	@Override
	public Class<ModelStore> getModelClazz() {
		return ModelStore.class;
	}

}
