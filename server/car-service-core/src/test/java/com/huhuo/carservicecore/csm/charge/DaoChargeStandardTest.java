package com.huhuo.carservicecore.csm.charge;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.huhuo.carservicecore.BaseTest;

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
	
}
