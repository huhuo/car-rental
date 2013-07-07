package com.huhuo.carservicecore.sys.file;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.huhuo.carservicecore.CarServiceCoreTest;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Page;

public class DaoFileUploadTest extends CarServiceCoreTest {
	
	@Resource(name = "carservicecoreDaoFileUpload")
	private IDaoFileUpload<ModelFileUpload> iDaoFileUpload;
	
	@Test
	public void crud() throws Exception {
		// create
		ModelFileUpload t = new ModelFileUpload();
		t.setCreateTime(new Date());
		t.setMd5("9b75840b9b92e6853a603597b23b654c");
		t.setName("快递费撒了");
		t.setPath("/huhuo/data/car-module-system");
		t.setStatus(1);
		t.setType(2);
		t.setUpdateTime(new Date());
		iDaoFileUpload.add(t);
		// retrieve
		ModelFileUpload t2 = iDaoFileUpload.find(ModelFileUpload.class, t.getId());
		Assert.assertEquals(t.toString(), t2.toString());
		// update
		t2.setName(t.getName() + "^_^");
		iDaoFileUpload.update(t2);
		ModelFileUpload t3 = iDaoFileUpload.find(ModelFileUpload.class, t2.getId());
		Assert.assertEquals(t2.toString(), t3.toString());
		// delete
		iDaoFileUpload.deletePhysical(t3);
		ModelFileUpload t4 = iDaoFileUpload.find(ModelFileUpload.class, t3.getId());
		Assert.assertEquals(null, t4);
		System.out.println("--> test success!!!");
	}
	
	@Test
	public void testAddBatch() {
		List<ModelFileUpload> list = new ArrayList<ModelFileUpload>();
		for(int i=0; i<55; i++) {
			ModelFileUpload t = new ModelFileUpload();
			t.setCreateTime(new Date());
			t.setMd5("9b75840b9b92e6853a603597b23b654c");
			t.setName("快递费撒了");
			t.setPath("/huhuo/data/car-module-system");
			t.setStatus(1);
			t.setType(2);
			t.setUpdateTime(new Date());
			list.add(t);
		}
		iDaoFileUpload.addBatch(list);
	}
	
	@Test
	public void queryForList() {
		Condition<ModelFileUpload> condition = new Condition<ModelFileUpload>();
		condition.setPage(new Page<ModelFileUpload>(0L, 10L));
		List<ModelFileUpload> list = iDaoFileUpload.findByCondition(condition);
		print(list);
	}
	
}
