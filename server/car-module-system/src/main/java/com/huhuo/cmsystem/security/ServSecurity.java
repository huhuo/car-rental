package com.huhuo.cmsystem.security;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huhuo.carservicecore.sys.user.IDaoUser;
import com.huhuo.carservicecore.sys.user.ModelUser;
import com.huhuo.integration.base.BaseServ;
import com.huhuo.integration.db.mysql.Condition;

@Service("cmsystemServSecurity")
public class ServSecurity extends BaseServ implements IServSecurity {

	@Resource(name = "carservicecoreDaoUser")
	private IDaoUser iDaoUser;

	@Override
	public Boolean isAvailable(String username) {
		// TODO Auto-generated method stub
		logger.info("check existent for username=?", username);
		ModelUser t = new ModelUser();
		t.setUsername(username);
		Condition<ModelUser> condition = new Condition<ModelUser>(t, null, null, null);
		List<ModelUser> list = iDaoUser.findByCondition(condition);
		logger.info("get user --> {} with username={}", list, username);
		return list==null || list.size()==0;
	}

	@Override
	public ModelUser validate(String username, String password) {
		logger.info("validate with username={}, password=?", username, password);
		ModelUser t = new ModelUser();
		t.setUsername(username);
		t.setPassword(password);
		Condition<ModelUser> condition = new Condition<ModelUser>(t, null, null, null);
		List<ModelUser> list = iDaoUser.findByCondition(condition);
		logger.info("get user --> {} with username={}, password={}", list, username, password);
		if(list!=null && list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Boolean login(ModelUser user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
