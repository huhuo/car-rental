package com.huhuo.cmorder.order;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.carservicecore.csm.consumer.IDaoConsumer;
import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
import com.huhuo.carservicecore.csm.order.ModelOrder;
import com.huhuo.carservicecore.cust.car.IDaoCar;
import com.huhuo.carservicecore.cust.car.IDaoCarType;
import com.huhuo.carservicecore.cust.car.ModelCar;
import com.huhuo.carservicecore.cust.car.ModelCarType;
import com.huhuo.carservicecore.cust.car.ModelChargeStandard;
import com.huhuo.carservicecore.cust.store.IDaoStore;
import com.huhuo.carservicecore.cust.store.ModelStore;
import com.huhuo.cmorder.order.model.OrderFormModel;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Page;
import com.huhuo.integration.util.BeanUtils;
import com.huhuo.integration.util.ExtUtils;
import com.huhuo.integration.util.TimeUtils;
import com.huhuo.integration.web.Message;
import com.huhuo.integration.web.Message.Status;
import com.huhuo.webbase.general.HuhuoWebBaseBaseCtrl;

@Controller("cmorderCtrlOrder")
@RequestMapping(value = "/cmorder/order")
public class CtrlOrder extends HuhuoWebBaseBaseCtrl {

	protected final static String basePath = "/car-module-order";

	@Resource(name = "cmorderServOrder")
	private IServOrder iservOrder;
	
	
	@Resource(name = "carservicecoreDaoConsumer")
	private IDaoConsumer iDaoConsumer;
	
	
	@Resource(name = "carservicecoreDaoCar")
	private IDaoCar iDaoCar;
	
	@Resource(name = "carservicecoreDaoCarType")
	private IDaoCarType iDaoCarType;
	
	@Resource(name = "carservicecoreDaoStore")
	private IDaoStore iDaoStore;
	

	/*************************************************************
	 * order info management
	 *************************************************************/

	@RequestMapping(value = "/index.do")
	public String index() { // order manage page
		logger.debug("access order management page");
		return basePath + "/index";
	}
	
