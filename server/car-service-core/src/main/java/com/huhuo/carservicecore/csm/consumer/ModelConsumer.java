package com.huhuo.carservicecore.csm.consumer;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.huhuo.carservicecore.sys.dictionary.ModelDictionary;
import com.huhuo.integration.base.BaseModel;
import com.huhuo.integration.db.mysql.NotSqlField;
import com.huhuo.integration.util.TimeUtils;

public class ModelConsumer extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1861309734833243740L;
	
	/** 省份证号码 **/
	private String identityCardId;
	/** 用户姓名 **/
	private String username;
	/** 用户密码 **/
	private String password;
	/** 用户头像**/
	private String avatar;
	/** 固定电 **/
	private String telephone;
	/** 手机号码 **/
	private String mobileNumber;
	/** 邮箱 **/
	private String email;
	/** 地址 **/
	private String address;
	/** 邮编 **/
	private String zipcode;
	/** 腾讯qq号 **/
	private String qq;
	/** 紧急联系人 **/
	private String emergencyContact;
	/** 紧急联系人电话 **/
	private String emergencyTel;
	/** 性别（字典表查询，组名GENERAL_GENDER，dictValue：1：男；2：女） **/
	private Integer gender;
	/** 民族 **/
	private String nation;
	/** 出生日期 **/
	private Date birthday;
	/** 驾照 **/
	private String licenseNum;
	/** 会员积分 **/
	private Integer integral;
	/** 创建时间 **/
	private Date createTime;
	/** 更新时间 **/
	private Date updateTime;
	/** 是否是黑名单*/
	private Boolean blackList;
	
	@NotSqlField
	private Integer age;
	
	/**
	 * 外联对象
	 * @return
	 */
	private transient ModelDictionary genderDic;
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmergencyContact() {
		return emergencyContact;
	}
	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
		if(this.birthday!=null){
			this.age=TimeUtils.getAge(this.birthday);
		}
	}
	public String getLicenseNum() {
		return licenseNum;
	}
	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}
	public Integer getIntegral() {
		return integral;
	}
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public ModelDictionary getGenderDic() {
		return genderDic;
	}
	public void setGenderDic(ModelDictionary genderDic) {
		this.genderDic = genderDic;
	}
	public String getEmergencyTel() {
		return emergencyTel;
	}
	public void setEmergencyTel(String emergencyTel) {
		this.emergencyTel = emergencyTel;
	}
	public Boolean getBlackList() {
		return blackList;
	}
	public void setBlackList(Boolean blackList) {
		this.blackList = blackList;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Integer getAge() {
		return this.age;
	}
	public void setAge(Integer age) {
		this.age = age;
		
		
	}
	
	
}
