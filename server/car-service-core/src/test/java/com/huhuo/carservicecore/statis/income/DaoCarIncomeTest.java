package com.huhuo.carservicecore.statis.income;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.huhuo.carservicecore.CarServiceCoreTest;
import com.huhuo.integration.util.TimeUtils;

public class DaoCarIncomeTest extends CarServiceCoreTest {

	@Autowired
	private IDaoCarIncome iDaoCarIncome;
	
	@Test
	public void crud() {
		// add
		ModelCarIncome t = new ModelCarIncome();
		t.setCarId(234L);
		t.setIncome(314234.1342);
		t.setPay(3242.234);
		t.setCreateTime(TimeUtils.offsetDate(-3, new Date()));
		t.setStatus(0);
		t.setUpdateTime(new Date());
		iDaoCarIncome.add(t);
		ModelCarIncome actual = iDaoCarIncome.find(t.getId());
		Assert.assertEquals("failed to add ModelOrder", t, actual);
		// update
		t.setCarId(333L);
		iDaoCarIncome.update(t);
		actual = iDaoCarIncome.find(t.getId());
		Assert.assertEquals("failed to update ModelOrder", t, actual);
		// delete
		iDaoCarIncome.deletePhysical(t);
		actual = iDaoCarIncome.find(t.getId());
		Assert.assertNull("failed to delete ModelOrder", actual);
	}
	
}
