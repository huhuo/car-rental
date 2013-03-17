package com.huhuo.cmsystem.security;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.integration.base.BaseCtrl;

@Controller("cmsystemCtrlHome")
@RequestMapping("/cmsystem/security/home")
public class CtrlHome extends BaseCtrl {
	
	protected String basePath = "system-manage/";
	
	@RequestMapping(value="/frontpage.do")
	public String frontpage(Model model, HttpSession session){
		logger.debug("access page-home");
		
		return basePath + "frontpage";
	}

}
