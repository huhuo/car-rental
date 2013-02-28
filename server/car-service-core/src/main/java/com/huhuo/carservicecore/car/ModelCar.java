package com.huhuo.carservicecore.car;

import com.huhuo.integration.base.BaseModel;


public class ModelCar extends BaseModel {

	private static final long serialVersionUID = -7109163662313613337L;
	
	/** 车辆型号（1个车型对应多个车辆） **/
	private Long carTypeId;
	/** 车牌号 **/
	private String licencePlate;
	/** 所属门店 **/
	private Long storeId;
	
	public Long getCarTypeId() {
		return carTypeId;
	}
	public void setCarTypeId(Long carTypeId) {
		this.carTypeId = carTypeId;
	}
	public String getLicencePlate() {
		return licencePlate;
	}
	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	
}
