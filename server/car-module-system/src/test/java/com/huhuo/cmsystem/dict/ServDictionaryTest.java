package com.huhuo.cmsystem.dict;

import javax.annotation.Resource;

import org.junit.Test;

import com.huhuo.cmsystem.CarModuleSystemTest;

public class ServDictionaryTest extends CarModuleSystemTest {

	@Resource(name = "cmsystemServDictionary")
	private IServDictionary iServDictionary;
	
	@Test
	public void crud() {
//		print(iServDictionary.find(1));
		print(iServDictionary.findByCondition(null));
	}
	
}
