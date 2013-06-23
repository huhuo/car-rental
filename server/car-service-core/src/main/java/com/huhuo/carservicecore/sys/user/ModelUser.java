package com.huhuo.carservicecore.sys.user;

import java.util.Date;

import com.huhuo.carservicecore.cust.store.ModelStore;
import com.huhuo.carservicecore.sys.dictionary.ModelDictionary;
import com.huhuo.carservicecore.sys.file.ModelFileUpload;
import com.huhuo.integration.base.BaseModel;
import com.huhuo.integration.db.mysql.Dict;
import com.huhuo.integration.db.mysql.DictMgr;
import com.huhuo.integration.db.mysql.NotSqlField;

public class ModelUser extends BaseModel {

	private static final long serialVersionUID = 346195700651853997L;

	/** 用户姓名 **/
	private String username;
	/** 登录名 **/
	private String loginName;
	/** 上传图片的id，与sys_file_upload表关联 **/
	private Long pictureId;
	/** 密码 **/
	private String password;
	/** 性别（字典查询：1：男；2：女） **/
	private Integer gender;
	/** 状态，字典表字段，组名：sys_user_status，0：删除 1：已激活  2：已锁定； **/
	private Integer status;
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
	/** 用户当前登录的sessionId（用于实现单用户登录） **/
	private String sessionId;

	@NotSqlField
	private ModelDictionary genderDict;
	@NotSqlField
	private ModelStore store;
	@NotSqlField
	private ModelFileUpload picture;
	@NotSqlField
	private Dict statusDict;
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		Dict dict = DictMgr.get(GROUP_SYS_USER_STATUS, status);
		setStatusDict(dict);
		this.status = status;
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
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public void setGenderDict(ModelDictionary genderDict) {
		
		this.genderDict = genderDict;
	}
	public ModelStore getStore() {
		return store;
	}
	public void setStore(ModelStore store) {
		this.store = store;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public Long getPictureId() {
		return pictureId;
	}
	public void setPictureId(Long pictureId) {
		this.pictureId = pictureId;
	}
	public ModelFileUpload getPicture() {
		return picture;
	}
	public void setPicture(ModelFileUpload picture) {
		this.picture = picture;
	}
	public Dict getStatusDict() {
		return statusDict;
	}
	public void setStatusDict(Dict statusDict) {
		this.statusDict = statusDict;
	}
	
	
	
	
	
	/** status definition **/
	public static final String GROUP_SYS_USER_STATUS = "SYS_USER_STATUS";
	public static final Dict STATUS_DELETED = new Dict(GROUP_SYS_USER_STATUS, 0, "已删除");
	public static final Dict STATUS_ACTICATE = new Dict(GROUP_SYS_USER_STATUS, 1, "已激活");
	public static final Dict STATUS_LOCK = new Dict(GROUP_SYS_USER_STATUS, 2, "已锁定");
	/** status definition **/
	public static final String GROUP_SYS_USER_GENDER = "SYS_USER_GENDER";
	public static final Dict GENDER_MALE = new Dict(GROUP_SYS_USER_GENDER, 1, "男");
	public static final Dict GENDER_FAMALE = new Dict(GROUP_SYS_USER_GENDER, 2, "女");
}
