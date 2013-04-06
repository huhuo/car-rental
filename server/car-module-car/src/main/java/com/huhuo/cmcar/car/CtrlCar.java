package com.huhuo.cmcar.car;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.carservicecore.cust.car.ModelCar;
import com.huhuo.carservicecore.cust.car.ModelCarType;
import com.huhuo.integration.base.BaseCtrl;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Page;
import com.huhuo.integration.util.ExtUtils;


@Controller("cmcarCtrlCar")
@RequestMapping(value="/cmcar/car")
public class CtrlCar extends BaseCtrl {
	
	protected String basePath = "/car-module-car";
	
	@Resource(name = "cmcarServCar")
	private IServCar iservCar;
	
	/*************************************************************
	 * car management
	 *************************************************************/
	
	@RequestMapping(value="/index.do")
	public String index() {
		logger.debug("---> access car management page");
		return basePath + "/car/index";
	}
	
	@RequestMapping(value="/get.do")
	public void get(HttpServletResponse resp, Condition<ModelCarType> condition){
		logger.debug("---> server receive: condition={}", condition);
//			condition.setPage(new Page(0, 30));
//			List<ModelConsumer> list = servConsumer.findByCondition(condition);
		List<ModelCar> list = iservCar.findModels(new Page<ModelCar>(0, 10));
		write(ExtUtils.getJsonStore(list, list.size()), resp);
	}
	
	/*************************************************************
	 * car trace business
	 *************************************************************/
	
	@RequestMapping(value="/trace/index.do")
	public String trace() {	// car trace management page
		logger.debug("---> access car trace page");
		return basePath + "/car/index";
	}
}
