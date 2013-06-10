package com.huhuo.carservicecore.sys.user;

import com.huhuo.integration.base.IBaseExtenseDao;

public interface IDaoUser extends IBaseExtenseDao<ModelUser> {

	/**
	 * count the number by username
	 * @param username
	 * @return amount of matching record
	 */
	Long countBy(String username);
	/**
	 * find a unique user by username and password
	 * @param username
	 * @param password
	 * @return
	 */
	ModelUser findBy(String username, String password);
	
	ModelUser findByLoginName(String username);
	ModelUser findByName(String username);
	
}
