package com.huhuo.carservicecore.sys.district;

import org.springframework.stereotype.Repository;

import com.huhuo.carservicecore.db.GenericBaseExtenseDao;

@Repository("carservicecoreDaoCity")
public class DaoCity extends GenericBaseExtenseDao<ModelCity> implements IDaoCity<ModelCity> {

	@Override
	public String getTableName() {
		return "sys_city";
	}

	@Override
	public Class<ModelCity> getModelClazz() {
		return ModelCity.class;
	}

}
