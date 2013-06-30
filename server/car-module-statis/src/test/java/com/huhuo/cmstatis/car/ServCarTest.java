package com.huhuo.cmstatis.car;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;

import com.huhuo.carservicecore.cust.car.ModelCar;
import com.huhuo.cmstatis.CarModuleStatisTest;
import com.huhuo.integration.db.mysql.Page;
import com.huhuo.integration.util.TimeUtils;

public class ServCarTest extends CarModuleStatisTest {

	@Resource(name="cmstatisServCar")
	private IServCar iServCar;
	
	@Test
	public void addTestCaseData() {
		List<ModelCar> list = new ArrayList<ModelCar>();
		Date today = new Date();
		for(Long carTypeId=0L; carTypeId<4; carTypeId++) {
			for(int j=0; j<25; j++) {
				int size = RandomUtils.nextInt(8);
				for(int count=0; count<size; count++) {
					ModelCar car = new ModelCar();
					car.setCreateTime(TimeUtils.offsetMonth(-j, today));
					car.setStoreId(carTypeId + 1);
					car.setCarTypeId(carTypeId + 1);
					list.add(car);
				}
			}
		}
		iServCar.addBatch(list);
	}
	
	@Test
	public void findModels() {
		List<ModelCar> list = iServCar.findModels(new Page<ModelCar>(0, 15));
		print(list);
	}
	
	@Test
	public void getCountTrendMonthCartype() {
		List<Map<String, Object>> list = iServCar.
				getCountTrendMonthCartype(TimeUtils.offsetMonth(-6, new Date()), new Date());
		print(list);
	}
	@Test
	public void getCountTrendMonthStore() {
		List<Map<String, Object>> list = iServCar.
				getCountTrendMonthStore(TimeUtils.offsetMonth(-6, new Date()), new Date());
		print(list);
	}
	
}
