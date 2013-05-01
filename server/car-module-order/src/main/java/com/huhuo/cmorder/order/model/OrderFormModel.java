package com.huhuo.cmorder.order.model;

import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
import com.huhuo.carservicecore.csm.order.ModelOrder;
import com.huhuo.carservicecore.cust.car.ModelCar;
import com.huhuo.carservicecore.cust.car.ModelChargeStandard;

public class OrderFormModel {
	private ModelCar car;
	private ModelConsumer consumer;
	
	private ModelOrder order;
	
	private ModelChargeStandard chargeStandard;

	public ModelCar getCar() {
		return car;
	}

	public void setCar(ModelCar car) {
		this.car = car;
	}

	public ModelConsumer getConsumer() {
		return consumer;
	}

	public void setConsumer(ModelConsumer consumer) {
		this.consumer = consumer;
	}

	public ModelOrder getOrder() {
		return order;
	}

	public void setOrder(ModelOrder order) {
		this.order = order;
	}

	public ModelChargeStandard getChargeStandard() {
		return chargeStandard;
	}

	public void setChargeStandard(ModelChargeStandard chargeStandard) {
		this.chargeStandard = chargeStandard;
	}

	@Override
	public String toString() {
		return "OrderFormModel [car=" + car + ", consumer=" + consumer
				+ ", order=" + order + ", chargeStandard=" + chargeStandard
				+ "]";
	}
	
	
	
}
