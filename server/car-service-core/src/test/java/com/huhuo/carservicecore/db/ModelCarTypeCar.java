package com.huhuo.carservicecore.db;

import com.huhuo.integration.base.BaseModel;

public class ModelCarTypeCar extends BaseModel {

	private static final long serialVersionUID = 6297678576812875826L;

	/** 车型名称 **/
	private String name;
	/** 图片地址（静态资源） **/
	private String icon;
	/** 车辆类别（字典表字段，组名：cust_car_type_category，1、轿车；2、越野汽车；3、客车；4、货车；5、自卸汽车；6、牵引汽车；7、专用汽车） **/
	private Integer category;
	/** 座位数 **/
	private Integer seating;
	/** 油箱容量（单位：升） **/
	private Integer tankCapacity;
	/** 可行驶里程数 **/
	private Double drivingRange;
	/** 车型使用的收费标准id（与csm_charge_standard表一对一） **/
	private Long chargeStandardId;
	
	
	/** 押金（元） **/
	private Double deposit;
	/** 保险费，xxx元/次 **/
	private Double premium;
	/** 租金（xxx元/天） **/
	private Double rent;
	/** 里程限制（xxx公里/日） **/
	private Long mileageLimits;
	/** 超里程费用（xxx元/公里） **/
	private Double overMileageFare;
	/** 超时标准（xxx元/小时） **/
	private Double overTimeFare;
	/** 上门送车（xxx元） **/
	private Double carSendFare;
	/** 异店结算（还车）附加费（元） **/
	private Double diffShopReturnFare;
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
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public Integer getSeating() {
		return seating;
	}
	public void setSeating(Integer seating) {
		this.seating = seating;
	}
	public Integer getTankCapacity() {
		return tankCapacity;
	}
	public void setTankCapacity(Integer tankCapacity) {
		this.tankCapacity = tankCapacity;
	}
	public Double getDrivingRange() {
		return drivingRange;
	}
	public void setDrivingRange(Double drivingRange) {
		this.drivingRange = drivingRange;
	}
	public Long getChargeStandardId() {
		return chargeStandardId;
	}
	public void setChargeStandardId(Long chargeStandardId) {
		this.chargeStandardId = chargeStandardId;
	}
	public Double getDeposit() {
		return deposit;
	}
	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}
	public Double getPremium() {
		return premium;
	}
	public void setPremium(Double premium) {
		this.premium = premium;
	}
	public Double getRent() {
		return rent;
	}
	public void setRent(Double rent) {
		this.rent = rent;
	}
	public Long getMileageLimits() {
		return mileageLimits;
	}
	public void setMileageLimits(Long mileageLimits) {
		this.mileageLimits = mileageLimits;
	}
	public Double getOverMileageFare() {
		return overMileageFare;
	}
	public void setOverMileageFare(Double overMileageFare) {
		this.overMileageFare = overMileageFare;
	}
	public Double getOverTimeFare() {
		return overTimeFare;
	}
	public void setOverTimeFare(Double overTimeFare) {
		this.overTimeFare = overTimeFare;
	}
	public Double getCarSendFare() {
		return carSendFare;
	}
	public void setCarSendFare(Double carSendFare) {
		this.carSendFare = carSendFare;
	}
	public Double getDiffShopReturnFare() {
		return diffShopReturnFare;
	}
	public void setDiffShopReturnFare(Double diffShopReturnFare) {
		this.diffShopReturnFare = diffShopReturnFare;
	}
	
}
