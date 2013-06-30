package com.huhuo.cmcar.car;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.huhuo.carservicecore.cust.car.ModelCar;
import com.huhuo.cmcar.CarModuleCarTest;
import com.huhuo.integration.db.mysql.Dict;
import com.huhuo.integration.db.mysql.DictMgr;
import com.huhuo.integration.db.mysql.Page;

public class ServCarTest extends CarModuleCarTest {

	@Resource(name="cmcarServCar")
	private IServCar iServCar;
	
	@Test
	public void curd() {
		
	}
	
	@Test
	public void findModels() {
		List<ModelCar> list = iServCar.findModels(new Page<ModelCar>(0, 15));
		print(list);
	}
	
	@Test
	public void testDictMgr() {
		ModelCar car = new ModelCar();
		print(car);
		List<Dict> list = DictMgr.get(ModelCar.GROUP_CUST_CAR_COLOR);
		print(list);
		List<Dict> match = DictMgr.match("sfff", "黄");
		print(match);
		Dict dict = DictMgr.get("ssa", 2);
		print(dict);
	}
	
}
