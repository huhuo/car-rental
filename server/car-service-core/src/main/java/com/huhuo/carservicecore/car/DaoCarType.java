package com.huhuo.carservicecore.car;

import org.springframework.stereotype.Repository;

import com.huhuo.carservicecore.db.GenericBaseExtenseDao;

@Repository("carservicecoreDaoCarType")
public class DaoCarType extends GenericBaseExtenseDao<ModelCarType> implements IDaoCarType {

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "cust_car_type";
	}

}
