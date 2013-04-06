package com.huhuo.cmcar.cartype;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.huhuo.carservicecore.constant.Dictionary.Dict;
import com.huhuo.carservicecore.cust.car.ModelCarType;
import com.huhuo.carservicecore.cust.car.ModelChargeStandard;
import com.huhuo.cmcar.CarModuleCarTest;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Page;

public class ServCarTypeTest extends CarModuleCarTest {

	@Resource(name = "cmcarServCarType")
	private IServCarType iServCarType;

	@Test
	public void curd() {
		// add
		ModelCarType t = new ModelCarType();
		t.setCategory(Dict.CUST_CAR_TYPE_CATEGORY_coach.getDicKey());
		t.setChargeStandardId(234L);
		t.setDrivingRange(3214.324);
		t.setIcon("htt://www.google.com");
		t.setName("jeep coach");
		t.setSeating(32);
		t.setTankCapacity(24234);
		iServCarType.add(t);
		ModelCarType actual = iServCarType.find(t.getId());
		Assert.assertEquals("failed to add ModelOrder", t, actual);
		// update
		t.setName("hello");
		iServCarType.update(t);
		actual = iServCarType.find(t.getId());
		Assert.assertEquals("failed to update ModelOrder", t, actual);
		// delete
		iServCarType.deletePhysical(t);
		actual = iServCarType.find(t.getId());
		Assert.assertNull("failed to delete ModelOrder", actual);
	}

	@Test
	public void addInternalObj() {
		ModelCarType t = getInstance();
		iServCarType.add(t);
		Condition<ModelCarType> condition = new Condition<ModelCarType>();
		condition.setT(t);
		print(iServCarType.findByCondition(condition, true));
	}
	
	private ModelCarType getInstance() {
		ModelCarType t = new ModelCarType();
		t.setCategory(Dict.CUST_CAR_TYPE_CATEGORY_coach.getDicKey());
		t.setChargeStandardId(234L);
		t.setDrivingRange(3214.324);
		t.setIcon("htt://www.google.com");
		t.setName("jeep coach");
		t.setSeating(32);
		t.setTankCapacity(24234);
		ModelChargeStandard chargeStandard = new ModelChargeStandard();
		chargeStandard.setCarSendFare(3234.32);
		chargeStandard.setDeposit(3242.32);
		chargeStandard.setDiffShopReturnFare(3144.23);
		chargeStandard.setMileageLimits(31424124L);
		chargeStandard.setPremium(32341.32);
		chargeStandard.setRent(2342.32);
		chargeStandard.setOverMileageFare(32369.32);
		chargeStandard.setOverTimeFare(8934234.32);
		t.setChargeStandard(chargeStandard);
		return t;
	}
	
	@Test
	public void addBatch() {
		List<ModelCarType> list = new ArrayList<ModelCarType>();
		for(int i=0; i<52; i++) {
			list.add(getInstance());
		}
		iServCarType.addBatch(list);
	}
	
	@Test
	public void dataGenerate() {
		for(int i=0; i<52; i++) {
			iServCarType.add(getInstance());
		}
	}
	
	@Test
	public void findModels() {
		List<ModelCarType> list = iServCarType.findModels(new Page<ModelCarType>(0, 15));
		print(list);
	}

}
