package com.huhuo.cmsystem.security;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huhuo.carservicecore.sys.user.IDaoUser;
import com.huhuo.carservicecore.sys.user.ModelUser;
import com.huhuo.integration.base.BaseServ;

@Service("cmsystemServSecurity")
public class ServSecurity extends BaseServ implements IServSecurity {

	@Resource(name = "carservicecoreDaoUser")
	private IDaoUser iDaoUser;

	@Override
	public Boolean isAvailable(String username) {
		// TODO Auto-generated method stub
		logger.info("==> check existent for username=?", username);
		Long count = iDaoUser.countBy(username);
		logger.info("==> there are {} users with username={}", count, username);
		return count == 0;
	}

	@Override
	public ModelUser validate(String username, String password) {
		logger.info("validate with username={}, password=?", username, password);
		ModelUser user = iDaoUser.findBy(username, password);
		logger.info("get user --> {} with username={}, password={}", user, username, password);
		return user;
	}

	@Override
	public Boolean login(ModelUser user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
