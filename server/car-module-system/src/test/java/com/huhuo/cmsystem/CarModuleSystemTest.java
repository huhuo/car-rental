package com.huhuo.cmsystem;

import org.springframework.test.context.ContextConfiguration;

import com.huhuo.integration.base.BaseTest;

@ContextConfiguration(locations=
	{"classpath:META-INF/resources/conf/car-module-system/app-context.xml",
		"classpath:conf/*/app-context.xml"})
public class CarModuleSystemTest extends BaseTest {
	
}
