package com.huhuo.cmstatis.car;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.cmsystem.SystemBaseCtrl;


@Controller("cmstatisCtrlCar")
@RequestMapping(value="/cmstatis/car/car")
public class CtrlCar extends SystemBaseCtrl {
	
	protected String basePath = "/car-module-statis/car/car";
	
	@Resource(name = "cmstatisServCar")
	private IServCar iservCar;
	
	@RequestMapping(value="/index.do")
	public String index() {
		logger.debug("==> access car analysis index page");
		System.out.println(iservCar);
		return basePath + "/index";
	}
	
	/*************************************************************
	 * last 12 weeks analysis
	 *************************************************************/
	@RequestMapping(value="/last-week/12.do")
	public String lastWeek12() {
		logger.debug("==> lastWeek12 analysis");
		return basePath + "/last-week";
	}
	
	/*************************************************************
	 * last year analysis
	 *************************************************************/
	@RequestMapping(value="/last-year.do")
	public String lastYear() {
		logger.debug("==> lastYear analysis");
		return basePath + "/last-year";
	}
	
}
