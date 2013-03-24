package com.huhuo.cmconsumer.consumer;

import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
import com.huhuo.integration.base.BaseCtrl;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Group;
import com.huhuo.integration.db.mysql.Page;
import com.huhuo.integration.exception.HuhuoException;
import com.huhuo.integration.util.ExtUtils;
import com.huhuo.integration.web.Message;
import com.huhuo.integration.web.Message.Status;


@Controller("cmconsumerCtrlConsumer")
@RequestMapping(value="/cmconsumer/consumer")
public class CtrlConsumer extends BaseCtrl {
	
	protected String basePath = "/car-module-consumer";
	
	@Resource(name = "cmconsumerServConsumer")
	private IServConsumer iservConsumer;
	
	/*************************************************************
	 * consumer management
	 *************************************************************/
	
	@RequestMapping(value="/index.do")
	public String consumerIndex() {
		logger.debug("access consumer management page");
		return basePath + "/consumer/index";
	}
	
	@RequestMapping(value="/get.do")
	public void get(Condition<ModelConsumer> condition, OutputStream out){
		try {
			logger.debug("server receive: condition={}", condition);
//			condition.setPage(new Page(0, 30));
//			List<ModelConsumer> list = servConsumer.findByCondition(condition);
			List<ModelConsumer> list = iservConsumer.findModels(new Page(0, 10));
			write(ExtUtils.getJsonStore(list, list.size()), out);
		} catch (HuhuoException e) {
			logger.warn(e.getMessage());
			write(new Message<String>(Status.FAILURE, e.getMessage()), out);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getFullStackTrace(e));
			write(new Message<String>(Status.ERROR, e.getMessage()), out);
		}
	}
	/**
	 * register customer
	 * @param condition
	 * @param out
	 */
	@RequestMapping(value="/register.do")
	public void registerCustomer(String username) {
		setEncoding("UTF-8");
		logger.debug("--->");
		logger.debug("username:" + username);
		logger.debug("<---");
		
	}
	
	/*************************************************************
	 * loyalty point management
	 *************************************************************/
	
	@RequestMapping(value="/points.do")
	public String pointsIndex() {
		logger.debug("access consumer points management page");
		return basePath + "/points/index";
	}
	
}
