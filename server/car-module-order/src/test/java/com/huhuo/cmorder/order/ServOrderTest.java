package com.huhuo.cmorder.order;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.huhuo.carservicecore.csm.order.ModelOrder;
import com.huhuo.cmorder.CarModuleOrderTest;
import com.huhuo.cmorder.order.IServOrder;
import com.huhuo.integration.db.mysql.Page;

public class ServOrderTest extends CarModuleOrderTest {

	@Resource(name="cmorderServOrder")
	private IServOrder iServOrder;
	
	@Test
	public void curd() {
		
	}
	
	@Test
	public void findModels() {
		List<ModelOrder> list = iServOrder.findModels(new Page<ModelOrder>(0L, 15L));
		print(list);
	}
	
}
