package com.huhuo.carservicecore.cust.store;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.huhuo.carservicecore.CarServiceCoreTest;
import com.huhuo.integration.util.TimeUtils;

public class DaoStoreTest extends CarServiceCoreTest {

	@Autowired
	private IDaoStore iDaoStore;
	
	@Test
	public void crud() {
		// add
		ModelStore t = new ModelStore();
		t.setAddress("load aslkda");
		t.setManagerId(323L);
		t.setName("andy");
		t.setCreateTime(TimeUtils.offsetDate(-3, new Date()));
		t.setStatus(0);
		t.setUpdateTime(new Date());
		iDaoStore.add(t);
		ModelStore actual = iDaoStore.find(t.getId());
		Assert.assertEquals("failed to add ModelOrder", t, actual);
		// update
		t.setName("cookie");
		iDaoStore.update(t);
		actual = iDaoStore.find(t.getId());
		Assert.assertEquals("failed to update ModelOrder", t, actual);
		// delete
		iDaoStore.delete(t);
		actual = iDaoStore.find(t.getId());
		Assert.assertNull("failed to delete ModelOrder", actual);
	}
	
}
