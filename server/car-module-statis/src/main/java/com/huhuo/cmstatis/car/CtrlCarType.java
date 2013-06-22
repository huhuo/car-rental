package com.huhuo.cmstatis.car;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.cmsystem.SystemBaseCtrl;


@Controller("cmstatisCtrlCarType")
@RequestMapping(value="/cmstatis/car/cartype")
public class CtrlCarType extends SystemBaseCtrl {
	
	protected String basePath = "/car-module-statis/car/cartype";
	
	@Resource(name = "cmstatisServCar")
	private IServCar iservCar;
	
	@RequestMapping(value="/index.do")
	public String index() {
		logger.debug("==> access car type analysis index page");
		System.out.println(iservCar);
		return basePath + "/index";
	}
	
	/*************************************************************
	 * last 12 weeks analysis
	 *************************************************************/
	@RequestMapping(value="/last-week/12.do")
	public String lastWeek12() {
		logger.debug("==> lastWeek12 analysis");
		return basePath + "/car";
	}
	
	/*************************************************************
	 * last year analysis
	 *************************************************************/
	@RequestMapping(value="/last-year.do")
	public String lastYear() {
		logger.debug("---> lastYear analysis");
		return basePath + "/cartype";
	}
	
}
