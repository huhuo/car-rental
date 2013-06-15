package com.huhuo.carservicecore.sys.user;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.huhuo.carservicecore.CarServiceCoreTest;
import com.huhuo.integration.util.TimeUtils;

public class DaoUserTest extends CarServiceCoreTest {

	@Autowired
	private IDaoUser iDaoCarIncome;
	
	
	
	@Test
	public void crud() {
		// add
		ModelUser t = new ModelUser();
		t.setAddress("asdfasfdas");
		t.setBirthday(new Date());
		t.setEmail("fasdfl@aflasf");
		t.setGender(32134);
		t.setIdentityCardId("sadfasfdasdf234");
		t.setMobileNumber("314asldf3");
		t.setPassword("sadfasf34qfae");
		t.setStoreId(123L);
		t.setTelephone("31411234123");
		t.setUsername("wyx");
		t.setCreateTime(TimeUtils.offsetDate(-3, new Date()));
		t.setStatus(0);
		t.setUpdateTime(new Date());
		iDaoCarIncome.add(t);
		ModelUser actual = iDaoCarIncome.find(t.getId());
		Assert.assertEquals("failed to add ModelOrder", t, actual);
		// update
		t.setUsername("xjjjj");
		iDaoCarIncome.update(t);
		actual = iDaoCarIncome.find(t.getId());
		Assert.assertEquals("failed to update ModelOrder", t, actual);
		// delete
		iDaoCarIncome.deletePhysical(t);
		actual = iDaoCarIncome.find(t.getId());
		Assert.assertNull("failed to delete ModelOrder", actual);
	}
	
	@Test
	public void addBatch(){
		for (int i = 1; i <= 50; i++) {
			ModelUser t = new ModelUser();
			t.setAddress("厦门分店"+i);
			t.setBirthday(new Date());
			t.setEmail("huhuo@zuche"+1+".com");
			if(i%2==0){
				t.setGender(1);
			}else {
				t.setGender(2);
			}
			
			t.setIdentityCardId("45212318551221572"+i);
			t.setMobileNumber("1562784332"+i);
			t.setPassword("123456");
			t.setStoreId((long) i);
			t.setTelephone("1086357288");
			t.setUsername("刘能"+i);
			t.setCreateTime(TimeUtils.offsetDate(-3, new Date()));
			t.setStatus(1);
			t.setUpdateTime(new Date());
			iDaoCarIncome.add(t);
		}
	}
}
