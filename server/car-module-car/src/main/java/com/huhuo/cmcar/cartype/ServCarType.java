package com.huhuo.cmcar.cartype;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huhuo.carservicecore.cust.car.IDaoCarType;
import com.huhuo.carservicecore.cust.car.ModelCarType;
import com.huhuo.carservicecore.cust.car.ModelChargeStandard;
import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.carservicecore.sys.dictionary.ModelDictionary;
import com.huhuo.cmsystem.dict.IServDictionary;
import com.huhuo.integration.base.IBaseExtenseDao;
import com.huhuo.integration.db.mysql.Condition;

@Service("cmcarServCarType")
public class ServCarType extends GenericBaseExtenseServ<ModelCarType> implements IServCarType {

	@Resource(name = "carservicecoreDaoCarType")
	private IDaoCarType idaoCarType;
	
	@Resource(name = "cmcarServChargeStandards")
	private IServChargeStandard iServChargeStandard;
	
	@Resource(name = "cmsystemServDictionary")
	private IServDictionary iServDictionary;

	@Override
	public IBaseExtenseDao<ModelCarType> getDao() {
		return idaoCarType;
	}

	@Override
	public Class<ModelCarType> getModelClazz() {
		return ModelCarType.class;
	}

	@Override
	public List<ModelCarType> findByCondition(
			Condition<ModelCarType> condition, boolean injected) {
		List<ModelCarType> list = findByCondition(condition);
		if(injected) {
			for(ModelCarType t : list) {
				ModelDictionary categoryDict = iServDictionary.find(t.getCategory());
				t.setCategoryDict(categoryDict);
				ModelChargeStandard chargeStandard = iServChargeStandard.find(t.getChargeStandardId());
				t.setChargeStandard(chargeStandard);
			}
		}
		return list;
	}

}
