package com.huhuo.cmstatis.turnover;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.cmsystem.SystemBaseCtrl;


@Controller("cmstatisCtrlTurnOverGeneral")
@RequestMapping(value="/cmstatis/turnover/general")
public class CtrlTurnOverGeneral extends SystemBaseCtrl {
	
	protected String basePath = "/car-module-statis/turnover";
	
	@Resource(name = "cmstatisServTurnover")
	private IServTurnover iServTurnover;
	
	@RequestMapping(value="/index.do")
	public String index() {
		logger.debug("---> access general analysis page");
		System.out.println(iServTurnover);
		return basePath + "/index";
	}
	
	/*************************************************************
	 * last month analysis
	 *************************************************************/
	@RequestMapping(value="/last-month.do")
	public String lastMonth() {
		logger.debug("---> access car trace page");
		return basePath + "/last-month";
	}
	
	/*************************************************************
	 * last quarter analysis
	 *************************************************************/
	@RequestMapping(value="/last-quarter.do")
	public String lastQuarter() {
		logger.debug("---> access car trace page");
		return basePath + "/last-quarter";
	}
	
}
