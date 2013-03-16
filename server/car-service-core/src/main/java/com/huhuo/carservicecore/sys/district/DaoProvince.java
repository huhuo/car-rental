package com.huhuo.carservicecore.sys.district;

import org.springframework.stereotype.Repository;

import com.huhuo.carservicecore.db.GenericBaseExtenseDao;

@Repository("carservicecoreDaoProvince")
public class DaoProvince extends GenericBaseExtenseDao<ModelProvince> 
	implements IDaoProvince<ModelProvince> {

	@Override
	public String getTableName() {
		return "sys_province";
	}

	@Override
	public Class<ModelProvince> getModelClazz() {
		return ModelProvince.class;
	}
	

}
