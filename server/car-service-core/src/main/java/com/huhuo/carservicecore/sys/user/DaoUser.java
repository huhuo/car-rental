package com.huhuo.carservicecore.sys.user;

import org.springframework.stereotype.Repository;

import com.huhuo.carservicecore.db.GenericBaseExtenseDao;

@Repository("carservicecoreDaoUser")
public class DaoUser extends GenericBaseExtenseDao<ModelUser> implements IDaoUser {

	@Override
	public String getTableName() {
		return "sys_user";
	}

	@Override
	public Class<ModelUser> getModelClazz() {
		return ModelUser.class;
	}

	@Override
	public Long countBy(String username) {
		String sql = String.format("SELECT COUNT(*) FROM %s WHERE username=?", getTableName());
		Long count = queryForSingleColVal(sql, Long.class, username);
		return count;
	}

	@Override
	public ModelUser findBy(String username, String password) {
		String sql = String.format("SELECT * FROM %s WHERE username=? AND password=?", getTableName());
		ModelUser user = findObject(sql, username, password);
		return user;
	}

}
