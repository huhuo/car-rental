package com.huhuo.carservicecore.cust.car;

import org.springframework.stereotype.Repository;

import com.huhuo.carservicecore.db.GenericBaseExtenseDao;

@Repository("carservicecoreDaoCarType")
public class DaoCarType extends GenericBaseExtenseDao<ModelCarType> implements IDaoCarType {

	@Override
	public String getTableName() {
		return "cust_car_type";
	}

	@Override
	public Class<ModelCarType> getModelClazz() {
		return ModelCarType.class;
	}

}
