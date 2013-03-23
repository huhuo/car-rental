package com.huhuo.carservicecore.cust.car;

import com.huhuo.integration.base.BaseModel;


public class ModelCarLocation extends BaseModel {

	private static final long serialVersionUID = 5642275727452077530L;
	
	/** 经度 **/
	private Double longitude;
	/** 纬度 **/
	private Double latitude;
	/** 入库门店id，与cust_store表关联，如果没有入库，则该字段为null **/
	private Long holderStoreId;
	
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Long getHolderStoreId() {
		return holderStoreId;
	}
	public void setHolderStoreId(Long holderStoreId) {
		this.holderStoreId = holderStoreId;
	}
	
}
