package com.huhuo.carservicecore.cust.car;

import org.springframework.stereotype.Repository;

import com.huhuo.carservicecore.db.GenericBaseExtenseDao;

@Repository("carservicecoreDaoCar")
public class DaoCar extends GenericBaseExtenseDao<ModelCar> implements IDaoCar {

	@Override
	public String getTableName() {
		return "cust_car";
	}

	@Override
	public Class<ModelCar> getModelClazz() {
		return ModelCar.class;
	}

}
