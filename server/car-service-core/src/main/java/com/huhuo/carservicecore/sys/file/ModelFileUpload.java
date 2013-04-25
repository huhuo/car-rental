package com.huhuo.carservicecore.sys.file;

import com.huhuo.integration.base.BaseModel;


public class ModelFileUpload extends BaseModel {

	private static final long serialVersionUID = 2810998066713702692L;
	
	/** 文件名称，如，beauty.jpg **/
	private String name;
	/** 文件相对路径，如，file/upload/2013-04-21 **/
	private String path;
	/** MD5文件名（后缀由type解析），如，d9197322d05124d7ec1164337ad7a9a1.jpg **/
	private String md5;
	/** 文件类型（代码中用枚举解析），1、jpg；2、 **/
	private Integer type;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

	
}
