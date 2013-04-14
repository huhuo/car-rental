package com.huhuo.cmsystem.file;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.carservicecore.sys.file.IDaoFileUpload;
import com.huhuo.carservicecore.sys.file.ModelFileUpload;
import com.huhuo.integration.base.IBaseExtenseDao;

@Service("cmsystemServFileUpload")
public class ServFileUpload extends GenericBaseExtenseServ<ModelFileUpload> implements IServFileUpload {

	@Resource(name = "carservicecoreDaoFileUpload")
	private IDaoFileUpload<ModelFileUpload> iDaoFileUpload;

	@Override
	public IBaseExtenseDao<ModelFileUpload> getDao() {
		// TODO Auto-generated method stub
		return iDaoFileUpload;
	}

	@Override
	public Class<ModelFileUpload> getModelClazz() {
		// TODO Auto-generated method stub
		return ModelFileUpload.class;
	}
	

}
