package com.huhuo.carservicecore.cust.car;

import org.springframework.stereotype.Repository;

import com.huhuo.carservicecore.db.GenericBaseExtenseDao;
@Repository("carservicecoreDaoCarLocation")
public class DaoCarLocation extends GenericBaseExtenseDao<ModelCarLocation> implements IDaoCarLocation {

	@Override
	public String getTableName() {
		return "cust_car_location";
	}

	@Override
	public Class<ModelCarLocation> getModelClazz() {
		return ModelCarLocation.class;
	}


}
