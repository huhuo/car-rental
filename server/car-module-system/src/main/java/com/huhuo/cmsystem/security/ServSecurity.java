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
	public Boolean validate(String username) {
		// TODO Auto-generated method stub
		logger.info(iDaoUser.toString());
		return null;
	}

	@Override
	public Boolean validate(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean login(ModelUser user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
