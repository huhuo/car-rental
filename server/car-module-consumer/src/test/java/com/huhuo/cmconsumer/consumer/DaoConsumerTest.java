package com.huhuo.cmconsumer.consumer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.huhuo.carservicecore.csm.consumer.IDaoConsumer;
import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
import com.huhuo.cmconsumer.CarModuleConsumerTest;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Page;

public class DaoConsumerTest extends CarModuleConsumerTest {

	@Resource(name="carservicecoreDaoConsumer")
	private IDaoConsumer idaoConsumer;
	
	@Test
	public void crud() {
		ModelConsumer t = new ModelConsumer();
		t.setUsername("收到了快递费");
		t.setEmail("wuyuxuan2014@gmail.com");
		Integer row = idaoConsumer.add(t);
		print(row);
	}
	
	
	@Test
	public void insertTestData() {
		List<ModelConsumer> list = new ArrayList<ModelConsumer>();
		for (int i = 0 ; i < 100; i ++) {
			ModelConsumer c = new ModelConsumer();
			c.setIdentityCardId(i+"");
			c.setUsername("三皮 " + i);
			c.setPassword("abcde" + i);
			c.setTelephone("10086" + i);
			c.setStatus(0);
			c.setTelephone("1383838383" + i);
			c.setEmail("user" + i + "@ihuhuo.com");
			c.setAddress("北京市海淀区农大南路万树园" + i + "号楼" + i + "单元");
			c.setZipcode("1001" + i);
			c.setQq("2323425"+ i);
			c.setEmergencyContact("三皮" + i);
			c.setGender(i % 2 == 0 ? 2 : 1);
			c.setNation("汉族");
			c.setBirthday(new Date());
			c.setLicenseNum("11110000" + i);
			c.setIntegral(1000 + i );
			c.setCreateTime(new Date());
			c.setUpdateTime(new Date());
			list.add(c);
		}
		idaoConsumer.addBatch(list);
	}
	
	@Test
	public void findModels() {
		List<ModelConsumer> list = idaoConsumer.findModels(ModelConsumer.class, null, null);
		print(list);
	}
	
	@Test
	public void findByCondition() {
		Condition<ModelConsumer> condition = new Condition<ModelConsumer>(null, null, null, new Page(0, 10));
		List<ModelConsumer> list = idaoConsumer.findByCondition(condition);
		print(list);
	}
	
}
