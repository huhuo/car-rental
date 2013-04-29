package com.huhuo.cmsystem.store;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.carservicecore.constant.Dictionary.DictGroup;
import com.huhuo.carservicecore.cust.car.ModelCarType;
import com.huhuo.carservicecore.cust.car.ModelChargeStandard;
import com.huhuo.carservicecore.cust.store.ModelStore;
import com.huhuo.carservicecore.sys.dictionary.ModelDictionary;
import com.huhuo.cmsystem.dict.IServDictionary;
import com.huhuo.integration.base.BaseCtrl;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Dir;
import com.huhuo.integration.db.mysql.Order;
import com.huhuo.integration.db.mysql.Page;
import com.huhuo.integration.web.Message;
import com.huhuo.integration.web.Message.Status;


@Controller("cmsystemCtrlStore")
@RequestMapping(value="/cmsystem/store")
public class CtrlStore extends BaseCtrl {
	
	protected String basePath = "/car-module-system";
	
	/*************************************************************
	 * store info management
	 *************************************************************/
	@Resource(name="cmcarServStore")
	private IServStore iServStore;
	
	@Resource(name = "cmsystemServDictionary")
	private IServDictionary iServDictionary;
	
	@RequestMapping(value="/index.do")
	public String index() {
		logger.debug("---> access order management page");
		return basePath + "/store/index";
	}
	
	@RequestMapping(value="/condition/get.do")
	public String get(Model model, Condition<ModelStore> condition, ModelStore t) {
		condition.setT(t);
		condition.setOrderList(new Order("createTime", Dir.DESC), new Order("updateTime", Dir.DESC));
		logger.debug("---> server receive: condition={}", condition);
		Page<ModelStore> page = condition.getPage();
		if(page==null){
			page = new Page<ModelStore>();
		}
		//List<Map<String, Object>> records = iServStore.findByCondition(condition);
		List<Map<String, Object>> records = iServStore.multiQuery();
		model.addAttribute("records", records);
		page.setTotal(iServStore.countByCondition(condition));
		model.addAttribute("page", page);
		model.addAttribute("t", t);
		
		return basePath + "/store/page-grid";
	}
	
	@RequestMapping(value="/add-ui.do")
	public String addUI(Model model) {
		List<ModelStore> list = iServStore.findByCondition(null);
		model.addAttribute("record", list);
		logger.debug("==> access add ui");
		return basePath + "/store/add-ui";
	}
	@RequestMapping(value="/add.do")
	public void add(HttpServletResponse resp, ModelStore store, ModelChargeStandard chargeStandard) {
		
		logger.debug("==> access add ui");
		iServStore.add(store);
		Message<ModelStore> msg = new Message<ModelStore>(Status.SUCCESS, "add new cartype success!", store);
		write(msg, resp);
	}
	
	@RequestMapping(value="/edit-ui.do")
	public String editUI(Model model, ModelCarType t) {
		logger.debug("==> edit ModelStore with id --> {}", t.getId());
		Condition<ModelStore> condition = new Condition<ModelStore>();
		model.addAttribute("store", iServStore.findByCondition(condition, true).get(0));
		List<ModelDictionary> storeCategoryList = iServDictionary.getGroupsBy(DictGroup.CUST_CAR_TYPE_CATEGORY);
		model.addAttribute("carTypeCategoryList", storeCategoryList);
		return basePath + "/store/edit-ui";
	}
}
