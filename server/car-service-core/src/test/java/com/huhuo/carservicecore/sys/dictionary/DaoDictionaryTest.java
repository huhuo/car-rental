package com.huhuo.carservicecore.sys.dictionary;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.huhuo.carservicecore.CarServiceCoreTest;
import com.huhuo.carservicecore.constant.Dictionary.Dict;
import com.huhuo.carservicecore.constant.Dictionary.DictGroup;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Dir;
import com.huhuo.integration.db.mysql.Group;
import com.huhuo.integration.db.mysql.Order;
import com.huhuo.integration.db.mysql.Page;

public class DaoDictionaryTest extends CarServiceCoreTest {

	@Autowired
	private IDaoDictionary iDaoDictionary;
	
	@Test
	public void crud() {
		print(iDaoDictionary.findModels(0, 20));
	}
	@Test
	public void getGroupsBy() {
		print(iDaoDictionary.getGroupsBy(DictGroup.GENERAL_SYS_GENDER));
	}
	@Test
	public void getBy() {
		DictGroup valueOf = DictGroup.valueOf("GENERAL_GENDER");
		print(valueOf);
		print(iDaoDictionary.getBy(DictGroup.GENERAL_SYS_GENDER, 1));
	}
	@Test
	public void getBy2() {
		print(iDaoDictionary.getBy(Dict.GENERAL_SYS_GENDER_female));
	}
	@Test
	public void findByCondition() {
		ModelDictionary t = new ModelDictionary();
		t.setGroupDisplayName("性别");
		t.setGroupDisplayName("");
		t.setDictKey(1);
		t.setGroupName("GENERAL_GENDER");
		List<Group> groupList = new ArrayList<Group>();
//		groupList.add(new Group("groupName", Dir.ASC));
//		groupList.add(new Group("dictKey", Dir.DESC));
		List<Order> orderList = new ArrayList<Order>();
//		orderList.add(new Order("dictDisplayName", Dir.DESC));
//		orderList.add(new Order("orderNo", Dir.ASC));
		Condition<ModelDictionary> condition = new Condition<ModelDictionary>(t, groupList, orderList, new Page<ModelDictionary>());
		print(iDaoDictionary.findByCondition(condition));
	}
	@Test
	public void countByCondition() {
		ModelDictionary t = new ModelDictionary();
		t.setGroupDisplayName("性别");
		t.setGroupDisplayName("");
		t.setDictKey(1);
		t.setGroupName("GENERAL_GENDER");
		List<Group> groupList = new ArrayList<Group>();
		groupList.add(new Group("groupName", Dir.ASC));
		groupList.add(new Group("dictKey", Dir.DESC));
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(new Order("dictDisplayName", Dir.DESC));
		orderList.add(new Order("orderNo", Dir.ASC));
		Condition<ModelDictionary> condition = new Condition<ModelDictionary>(t, groupList, orderList, new Page<ModelDictionary>());
		print(iDaoDictionary.countByCondition(condition));
	}
	
