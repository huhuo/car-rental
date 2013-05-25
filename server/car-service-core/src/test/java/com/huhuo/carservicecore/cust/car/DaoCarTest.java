package com.huhuo.carservicecore.cust.car;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.huhuo.carservicecore.CarServiceCoreTest;
import com.huhuo.carservicecore.cust.store.IDaoStore;
import com.huhuo.carservicecore.cust.store.ModelStore;
import com.huhuo.carservicecore.sys.dictionary.IDaoDictionary;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Dict;
import com.huhuo.integration.db.mysql.DictMgr;
import com.huhuo.integration.db.mysql.Dir;
import com.huhuo.integration.db.mysql.Order;
import com.huhuo.integration.util.TimeUtils;

public class DaoCarTest extends CarServiceCoreTest {

	@Autowired
	private IDaoCar iDaoCar;
	@Autowired
	private IDaoCarType iDaoCarType;
	@Autowired
	private IDaoStore iDaoStore;
	@Autowired
	private IDaoDictionary iDaoDictionary;
	@Autowired
	private IDaoCarLocation iDaoCarLocation;
	@Autowired
	
	@Test
	public void crud() {
		// add
		ModelCar t = new ModelCar();
		t.setCarTypeId(32L);
		t.setColor(ModelCar.COLOR_green.getKey());
		t.setCreateTime(TimeUtils.offsetDate(-3, new Date()));
		t.setDrivedKilometer(3242134L);
		t.setEngineNo("34lsdf-3asfd3-43ds3a-fa");
		t.setGpsNo("sdfa-34ladsf3-adslf");
		t.setLicencePlate("aslf-dsfsag-0fasd");
		t.setLocationId(34L);
		t.setOilMass(53454.34255234D);
		t.setStatus(ModelCar.STATUS_SCRAP.getKey());
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
	
	@Test
//	@Transactional
	public void dataInitialization() {
		// initialization
		iDaoCar.execute("TRUNCATE cust_car");
		// add batch data
		List<ModelCar> list = new ArrayList<ModelCar>();
		Condition<ModelCarType> condition = new Condition<ModelCarType>();
		condition.setOrderList(new Order("id", Dir.DESC));
		List<ModelCarType> carTypeList = iDaoCarType.findByCondition(condition);
		List<Dict> colorGroupList = DictMgr.get(ModelCar.GROUP_CUST_CAR_COLOR);
		Condition<ModelCarLocation> carLocCon = new Condition<ModelCarLocation>();
		carLocCon.setOrderList(new Order("id", Dir.DESC));
		List<ModelCarLocation> carLocConList = iDaoCarLocation.findByCondition(carLocCon);
		List<Dict> carStatusList = DictMgr.get(ModelCar.GROUP_CUST_CAR_STATUS);
		Condition<ModelStore> storeCon = new Condition<ModelStore>();
		storeCon.setOrderList(new Order("id", Dir.DESC));
		List<ModelStore> storeList = iDaoStore.findByCondition(storeCon);
		Random r = new Random();
		for(int i=0; i<12; i++) {
			ModelCar e = new ModelCar();
			e.setCarTypeId(carTypeList.get(r.nextInt(carTypeList.size())).getId());
			e.setColor(colorGroupList.get(r.nextInt(colorGroupList.size())).getKey());
			e.setDrivedKilometer(Math.abs(r.nextLong()));
			e.setEngineNo(UUID.randomUUID().toString());
			e.setGpsNo(UUID.randomUUID().toString());
			e.setLicencePlate(Long.toString(TimeUtils.offsetHour(r.nextInt(123), new Date()).getTime()));
			e.setLocationId(carLocConList.get(r.nextInt(carLocConList.size())).getId());
			e.setOilMass(r.nextDouble());
			e.setStatus(carStatusList.get(r.nextInt(carStatusList.size())).getKey());
			e.setStoreId(storeList.get(r.nextInt(storeList.size())).getId());
			e.setWarehouseId(storeList.get(r.nextInt(storeList.size())).getId());
			e.setCreateTime(new Date());
			e.setUpdateTime(new Date());
			list.add(e);
		}
		iDaoCar.addBatch(list);
		// initial other field
		iDaoDictionary.update("UPDATE cust_car SET status=?, createTime=?, updateTime=?", 1, new Date(), new Date());
	}
	
}
