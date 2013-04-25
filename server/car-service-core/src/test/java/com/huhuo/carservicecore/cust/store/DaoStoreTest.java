package com.huhuo.carservicecore.cust.store;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.huhuo.carservicecore.CarServiceCoreTest;
import com.huhuo.integration.util.TimeUtils;

public class DaoStoreTest extends CarServiceCoreTest {

	@Autowired
	private IDaoStore iDaoStore;
	
	@Test
	public void crud() {
		// add
		ModelStore t = new ModelStore();
		t.setAddress("load aslkda");
		t.setManagerId(323L);
		t.setName("andy");
		t.setCreateTime(TimeUtils.offsetDate(-3, new Date()));
		t.setStatus(0);
		t.setUpdateTime(new Date());
		iDaoStore.add(t);
		ModelStore actual = iDaoStore.find(t.getId());
		Assert.assertEquals("failed to add ModelOrder", t, actual);
		// update
		t.setName("cookie");
		iDaoStore.update(t);
		actual = iDaoStore.find(t.getId());
		Assert.assertEquals("failed to update ModelOrder", t, actual);
		// delete
		iDaoStore.deletePhysical(t);
		actual = iDaoStore.find(t.getId());
		Assert.assertNull("failed to delete ModelOrder", actual);
	}
	
	@Test
	public void mulpleQuery() {
		String sql = "SELECT table1.*, table2.rentedNum ";
		sql += "FROM (SELECT cs.*, COUNT(cc.id) total FROM cust_store cs LEFT JOIN cust_car cc ON cs.id=cc.storeId GROUP BY cc.storeId) table1, ";
		sql += "(SELECT cs.*, COUNT(cc.id) rentedNum FROM cust_store cs LEFT JOIN cust_car cc ON cs.id=cc.storeId WHERE cc.status=2 OR cc.status IS NULL GROUP BY cc.storeId) table2 ";
		sql += "WHERE table1.id=table2.id ORDER BY id";
		List<Map<String,Object>> list = iDaoStore.queryForMapList(sql);
		print(list);
		
		List<ModelStore> findList = iDaoStore.findList(sql);
		print(findList);
	}
	
}
