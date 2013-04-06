package com.huhuo.carservicecore.sys.file;

import org.springframework.stereotype.Repository;

import com.huhuo.carservicecore.db.GenericBaseExtenseDao;

@Repository("carservicecoreDaoFileUpload")
public class DaoFileUpload extends GenericBaseExtenseDao<ModelFileUpload> implements IDaoFileUpload<ModelFileUpload> {

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "sys_file_upload";
	}

	@Override
	public Class<ModelFileUpload> getModelClazz() {
		// TODO Auto-generated method stub
		return ModelFileUpload.class;
	}

}
