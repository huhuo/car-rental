package com.huhuo.carservicecore.cust.car;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.huhuo.carservicecore.CarServiceCoreTest;
import com.huhuo.carservicecore.constant.Dictionary.Dict;
import com.huhuo.integration.util.TimeUtils;

public class DaoCarTest extends CarServiceCoreTest {

	@Autowired
	private IDaoCar iDaoCar;
	
	@Test
	public void crud() {
		// add
		ModelCar t = new ModelCar();
		t.setCarTypeId(32L);
		t.setColor(Dict.CUST_CAR_COLOR_green.getDicKey());
		t.setCreateTime(TimeUtils.offsetDate(-3, new Date()));
		t.setDrivedKilometer(3242134L);
		t.setEngineNo("34lsdf-3asfd3-43ds3a-fa");
		t.setGpsNo("sdfa-34ladsf3-adslf");
		t.setLicencePlate("aslf-dsfsag-0fasd");
		t.setLocation(34L);
		t.setOilMass(53454.34255234D);
		t.setStatus(Dict.CUST_CAR_STATUS_scrap.getDicKey());
		t.setStoreId(23L);
		t.setUpdateTime(new Date());
		t.setWarehouseId(32L);
		iDaoCar.add(t);
		ModelCar actual = iDaoCar.find(t.getId());
		Assert.assertEquals("failed to add ModelOrder", t, actual);
		// update
		t.setWarehouseId(34L);
		iDaoCar.update(t);
		actual = iDaoCar.find(t.getId());
		Assert.assertEquals("failed to update ModelOrder", t, actual);
		// delete
		iDaoCar.deletePhysical(t);
		actual = iDaoCar.find(t.getId());
		Assert.assertNull("failed to delete ModelOrder", actual);
	}
	
}
