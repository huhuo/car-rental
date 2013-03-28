package com.huhuo.cmcar.cartype;

import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.carservicecore.cust.car.ModelCarType;
import com.huhuo.integration.base.BaseCtrl;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Page;
import com.huhuo.integration.exception.HuhuoException;
import com.huhuo.integration.util.ExtUtils;
import com.huhuo.integration.web.Message;
import com.huhuo.integration.web.Message.Status;


@Controller("cmcarCtrlCarType")
@RequestMapping(value="/cmcar/cartype")
public class CtrlCarType extends BaseCtrl {
	
	protected String basePath = "/car-module-car";
	
	@Resource(name = "cmcarServCarType")
	private IServCarType iservCarType;
	
	/*************************************************************
	 * car type management
	 *************************************************************/
	
	@RequestMapping(value="/index.do")
	public String index() {	// car type management page
		logger.debug("---> access car type management page");
		return basePath + "/cartype/index";
	}
	
	@RequestMapping(value="/condition/get.do")
	public void get(OutputStream out, Condition<ModelCarType> condition, ModelCarType t){
		try {
			condition.setT(t);
			logger.debug("---> server receive: condition={}", condition);
			List<ModelCarType> records = iservCarType.findByCondition(condition);
			write(ExtUtils.getJsonStore(records, records.size()), out);
		} catch (HuhuoException e) {
			logger.warn(e.getMessage());
			write(new Message<String>(Status.FAILURE, e.getMessage()), out);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getFullStackTrace(e));
			write(new Message<String>(Status.ERROR, e.getMessage()), out);
		}
	}
	@RequestMapping(value="/huhuo/form.do")
	public void huhuoForm(String first, String second, OutputStream out){
		try {
			logger.debug("---> server receive: first={}, second={}", first, second);
			List<ModelCarType> list = iservCarType.findModels(new Page(0, 10));
			write(ExtUtils.getJsonStore(list, list.size()), out);
		} catch (HuhuoException e) {
			logger.warn(e.getMessage());
			write(new Message<String>(Status.FAILURE, e.getMessage()), out);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getFullStackTrace(e));
			write(new Message<String>(Status.ERROR, e.getMessage()), out);
		}
	}
}