	@RequestMapping(value = "/beforeCheckout.do")
	public String beforeCheckout(Long id,Model model) { // order manage page
		logger.debug("access order management page");
		ModelOrder order=iservOrder.find(id);
		
		
		Long consumerId = order.getConsumerId();
		
		ModelConsumer consumer = iDaoConsumer.find(consumerId);
		
		
		
		Long carId = order.getCarId();
		
		
		ModelCar car = iDaoCar.find(carId);
		
		ModelCarType carType = iDaoCarType.find(car.getCarType());
		
		
		ModelStore store = iDaoStore.find(car.getStoreId());
		
		car.setStore(store);
		
		Date carRentTime = order.getCarRentTime();
		Date carPlanRetTime = order.getCarPlanRetTime();
		
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(carRentTime);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		//租车天数
		Integer days=Long.valueOf((carPlanRetTime.getTime()-calendar.getTimeInMillis())/(24*3600*1000)).intValue();
		
		if(days<1){
			days=1;
		}
		
		
		Date date = new Date();
		long overmill=date.getTime()-carPlanRetTime.getTime();
		Integer hours=0;
		if(overmill>0)
		//超时小时
			hours	=Long.valueOf(overmill/(3600*1000)).intValue();
		
		
		Double overPrice=hours*order.getOverTimeFare();
		
		
		Double rent = order.getRent();
		
		Double normalPrice=days*rent;
		
		
		Long mileageLimits = order.getMileageLimits();
		
		Long limitMile=mileageLimits*days+order.getMileageBegin();
		
		model.addAttribute("retTime", TimeUtils.format(date, true));
		model.addAttribute("carRentTime", TimeUtils.format(carRentTime, true));
		model.addAttribute("carPlanRetTime", TimeUtils.format(carPlanRetTime, true));
		model.addAttribute("hours", hours);
		model.addAttribute("overTimePrice", overPrice);
		model.addAttribute("days", days);
		model.addAttribute("normalPrice", normalPrice);
		model.addAttribute("limitMile", limitMile);
		
		
		model.addAttribute("order", order);
		model.addAttribute("consumer", consumer);
		model.addAttribute("car", car);
		model.addAttribute("carType", carType);
		
		return basePath + "/checkOutui";
	}
	
	
	@RequestMapping(value = "/checkout.do")
	public void checkout(HttpServletResponse resp,Long orderId,Double oil,Long mile) { // order manage page
		logger.debug("access order management page");
		ModelOrder order=iservOrder.find(orderId);
		
		Date carRentTime = order.getCarRentTime();
		Date carPlanRetTime = order.getCarPlanRetTime();
		
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(carRentTime);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		//租车天数
		Integer days=Long.valueOf((carPlanRetTime.getTime()-calendar.getTimeInMillis())/(24*3600*1000)).intValue();
		
		if(days<1){
			days=1;
		}
		
		
		Date date = new Date();
		long overmill=date.getTime()-carPlanRetTime.getTime();
		Integer hours=0;
		if(overmill>0)//超时小时
			hours	=Long.valueOf(overmill/(3600*1000)).intValue();
		
		
		Double overPrice=hours*order.getOverTimeFare();
		
		
		Double rent = order.getRent();
		
		Double normalPrice=days*rent;
		
		
		Long mileageLimits = order.getMileageLimits();
		
		Long limitMile=mileageLimits*days+order.getMileageBegin();
		Double oilmassBegin = order.getOilmassBegin();
		Double oilPrice = order.getOilPrice();
		Double overMileageFare = order.getOverMileageFare();
		double oilSpend=0;
		double mileEnd=0;
		if(oil<oilmassBegin){
			oilSpend=(oilmassBegin-oil)*oilPrice;
		}
		if(mile>limitMile){
			mileEnd=(mile-limitMile)*overMileageFare;
		}
		
		Double totalPrice=normalPrice+overPrice+oilSpend+mileEnd;
		
		
		order.setCarRetTime(date);
		order.setOilmassEnd(oil);
		order.setMileageEnd(mile);
		order.setStatus(2);
		order.setTotalFee(totalPrice);
		
		Integer update = iservOrder.update(order);

		Message<ModelOrder> msg = new Message<ModelOrder>(Status.SUCCESS, "checkout order success!", order);
		logger.debug("orderlist is [{}]", msg);
		write(msg, resp);
	}
	
	@RequestMapping(value = "/addUI.do")
	public String addUI() { // order manage page
		logger.debug("access addUI management page");
		return basePath + "/addui";
	}

	@RequestMapping(value = "/get.do")
	public String get(Condition<Map<String,Object>> condition, Model model) {
		logger.debug("server receive: condition={}", condition);
		Map<String, Object> opt = condition.getOpt();
		opt.put("status", 1);
		
		Page<Map<String,Object>> page = condition.getPage();

		if (page == null) {
			page = new Page<Map<String,Object>>();
			condition.setPage(page);
		}
		// condition.setPage(new Page(0, 30));
		// List<ModelConsumer> list = servConsumer.findByCondition(condition);
		page= iservOrder.findOrderPage(condition);
		model.addAttribute("orderPage", page);
		logger.debug("page is [{}]", page);
		return basePath + "/ordertable";
	}
	@RequestMapping(value = "/historyordertable.do")
	public String historyordertable(Condition<Map<String,Object>> condition, Model model) {
		logger.debug("server receive: condition={}", condition);
		Map<String, Object> opt = condition.getOpt();
		//结账状态
		opt.put("status", 2);
		
		Page<Map<String,Object>> page = condition.getPage();
		
		if (page == null) {
			page = new Page<Map<String,Object>>();
			condition.setPage(page);
		}
		// condition.setPage(new Page(0, 30));
		// List<ModelConsumer> list = servConsumer.findByCondition(condition);
		page= iservOrder.findOrderPage(condition);
		model.addAttribute("orderPage", page);
		logger.debug("page is [{}]", page);
		return basePath + "/historyordertable";
	}

