package com.huhuo.carservicecore.csm.charge;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.huhuo.carservicecore.db.GenericBaseExtenseDao;
import com.huhuo.integration.exception.DaoException;

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
	
	@Override
	public List<ModelChargeStandard> findModels(Integer start, Integer limit)
			throws DaoException {
		/**
		 * this is an example to show how to override the method of GenericBaseExtenseDao
		 * for any business purpose, for example add memcached logical to the DAO
		 */
		System.out.println("=== over ride success ===");
		return super.findModels(start, limit);
	}

}
