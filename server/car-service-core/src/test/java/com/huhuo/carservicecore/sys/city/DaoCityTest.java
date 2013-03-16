package com.huhuo.carservicecore.sys.city;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.huhuo.carservicecore.BaseTest;
import com.huhuo.carservicecore.sys.district.DaoCity;
import com.huhuo.carservicecore.sys.district.ModelCity;

public class DaoCityTest extends BaseTest {
	
	@Resource(name = "carservicecoreDaoCity")
	private DaoCity daoCity;
	
	@Test
	public void curd() throws Exception {
		// create
		ModelCity t = new ModelCity();
		t.setCityLevel(3);
		t.setCityName("睡啦发的^");
		t.setOrderNo(2);
		t.setProvinceId(7L);
		t.setSpelling("hahaha");
		daoCity.add(t);
		// retrieve
		ModelCity t2 = daoCity.find(ModelCity.class, t.getId());
		Assert.assertEquals(t.toString(), t2.toString());
		// update
		t2.setCityName("撒旦了丰盛的");
		daoCity.update(t2);
		ModelCity t3 = daoCity.find(ModelCity.class, t2.getId());
		Assert.assertEquals(t2.toString(), t3.toString());
		// delete
		daoCity.delete(t3);
		ModelCity t4 = daoCity.find(ModelCity.class, t3.getId());
		Assert.assertEquals(null, t4);
		System.out.println("--> test success!!!");
	}
	@Test
	public void count() throws Exception{
		print(daoCity.count());
	}
	@Test
	public void findById() throws Exception{
		ModelCity city = daoCity.find(ModelCity.class, 101010105);
		System.out.println(city);
		ModelCity city2 = daoCity.queryForObject("SELECT * FROM sys_city WHERE id=?", ModelCity.class, 101010105);
		print(city2);
	}
	@Test
	public void queryForObject() throws Exception{
		ModelCity t = daoCity.queryForObject("SELECT * FROM sys_city WHERE id=?", ModelCity.class, 101010105);
		print(t);
	}
	
	@Test
	public void findBeans() throws Exception{
		List<ModelCity> list = daoCity.findModels(ModelCity.class, 0, 2);
		print(list);
	}
	
	@Test
	public void queryForList() throws Exception{
		String sql = "select * from sys_city";
		List<ModelCity> list = daoCity.queryForList(sql, ModelCity.class);
		print(list);
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
		daoCity.addBatch(list);
	}
	
}
