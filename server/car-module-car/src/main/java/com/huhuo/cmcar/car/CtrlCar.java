package com.huhuo.cmcar.car;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.huhuo.carservicecore.constant.Dictionary.DictGroup;
import com.huhuo.carservicecore.cust.car.ModelCar;
import com.huhuo.carservicecore.cust.car.ModelCarType;
import com.huhuo.carservicecore.cust.store.ModelStore;
import com.huhuo.carservicecore.sys.dictionary.ModelDictionary;
import com.huhuo.cmcar.cartype.IServCarType;
import com.huhuo.cmsystem.SystemBaseCtrl;
import com.huhuo.cmsystem.dict.IServDictionary;
import com.huhuo.cmsystem.store.IServStore;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Dir;
import com.huhuo.integration.db.mysql.Order;
import com.huhuo.integration.db.mysql.Page;
import com.huhuo.integration.web.JsonStore;
import com.huhuo.integration.web.Message;
import com.huhuo.integration.web.Message.Status;


@Controller("cmcarCtrlCar")
@RequestMapping(value="/cmcar/car")
public class CtrlCar extends SystemBaseCtrl {
	
	protected String basePath = "/car-module-car";
	
	@Resource(name = "cmcarServCar")
	private IServCar iservCar;
	@Resource(name = "cmcarServCarType")
	private IServCarType iServCarType;
	@Resource(name = "cmsystemServStore")
	private IServStore iServStore;
	@Resource(name = "cmsystemServDictionary")
	private IServDictionary iServDictionary;
	
	/*************************************************************
	 * car management
	 *************************************************************/
	
	@RequestMapping(value="/index.do")
	public String index() {
		logger.debug("---> access car management page");
		return basePath + "/car/index";
	}
	
	@RequestMapping(value="/condition/get.do")
	public String get(Model model, Condition<ModelCar> condition, ModelCar t){
		if(t.getStatus() == null) {
			t.setStatus(1);
		}
		condition.setT(t);
		condition.setOrderList(new Order("createTime", Dir.DESC), new Order("updateTime", Dir.DESC));
		logger.debug("---> server receive: condition={}", condition);
		Page<ModelCar> page = condition.getPage();
		if(page == null) {
			page = new Page<ModelCar>();
		}
		List<ModelCar> records = iservCar.findByCondition(condition, true);
		model.addAttribute("records", records);
		page.setTotal(iservCar.countByCondition(condition));
		model.addAttribute("page", page);
		model.addAttribute("t", t);
		return basePath + "/car/page-grid";
	}
	
	@RequestMapping(value = "/typeahead/car.do")
	public String car(Model model, Condition<ModelCar> condition, ModelCar t) {
		condition.setT(t);
		List<ModelCar> records = iservCar.findByCondition(condition);
		return render(model, new JsonStore<ModelCar>(records, records.size()));
	}
	
	@RequestMapping(value = "/typeahead/cartype.do")
	public String carType(Model model, Condition<ModelCarType> condition, ModelCarType t) {
		condition.setT(t);
		List<ModelCarType> records = iServCarType.findByCondition(condition);
		return render(model, new JsonStore<ModelCarType>(records, records.size()));
	}
	
	@RequestMapping(value = "/typeahead/store.do")
	public String store(Model model, Condition<ModelStore> condition, ModelStore t) {
		condition.setT(t);
		List<ModelStore> records = iServStore.findByCondition(condition);
		return render(model, new JsonStore<ModelStore>(records, records.size()));
	}
	
	@RequestMapping(value = "/typeahead/dict.do")
	public String dict(Model model, Condition<ModelDictionary> condition, ModelDictionary t) {
		condition.setT(t);
		t.setGroupName(DictGroup.CUST_CAR_STATUS.getGroupName());
		condition.setOrderList(new Order("orderNo", Dir.DESC), new Order("id", Dir.ASC));
		List<ModelDictionary> records = iServDictionary.findByCondition(condition);
		return render(model, new JsonStore<ModelDictionary>(records, records.size()));
	}
	
	@RequestMapping(value="/add-ui.do")
	public String addUI(Model model) {
		logger.debug("==> access add ui");
		return basePath + "/car/add-ui";
	}
	
	
	@RequestMapping(value="/delete.do")
	public void delete(HttpServletResponse resp, @RequestParam(value="ids[]") List<Long> ids) {
		// receive data
		logger.debug("==> batch delete -->{}", ids);
		// retrieve model form DB
		iservCar.deleteBatch(ids);
		write(new Message<List<Long>>(Status.SUCCESS, "删除成功", ids), resp);
	}
	
	/*************************************************************
	 * car trace business
	 *************************************************************/
	
	@RequestMapping(value="/trace/index.do")
	public String trace() {	// car trace management page
		logger.debug("---> access car trace page");
		return basePath + "/car/index";
	}
	@RequestMapping(value="/trace/trace.do")
	public String traceTemp() {	// car trace management page
		return basePath + "/trace/index";
	}
}
