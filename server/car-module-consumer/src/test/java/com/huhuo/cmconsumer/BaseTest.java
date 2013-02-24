package com.huhuo.cmconsumer;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations="classpath:conf/car-module-consumer/app-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTest {
	
	protected <T> void print(List<T> list){
		if(list!=null){
			for(T ele: list){
				System.out.println(ele);
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
