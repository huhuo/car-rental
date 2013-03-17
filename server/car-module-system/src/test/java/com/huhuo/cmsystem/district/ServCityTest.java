package com.huhuo.cmsystem.district;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.huhuo.carservicecore.sys.district.ModelCity;
import com.huhuo.cmsystem.CarModuleSystemTest;
import com.huhuo.integration.db.mysql.Page;

public class ServCityTest extends CarModuleSystemTest {

	@Resource(name="cmcarServCity")
	private IServCity iServCity;
	
	@Test
	public void curd() {
		
	}
	
	@Test
	public void findModels() {
		List<ModelCity> list = iServCity.findModels(new Page(0, 15));
		print(list);
	}
	
}
