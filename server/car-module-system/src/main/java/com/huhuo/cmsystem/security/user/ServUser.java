package com.huhuo.cmsystem.security.user;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huhuo.carservicecore.constant.Dictionary.ModelDictGroup;
import com.huhuo.carservicecore.cust.store.ModelStore;
import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.carservicecore.sys.dictionary.ModelDictionary;
import com.huhuo.carservicecore.sys.file.ModelFileUpload;
import com.huhuo.carservicecore.sys.user.IDaoUser;
import com.huhuo.carservicecore.sys.user.ModelUser;
import com.huhuo.cmsystem.dict.IServDictionary;
import com.huhuo.cmsystem.file.IServFileUpload;
import com.huhuo.cmsystem.store.IServStore;
import com.huhuo.integration.base.IBaseExtenseDao;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.exception.ServException;
import com.huhuo.integration.util.BeanUtils;
@Transactional
@Service("cmsystemServUser")
public class ServUser extends GenericBaseExtenseServ<ModelUser> implements IServUser {

	@Resource(name = "carservicecoreDaoUser")
	private IDaoUser iDaoUser;
	
	@Resource(name = "cmsystemServDictionary")
	private IServDictionary iServDictionary;
	
	@Resource(name = "cmsystemServFileUpload")
	private IServFileUpload iServFileUpload;
	
	@Resource(name = "cmsystemServStore")
	private IServStore iServStore;

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
				ModelStore store = iServStore.find(t.getStoreId());
				t.setStore(store);
				ModelFileUpload picture = iServFileUpload.find(t.getPictureId());
				t.setPicture(picture);
			}
		}
		return list;
	}
	
	@Override
	public Integer add(ModelUser t) {
		// validation
		if(t == null) {
			return null;
		}
		// update inner object chargeStandard
		
		// update t
		t.setCreateTime(new Date());
		t.setUpdateTime(new Date());
		// update icon information
		ModelFileUpload picture = t.getPicture();
		// update DB
		if(picture != null) {
			picture = iServFileUpload.uploadFile(picture);
			t.setPictureId(picture.getId());
		}
		return super.add(t);
	}
	
	@Override
	public Integer update(ModelUser t) {
		try {
			// validation
			if(t == null) {
				return null;
			}
			// persist object in security mode
			ModelUser userDB = find(t.getId());
			BeanUtils.copyProperties(userDB, t, false);
			ModelFileUpload picture = t.getPicture();
			if(picture != null) {
				iServFileUpload.uploadFile(picture);
				userDB.setPictureId(picture.getId());
			}
			return super.update(userDB);
		} catch (Exception e) {
			throw new ServException(e);
		}
	}
}
