package com.huhuo.cmconsumer.consumer;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.beanutils.MethodUtils;
import org.junit.Test;
import org.springframework.asm.commons.Method;

import com.huhuo.carservicecore.csm.consumer.IDaoConsumer;
import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
import com.huhuo.cmconsumer.CarModuleConsumerTest;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Page;
import com.huhuo.integration.util.TimeUtils;

public class DaoConsumerTest extends CarModuleConsumerTest {

	@Resource(name="carservicecoreDaoConsumer")
	private IDaoConsumer idaoConsumer;
	
	@Test
	public void crud() {
	
		
		
		List<ModelConsumer> list = new ArrayList<ModelConsumer>();
		for(int i=1;i<=365;i++){
			Random random = new Random();
			int nextInt = random.nextInt(31);
			for(int j=0;j<=nextInt; j++){
				ModelConsumer t = new ModelConsumer();
				t.setAddress("北京海淀");
				t.setAge(23);
				t.setAvatar("eret");
				t.setCreateTime(TimeUtils.offsetDate(-(new Random().nextInt(365)), new Date()));
				t.setEmergencyContact("中国共产党");
				t.setEmergencyTel("010543643");
				t.setGender(1);
				t.setUsername("收到了快递费");
				t.setEmail("wuyuxuan2014@gmail.com");
				list.add(t);
			}
		
		}
		idaoConsumer.addBatch(list);
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
		Condition<ModelConsumer> condition = new Condition<ModelConsumer>(null, null, null, new Page(0L, 10L));
		List<ModelConsumer> list = idaoConsumer.findByCondition(condition);
		print(list);
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		for(int i=0; i<=365; i++){
		System.out.println(-(new Random().nextInt(365)));
		}
	}
}