	@Test
	@Transactional
	public void initialization() {
		// delete add data in table sys_dictionary
		iDaoDictionary.execute("TRUNCATE sys_dictionary");
//		System.out.println(1/0);
		// CUST_CAR_COLOR
		iDaoDictionary.add(new ModelDictionary(DictGroup.CUST_CAR_COLOR.getGroupName(), 
				DictGroup.CUST_CAR_COLOR.getGroupDisplayName(), 
				1, "1", "银色", 1, null));
		iDaoDictionary.add(new ModelDictionary(DictGroup.CUST_CAR_COLOR.getGroupName(), 
				DictGroup.CUST_CAR_COLOR.getGroupDisplayName(), 
				2, "2", "银灰色", 2, null));
		iDaoDictionary.add(new ModelDictionary(DictGroup.CUST_CAR_COLOR.getGroupName(), 
				DictGroup.CUST_CAR_COLOR.getGroupDisplayName(), 
				3, "3", "灰色", 3, null));
		iDaoDictionary.add(new ModelDictionary(DictGroup.CUST_CAR_COLOR.getGroupName(), 
				DictGroup.CUST_CAR_COLOR.getGroupDisplayName(), 
				4, "3", "红色", 4, null));
		iDaoDictionary.add(new ModelDictionary(DictGroup.CUST_CAR_COLOR.getGroupName(), 
				DictGroup.CUST_CAR_COLOR.getGroupDisplayName(), 
				5, "5", "黄色", 5, null));
		iDaoDictionary.add(new ModelDictionary(DictGroup.CUST_CAR_COLOR.getGroupName(), 
				DictGroup.CUST_CAR_COLOR.getGroupDisplayName(), 
				6, "6", "白色", 6, null));
		iDaoDictionary.add(new ModelDictionary(DictGroup.CUST_CAR_COLOR.getGroupName(), 
				DictGroup.CUST_CAR_COLOR.getGroupDisplayName(), 
				7, "7", "橙色", 7, null));
		iDaoDictionary.add(new ModelDictionary(DictGroup.CUST_CAR_COLOR.getGroupName(), 
				DictGroup.CUST_CAR_COLOR.getGroupDisplayName(), 
				8, "8", "绿色", 8, null));
		// CUST_CAR_STATUS
		iDaoDictionary.add(new ModelDictionary(DictGroup.CUST_CAR_STATUS.getGroupName(), 
				DictGroup.CUST_CAR_STATUS.getGroupDisplayName(), 
				1, "1", "在库待租", 1, null));
		iDaoDictionary.add(new ModelDictionary(DictGroup.CUST_CAR_STATUS.getGroupName(), 
				DictGroup.CUST_CAR_STATUS.getGroupDisplayName(), 
				2, "2", "已经出租", 2, null));
		// CUST_CAR_TYPE_CATEGORY
		iDaoDictionary.add(new ModelDictionary(DictGroup.CUST_CAR_TYPE_CATEGORY.getGroupName(), 
				DictGroup.CUST_CAR_TYPE_CATEGORY.getGroupDisplayName(), 
				1, "1", "轿车", 1, null));
		iDaoDictionary.add(new ModelDictionary(DictGroup.CUST_CAR_TYPE_CATEGORY.getGroupName(), 
				DictGroup.CUST_CAR_TYPE_CATEGORY.getGroupDisplayName(), 
				2, "2", "越野汽车", 2, null));
		iDaoDictionary.add(new ModelDictionary(DictGroup.CUST_CAR_TYPE_CATEGORY.getGroupName(), 
				DictGroup.CUST_CAR_TYPE_CATEGORY.getGroupDisplayName(), 
				3, "3", "客车", 3, null));
		iDaoDictionary.add(new ModelDictionary(DictGroup.CUST_CAR_TYPE_CATEGORY.getGroupName(), 
				DictGroup.CUST_CAR_TYPE_CATEGORY.getGroupDisplayName(), 
				4, "4", "货车", 4, null));
		iDaoDictionary.add(new ModelDictionary(DictGroup.CUST_CAR_TYPE_CATEGORY.getGroupName(), 
				DictGroup.CUST_CAR_TYPE_CATEGORY.getGroupDisplayName(), 
				5, "5", "自卸汽车", 5, null));
		iDaoDictionary.add(new ModelDictionary(DictGroup.CUST_CAR_TYPE_CATEGORY.getGroupName(), 
				DictGroup.CUST_CAR_TYPE_CATEGORY.getGroupDisplayName(), 
				6, "6", "牵引汽车", 6, null));
		iDaoDictionary.add(new ModelDictionary(DictGroup.CUST_CAR_TYPE_CATEGORY.getGroupName(), 
				DictGroup.CUST_CAR_TYPE_CATEGORY.getGroupDisplayName(), 
				7, "7", "专用汽车", 7, null));
		// GENERAL_SYS_GENDER
		iDaoDictionary.add(new ModelDictionary(DictGroup.GENERAL_SYS_GENDER.getGroupName(), 
				DictGroup.GENERAL_SYS_GENDER.getGroupDisplayName(), 
				1, "1", "男", 1, null));
		iDaoDictionary.add(new ModelDictionary(DictGroup.GENERAL_SYS_GENDER.getGroupName(), 
				DictGroup.GENERAL_SYS_GENDER.getGroupDisplayName(), 
				2, "2", "女", 2, null));
		
		// initial other field
		iDaoDictionary.update("UPDATE sys_dictionary SET status=?, createTime=?, updateTime=?", 1, new Date(), new Date());
	}
	
}