	@RequestMapping(value = "/addorder.do")
	public void addOrder(HttpServletResponse resp, OrderFormModel orderForm) {
		logger.debug("server receive: orderForm=[{}]", orderForm);
		
		//TODO 添加入入参校验
		try {
			ModelConsumer consumer = orderForm.getConsumer();
			ModelCar car = orderForm.getCar();
			ModelChargeStandard chargeStandard = orderForm.getChargeStandard();
			ModelOrder order = orderForm.getOrder();
			
			
			order.setCarId(car.getId());
			order.setOilmassBegin(car.getOilMass());
			order.setMileageBegin(car.getDrivedKilometer());
			
			
			order.setConsumerId(consumer.getId());
			
			
			order.setCarRentTime(new Date());
			
			
			BeanUtils.copyProperties(order, chargeStandard, false);
			
			order.setId(null);
			iservOrder.add(order);
			
			
			iservOrder.updateCarStatus(car.getId(),2);
			
			
					
			Message<ModelOrder> msg = new Message<ModelOrder>(Status.SUCCESS, "add new order success!", order);
			logger.debug("orderlist is [{}]", msg);
			write(msg, resp);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		
		// condition.setPage(new Page(0, 30));
		// List<ModelConsumer> list = servConsumer.findByCondition(condition);
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
		logger.debug("111");
		List<ModelConsumer> list = iservOrder.getConsumerListByPhone(mobileNumber);
		
		logger.debug("MOdelConsumerList is {}",list);
		write(ExtUtils.getJsonStore(list, list.size()), resp);
	}
	
	@RequestMapping(value="/car.do")
	public void getCar(HttpServletResponse resp, String licencePlate,Long carTypeId){
		logger.debug("server receive: licencePlate={},carTypeId={}", licencePlate,carTypeId);
//			condition.setPage(new Page(0, 30));
//			List<ModelConsumer> list = servConsumer.findByCondition(condition);
		List<ModelCar> list = iservOrder.getCarListBylicencePlate(licencePlate,carTypeId);
		write(ExtUtils.getJsonStore(list, list.size()), resp);
	}
	@RequestMapping(value="/carType.do")
	public void getCarType(HttpServletResponse resp, String carTypeName,Long carTypeId){
		logger.debug("server receive: carTypeName={},carTypeId={}", carTypeName,carTypeId);
//			condition.setPage(new Page(0, 30));
//			List<ModelConsumer> list = servConsumer.findByCondition(condition);
		List<ModelCarType> list = iservOrder.getCarTypeList(carTypeName,carTypeId);
		write(ExtUtils.getJsonStore(list, list.size()), resp);
	}
	
	@RequestMapping(value="/store.do")
	public void getCarType(HttpServletResponse resp,Long storeId){
		logger.debug("server receive: storeId={}", storeId);
//			condition.setPage(new Page(0, 30));
//			List<ModelConsumer> list = servConsumer.findByCondition(condition);
		List<ModelStore> list = iservOrder.getStoreById(storeId);
		write(ExtUtils.getJsonStore(list, list.size()), resp);
		
	}
	
	@RequestMapping(value="/chargeStandard.do")
	public void getChargeStandard(HttpServletResponse resp,Long chargeStandardId){
		logger.debug("server receive: chargeStandardId={}", chargeStandardId);
//			condition.setPage(new Page(0, 30));
//			List<ModelConsumer> list = servConsumer.findByCondition(condition);
		List<ModelChargeStandard> list = iservOrder.getchargeStandardById(chargeStandardId);
		write(ExtUtils.getJsonStore(list, list.size()), resp);
	}
	
	
	@RequestMapping(value="/delete.do")
	public void delete(HttpServletResponse resp,Long id){
		logger.debug("server receive: id={}", id);
//			condition.setPage(new Page(0, 30));
//			List<ModelConsumer> list = servConsumer.findByCondition(condition);
		ModelOrder modelOrder = new ModelOrder();
		modelOrder.setId(id);
		iservOrder.delete(modelOrder);
		iservOrder.updateCarStatus(modelOrder.getCarId(),1);
		
		write(new Message<Long>(Status.SUCCESS, "删除成功", id), resp);
	}

}
