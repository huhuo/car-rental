package com.huhuo.carservicecore.sys.district;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.integration.base.IBaseExtenseDao;

@Service("carservicecoreServSysCity")
public class ServCity extends GenericBaseExtenseServ<ModelCity> implements IServSysCity {
	
	@Resource(name = "carservicecoreDaoCity")
	private IDaoCity<ModelCity> iDaoCity;

	@Override
	public IBaseExtenseDao<ModelCity> getDao() {
		// TODO Auto-generated method stub
		return iDaoCity;
	}

	@Override
	public Class<ModelCity> getModelClazz() {
		// TODO Auto-generated method stub
		return ModelCity.class;
	}
	
}
