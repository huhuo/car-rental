package com.huhuo.carservicecore.sys.city;

import org.springframework.stereotype.Repository;

import com.huhuo.carservicecore.db.GenericBaseExtenseDao;

@Repository("cscoreDaoCity")
public class DaoCity extends GenericBaseExtenseDao<ModelCity> implements IDaoCity<ModelCity> {

	@Override
	public String getTableName() {
		return "sys_city";
	}

}
