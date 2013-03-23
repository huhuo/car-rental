package com.huhuo.carservicecore.statis.income;

import java.io.Serializable;

import com.huhuo.integration.base.BaseModel;

public class ModelCarIncome extends BaseModel implements Serializable {

	private static final long serialVersionUID = -4415196899388568753L;
	
	/** 车辆id **/
	private Long carId;
	/** 收入 **/
	private Double income;
	/** 车辆支出 **/
	private Double pay;
	
	public Long getCarId() {
		return carId;
	}
	public void setCarId(Long carId) {
		this.carId = carId;
	}
	public Double getIncome() {
		return income;
	}
	public void setIncome(Double income) {
		this.income = income;
	}
	public Double getPay() {
		return pay;
	}
	public void setPay(Double pay) {
		this.pay = pay;
	}
	
}
