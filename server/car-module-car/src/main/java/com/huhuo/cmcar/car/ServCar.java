package com.huhuo.cmcar.car;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huhuo.carservicecore.constant.Dictionary.DictGroup;
import com.huhuo.carservicecore.cust.car.IDaoCar;
import com.huhuo.carservicecore.cust.car.ModelCar;
import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.carservicecore.sys.file.ModelFileUpload;
import com.huhuo.cmcar.cartype.IServCarType;
import com.huhuo.cmsystem.dict.IServDictionary;
import com.huhuo.cmsystem.file.IServFileUpload;
import com.huhuo.cmsystem.store.IServStore;
import com.huhuo.integration.base.IBaseExtenseDao;
import com.huhuo.integration.db.mysql.Condition;

@Service("cmcarServCar")
public class ServCar extends GenericBaseExtenseServ<ModelCar> implements IServCar {

	@Resource(name = "carservicecoreDaoCar")
	private IDaoCar idaoCar;
	@Resource(name = "cmcarServCarType")
	private IServCarType iServCarType;
	@Resource(name = "cmsystemServDictionary")
	private IServDictionary iServDictionary;
	@Resource(name = "cmsystemServFileUpload")
	private IServFileUpload iServFileUpload;
	@Resource(name = "cmsystemServStore")
	private IServStore iServStore;

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
		List<ModelCar> list = findByCondition(condition);
		if(injected) {
			for(ModelCar car : list) {
				car.setCarType(iServCarType.find(car.getCarTypeId()));
				car.setColorDict(iServDictionary.getBy(DictGroup.CUST_CAR_COLOR, car.getColor()));
				car.setStatusDict(iServDictionary.getBy(DictGroup.CUST_CAR_STATUS, car.getStatus()));
				car.setPicture(iServFileUpload.find(car.getPictureId()));
				car.setStore(iServStore.find(car.getStoreId()));
				car.setWarehouse(iServStore.find(car.getWarehouseId()));
			}
		}
		return list;
	}

	@Override
	public Integer add(ModelCar t) {
		// validation
		if (t == null) {
			return null;
		}
		// update icon information
		ModelFileUpload picture = t.getPicture();
		// update DB
		if (picture != null) {
			picture = iServFileUpload.uploadFile(picture);
			t.setPictureId(picture.getId());
		}
		return super.add(t);
	}
	
}
