package com.huhuo.cmstatis;

import org.springframework.test.context.ContextConfiguration;

import com.huhuo.integration.base.BaseTest;

@ContextConfiguration(locations=
	{"classpath:META-INF/resources/conf/car-module-statis/app-context.xml",
		"classpath:conf/**/app-context.xml"})
public class CarModuleStatisTest extends BaseTest {
	
	
}
