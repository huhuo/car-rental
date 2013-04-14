package com.huhuo.cmsystem.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.cmsystem.SystemBaseCtrl;

@Controller("smCtrlSecurity")
@RequestMapping("/cmsystem/security/security")
public class CtrlSecurity extends SystemBaseCtrl {
	private String basePath = "system-manage/security/";
	
	@RequestMapping(value="/index.do")
	public String index(){
		return basePath + "index";
	}
}
