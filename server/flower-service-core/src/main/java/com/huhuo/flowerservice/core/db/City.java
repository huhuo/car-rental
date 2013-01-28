package com.huhuo.flowerservice.core.db;

import com.huhuo.integration.db.mysql.GenericModel;

public class City extends GenericModel {

	private static final long serialVersionUID = -5653849031008398674L;

	private String cityName;
	
	private String spelling;
	
	private Integer orderNo;
	
	private String zipCode;
	
	private Long provinceId;
	
	private Integer cityLevel;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getSpelling() {
		return spelling;
	}

	public void setSpelling(String spelling) {
		this.spelling = spelling;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityLevel() {
		return cityLevel;
	}

	public void setCityLevel(Integer cityLevel) {
		this.cityLevel = cityLevel;
	}

}
