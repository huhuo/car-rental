package com.huhuo.cmbiz;

import org.springframework.test.context.ContextConfiguration;

import com.huhuo.integration.base.BaseTest;

@ContextConfiguration(locations=
	{"classpath:META-INF/resources/conf/car-module-biz/app-context.xml",
		"classpath:conf/**/app-context.xml"})
public class CarModuleBizTest extends BaseTest {
	
	
}
