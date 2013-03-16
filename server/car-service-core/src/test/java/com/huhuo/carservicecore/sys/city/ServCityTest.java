package com.huhuo.carservicecore.sys.city;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.huhuo.carservicecore.BaseTest;
import com.huhuo.carservicecore.sys.district.IServSysCity;
import com.huhuo.carservicecore.sys.district.ModelCity;

public class ServCityTest extends BaseTest {
	
	@Resource(name = "carservicecoreServSysCity")
	private IServSysCity iServSysCity;
	
	@Test
	public void curd() throws Exception {
		// create
		ModelCity t = new ModelCity();
		t.setCityLevel(3);
		t.setCityName("睡啦发的^");
		t.setOrderNo(2);
		t.setProvinceId(7L);
		t.setSpelling("hahaha");
		iServSysCity.add(t);
		// retrieve
		ModelCity t2 = iServSysCity.find(t.getId());
		Assert.assertEquals(t.toString(), t2.toString());
		// update
		t2.setCityName("撒旦了丰盛的");
		iServSysCity.update(t2);
		ModelCity t3 = iServSysCity.find(t2.getId());
		Assert.assertEquals(t2.toString(), t3.toString());
		// delete
		iServSysCity.delete(t3);
		ModelCity t4 = iServSysCity.find(t3.getId());
		Assert.assertEquals(null, t4);
		System.out.println("--> test success!!!");
	}
	
	@Test
	public void testAddBatch() {
		List<ModelCity> list = new ArrayList<ModelCity>();
		for(int i=0; i<10; i++) {
			ModelCity t = new ModelCity();
			t.setCityName("测试" + i);
			t.setSpelling("ceshile" + i);
			list.add(t);
		}
		print(iServSysCity.addBatch(list));
	}
	
}
