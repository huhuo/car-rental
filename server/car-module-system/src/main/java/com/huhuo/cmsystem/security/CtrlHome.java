package com.huhuo.cmsystem.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.carservicecore.sys.user.ModelUser;
import com.huhuo.cmsystem.SystemBaseCtrl;
import com.huhuo.cmsystem.security.Session.SessionKey;

@Controller("cmsystemCtrlHome")
public class CtrlHome extends SystemBaseCtrl {
	
	protected String basePath = "/car-module-system";
	
	@RequestMapping(value="/")
	public String frontpage(Model model, HttpServletRequest req){
		logger.info("access frontpage");
		ModelUser user = getSession(req).get(SessionKey.USER);
		model.addAttribute("user", user);
		return basePath + "/frontpage";
	}
	
}
