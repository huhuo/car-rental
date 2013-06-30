package com.huhuo.cmstatis.turnover;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.cmsystem.SystemBaseCtrl;


@Controller("cmstatisCtrlTurnOver")
@RequestMapping(value="/cmstatis/turnover")
public class CtrlTurnOver extends SystemBaseCtrl {
	
	protected String basePath = "/car-module-statis/turnover";
	
	@RequestMapping(value="/index.do")
	public String index() {
		logger.info("==> access general analysis page");
		return basePath + "/index";
	}
	
}
