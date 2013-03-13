package com.huhuo.carservicecore.csm.charge;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.huhuo.carservicecore.BaseTest;
import com.huhuo.carservicecore.db.DataSourceContextHolder;
import com.huhuo.carservicecore.db.DataSourceType;

public class DaoChargeStandardTest extends BaseTest {

	@Resource(name = "carservicecoreDaoChargeStandard")
	private IDaoChargeStandard iDaoChargeStandard;
	
	@Test
	public void crud() {
		List<ModelChargeStandard> list = iDaoChargeStandard.findModels(0, 15);
		print(list);
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
