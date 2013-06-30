package com.huhuo.cmstatis.turnover;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.huhuo.cmsystem.SystemBaseCtrl;
import com.huhuo.integration.util.TimeUtils;


@Controller("cmstatisCtrlTurnOverGeneral")
@RequestMapping(value="/cmstatis/turnover/general")
public class CtrlTurnOverGeneral extends SystemBaseCtrl {
	
	protected String basePath = "/car-module-statis/turnover/general";
	
	@Resource(name = "cmstatisServTurnover")
	private IServTurnover iServTurnover;
	
	@RequestMapping(value="/store.do")
	public String store(Model model) {
		logger.info("==> access store analy page (this month)");
		Date now = new Date();
		List<Map<String, Object>> list = iServTurnover.
				getSumTotalFeeByStore(TimeUtils.getMonthBegin(now), TimeUtils.getMonthEnd(now));
		model.addAttribute("list", JSON.toJSONString(list));
		return basePath + "/store";
	}
	
	@RequestMapping(value="/cartype.do")
	public String cartype(Model model) {
		logger.info("==> access store analy page (this month)");
		Date now = new Date();
		List<Map<String, Object>> list = iServTurnover.
				getSumTotalFeeByCarType(TimeUtils.getMonthBegin(now), TimeUtils.getMonthEnd(now));
		model.addAttribute("list", JSON.toJSONString(list));
		return basePath + "/cartype";
	}
	
}
