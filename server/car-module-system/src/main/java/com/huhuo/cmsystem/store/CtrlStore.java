package com.huhuo.cmsystem.store;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
import com.huhuo.integration.base.BaseCtrl;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.util.ExtUtils;


@Controller("cmsystemCtrlStore")
@RequestMapping(value="/cmsystem/store")
public class CtrlStore extends BaseCtrl {
	
	protected String basePath = "/car-module-system";
	
	/*************************************************************
	 * store info management
	 *************************************************************/
	
	@RequestMapping(value="/index.do")
	public String index() {
		logger.debug("---> access order management page");
		return basePath + "/store/index";
	}
	
	@RequestMapping(value="/get.do")
	public void get(HttpServletResponse resp, Condition<ModelConsumer> condition) {
		logger.debug("---> server receive: condition={}", condition);
		write(ExtUtils.getJsonStore(null, 10), resp);
	}
	
}
