package com.huhuo.flowerservice.core;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.huhuo.integration.config.GlobalConstant.DateFormat;

@ContextConfiguration(locations="classpath:app-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTest {

	/**
	 * util method printing a list
	 * @param list
	 */
	public <E> void print(List<E> list) {
		if(list == null) {
			System.out.println("------> null");
		} else if(list.size() == 0) {
			System.out.println("------> empty");
		} else {
			for(E e : list) {
				String output = JSONObject.toJSONStringWithDateFormat(e, DateFormat.LONG_FORMAT);
				System.out.println("------> " + output);
			}
		}
	}
	/**
	 * @see #print(List)
	 */
	public void print(Object... objs) {
		if(objs == null) {
			System.out.println("------> null");
		} else {
			List<Object> list = new ArrayList<Object>();
			for(Object obj : objs) {
				list.add(obj);
			}
			print(list);
		}
	}
	
}
