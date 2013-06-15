package com.huhuo.cmcar.trace;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.cmsystem.SystemBaseCtrl;


@Controller("cmcarCtrlTrace")
@RequestMapping(value="/cmcar/trace")
public class CtrlTrace extends SystemBaseCtrl {
	
	protected String basePath = "/car-module-car/trace";
	
//	String url = "http://localhost:8080/spring-mvc/redirect?";
	String url = "http://222.76.126.217:88/loginServlet?username=3390&password=123456";
//	String url = "http://www.zuchechina.com/cmsystem/security/validation/login.do?username=admin&password=huhuotech100";
	
	/*************************************************************
	 * car trace
	 *************************************************************/
	@RequestMapping(value="/trace.do")
	public String traceTemp() {	// car trace management page
		// login
//		return "redirect:" + url;
		return basePath + "/index";
	}
}
