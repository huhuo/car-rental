package com.huhuo.cmstatis.car;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huhuo.carservicecore.cust.car.IDaoCar;
import com.huhuo.carservicecore.cust.car.ModelCar;
import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.integration.base.IBaseExtenseDao;

@Service("cmstatisServCar")
@Transactional
public class ServCar extends GenericBaseExtenseServ<ModelCar> implements IServCar {

	@Resource(name = "carservicecoreDaoCar")
	private IDaoCar idaoCar;
	
	@Override
	public IBaseExtenseDao<ModelCar> getDao() {
		// TODO Auto-generated method stub
		return idaoCar;
	}

	@Override
	public void inject(ModelCar t) {
		// TODO Auto-generated method stub
		
	}

}
