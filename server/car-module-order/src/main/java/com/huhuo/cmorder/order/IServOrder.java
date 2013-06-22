package com.huhuo.cmorder.order;

import java.util.List;
import java.util.Map;

import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
import com.huhuo.carservicecore.csm.order.ModelOrder;
import com.huhuo.carservicecore.cust.car.ModelCar;
import com.huhuo.carservicecore.cust.car.ModelCarType;
import com.huhuo.carservicecore.cust.car.ModelChargeStandard;
import com.huhuo.carservicecore.cust.store.ModelStore;
import com.huhuo.integration.base.IBaseExtenseServ;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Page;


public interface IServOrder extends IBaseExtenseServ<ModelOrder> {
	
	public List<ModelConsumer> getConsumerListByPhone(String phone);
	public List<ModelCar> getCarListBylicencePlate(String licencePlate,Long carTypeId);
	public List<ModelCarType> getCarTypeList(String carTypeName, Long carTypeId);
	public List<ModelStore> getStoreById(Long storeId);
	public List<ModelChargeStandard> getchargeStandardById(Long chargeStandardId);
	public void updateCarStatus(Long id,int status);
	
	public Page<Map<String, Object>> findOrderPage(
			Condition<Map<String, Object>> condition);
	
}
