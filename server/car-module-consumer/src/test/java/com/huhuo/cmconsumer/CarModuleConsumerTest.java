package com.huhuo.cmconsumer;

import org.springframework.test.context.ContextConfiguration;

import com.huhuo.integration.base.BaseTest;

@ContextConfiguration(locations=
	{"classpath:META-INF/resources/conf/car-module-consumer/app-context.xml",
		"classpath:conf/**/app-context.xml"})
public class CarModuleConsumerTest extends BaseTest {
	
	
}
