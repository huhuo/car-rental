package com.huhuo.cmorder.order;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.huhuo.carservicecore.order.ModelOrder;
import com.huhuo.cmorder.BaseTest;
import com.huhuo.cmorder.order.IServOrder;
import com.huhuo.integration.db.mysql.Page;

public class ServOrderTest extends BaseTest {

	@Resource(name="cmorderServOrder")
	private IServOrder iServOrder;
	
	@Test
	public void curd() {
		
	}
	
	@Test
	public void findModels() {
		List<ModelOrder> list = iServOrder.findModels(new Page(0, 15));
		print(list);
	}
	
}
