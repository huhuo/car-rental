package com.huhuo.carservicecore.cust.car;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.huhuo.carservicecore.CarServiceCoreTest;
import com.huhuo.carservicecore.cust.car.IDaoChargeStandard;
import com.huhuo.carservicecore.cust.car.ModelChargeStandard;
import com.huhuo.carservicecore.db.DataSourceContextHolder;
import com.huhuo.carservicecore.db.DataSourceType;

public class DaoChargeStandardTest extends CarServiceCoreTest {

	@Resource(name = "carservicecoreDaoChargeStandard")
	private IDaoChargeStandard iDaoChargeStandard;
	
	@Test
	public void crud() {	// create, retrieve, update, delete
		// create
		ModelChargeStandard t = new ModelChargeStandard();
		t.setCarSendFare(314.343D);
		t.setCreateTime(new Date());
		t.setDeposit(2000.34D);
		t.setDiffShopReturnFare(345.32D);
		t.setMileageLimits(32424L);
		t.setOverMileageFare(2343.93D);
		t.setOverTimeFare(230.53D);
		t.setRent(350.35D);
		t.setStatus(1);
		t.setUpdateTime(new Date());
		iDaoChargeStandard.add(t);
		// retrieve
		ModelChargeStandard actual = iDaoChargeStandard.find(t.getId());
		Assert.assertEquals("failed to add operation", t, actual);
		// update
		t.setRent(542.33D);
		iDaoChargeStandard.update(t);
		actual = iDaoChargeStandard.find(t.getId());
		Assert.assertEquals("failed to update operation", t, actual);
		// delete
		iDaoChargeStandard.delete(t);
		actual = iDaoChargeStandard.find(t.getId());
		Assert.assertNull("failed to delete operation", actual);
	}
	
	@Test
	public void addBatchTest() {
		List<ModelChargeStandard> list = new ArrayList<ModelChargeStandard>();
		for(int i=0; i<100; i++) {
			// create
			ModelChargeStandard t = new ModelChargeStandard();
			t.setCarSendFare(314.343D);
			t.setCreateTime(new Date());
			t.setDeposit(2000.34D);
			t.setDiffShopReturnFare(345.32D);
			t.setMileageLimits(32424L);
			t.setOverMileageFare(2343.93D);
			t.setOverTimeFare(230.53D);
			t.setRent(350.35D);
			t.setStatus(1);
			t.setUpdateTime(new Date());
			list.add(t);
		}
		iDaoChargeStandard.addBatch(list);
	}
	
	@Test
	public void findModels() {
		List<ModelChargeStandard> list = iDaoChargeStandard.findModels(0, 20);
		print(list);
	}
	
	@Test
	public void routingDSTest() {
		// test multiple data source
		DataSourceContextHolder.setDataSourceType(DataSourceType.CUSTOMER_CHITONG);
		print(iDaoChargeStandard.findModels(0, 10));
		// another data source
		DataSourceContextHolder.setDataSourceType(DataSourceType.CUSTOMER_2);
		print(iDaoChargeStandard.findModels(0, 10));
	}
	
}
