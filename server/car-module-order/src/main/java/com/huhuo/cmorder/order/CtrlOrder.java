package com.huhuo.cmorder.order;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
import com.huhuo.carservicecore.csm.order.ModelOrder;
import com.huhuo.carservicecore.cust.car.ModelCar;
import com.huhuo.integration.base.BaseCtrl;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Page;
import com.huhuo.integration.util.ExtUtils;

@Controller("cmorderCtrlOrder")
@RequestMapping(value = "/cmorder/order")
public class CtrlOrder extends BaseCtrl {

	protected final static String basePath = "/car-module-order";

	@Resource(name = "cmorderServOrder")
	private IServOrder iservOrder;
	

	/*************************************************************
	 * order info management
	 *************************************************************/

	@RequestMapping(value = "/index.do")
	public String index() { // order manage page
		logger.debug("access order management page");
		return basePath + "/index";
	}

	@RequestMapping(value = "/get.do")
	public String get(Condition<ModelOrder> condition, Model model) {
		logger.debug("server receive: condition={}", condition);
		Page<ModelOrder> page = condition.getPage();

		if (page == null) {
			page = new Page<ModelOrder>();
			condition.setPage(page);
		}
		// condition.setPage(new Page(0, 30));
		// List<ModelConsumer> list = servConsumer.findByCondition(condition);
		List<ModelOrder> list = iservOrder.findModels(page);

		page.setTotal(iservOrder.count());
		page.setRecords(list);
		model.addAttribute("orderPage", page);
		logger.debug("orderlist is [{}]", list);
		return basePath + "/ordertable";
	}

	@RequestMapping(value = "/addorder.do")
	public void addOrder(Condition<ModelOrder> condition, Model model) {
		logger.debug("server receive: condition={}", condition);
		Page<ModelOrder> page = condition.getPage();

		if (page == null) {
			page = new Page<ModelOrder>();
			condition.setPage(page);
		}
		// condition.setPage(new Page(0, 30));
		// List<ModelConsumer> list = servConsumer.findByCondition(condition);
		List<ModelOrder> list = iservOrder.findModels(page);

		page.setTotal(iservOrder.count());
		page.setRecords(list);
		model.addAttribute("orderPage", page);
		logger.debug("orderlist is [{}]", list);
	}

	/*************************************************************
	 * order history management
	 *************************************************************/

	@RequestMapping(value = "/history.do")
	public String history() { // order manage page
		logger.debug("access order history page");
		return basePath + "/history";
	}
	
	@RequestMapping(value="/conumer.do")
	public void getConsumer(HttpServletResponse resp, String mobileNumber){
		logger.debug("server receive: mobileNumber={}", mobileNumber);
//			condition.setPage(new Page(0, 30));
//			List<ModelConsumer> list = servConsumer.findByCondition(condition);
		List<ModelConsumer> list = iservOrder.getConsumerListByPhone(mobileNumber);
		write(ExtUtils.getJsonStore(list, list.size()), resp);
	}
	
	@RequestMapping(value="/car.do")
	public void get(HttpServletResponse resp, String licencePlate){
		logger.debug("server receive: licencePlate={}", licencePlate);
//			condition.setPage(new Page(0, 30));
//			List<ModelConsumer> list = servConsumer.findByCondition(condition);
		List<ModelCar> list = iservOrder.getCarListBylicencePlate(licencePlate);
		write(ExtUtils.getJsonStore(list, list.size()), resp);
	}

}
