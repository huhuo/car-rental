package com.huhuo.carservicecore.csm.order;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.huhuo.carservicecore.CarServiceCoreTest;
import com.huhuo.integration.util.TimeUtils;

public class DaoOrderSnapshotTest extends CarServiceCoreTest {

	@Autowired
	private IDaoOrderSnapshot iDaoOrderSnapshot;
	
	@Test
	public void crud() {
		// add
		ModelOrderSnapshot t = new ModelOrderSnapshot();
		t.setOrderId(3L);
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
		iDaoOrderSnapshot.add(t);
		ModelOrderSnapshot actual = iDaoOrderSnapshot.find(t.getId());
		Assert.assertEquals("failed to add ModelOrder", t, actual);
		// update
		t.setMileageEnd(325L);
		iDaoOrderSnapshot.update(t);
		actual = iDaoOrderSnapshot.find(t.getId());
		Assert.assertEquals("failed to update ModelOrder", t, actual);
		// delete
		iDaoOrderSnapshot.deletePhysical(t);
		actual = iDaoOrderSnapshot.find(t.getId());
		Assert.assertNull("failed to delete ModelOrder", actual);
	}
	
}
