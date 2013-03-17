package com.huhuo.cmsystem.store;

import java.io.OutputStream;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
import com.huhuo.integration.base.BaseCtrl;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.exception.HuhuoException;
import com.huhuo.integration.util.ExtUtils;
import com.huhuo.integration.web.Message;
import com.huhuo.integration.web.Message.Status;


@Controller("cmsystemCtrlStore")
@RequestMapping(value="/cmsystem/store")
public class CtrlStore extends BaseCtrl {
	
	protected String basePath = "/car-module-system";
	
	
	/*************************************************************
	 * store info management
	 *************************************************************/
	
	@RequestMapping(value="/index.do")
	public String index() {
		logger.debug("access order management page");
		return basePath + "/index";
	}
	
	@RequestMapping(value="/get.do")
	public void get(Condition<ModelConsumer> condition, OutputStream out) {
		try {
			logger.debug("server receive: condition={}", condition);
			write(ExtUtils.getJsonStore(null, 10), out);
		} catch (HuhuoException e) {
			logger.warn(e.getMessage());
			write(new Message<String>(Status.FAILURE, e.getMessage()), out);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getFullStackTrace(e));
			write(new Message<String>(Status.ERROR, e.getMessage()), out);
		}
	}
	
}
