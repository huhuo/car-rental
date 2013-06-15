package com.huhuo.carservicecore.sys.dictionary;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.huhuo.carservicecore.CarServiceCoreTest;
import com.huhuo.carservicecore.constant.Dictionary.ModelDict;
import com.huhuo.carservicecore.constant.Dictionary.ModelDictGroup;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Dir;
import com.huhuo.integration.db.mysql.Group;
import com.huhuo.integration.db.mysql.Order;
import com.huhuo.integration.db.mysql.Page;
import com.huhuo.integration.db.mysql.Where;
import com.huhuo.integration.db.mysql.Where.Join;

public class DaoDictionaryTest extends CarServiceCoreTest {

	@Autowired
	private IDaoDictionary iDaoDictionary;
	
	@Test
	public void crud() {
		print(iDaoDictionary.findModels(0, 20));
	}
	@Test
	public void getGroupsBy() {
		print(iDaoDictionary.getGroupsBy(ModelDictGroup.GENERAL_SYS_GENDER));
	}
	@Test
	public void getBy() {
		ModelDictGroup valueOf = ModelDictGroup.valueOf("GENERAL_SYS_GENDER");
		print(valueOf);
		print(iDaoDictionary.getBy(ModelDictGroup.GENERAL_SYS_GENDER, 1));
	}
	@Test
	public void getBy2() {
		print(iDaoDictionary.getBy(ModelDict.GENERAL_SYS_GENDER_female));
	}
	@Test
	public void findByCondition() {
		ModelDictionary t = new ModelDictionary();
//		t.setGroupDisplayName("性别");
//		t.setGroupDisplayName("");
//		t.setDictKey(1);
//		t.setGroupName("GENERAL_GENDER");
		List<Group> groupList = new ArrayList<Group>();
//		groupList.add(new Group("groupName", Dir.ASC));
//		groupList.add(new Group("dictKey", Dir.DESC));
		List<Order> orderList = new ArrayList<Order>();
//		orderList.add(new Order("dictDisplayName", Dir.DESC));
//		orderList.add(new Order("orderNo", Dir.ASC));
		Condition<ModelDictionary> condition = new Condition<ModelDictionary>(t, groupList, orderList, new Page<ModelDictionary>());
		condition.setWhereList(new Where("orderNo IN(?,?,?)", Join.OR, 9, 5, 11));
//		condition.setWhereList(new Where("comment LIKE ? AND dictValue IN (?,?)", "%ha%", 3, 2), new Where("orderNo IN(?,?,?)", Join.OR, 9, 5, 11));
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
		// GENERAL_SYS_GENDER
		iDaoDictionary.add(new ModelDictionary(ModelDictGroup.GENERAL_SYS_GENDER.getGroupName(), 
				ModelDictGroup.GENERAL_SYS_GENDER.getGroupDisplayName(), 
				1, "1", "男", 1, null));
		iDaoDictionary.add(new ModelDictionary(ModelDictGroup.GENERAL_SYS_GENDER.getGroupName(), 
				ModelDictGroup.GENERAL_SYS_GENDER.getGroupDisplayName(), 
				2, "2", "女", 2, null));
		
		// initial other field
		iDaoDictionary.update("UPDATE sys_dictionary SET status=?, createTime=?, updateTime=?", 1, new Date(), new Date());
	}
	
}
