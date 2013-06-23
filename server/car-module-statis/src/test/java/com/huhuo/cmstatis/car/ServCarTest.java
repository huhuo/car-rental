package com.huhuo.cmstatis.car;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.huhuo.carservicecore.cust.car.ModelCar;
import com.huhuo.cmstatis.CarModuleStatisTest;
import com.huhuo.cmstatis.car.IServCar;
import com.huhuo.integration.db.mysql.Page;

public class ServCarTest extends CarModuleStatisTest {

	@Resource(name="cmstatisServCar")
	private IServCar iServCar;
	
	@Test
	public void curd() {
		
	}
	
	@Test
	public void findModels() {
		List<ModelCar> list = iServCar.findModels(new Page<ModelCar>(0, 15));
		print(list);
	}
	
}
