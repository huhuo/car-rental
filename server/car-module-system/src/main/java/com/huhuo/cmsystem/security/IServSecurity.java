package com.huhuo.cmsystem.security;

import com.huhuo.carservicecore.sys.user.ModelUser;


public interface IServSecurity {
	
	/**
	 * validate whether the username exist
	 * @param username
	 * @return
	 */
	Boolean validate(String username);
	/**
	 * validate the legality with username and password
	 * @param username
	 * @param password
	 * @return
	 */
	Boolean validate(String username, String password);
	/**
	 * login
	 * @param user
	 * @return
	 */
	Boolean login(ModelUser user);
}
