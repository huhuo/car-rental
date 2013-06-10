package com.huhuo.cmsystem.store;

import java.util.List;
import java.util.Map;

import com.huhuo.carservicecore.cust.car.ModelCar;
import com.huhuo.carservicecore.cust.store.ModelStore;
import com.huhuo.integration.base.IBaseExtenseServ;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Page;


public interface IServStore extends IBaseExtenseServ<ModelStore> {

	List<Map<String, Object>> multiQuery(ModelStore t, Page<ModelStore> page);
	
	Long countMultiQuery(ModelStore t, Page<ModelStore> page);
	
	Map<String,Object> detailQuery(ModelStore t);
	
	List<ModelCar> getCarByCondition(Condition<ModelCar> condition);

	ModelStore detailFind(Long id);
	
}

