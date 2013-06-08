package com.huhuo.cmsystem.security.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huhuo.carservicecore.constant.Dictionary.ModelDictGroup;
import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.carservicecore.sys.dictionary.ModelDictionary;
import com.huhuo.carservicecore.sys.user.IDaoUser;
import com.huhuo.carservicecore.sys.user.ModelUser;
import com.huhuo.cmsystem.dict.IServDictionary;
import com.huhuo.integration.base.IBaseExtenseDao;
import com.huhuo.integration.db.mysql.Condition;

@Service("cmsystemServUser")
public class ServUser extends GenericBaseExtenseServ<ModelUser> implements IServUser {

	@Resource(name = "carservicecoreDaoUser")
	private IDaoUser iDaoUser;
	
	@Resource(name = "cmsystemServDictionary")
	private IServDictionary iServDictionary;

	@Override
	public IBaseExtenseDao<ModelUser> getDao() {
		// TODO Auto-generated method stub
		return iDaoUser;
	}
	
	@Override
	public void inject(ModelUser t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ModelUser> findByCondition(Condition<ModelUser> condition,
			boolean injected) {
		// TODO Auto-generated method stub
		List<ModelUser> list = findByCondition(condition);
		if(injected) {
			for(ModelUser t : list) {
				ModelDictionary genderDict = iServDictionary.getBy(ModelDictGroup.GENERAL_SYS_GENDER, t.getGender());
				t.setGenderDict(genderDict);
			}
		}
		return list;
	}

}
