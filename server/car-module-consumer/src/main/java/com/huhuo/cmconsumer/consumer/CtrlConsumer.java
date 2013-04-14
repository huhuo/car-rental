package com.huhuo.cmconsumer.consumer;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.carservicecore.csm.consumer.IDaoConsumer;
import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
import com.huhuo.integration.base.BaseCtrl;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Dir;
import com.huhuo.integration.db.mysql.Order;
import com.huhuo.integration.db.mysql.Page;
import com.huhuo.integration.util.ExtUtils;
import com.huhuo.integration.web.Message;
import com.huhuo.integration.web.Message.Status;


@Controller("cmconsumerCtrlConsumer")
@RequestMapping(value="/cmconsumer/consumer")
public class CtrlConsumer extends BaseCtrl {
	
	protected String basePath = "/car-module-consumer";
	
	@Resource(name = "cmconsumerServConsumer")
	private IServConsumer iservConsumer;
	
	
	@Resource(name="carservicecoreDaoConsumer")
	private IDaoConsumer idaoConsumer;
	
	/*************************************************************
	 * consumer management
	 *************************************************************/
	
	@RequestMapping(value="/index.do")
	public String consumerIndex(Model model) {
		logger.debug("access consumer management page");
		Condition<ModelConsumer> condition = new Condition<ModelConsumer>();
		condition.setOrderList(new Order("createTime", Dir.DESC), new Order("updateTime", Dir.DESC));
		condition.setPage(new Page<ModelConsumer>(0, 20));
		List<ModelConsumer> list = iservConsumer.findByCondition(condition, true);
		model.addAttribute("list", list);
		return basePath + "/consumer/index";
	}
	
	@RequestMapping(value="/get.do")
	public void get(HttpServletResponse resp, Condition<ModelConsumer> condition){
		logger.debug("server receive: condition={}", condition);
//			condition.setPage(new Page(0, 30));
//			List<ModelConsumer> list = servConsumer.findByCondition(condition);
		List<ModelConsumer> list = iservConsumer.findModels(new Page<ModelConsumer>(0, 10));
		write(ExtUtils.getJsonStore(list, list.size()), resp);
	}
	
	@RequestMapping(value="/condition/get.do")
	public String get(Model model, Condition<ModelConsumer> condition, ModelConsumer t){
		condition.setT(t);
		condition.setOrderList(new Order("createTime", Dir.DESC), new Order("updateTime", Dir.DESC));
		logger.debug("---> server receive: condition={}", condition);
		Page<ModelConsumer> page = condition.getPage();
		if(page == null) {
			page = new Page<ModelConsumer>();
		}
		List<ModelConsumer> records = iservConsumer.findByCondition(condition);
		model.addAttribute("records", records);
		page.setTotal(iservConsumer.countByCondition(condition));
		model.addAttribute("page", page);
		model.addAttribute("t", t);
		
		return basePath + "/consumer/page-grid";
	}
	
	@RequestMapping(value="/add.do")
	public void add(HttpServletResponse resp, ModelConsumer consumer) {
		logger.debug("---> server receive: carType={}, chargeStandard={}", consumer);
		// add car type
		iservConsumer.add(consumer);
		Message<ModelConsumer> msg = new Message<ModelConsumer>(Status.SUCCESS, "add new consumer success!", consumer);
		write(msg, resp);
	}
	/**
	 * register customer
	 * @param condition
	 * @param out
	 */
	@RequestMapping(value="/register.do")
	public void registerCustomer(String username) {
		logger.debug("--->");
		logger.debug("username:" + username);
		logger.debug("<---");
		
	}
	
	/*************************************************************
	 * loyalty point management
	 *************************************************************/
	
	@RequestMapping(value="/points.do")
	public String pointsIndex() {
		logger.debug("access consumer points management page");
		return basePath + "/points/index";
	}
	
}
