package com.huhuo.carservicecore.cust.car;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.huhuo.carservicecore.CarServiceCoreTest;
import com.huhuo.carservicecore.constant.Dictionary.Dict;
import com.huhuo.integration.util.TimeUtils;

public class DaoCarTypeTest extends CarServiceCoreTest {

	@Autowired
	private IDaoCarType iDaoCarType;
	
	@Test
	public void crud() {
		// add
		ModelCarType t = new ModelCarType();
		t.setCategory(Dict.CUST_CAR_TYPE_CATEGORY_coach.getDicKey());
		t.setChargeStandardId(234L);
		t.setCreateTime(TimeUtils.offsetDate(-3, new Date()));
		t.setDrivingRange(3214.324);
		t.setIconId(1L);
		t.setName("jeep coach");
		t.setSeating(32);
		t.setStatus(0);
		t.setTankCapacity(24234);
		t.setUpdateTime(new Date());
		iDaoCarType.add(t);
		ModelCarType actual = iDaoCarType.find(t.getId());
		Assert.assertEquals("failed to add ModelOrder", t, actual);
		// update
		t.setName("hello");
		iDaoCarType.update(t);
		actual = iDaoCarType.find(t.getId());
		Assert.assertEquals("failed to update ModelOrder", t, actual);
		// delete
		iDaoCarType.deletePhysical(t);
		actual = iDaoCarType.find(t.getId());
		Assert.assertNull("failed to delete ModelOrder", actual);
	}
	
	@Test
	public void testPhysicalDelete() {
		// add
		ModelCarType t = new ModelCarType();
		t.setCategory(Dict.CUST_CAR_TYPE_CATEGORY_coach.getDicKey());
		t.setChargeStandardId(234L);
		t.setCreateTime(TimeUtils.offsetDate(-3, new Date()));
		t.setDrivingRange(3214.324);
		t.setIconId(1L);
		t.setName("jeep coach");
		t.setSeating(32);
		t.setStatus(0);
		t.setTankCapacity(24234);
		t.setUpdateTime(new Date());
		iDaoCarType.add(t);
		iDaoCarType.delete(t);
		iDaoCarType.deletePhysical(t);
	}
	
}
