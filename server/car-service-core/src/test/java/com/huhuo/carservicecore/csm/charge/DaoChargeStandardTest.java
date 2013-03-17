package com.huhuo.carservicecore.csm.charge;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.huhuo.carservicecore.CarServiceCoreTest;
import com.huhuo.carservicecore.db.DataSourceContextHolder;
import com.huhuo.carservicecore.db.DataSourceType;

public class DaoChargeStandardTest extends CarServiceCoreTest {

	@Resource(name = "carservicecoreDaoChargeStandard")
	private IDaoChargeStandard iDaoChargeStandard;
	
	@Test
	public void crud() {	// create, retrieve, update, delete
		// create
		ModelChargeStandard t = new ModelChargeStandard();
		t.setCarSendFare(314.343F);
		t.setCreateTime(new Date());
		t.setDeposit(2000.34F);
		t.setDiffShopReturnFare(345.32F);
		t.setMileageLimits(32424L);
		t.setOverMileageFare(2343.93F);
		t.setOverTimeFare(230.53F);
		t.setRent(350.35F);
		t.setStatus(1);
		t.setUpdateTime(new Date());
		iDaoChargeStandard.add(t);
		// retrieve
		ModelChargeStandard actual = iDaoChargeStandard.find(t.getId());
		Assert.assertEquals("failed to add operation", t, actual);
		// update
		t.setRent(542.33F);
		iDaoChargeStandard.update(t);
		actual = iDaoChargeStandard.find(t.getId());
		Assert.assertEquals("failed to update operation", t, actual);
		// delete
		iDaoChargeStandard.delete(t);
		actual = iDaoChargeStandard.find(t.getId());
		Assert.assertNull("failed to delete operation", actual);
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
