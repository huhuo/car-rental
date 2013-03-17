package com.huhuo.cmorder;

import org.springframework.test.context.ContextConfiguration;

import com.huhuo.integration.base.BaseTest;

@ContextConfiguration(locations=
	{"classpath:META-INF/resources/conf/car-module-order/app-context.xml",
		"classpath:conf/**/app-context.xml"})
public class CarModuleOrderTest extends BaseTest {
	
	
}
