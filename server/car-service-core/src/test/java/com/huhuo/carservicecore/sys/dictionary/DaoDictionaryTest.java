package com.huhuo.carservicecore.sys.dictionary;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
	private IDaoDictionary<ModelDictionary> iDaoDictionary;
	
	@Test
	public void crud() {
		print(iDaoDictionary.findModels(0, 20));
	}
	@Test
	public void getGroupsBy() {
		print(iDaoDictionary.getGroupsBy(DictGroup.GENERAL_GENDER));
	}
	@Test
	public void getBy() {
		DictGroup valueOf = DictGroup.valueOf("GENERAL_GENDER");
		print(valueOf);
		print(iDaoDictionary.getBy(DictGroup.GENERAL_GENDER, 1));
	}
	@Test
	public void getBy2() {
		print(iDaoDictionary.getBy(Dict.GENERAL_GENDER_female));
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
	
}
