package com.huhuo.cmsystem.security.user;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
import com.huhuo.cmsystem.SystemBaseCtrl;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.util.ExtUtils;


@Controller("cmsystemCtrlUser")
@RequestMapping(value="/cmsystem/user")
public class CtrlUser extends SystemBaseCtrl {
	
	protected String basePath = "/car-module-system/security";
	
	
	/*************************************************************
	 * store info management
	 *************************************************************/
	
	@RequestMapping(value="/index.do")
	public String index() {
		logger.debug("---> access user management page");
		return basePath + "/user/index";
	}
	
	@RequestMapping(value="/get.do")
	public void get(HttpServletResponse resp, Condition<ModelConsumer> condition) {
		logger.debug("---> server receive: condition={}", condition);
		write(ExtUtils.getJsonStore(null, 10), resp);
	}
	
	
	/*************************************************************
	 * person info management, password modify, person info modify, etc
	 *************************************************************/
	
	@RequestMapping(value="/person.do")
	public String person() {
		logger.debug("---> access person management page");
		return basePath + "/user/person";
	}
}
