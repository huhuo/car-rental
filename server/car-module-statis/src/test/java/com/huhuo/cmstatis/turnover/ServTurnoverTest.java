package com.huhuo.cmstatis.turnover;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;

import com.huhuo.carservicecore.csm.order.ModelOrder;
import com.huhuo.carservicecore.cust.store.ModelStore;
import com.huhuo.cmstatis.CarModuleStatisTest;
import com.huhuo.cmsystem.store.IServStore;
import com.huhuo.integration.util.TimeUtils;
import com.huhuo.integration.util.TimeUtils.HPattern;

public class ServTurnoverTest extends CarModuleStatisTest {

	@Resource(name="cmstatisServTurnover")
	private IServTurnover iServTurnover;
	
	@Resource(name="cmsystemServStore")
	private IServStore iServStore;
	
	@Test
	public void addTestCaseData() {
		List<ModelOrder> list = new ArrayList<ModelOrder>();
		Date today = new Date();
		for(Long storeId=0L; storeId<4; storeId++) {
			for(int j=0; j<380; j++) {
				ModelOrder order = new ModelOrder();
				order.setCreateTime(TimeUtils.offsetDate(-j, today));
				order.setStoreId(storeId + 1);
				order.setCarId(storeId + 1);
				order.setTotalFee(8000 * RandomUtils.nextDouble());
				list.add(order);
				order = new ModelOrder();
				order.setCreateTime(TimeUtils.offsetDate(-j, today));
				order.setStoreId(storeId + 1);
				order.setCarId(storeId + 1);
				order.setTotalFee(8000 * RandomUtils.nextDouble());
				list.add(order);
			}
		}
		iServTurnover.addBatch(list);
	}
	
	@Test
	public void addStore() {
		ModelStore t = new ModelStore();
		t.setId(1L);
		t.setName("北京分店");
		t.setAddress("海龙大厦");
		t.setTelephone("01065895868");
		iServStore.add(t);
		t.setId(2L);
		t.setName("福建分店");
		t.setAddress("望海大厦");
		t.setTelephone("05065895868");
		iServStore.add(t);
		t.setId(3L);
		t.setName("深圳分店");
		t.setAddress("深圳大厦");
		t.setTelephone("04065895868");
		iServStore.add(t);
		t.setId(4L);
		t.setName("上海分店");
		t.setAddress("上海大厦");
		t.setTelephone("02065895868");
		iServStore.add(t);
	}
	
	@Test
	public void getSumTotalFeeByStore() {
		Date begin = TimeUtils.parse("2013-06-10 17:36:40", HPattern.LONG);
		Date end = TimeUtils.parse("2013-06-29 11:15:57", HPattern.LONG);
		List<Map<String, Object>> list = iServTurnover.getSumTotalFeeByStore(begin, end);
		print(list);
	}
	@Test
	public void getSumTotalFeeByDate() {
		Date begin = TimeUtils.parse("2013-06-10 17:36:40", HPattern.LONG);
		Date end = TimeUtils.parse("2013-06-29 11:15:57", HPattern.LONG);
		List<Map<String, Object>> list = iServTurnover.getSumTotalFeeByDate(begin, end);
		print(list);
	}
	@Test
	public void getSumTotalFeeByWeek() {
		Date begin = TimeUtils.parse("2013-04-13 17:36:40", HPattern.LONG);
		Date end = TimeUtils.parse("2013-06-29 11:15:57", HPattern.LONG);
		List<Map<String, Object>> list = iServTurnover.getSumTotalFeeByWeek(begin, end);
		print(list);
	}
	@Test
	public void getSumTotalFeeByMonth() {
		Date begin = TimeUtils.parse("2012-06-10 17:36:40", HPattern.LONG);
		Date end = TimeUtils.parse("2013-06-29 11:15:57", HPattern.LONG);
		List<Map<String, Object>> list = iServTurnover.getSumTotalFeeByMonth(begin, end);
		print(list);
	}
	
}
