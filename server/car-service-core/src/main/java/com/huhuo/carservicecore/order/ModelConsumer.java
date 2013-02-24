package com.huhuo.carservicecore.order;

import java.io.Serializable;

import com.huhuo.integration.base.BaseModel;

public class ModelConsumer extends BaseModel implements Serializable {

	private static final long serialVersionUID = -4415196899388568753L;
	
	/** 租车人id（与csm_consumer关联） **/
	private Long consumerId;
	/** 车辆id **/
	private Long carId;
	/** 车辆出租前油量 **/
	private Float oilmassBegin;
	public Long getConsumerId() {
		return consumerId;
	}
	public void setConsumerId(Long consumerId) {
		this.consumerId = consumerId;
	}
	public Long getCarId() {
		return carId;
	}
	public void setCarId(Long carId) {
		this.carId = carId;
	}
	public Float getOilmassBegin() {
		return oilmassBegin;
	}
	public void setOilmassBegin(Float oilmassBegin) {
		this.oilmassBegin = oilmassBegin;
	}
	
}
