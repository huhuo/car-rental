package com.huhuo.cmcar.cartype;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huhuo.carservicecore.cust.car.IDaoChargeStandard;
import com.huhuo.carservicecore.cust.car.ModelChargeStandard;
import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.integration.base.IBaseExtenseDao;

@Service("cmcarServChargeStandards")
public class ServChargeStandard extends GenericBaseExtenseServ<ModelChargeStandard> implements IServChargeStandard {

	@Resource(name = "carservicecoreDaoChargeStandard")
	private IDaoChargeStandard iDaoChargeStandard;

	@Override
	public IBaseExtenseDao<ModelChargeStandard> getDao() {
		// TODO Auto-generated method stub
		return iDaoChargeStandard;
	}

	@Override
	public Class<ModelChargeStandard> getModelClazz() {
		// TODO Auto-generated method stub
		return ModelChargeStandard.class;
	}
	

}
