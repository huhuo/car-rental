package com.huhuo.cmsystem.security;

import com.huhuo.carservicecore.sys.user.ModelUser;


public interface IServSecurity {
	
	/**
	 * validate whether the username available, which mean username is not exist in DB
	 * @param username
	 * @return true if username is not exist in DB
	 */
	Boolean isAvailable(String username);
	/**
	 * validate the legality with the username and the password
	 * @param username
	 * @param password
	 * @return user who fit out the username and the password
	 */
	ModelUser validate(String username, String password);
	/**
	 * login
	 * @param user
	 * @return
	 */
	Boolean login(ModelUser user);
}
