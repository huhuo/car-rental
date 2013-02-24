package com.huhuo.carservicecore.sys.province;

import com.huhuo.integration.base.BaseModel;


public class ModelProvince extends BaseModel {

	private static final long serialVersionUID = 7496159828865932193L;
	
	/** 省份名称 **/
	private String name;
	/** 拼音 **/
	private String spelling;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpelling() {
		return spelling;
	}
	public void setSpelling(String spelling) {
		this.spelling = spelling;
	}
	
}
