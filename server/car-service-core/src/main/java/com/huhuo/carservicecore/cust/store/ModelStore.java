package com.huhuo.carservicecore.cust.store;

import java.util.Date;

import com.huhuo.integration.base.BaseModel;
import com.huhuo.integration.db.mysql.NotSqlField;


public class ModelStore extends BaseModel {
	
	private static final long serialVersionUID = 7720842469295943252L;
	
	/** 门店名称 **/
	private String name;
	/** 地址 **/
	private String address;
	/** 未租车数 **/
	@NotSqlField
	private Integer freeNum;
	/** 已租车数 **/
	@NotSqlField
	private Integer rentNum;
	/** 车辆总数 **/
	@NotSqlField
	private Integer totalNum;
	/** 状态，字典表字段，组名：cust_store_status，0：删除； **/
	private Integer status;
	/** 创建时间 **/
	private Date createTime;
	/** 更改时间**/
	private Date updateTime;
	/** 分店联系电话 **/
	private String telephone;
	/** 店长（与sys_user关联） **/
	private Long managerId;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getManagerId() {
		return managerId;
	}
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
	public Integer getFreeNum() {
		return freeNum;
	}
	public void setFreeNum(Integer freeNum) {
		this.freeNum = freeNum;
	}
	public Integer getRentNum() {
		return rentNum;
	}
	public void setRentNum(Integer rentNum) {
		this.rentNum = rentNum;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
}
