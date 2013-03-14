package com.huhuo.carservicecore.csm.charge;

import com.huhuo.integration.base.BaseModel;

public class ModelChargeStandard extends BaseModel {

	private static final long serialVersionUID = 9148390804617805084L;

	/** 押金（元） **/
	private Float deposit;
	/** 租金（xxx元/天） **/
	private Float rent;
	/** 里程限制（xxx公里/日） **/
	private Long mileageLimits;
	/** 超里程费用（xxx元/公里） **/
	private Float overMileageFare;
	/** 超时标准（xxx元/小时） **/
	private Float overTimeFare;
	/** 上门送车（xxx元） **/
	private Float carSendFare;
	/** 异店结算（还车）附加费（元） **/
	private Float diffShopReturnFare;
	
	public Float getDeposit() {
		return deposit;
	}
	public void setDeposit(Float deposit) {
		this.deposit = deposit;
	}
	public Float getRent() {
		return rent;
	}
	public void setRent(Float rent) {
		this.rent = rent;
	}
	public Long getMileageLimits() {
		return mileageLimits;
	}
	public void setMileageLimits(Long mileageLimits) {
		this.mileageLimits = mileageLimits;
	}
	public Float getOverMileageFare() {
		return overMileageFare;
	}
	public void setOverMileageFare(Float overMileageFare) {
		this.overMileageFare = overMileageFare;
	}
	public Float getOverTimeFare() {
		return overTimeFare;
	}
	public void setOverTimeFare(Float overTimeFare) {
		this.overTimeFare = overTimeFare;
	}
	public Float getCarSendFare() {
		return carSendFare;
	}
	public void setCarSendFare(Float carSendFare) {
		this.carSendFare = carSendFare;
	}
	public Float getDiffShopReturnFare() {
		return diffShopReturnFare;
	}
	public void setDiffShopReturnFare(Float diffShopReturnFare) {
		this.diffShopReturnFare = diffShopReturnFare;
	}
	
}
