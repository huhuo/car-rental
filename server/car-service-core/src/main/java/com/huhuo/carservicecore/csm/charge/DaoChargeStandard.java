package com.huhuo.carservicecore.csm.charge;

import org.springframework.stereotype.Repository;

import com.huhuo.carservicecore.db.GenericBaseExtenseDao;

@Repository("carservicecoreDaoChargeStandard")
public class DaoChargeStandard extends GenericBaseExtenseDao<ModelChargeStandard> implements
		IDaoChargeStandard {

	@Override
	public String getTableName() {
		return "csm_charge_standard";
	}

	@Override
	public Class<ModelChargeStandard> getModelClazz() {
		return ModelChargeStandard.class;
	}

}
