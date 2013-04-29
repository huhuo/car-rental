package com.huhuo.cmorder.order;

import java.util.List;

import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
import com.huhuo.carservicecore.csm.order.ModelOrder;
import com.huhuo.carservicecore.cust.car.ModelCar;
import com.huhuo.carservicecore.cust.car.ModelCarType;
import com.huhuo.carservicecore.cust.car.ModelChargeStandard;
import com.huhuo.carservicecore.cust.store.ModelStore;
import com.huhuo.integration.base.IBaseExtenseServ;


public interface IServOrder extends IBaseExtenseServ<ModelOrder> {
	
	public List<ModelConsumer> getConsumerListByPhone(String phone);
	public List<ModelCar> getCarListBylicencePlate(String licencePlate,Long carTypeId);
	public List<ModelCarType> getCarTypeList(String carTypeName, Long carTypeId);
	public List<ModelStore> getStoreById(Long storeId);
	public List<ModelChargeStandard> getchargeStandardById(Long chargeStandardId);
}
