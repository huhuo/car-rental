package com.huhuo.cmsystem.ms;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huhuo.carservicecore.cust.ms.IDaoSMS;
import com.huhuo.carservicecore.cust.ms.ModelSMS;
import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.carservicecore.sys.user.ModelUser;
import com.huhuo.integration.base.IBaseExtenseDao;

@Service("cmsystemServSMS")
public class ServSMS extends GenericBaseExtenseServ<ModelSMS> implements IServSMS {

	@Resource(name = "carservicecoreDaoSMS")
	private IDaoSMS iDaoSMS;
	
	@Override
	public IBaseExtenseDao<ModelSMS> getDao() {
		return iDaoSMS;
	}

	@Override
	public Class<ModelSMS> getModelClazz() {
		return ModelSMS.class;
	}

	@Override
	public Boolean send(Long senderId, Long recieverId, Long phoneNum,
			String msg) {
		// TODO Auto-generated method stub	未写完
		ModelSMS t = new ModelSMS();
		t.setSenderId(senderId);
		t.setRecieverId(recieverId);
		t.setPhoneNum(phoneNum);
		t.setContent(msg);
		add(t);
		return null;
	}

	@Override
	public Boolean send(ModelUser sender, ModelUser reciever, Long phoneNum,
			String msg) {
		// TODO Auto-generated method stub
		return null;
	}


}
