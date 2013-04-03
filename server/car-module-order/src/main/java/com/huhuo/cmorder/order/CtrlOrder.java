package com.huhuo.cmorder.order;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
import com.huhuo.carservicecore.csm.order.ModelOrder;
import com.huhuo.integration.base.BaseCtrl;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Page;
import com.huhuo.integration.exception.HuhuoException;


@Controller("cmorderCtrlOrder")
@RequestMapping(value="/cmorder/order")
public class CtrlOrder extends BaseCtrl {
	
	protected final static String basePath = "/car-module-order";
	
	@Resource(name = "cmorderServOrder")
	private IServOrder iservOrder;
	
	
	/*************************************************************
	 * order info management
	 *************************************************************/
	
	@RequestMapping(value="/index.do")
	public String index() {	// order manage page
		logger.debug("access order management page");
		return basePath + "/index";
	}
	
	@RequestMapping(value="/get.do")
	public String get(Condition<ModelOrder> condition,Model model,Integer b,Integer s) {
		try {
			Page<ModelOrder> page = new Page<ModelOrder>();
			page.setPageNo(b);
			page.setLimit(s);
			logger.debug("server receive: condition={}", condition);
//			condition.setPage(new Page(0, 30));
//			List<ModelConsumer> list = servConsumer.findByCondition(condition);
			List<ModelOrder> list = iservOrder.findModels(page);
			
			
			
			page.setTotal(iservOrder.count());
			page.setRecords(list);
			model.addAttribute("orderPage", page);
			logger.debug("orderlist is [{}]",list);
		} catch (HuhuoException e) {
			logger.warn(e.getMessage());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getFullStackTrace(e));
		}
		return basePath + "/ordertable";
	}
	
	
	/*************************************************************
	 * order history management
	 *************************************************************/
	
	@RequestMapping(value="/history.do")
	public String history() {	// order manage page
		logger.debug("access order history page");
		return basePath + "/history";
	}
	
	
}
