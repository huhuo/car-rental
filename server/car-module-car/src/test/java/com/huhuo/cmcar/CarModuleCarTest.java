package com.huhuo.cmcar;

import org.springframework.test.context.ContextConfiguration;

import com.huhuo.integration.base.BaseTest;

@ContextConfiguration(locations=
	{"classpath:META-INF/resources/conf/car-module-car/app-context.xml",
		"classpath:conf/**/app-context.xml"})
public class CarModuleCarTest extends BaseTest {
	
	
}
