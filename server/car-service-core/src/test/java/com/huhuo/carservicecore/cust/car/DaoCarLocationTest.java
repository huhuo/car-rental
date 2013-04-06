package com.huhuo.carservicecore.cust.car;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.huhuo.carservicecore.CarServiceCoreTest;
import com.huhuo.integration.util.TimeUtils;

public class DaoCarLocationTest extends CarServiceCoreTest {

	@Autowired
	private IDaoCarLocation iDaoCarLocation;
	
	@Test
	public void crud() {
		// add
		ModelCarLocation t = new ModelCarLocation();
		t.setCreateTime(TimeUtils.offsetDate(-3, new Date()));
		t.setHolderStoreId(34L);
		t.setLongitude(324.3141);
		t.setLatitude(24234.134124);
		t.setStatus(0);
		t.setUpdateTime(new Date());
		iDaoCarLocation.add(t);
		ModelCarLocation actual = iDaoCarLocation.find(t.getId());
		Assert.assertEquals("failed to add ModelOrder", t, actual);
		// update
		t.setLongitude(3214.234);
		iDaoCarLocation.update(t);
		actual = iDaoCarLocation.find(t.getId());
		Assert.assertEquals("failed to update ModelOrder", t, actual);
		// delete
		iDaoCarLocation.deletePhysical(t);
		actual = iDaoCarLocation.find(t.getId());
		Assert.assertNull("failed to delete ModelOrder", actual);
	}
	
}
