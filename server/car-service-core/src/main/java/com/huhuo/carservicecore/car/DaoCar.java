package com.huhuo.carservicecore.car;

import org.springframework.stereotype.Repository;

import com.huhuo.carservicecore.db.GenericBaseExtenseDao;

@Repository("carservicecoreDaoCar")
public class DaoCar extends GenericBaseExtenseDao<ModelCar> implements IDaoCar {

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "cust_car";
	}

}
