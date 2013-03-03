package com.huhuo.carservicecore.mid;

import com.huhuo.integration.base.BaseModel;

public class ModelCarConsumer extends BaseModel {

	private static final long serialVersionUID = 7961187046652874906L;

	/** 车辆id **/
	private Long carId;
	/** 消费者id **/
	private Long consumerId;
	
	public Long getCarId() {
		return carId;
	}
	public void setCarId(Long carId) {
		this.carId = carId;
	}
	public Long getConsumerId() {
		return consumerId;
	}
	public void setConsumerId(Long consumerId) {
		this.consumerId = consumerId;
	}
	
}
