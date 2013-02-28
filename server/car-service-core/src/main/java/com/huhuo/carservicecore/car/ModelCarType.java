package com.huhuo.carservicecore.car;

import com.huhuo.integration.base.BaseModel;


public class ModelCarType extends BaseModel {
	
	private static final long serialVersionUID = -8126350138823774934L;
	
	/** 车型名称 **/
	private String name;
	/** 图片地址（静态资源） **/
	private String icon;
	/** 品牌id **/
	private Long brandId;
	
	/** .............. **/
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	
	
	
}
