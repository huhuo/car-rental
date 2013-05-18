package com.huhuo.carservicecore.csm.consumer;

import java.io.Serializable;
import java.util.Date;

import com.huhuo.carservicecore.sys.dictionary.ModelDictionary;
import com.huhuo.integration.base.BaseModel;
import com.huhuo.integration.db.mysql.NotSqlField;
import com.huhuo.integration.util.TimeUtils;

public class ModelConsumer extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1861309734833243740L;
	
	/** 身份证号码 **/
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
	/**现在 地址 **/
	private String address;
	/**户籍地址*/
	private String permanentAddress;
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
	/**驾照领取地区*/
	private String receiveArea;
	/** 会员积分 **/
	private Integer integral;
	/** 创建时间 **/
	private Date createTime;
	/** 更新时间 **/
	private Date updateTime;
	@NotSqlField
	private Integer age;
	/**担保人*/
	private String bondsman;
	/**担保人身份证号*/
	private String bondsmanIdentityCard;
	/**担保人联系电话（手机）*/
	private String bondsmanTel;
	
	/**
	 * 外联对象
	 * @return
	 */
	private transient ModelDictionary genderDic;
	@NotSqlField
	private ModelConsumerStatus statusEnum;
	
	@NotSqlField
	private String statusStr;
	
	public enum ModelConsumerStatus {
		DELETE(0, "已删除"),
		NORMAL(1, "正常"),
		BLACK(2, "黑名单"),
		;
		private Integer key;
		private String disp;
		ModelConsumerStatus(Integer key, String disp) {
			this.key = key;
			this.disp = disp;
		}
		public Integer getKey() {
			return key;
		}
		public String getDisp() {
			return disp;
		}
		public static ModelConsumerStatus getBy(Integer key) {
			ModelConsumerStatus ret = NORMAL;
			switch (key) {
			case 0:
				ret = DELETE;
				break;
			case 1:
				ret = NORMAL;
				break;
			case 2:
				ret = BLACK;
				break;
			}
			return ret;
		}
	}
	
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
	@Override
	public void setStatus(Integer status) {
		// TODO Auto-generated method stub
		setStatusEnum(ModelConsumerStatus.getBy(status));
		setStatusStr(ModelConsumerStatus.getBy(status).getDisp());
		super.setStatus(status);
	}
	public ModelConsumerStatus getStatusEnum() {
		return statusEnum;
	}
	public void setStatusEnum(ModelConsumerStatus statusEnum) {
		this.statusEnum = statusEnum;
	}
	public String getEmergencyTel() {
		return emergencyTel;
	}
	public void setEmergencyTel(String emergencyTel) {
		this.emergencyTel = emergencyTel;
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
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public String getReceiveArea() {
		return receiveArea;
	}
	public void setReceiveArea(String receiveArea) {
		this.receiveArea = receiveArea;
	}
	public String getBondsman() {
		return bondsman;
	}
	public void setBondsman(String bondsman) {
		this.bondsman = bondsman;
	}
	public String getBondsmanIdentityCard() {
		return bondsmanIdentityCard;
	}
	public void setBondsmanIdentityCard(String bondsmanIdentityCard) {
		this.bondsmanIdentityCard = bondsmanIdentityCard;
	}
	public String getBondsmanTel() {
		return bondsmanTel;
	}
	public void setBondsmanTel(String bondsmanTel) {
		this.bondsmanTel = bondsmanTel;
	}
	public String getAvatar() {
		return avatar;
	}
	public String getStatusStr() {
		return statusStr;
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	
	
	
}
