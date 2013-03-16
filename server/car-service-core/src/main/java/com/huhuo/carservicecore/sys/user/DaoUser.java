package com.huhuo.carservicecore.sys.user;

import org.springframework.stereotype.Repository;

import com.huhuo.carservicecore.db.GenericBaseExtenseDao;

@Repository("carservicecoreDaoUser")
public class DaoUser extends GenericBaseExtenseDao<ModelUser> implements IDaoUser<ModelUser> {

	@Override
	public String getTableName() {
		return "sys_user";
	}

	@Override
	public Class<ModelUser> getModelClazz() {
		return ModelUser.class;
	}

}
