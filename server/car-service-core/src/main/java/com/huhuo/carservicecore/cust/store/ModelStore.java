package com.huhuo.carservicecore.cust.store;

import com.huhuo.integration.base.BaseModel;


public class ModelStore extends BaseModel {
	
	private static final long serialVersionUID = 7720842469295943252L;
	
	/** 门店名称 **/
	private String name;
	/** 地址 **/
	private String address;
	/** 店长（与sys_user关联） **/
	private Long managerId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getManagerId() {
		return managerId;
	}
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
	
}
