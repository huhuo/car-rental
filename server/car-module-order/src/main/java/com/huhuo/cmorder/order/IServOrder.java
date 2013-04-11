package com.huhuo.cmorder.order;

import java.util.List;

import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
import com.huhuo.carservicecore.csm.order.ModelOrder;
import com.huhuo.carservicecore.cust.car.ModelCar;
import com.huhuo.integration.base.IBaseExtenseServ;


public interface IServOrder extends IBaseExtenseServ<ModelOrder> {
	
	public List<ModelConsumer> getConsumerListByPhone(String phone);
	public List<ModelCar> getCarListBylicencePlate(String licencePlate);
}
