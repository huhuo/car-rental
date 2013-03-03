package com.huhuo.carservicecore.csm.order;

import org.springframework.stereotype.Repository;

import com.huhuo.carservicecore.db.GenericBaseExtenseDao;
@Repository("carservicecoreDaoOrder")
public class DaoOrder extends GenericBaseExtenseDao<ModelOrder> implements IDaoOrder {

	@Override
	public String getTableName() {
		return "csm_order";
	}

	@Override
	public Class<ModelOrder> getModelClazz() {
		return ModelOrder.class;
	}


}
