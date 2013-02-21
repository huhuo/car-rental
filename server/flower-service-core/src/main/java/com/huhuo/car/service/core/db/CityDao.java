package com.huhuo.car.service.core.db;




public class CityDao extends GenericDBDao<City> {

	@Override
	public String getTableName() {
		return "sys_city";
	}

}
