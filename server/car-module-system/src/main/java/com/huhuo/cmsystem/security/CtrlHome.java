package com.huhuo.cmsystem.security;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.cmsystem.SystemBaseCtrl;

@Controller("cmsystemCtrlHome")
public class CtrlHome extends SystemBaseCtrl {
	
	protected String basePath = "/car-module-system";
	
	@RequestMapping(value="/")
	public String frontpage(Model model, HttpSession session){
		
		logger.info("access frontpage");
		
		return basePath + "/frontpage";
	}
	
}
