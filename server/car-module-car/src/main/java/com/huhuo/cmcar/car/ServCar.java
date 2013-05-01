package com.huhuo.cmcar.car;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huhuo.carservicecore.cust.car.IDaoCar;
import com.huhuo.carservicecore.cust.car.ModelCar;
import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.integration.base.IBaseExtenseDao;
import com.huhuo.integration.db.mysql.Condition;

@Service("cmcarServCar")
public class ServCar extends GenericBaseExtenseServ<ModelCar> implements IServCar {

	@Resource(name = "carservicecoreDaoCar")
	private IDaoCar idaoCar;

	@Override
	public IBaseExtenseDao<ModelCar> getDao() {
		// TODO Auto-generated method stub
		return idaoCar;
	}

	@Override
	public Class<ModelCar> getModelClazz() {
		// TODO Auto-generated method stub
		return ModelCar.class;
	}

	@Override
	public List<ModelCar> findByCondition(Condition<ModelCar> condition,
			boolean injected) {
		// TODO Auto-generated method stub
		
		if(injected) {
			
		}
		
		return super.findByCondition(condition, injected);
	}

}
