package com.huhuo.cmsystem.store;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.huhuo.carservicecore.cust.store.ModelStore;
import com.huhuo.cmsystem.CarModuleSystemTest;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Page;

public class ServStoreTest extends CarModuleSystemTest {

	@Resource(name = "cmsystemServStore")
	private IServStore iServStore;

	@Test
	public void curd() {
		// add
		ModelStore t = new ModelStore();
		t.setAddress("厦门市复兴路1067号");
		t.setCreateTime(new Date());
		t.setId(1l);
		t.setManagerId(100l);
		t.setName("厦门分店");
		t.setStatus(1);
		t.setUpdateTime(new Date());
		t.setTelephone("18612257368");
		iServStore.add(t);
		ModelStore actual = iServStore.find(t.getId());
		Assert.assertEquals("failed to add ModelOrder", t, actual);
		// update
		t.setName("hello");
		iServStore.update(t);
		actual = iServStore.find(t.getId());
		Assert.assertEquals("failed to update ModelOrder", t, actual);
		// delete
		iServStore.deletePhysical(t);
		actual = iServStore.find(t.getId());
		Assert.assertNull("failed to delete ModelOrder", actual);
	}

	@Test
	public void addInternalObj() {
		ModelStore t = getInstance();
		iServStore.add(t);
		Condition<ModelStore> condition = new Condition<ModelStore>();
		condition.setT(t);
		print(iServStore.findByCondition(condition, true));
	}
	private ModelStore getInstance() {
		ModelStore t = new ModelStore();
		t.setAddress("厦门市复兴路1067号");
		t.setCreateTime(new Date());
		t.setId(1l);
		t.setManagerId(100l);
		t.setName("厦门分店");
		t.setStatus(1);
		t.setUpdateTime(new Date());
		t.setTelephone("18612257368");
		List<Map<String,Object>> list = iServStore.multiQuery(t, null);
		for(Map<String, Object> map : list) {
			System.out.println("----------->>>>"+map.get("freeNum"));
			System.out.println("----------->>>>"+map.get("rentNum"));
				t.setFreeNum(Integer.parseInt(map.get("freeNum").toString()));
				t.setFreeNum(Integer.parseInt(map.get("rentNum").toString()));
				t.setAddress(map.get("address").toString());
				t.setId(Long.parseLong(map.get("freeNum").toString()));
				t.setTelephone(map.get("telephone").toString());
				t.setName(map.get("Name").toString());
		}
		return t;
	}
	
	@Test
	public void getInstance1() {
		ModelStore t = new ModelStore();
		t.setAddress("厦门市复兴路1067号");
		t.setCreateTime(new Date());
		t.setId(1l);
		t.setManagerId(100l);
		t.setName("厦门分店");
		t.setStatus(1);
		t.setUpdateTime(new Date());
		t.setTelephone("18612257368");
		List<Map<String,Object>> list = iServStore.multiQuery(t, null);
		for(Map<String, Object> map : list) {
			System.out.println("----------->>>>"+map.get("freeNum"));
			System.out.println("----------->>>>"+map.get("rentNum"));
			t.setFreeNum(Integer.parseInt(map.get("freeNum").toString()));
			t.setFreeNum(Integer.parseInt(map.get("rentNum").toString()));
			t.setAddress(map.get("address").toString());
			t.setId(Long.parseLong(map.get("freeNum").toString()));
			t.setTelephone(map.get("telephone").toString());
			t.setName(map.get("Name").toString());
		}
		
	}
	
	@Test
	public void addBatch() {
		List<ModelStore> list = new ArrayList<ModelStore>();
		for(int i=0; i<52; i++) {
			ModelStore instance = getInstance();
			instance.setManagerId(100l+i);
			list.add(instance);
		}
		iServStore.addBatch(list);
	}
	
	@Test
	public void dataGenerate() {
		for(int i=0; i<52; i++) {
			iServStore.add(getInstance());
		}
	}
	
	@Test
	public void findModels() {
		List<ModelStore> list = iServStore.findModels(new Page<ModelStore>(0L, 15L));
		print(list);
	}

}
