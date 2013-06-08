package com.huhuo.cmcar.car;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huhuo.carservicecore.cust.car.IDaoCar;
import com.huhuo.carservicecore.cust.car.ModelCar;
import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.carservicecore.sys.file.ModelFileUpload;
import com.huhuo.cmcar.cartype.IServCarType;
import com.huhuo.cmsystem.file.IServFileUpload;
import com.huhuo.cmsystem.quartz.IServSchedule;
import com.huhuo.cmsystem.store.IServStore;
import com.huhuo.integration.base.IBaseExtenseDao;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.exception.ServException;
import com.huhuo.integration.util.BeanUtils;
import com.huhuo.integration.util.TimeUtils;

@Service("cmcarServCar")
@Transactional
public class ServCar extends GenericBaseExtenseServ<ModelCar> implements IServCar {

	@Resource(name = "carservicecoreDaoCar")
	private IDaoCar idaoCar;
	@Resource(name = "cmcarServCarType")
	private IServCarType iServCarType;
	@Resource(name = "cmsystemServFileUpload")
	private IServFileUpload iServFileUpload;
	@Resource(name = "cmsystemServStore")
	private IServStore iServStore;
	@Resource(name = "cmsystemServSchedule")
	private IServSchedule<ModelCar> iServSchedule;
	
	@Override
	public IBaseExtenseDao<ModelCar> getDao() {
		// TODO Auto-generated method stub
		return idaoCar;
	}

	@Override
	public void inject(ModelCar t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <V> ModelCar find(V id) {
		// TODO Auto-generated method stub
		ModelCar car = super.find(id);
		car.setCarType(iServCarType.find(car.getCarTypeId()));
		car.setPicture(iServFileUpload.find(car.getPictureId()));
		car.setStore(iServStore.find(car.getStoreId()));
		car.setWarehouse(iServStore.find(car.getWarehouseId()));
		return car;
	}

	@Override
	public List<ModelCar> findByCondition(Condition<ModelCar> condition,
			boolean injected) {
		List<ModelCar> list = findByCondition(condition);
		if(injected) {
			for(ModelCar car : list) {
				car.setCarType(iServCarType.find(car.getCarTypeId()));
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

	@Override
	public Integer update(ModelCar t) {
		try {
			// validation
			if(t == null) {
				return null;
			}
			// persist object in security mode
			ModelCar carDB = find(t.getId());
			BeanUtils.copyProperties(carDB, t, false);
			ModelFileUpload picture = t.getPicture();
			if(picture != null) {
				iServFileUpload.uploadFile(picture);
				carDB.setPictureId(picture.getId());
			}
			return super.update(carDB);
		} catch (Exception e) {
			throw new ServException(e);
		}
	}

	@Override
	public synchronized void book(ModelCar car, Date expireTime) {
		// set the status for car
		Long id = car.getId();
		if(id == null) {
			throw new ServException("car id can't be null!");
		}
		final ModelCar carDB = find(id);
		if(carDB == null) {
			throw new ServException("booked car is not exist!");
		}
		final Integer status = carDB.getStatus();
		if(ModelCar.STATUS_BOOKED.getKey() == status) {
			throw new ServException("this car is already booked!");
		}
		carDB.setStatus(ModelCar.STATUS_BOOKED.getKey());
		update(carDB);
		iServSchedule.schedule(new Runnable() {
			@Override
			public void run() {
				carDB.setStatus(status);
				update(carDB);
			}
		}, expireTime);
	}

	@Override
	public void book(ModelCar car, Integer delayTime) {
		if(delayTime < 0) {
			throw new ServException("delayTime can't be negative");
		}
		Date expireTime = TimeUtils.offsetHour(delayTime, new Date());
		book(car, expireTime);
	}

}
