package com.huhuo.carservicecore.csm.order;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.huhuo.carservicecore.CarServiceCoreTest;
import com.huhuo.integration.util.TimeUtils;

public class DaoOrderTest extends CarServiceCoreTest {

	@Autowired
	private IDaoOrder iDaoOrder;
	
	@Test
	public void crud() {
		// add
		ModelOrder t = new ModelOrder();
		t.setCarId(23L);
		t.setCarPlanRetTime(TimeUtils.offsetDate(2, new Date()));
		t.setCarRentTime(TimeUtils.offsetDate(-1, new Date()));
		t.setCarRetTime(TimeUtils.offsetDate(1, new Date()));
		t.setConsumerId(23L);
		t.setCreateTime(TimeUtils.offsetDate(-1, new Date()));
		t.setMileageBegin(3142L);
		t.setMileageEnd(323L);
		t.setOilmassBegin(4312343.23D);
		t.setOilmassEnd(6623.2D);
		t.setStatus(1);
		t.setTotalFee(141234.32D);
		t.setUpdateTime(new Date());
		iDaoOrder.add(t);
		ModelOrder actual = iDaoOrder.find(t.getId());
		Assert.assertEquals("failed to add ModelOrder", t, actual);
		// update
		t.setMileageEnd(325L);
		iDaoOrder.update(t);
		actual = iDaoOrder.find(t.getId());
		Assert.assertEquals("failed to update ModelOrder", t, actual);
		// delete
		iDaoOrder.delete(t);
		actual = iDaoOrder.find(t.getId());
		Assert.assertNull("failed to delete ModelOrder", actual);
	}
	
}
