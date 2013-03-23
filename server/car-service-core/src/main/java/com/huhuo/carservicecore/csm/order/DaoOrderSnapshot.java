package com.huhuo.carservicecore.csm.order;

import org.springframework.stereotype.Repository;

import com.huhuo.carservicecore.db.GenericBaseExtenseDao;
@Repository("carservicecoreDaoOrderSnapshot")
public class DaoOrderSnapshot extends GenericBaseExtenseDao<ModelOrderSnapshot> implements IDaoOrderSnapshot {

	@Override
	public String getTableName() {
		return "csm_order_snapshot";
	}

	@Override
	public Class<ModelOrderSnapshot> getModelClazz() {
		return ModelOrderSnapshot.class;
	}


}
