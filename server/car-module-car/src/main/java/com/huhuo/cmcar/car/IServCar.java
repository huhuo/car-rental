package com.huhuo.cmcar.car;

import java.util.Date;

import com.huhuo.carservicecore.cust.car.ModelCar;
import com.huhuo.integration.base.IBaseExtenseServ;
import com.huhuo.integration.exception.ServException;


public interface IServCar extends IBaseExtenseServ<ModelCar> {
	
	/**
	 * subscribe a car, and it will be cancelled if exceed the expire time, this method is thread-safe
	 * @param car car to book
	 * @param expireTime the deadline
	 */
	void book(ModelCar car, Date expireTime);
	/**
	 * subscribe a car, and it will be cancelled if exceed the expire time
	 * @param car car to book
	 * @param delayTime relative expire time in hour, can't be negative, or will throw {@link ServException}
	 * @throws ServException with message "delayTime can't be negative"
	 */
	void book(ModelCar car, Integer delayTime);
	
}
