package com.huhuo.cmcar.cartype;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huhuo.carservicecore.car.IDaoCarType;
import com.huhuo.carservicecore.car.ModelCarType;
import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.integration.base.IBaseExtenseDao;

@Service("cmcarServCarType")
public class ServCarType extends GenericBaseExtenseServ<ModelCarType> implements IServCarType {

	@Resource(name = "carservicecoreDaoCarType")
	private IDaoCarType idaoCarType;

	@Override
	public IBaseExtenseDao<ModelCarType> getDao() {
		// TODO Auto-generated method stub
		return idaoCarType;
	}

	@Override
	public Class<ModelCarType> getModelClazz() {
		// TODO Auto-generated method stub
		return ModelCarType.class;
	}
	

}
