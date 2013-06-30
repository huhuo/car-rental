package com.huhuo.cmstatis.car;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.cmsystem.SystemBaseCtrl;


@Controller("cmstatisCtrlCar")
@RequestMapping(value="/cmstatis/car")
public class CtrlCar extends SystemBaseCtrl {
	
	protected String basePath = "/car-module-statis/car";
	
	@Resource(name = "cmstatisServCar")
	private IServCar iservCar;
	
	@RequestMapping(value="/index.do")
	public String index() {
		logger.debug("==> access car analysis index page");
		System.out.println(iservCar);
		return basePath + "/index";
	}
	
}
