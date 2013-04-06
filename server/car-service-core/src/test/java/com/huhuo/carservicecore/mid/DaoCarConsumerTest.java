package com.huhuo.carservicecore.mid;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.huhuo.carservicecore.CarServiceCoreTest;
import com.huhuo.integration.util.TimeUtils;

public class DaoCarConsumerTest extends CarServiceCoreTest {

	@Autowired
	private IDaoCarConsumer iDaoCarConsumer;
	
	@Test
	public void crud() {
		// add
		ModelCarConsumer t = new ModelCarConsumer();
		t.setCarId(234L);
		t.setConsumerId(324L);
		t.setCreateTime(TimeUtils.offsetDate(-3, new Date()));
		t.setStatus(0);
		t.setUpdateTime(new Date());
		iDaoCarConsumer.add(t);
		ModelCarConsumer actual = iDaoCarConsumer.find(t.getId());
		Assert.assertEquals("failed to add ModelOrder", t, actual);
		// update
		t.setCarId(333L);
		iDaoCarConsumer.update(t);
		actual = iDaoCarConsumer.find(t.getId());
		Assert.assertEquals("failed to update ModelOrder", t, actual);
		// delete
		iDaoCarConsumer.deletePhysical(t);
		actual = iDaoCarConsumer.find(t.getId());
		Assert.assertNull("failed to delete ModelOrder", actual);
	}
	
}
