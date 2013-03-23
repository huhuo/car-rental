package com.huhuo.carservicecore.statis.income;

import org.springframework.stereotype.Repository;

import com.huhuo.carservicecore.db.GenericBaseExtenseDao;

@Repository("carservicecoreDaoCarIncome")
public class DaoCarIncome extends GenericBaseExtenseDao<ModelCarIncome> implements IDaoCarIncome {

	@Override
	public String getTableName() {
		return "statis_car_income";
	}

	@Override
	public Class<ModelCarIncome> getModelClazz() {
		return ModelCarIncome.class;
	}

}
