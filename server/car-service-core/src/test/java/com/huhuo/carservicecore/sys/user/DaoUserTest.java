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
		iDaoCarIncome.delete(t);
		actual = iDaoCarIncome.find(t.getId());
		Assert.assertNull("failed to delete ModelOrder", actual);
	}
	
}
