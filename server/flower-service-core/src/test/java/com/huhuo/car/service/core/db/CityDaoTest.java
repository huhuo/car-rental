package com.huhuo.car.service.core.db;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.huhuo.car.service.core.BaseTest;
import com.huhuo.car.service.core.db.City;
import com.huhuo.car.service.core.db.CityDao;

public class CityDaoTest extends BaseTest {
	
	@Test
	public void curd() throws Exception {
		// create
		City t = new City();
		t.setCityLevel(3);
		t.setCityName("睡啦发的^");
		t.setOrderNo(2);
		t.setProvinceId(7L);
		t.setSpelling("hahaha");
		CityDao dao = new CityDao();
		Long pk = dao.insert(t);
		// retrieve
		City t2 = dao.findById(City.class, pk);
		Assert.assertEquals(t.toString(), t2.toString());
		// update
		t2.setCityName("撒旦了丰盛的");
		dao.update(t2);
		City t3 = dao.findById(City.class, t2.getId());
		Assert.assertEquals(t2.toString(), t3.toString());
		// delete
		dao.delete(t3);
		City t4 = dao.findById(City.class, t3.getId());
		Assert.assertEquals(null, t4);
		System.out.println("--> test success!!!");
	}
	@Test
	public void findById() throws Exception{
		CityDao dao = new CityDao();
		City city = dao.findById(City.class, 101010105);
		System.out.println(city);
		City city2 = dao.queryForObject("SELECT * FROM sys_city WHERE id=?", City.class, 101010105);
		System.out.println(city2);
	}
	@Test
	public void queryForObject() throws Exception{
		CityDao dao = new CityDao();
		City t = dao.queryForObject("SELECT * FROM sys_city WHERE id=?", City.class, 101010105);
		System.out.println(t);
	}
	
	@Test
	public void findBeans() throws Exception{
		CityDao dao = new CityDao();
		List<City> list = dao.findModels(City.class, 0, 2);
		print(list);
	}
	
	@Test
	public void queryForList() throws Exception{
		String sql = "select * from sys_city";
		CityDao dao = new CityDao();
		List<City> list = dao.queryForList(sql, City.class);
		System.out.println(list);
		
	}
	
}
