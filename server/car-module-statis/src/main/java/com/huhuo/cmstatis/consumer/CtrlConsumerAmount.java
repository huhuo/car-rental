package com.huhuo.cmstatis.consumer;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
import com.huhuo.cmsystem.SystemBaseCtrl;
import com.huhuo.integration.util.TimeUtils;
import com.huhuo.integration.web.Message;
import com.huhuo.integration.web.Message.Status;


@Controller("cmstatisCtrlConsumerAmount")
@RequestMapping(value="/cmstatis/consumer/amount")
public class CtrlConsumerAmount extends SystemBaseCtrl {
	
	protected String basePath = "/car-module-statis/consumer/amount";
	
	@Resource(name = "cmstatisServConsumer")
	private IServConsumer iServConsumer;
	
	@RequestMapping(value="/index.do")
	public String index(Model model, HttpServletResponse resp) {
		logger.debug("==> access general analysis page");
		return basePath + "/index";
	}
	@RequestMapping(value="/this-week.do")
	public String thisWeek(Model model, HttpServletResponse resp) {
		logger.debug("==> access general analysis page");
		Date now = new Date();
		Date weekBegin = TimeUtils.getWeekBegin(now);
		List<Map<String,Object>> list = iServConsumer.getAmountByDay(weekBegin, now);
		for(Map<String,Object> record : list) {
			Object object = record.get("day");
			if("本周第1天".equals(object)) {
				record.put("day", "星期一");
			}else if ("本周第2天".equals(object)) {
				record.put("day", "星期二");
			}else if ("本周第3天".equals(object)) {
				record.put("day", "星期三");
			}else if ("本周第4天".equals(object)) {
				record.put("day", "星期四");
			}else if ("本周第5天".equals(object)) {
				record.put("day", "星期五");
			}else if ("本周第6天".equals(object)) {
				record.put("day", "星期六");
			}else if ("本周第0天".equals(object)) {
				record.put("day", "星期天");
			}
		}
		String jsonString = JSON.toJSONString(list);
		model.addAttribute("list", jsonString);
		return basePath + "/this-week";
	}
	
	/*************************************************************
	 * last month analysis
	 *************************************************************/
	@RequestMapping(value="/last-month.do")
	public String lastMonth(Model model) {
		logger.debug("==> access car trace page");
		Date date = new Date();
		Date lastMOnth = TimeUtils.offsetMonth(-1, date);
		Date begin = TimeUtils.getMonthBegin(lastMOnth);
		Date end = TimeUtils.getMonthEnd(lastMOnth);
		List<Map<String,Object>> list = iServConsumer.getAmountByDate(begin, end);
		String jsonString = JSON.toJSONString(list);
		model.addAttribute("list", jsonString);
		return basePath + "/last-month";
	}
	
	/*************************************************************
	 * last quarter analysis
	 *************************************************************/
	@RequestMapping(value="/last-quarter.do")
	public String lastQuarter(Model model,ModelConsumer t,HttpServletResponse resp) {
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.get(calendar.WEEK_OF_YEAR));
		calendar.add(Calendar.WEEK_OF_YEAR, -1);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		System.out.println(calendar.getFirstDayOfWeek());
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek()+6);
		System.out.println(calendar.DAY_OF_WEEK);
		System.out.println(calendar.getTime());
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 99);
		Date end1 = calendar.getTime();
		
		
		calendar.add(Calendar.WEEK_OF_YEAR, -11);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		System.out.println(calendar.getFirstDayOfWeek());
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
		System.out.println(calendar.DAY_OF_WEEK);
		System.out.println(calendar.getTime());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		System.out.println(calendar.get(calendar.WEEK_OF_YEAR));
		System.out.println(calendar.getTime());
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		Date start1 = calendar.getTime();
		List<Map<String,Object>> list = iServConsumer.getAmountByWeek(start1, end1);
		String jsonString = JSON.toJSONString(list);
		model.addAttribute("list", jsonString);
		logger.debug("==> access car trace page");
		return basePath + "/last-quarter";
	}
	
	/*************************************************************
	 * last year analysis
	 *************************************************************/
	@RequestMapping(value="/last-year.do")
	public String lastYear(Model model) {
		logger.debug("==> access car trace page");
		Date date = new Date();
		Date month = TimeUtils.offsetMonth(-13, date);
		Date beginMonth = TimeUtils.getMonthBegin(month);
		Date month2 = TimeUtils.offsetMonth(-1, date);
		Date endMonth = TimeUtils.getMonthEnd(month2);
		List<Map<String,Object>> list = iServConsumer.getAmountByMonth(beginMonth, endMonth);
		String jsonString = JSON.toJSONString(list);
		model.addAttribute("list", jsonString);
		return basePath + "/last-year";
	}
	
	public static void main(String[] args) {
		
		
	}
}
