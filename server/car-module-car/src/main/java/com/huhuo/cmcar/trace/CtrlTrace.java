package com.huhuo.cmcar.trace;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.cmsystem.SystemBaseCtrl;


@Controller("cmcarCtrlTrace")
@RequestMapping(value="/cmcar/trace")
public class CtrlTrace extends SystemBaseCtrl {
	
	protected String basePath = "/car-module-car";
	
	/*************************************************************
	 * car trace
	 *************************************************************/
	@RequestMapping(value="/trace.do")
	public String traceTemp() {	// car trace management page
		return basePath + "/trace/index";
	}
}
