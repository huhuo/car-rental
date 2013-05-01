package com.huhuo.carservicecore.db;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.huhuo.carservicecore.CarServiceCoreTest;
import com.huhuo.carservicecore.cust.car.IDaoCarType;
import com.huhuo.carservicecore.cust.car.ModelCarType;
import com.huhuo.carservicecore.sys.district.ModelCity;

public class GenericBaseExtenseDaoTest extends CarServiceCoreTest {

	@Resource(name = "carservicecoreDaoCarType")
	private IDaoCarType iDaoCarType;
	
	@Test
	public void queryForMapList() {
		String sql = "SELECT * FROM cust_car_type ct LEFT JOIN cust_charge_standard cs ON ct.chargeStandardId=cs.id LIMIT ?, ?";
//		List<Map<String, Object>> list = iDaoFileUpload.getJdbcTemplate().queryForList(sql, 0, 10);
//		print(list);
		print(iDaoCarType.count());
		print(iDaoCarType.queryForMapList(sql, 0, 10));
	}
	
	@Test
	public void queryForMap() {
		String sql = "SELECT * FROM cust_car_type WHERE id=?";
		print(iDaoCarType.queryForMap(sql, 1));
	}
	
	@Test
	public void queryForList() {
		String sql = "SELECT * FROM cust_car_type WHERE id=?";
		print(iDaoCarType.queryForList(sql, ModelCarType.class, 1));
		sql = "SELECT * FROM cust_car_type ct LEFT JOIN cust_charge_standard cs ON ct.chargeStandardId=cs.id LIMIT ?, ?";
		print(iDaoCarType.queryForList(sql, ModelCarTypeCar.class, 0, 10));
	}
	
	@Test
	public void queryForObject() {
		String sql = "SELECT * FROM cust_car_type WHERE id=?";
		print(iDaoCarType.queryForObject(sql, ModelCarType.class, 1));
		sql = "SELECT * FROM cust_car_type ct LEFT JOIN cust_charge_standard cs ON ct.chargeStandardId=cs.id LIMIT ?, ?";
		print(iDaoCarType.queryForObject(sql, ModelCarTypeCar.class, 0, 10));
	}
	
	@Test
	public void findList() {
		String sql = "SELECT * FROM cust_car_type WHERE id=?";
		print(iDaoCarType.findList(sql, 1));
		sql = "SELECT * FROM cust_car_type WHERE id IN (?,?)";
		print(iDaoCarType.findList(sql, 1, 2));
	}
	
	@Test
	public void findObject() {
		String sql = "SELECT * FROM cust_car_type WHERE id=?";
		print(iDaoCarType.findObject(sql, 1));
		// exception
		sql = "SELECT * FROM cust_car_type WHERE id IN (?,?)";
//		print(iDaoCarType.findObject(sql, 1, 2));
	}
	
	@Test
	public void queryForSingleColVal() {
		String sql = "SELECT count(*) FROM cust_car_type WHERE id IN (?,?)";
		print(iDaoCarType.queryForSingleColVal(sql, Long.class, 1, 2));
	}
	
	@Test
	public void find() {
		print(iDaoCarType.find(ModelCarType.class, 1));
		print(iDaoCarType.find(1));
		print(iDaoCarType.queryForList("select * from sys_city limit ?, ?", ModelCity.class, 0, 10));
	}
	
	@Test
	public void deleteBatch() {
		List<Long> ids = new ArrayList<Long>();
		ids.add(1L);
		ids.add(2L);
		ids.add(3L);
		print(iDaoCarType.deleteBatch(ids));
	}
	
	@Test
	public void deletePhysicalBatch() {
		List<Long> ids = new ArrayList<Long>();
		ids.add(1L);
		ids.add(2L);
		ids.add(3L);
		print(iDaoCarType.deletePhysicalBatch(ids));
	}
	
}

