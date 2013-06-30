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


@Controller("cmstatisCtrlTurnOverTrend")
@RequestMapping(value="/cmstatis/turnover/trend")
public class CtrlTurnOverTrend extends SystemBaseCtrl {
	
	protected String basePath = "/car-module-statis/turnover/trend";
	
	@Resource(name = "cmstatisServTurnover")
	private IServTurnover iServTurnover;
	
	/*************************************************************
	 * last month analysis
	 *************************************************************/
	@RequestMapping(value="/last-month.do")
	public String lastMonth(Model model) {
		logger.info("==> access last month page");
		Date now = new Date();
		List<Map<String, Object>> list = iServTurnover.
				getSumTotalFeeByDate(TimeUtils.offsetDate(-15, now), now);
		model.addAttribute("list", JSON.toJSONString(list));
		model.addAttribute("title", "前15天营业额统计");
		return basePath + "/last-month";
	}
	
	/*************************************************************
	 * last quarter analysis
	 *************************************************************/
	@RequestMapping(value="/last-quarter.do")
	public String lastQuarter(Model model) {
		logger.info("==> access last quarter page");
		Date now = new Date();
		Date dateBefore84 = TimeUtils.offsetDate(-77, now);
		List<Map<String, Object>> list = iServTurnover.getSumTotalFeeByWeek(
				TimeUtils.getWeekBegin(dateBefore84), TimeUtils.getWeekEnd(now));
		model.addAttribute("list", JSON.toJSONString(list));
		model.addAttribute("title", "前12周营业额统计");
		return basePath + "/last-quarter";
	}
	
	/*************************************************************
	 * last year analysis
	 *************************************************************/
	@RequestMapping(value="/last-year.do")
	public String lastYear(Model model) {
		logger.info("==> access last year page");
		Date now = new Date();
		Date lastYear = TimeUtils.offsetMonth(-11, now);
		List<Map<String, Object>> list = iServTurnover.getSumTotalFeeByMonth(
				TimeUtils.getMonthBegin(lastYear), TimeUtils.getMonthEnd(now));
		model.addAttribute("list", JSON.toJSONString(list));
		model.addAttribute("title", "前12个月营业额统计");
		return basePath + "/last-year";
	}
	
}
