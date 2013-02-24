package com.huhuo.cmcar.cartype;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.huhuo.carservicecore.car.ModelCarType;
import com.huhuo.cmcar.BaseTest;
import com.huhuo.integration.db.mysql.Page;

public class ServCarTypeTest extends BaseTest {

	@Resource(name="cmcarServCarType")
	private IServCarType iServCarType;
	
	@Test
	public void curd() {
		
	}
	
	@Test
	public void findModels() {
		List<ModelCarType> list = iServCarType.findModels(new Page(0, 15));
		print(list);
	}
	
}
