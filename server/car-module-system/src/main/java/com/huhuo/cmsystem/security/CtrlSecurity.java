package com.huhuo.cmsystem.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.integration.base.BaseCtrl;

@Controller("smCtrlSecurity")
@RequestMapping("/cmsystem/security/security")
public class CtrlSecurity extends BaseCtrl {
	private String basePath = "system-manage/security/";
	
	@RequestMapping(value="/index.do")
	public String index(){
		return basePath + "index";
	}
}
