package com.huhuo.cmcar.car;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.huhuo.carservicecore.car.ModelCar;
import com.huhuo.cmcar.BaseTest;
import com.huhuo.integration.db.mysql.Page;

public class ServCarTest extends BaseTest {

	@Resource(name="cmcarServCar")
	private IServCar iServCar;
	
	@Test
	public void curd() {
		
	}
	
	@Test
	public void findModels() {
		List<ModelCar> list = iServCar.findModels(new Page(0, 15));
		print(list);
	}
	
}
