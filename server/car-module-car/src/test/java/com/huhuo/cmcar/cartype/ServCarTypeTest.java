package com.huhuo.cmcar.cartype;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.huhuo.carservicecore.cust.car.DaoCarType;
import com.huhuo.carservicecore.cust.car.ModelCarType;
import com.huhuo.cmcar.CarModuleCarTest;
import com.huhuo.integration.db.mysql.Page;

public class ServCarTypeTest extends CarModuleCarTest {

	@Resource(name="cmcarServCarType")
	private IServCarType iServCarType;
	
	@Test
	public void curd() {
		
//		/** 车型名称 **/
//		private String name;
//		/** 图片地址（静态资源） **/
//		private String icon;
//		/** 车辆类别（字典表字段，组名：cust_car_type_category，1、轿车；2、越野汽车；3、客车；4、货车；5、自卸汽车；6、牵引汽车；7、专用汽车） **/
//		private Integer category;
//		/** 座位数 **/
//		private Integer seating;
//		/** 油箱容量（单位：升） **/
//		private Integer tankCapacity;
//		/** 可行驶里程数 **/
//		private Double drivingRange;
//		/** 车型使用的收费标准id（与csm_charge_standard表一对一） **/
//		private Long chargeStandardId;
		ModelCarType type = new ModelCarType();
		type.setName("奔驰s6");
		type.setIcon("");
		type.setCategory(1);
		type.setSeating(4);
		type.setTankCapacity(50);
		type.setDrivingRange(300D);
		type.setChargeStandard(null);
		DaoCarType dct = new DaoCarType();
		dct.add(type);
		
		
	}
	
	@Test
	public void findModels() {
		List<ModelCarType> list = iServCarType.findModels(new Page(0, 15));
		print(list);
	}
	
}
