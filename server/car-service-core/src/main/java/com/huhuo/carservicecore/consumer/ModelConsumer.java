package com.huhuo.carservicecore.consumer;

import java.io.Serializable;

import com.huhuo.integration.base.BaseModel;

public class ModelConsumer extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1861309734833243740L;
	
	/** 省份证号码 **/
	private String identityCardId;
	/** 用户姓名 **/
	private String username;
	/** 用户密码 **/
	private String password;
	/** 固定电 **/
	private String telephone;
	/** 手机号码 **/
	private String mobileNumber;
	/** 邮箱 **/
	private String email;
	
	public String getIdentityCardId() {
		return identityCardId;
	}
	public void setIdentityCardId(String identityCardId) {
		this.identityCardId = identityCardId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
