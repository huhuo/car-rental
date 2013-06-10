package com.huhuo.cmsystem.district;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.carservicecore.sys.district.IDaoCity;
import com.huhuo.carservicecore.sys.district.ModelCity;
import com.huhuo.integration.base.IBaseExtenseDao;

@Service("cmsystemServCity")
public class ServCity extends GenericBaseExtenseServ<ModelCity> implements IServCity {

	@Resource(name = "carservicecoreDaoCity")
	private IDaoCity<ModelCity> iDaoCity;

	@Override
	public IBaseExtenseDao<ModelCity> getDao() {
		// TODO Auto-generated method stub
		return iDaoCity;
	}

	@Override
	public void inject(ModelCity t) {
		// TODO Auto-generated method stub
		
	}

}
