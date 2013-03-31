package com.huhuo.carservicecore.cust.car;

import com.huhuo.integration.base.BaseModel;

public class ModelChargeStandard extends BaseModel {

	private static final long serialVersionUID = 9148390804617805084L;

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
