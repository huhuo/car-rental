package com.huhuo.carservicecore.cust.ms;

import org.springframework.stereotype.Repository;

import com.huhuo.carservicecore.db.GenericBaseExtenseDao;

@Repository("carservicecoreDaoSMS")
public class DaoSMS extends GenericBaseExtenseDao<ModelSMS> implements IDaoSMS {

	@Override
	public String getTableName() {
		return "cust_ms_sms";
	}

	@Override
	public Class<ModelSMS> getModelClazz() {
		return ModelSMS.class;
	}

}
