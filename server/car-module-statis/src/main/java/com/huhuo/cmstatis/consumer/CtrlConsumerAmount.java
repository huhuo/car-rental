package com.huhuo.cmstatis.consumer;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.cmsystem.SystemBaseCtrl;


@Controller("cmstatisCtrlConsumerAmount")
@RequestMapping(value="/cmstatis/consumer/amount")
public class CtrlConsumerAmount extends SystemBaseCtrl {
	
	protected String basePath = "/car-module-statis/consumer/amount";
	
	@Resource(name = "cmstatisServConsumer")
	private IServConsumer iServConsumer;
	
	@RequestMapping(value="/index.do")
	public String index(Model model) {
		logger.debug("==> access general analysis page");
		System.out.println(iServConsumer);
		return basePath + "/index";
	}
	
	/*************************************************************
	 * last month analysis
	 *************************************************************/
	@RequestMapping(value="/last-month.do")
	public String lastMonth() {
		logger.debug("==> access car trace page");
		return basePath + "/last-month";
	}
	
	/*************************************************************
	 * last quarter analysis
	 *************************************************************/
	@RequestMapping(value="/last-quarter.do")
	public String lastQuarter() {
		logger.debug("==> access car trace page");
		return basePath + "/last-quarter";
	}
	
	/*************************************************************
	 * last year analysis
	 *************************************************************/
	@RequestMapping(value="/last-year.do")
	public String lastYear() {
		logger.debug("==> access car trace page");
		return basePath + "/last-year";
	}
}
