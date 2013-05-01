package com.huhuo.carservicecore.sys.user;

import java.util.Date;

import com.huhuo.carservicecore.sys.dictionary.ModelDictionary;
import com.huhuo.integration.base.BaseModel;

public class ModelUser extends BaseModel {

	private static final long serialVersionUID = 346195700651853997L;

	/** 用户姓名 **/
	private String username;
	/** 密码 **/
	private String password;
	/** 性别（字典查询：1：男；2：女） **/
	private Integer gender;
	/** 生日 **/
	private Date birthday;
	/** 手机号码 **/
	private String mobileNumber;
	/** 固定电话 **/
	private String telephone;
	/** 邮箱 **/
	private String email;
	/** 身份证 **/
	private String identityCardId;
	/** 住址 **/
	private String address;
	/** 管理员所属分店id，与cust_store表关联 **/
	private Long storeId;
	
	private ModelDictionary genderDict;
	
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
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdentityCardId() {
		return identityCardId;
	}
	public void setIdentityCardId(String identityCardId) {
		this.identityCardId = identityCardId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public ModelDictionary getGenderDict() {
		return genderDict;
	}
	public void setGenderDict(ModelDictionary genderDict) {
		this.genderDict = genderDict;
	}
	
}
