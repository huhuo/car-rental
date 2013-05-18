package com.huhuo.cmcar.cartype;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.huhuo.carservicecore.constant.Dictionary.DictGroup;
import com.huhuo.carservicecore.cust.car.ModelCarType;
import com.huhuo.carservicecore.sys.dictionary.ModelDictionary;
import com.huhuo.cmsystem.dict.IServDictionary;
import com.huhuo.integration.base.BaseCtrl;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Dir;
import com.huhuo.integration.db.mysql.Order;
import com.huhuo.integration.db.mysql.Page;
import com.huhuo.integration.web.Message;
import com.huhuo.integration.web.Message.Status;


@Controller("cmcarCtrlCarType")
@RequestMapping(value="/cmcar/cartype")
public class CtrlCarType extends BaseCtrl {
	
	protected String basePath = "/car-module-car";
	
	protected String msgPageLocation = "/car-module-car";
	
	@Resource(name = "cmcarServCarType")
	private IServCarType iservCarType;
	
	@Resource(name = "cmsystemServDictionary")
	private IServDictionary iServDictionary;
	
	/*************************************************************
	 * car type management
	 *************************************************************/
	
	@RequestMapping(value="/index.do")
	public String index(Model model) {	// car type management page
		logger.debug("---> access car type management page");
		Condition<ModelCarType> condition = new Condition<ModelCarType>();
		condition.setOrderList(new Order("createTime", Dir.DESC), new Order("updateTime", Dir.DESC));
		condition.setPage(new Page<ModelCarType>(0, 20));
		List<ModelCarType> list = iservCarType.findByCondition(condition, true);
		model.addAttribute("list", list);
		return basePath + "/cartype/index";
	}
	
	@RequestMapping(value="/condition/get.do")
	public String get(Model model, Condition<ModelCarType> condition, ModelCarType t){
		condition.setT(t);
		condition.setOrderList(new Order("createTime", Dir.DESC), new Order("updateTime", Dir.DESC));
		logger.debug("---> server receive: condition={}", condition);
		Page<ModelCarType> page = condition.getPage();
		if(page == null) {
			page = new Page<ModelCarType>();
		}
		List<ModelCarType> records = iservCarType.findByCondition(condition, true);
		model.addAttribute("records", JSON.parseArray(records.toString()));
		page.setTotal(iservCarType.countByCondition(condition));
		model.addAttribute("page", page);
		model.addAttribute("t", t);
		return basePath + "/cartype/page-grid";
	}
	
	@RequestMapping(value="/add-ui.do")
	public String addUI(Model model) {
		logger.debug("==> access add ui");
		return basePath + "/cartype/add-ui";
	}
	
	@RequestMapping(value="/add.do")
	public void add(HttpServletResponse resp, ModelCarType carType, String icon) {
		logger.debug("---> server receive: carType={}, icon={}", carType, icon);
		// add car type
		iservCarType.add(carType);
		Message<ModelCarType> msg = new Message<ModelCarType>(Status.SUCCESS, "add new cartype success!", carType);
		write(msg, resp);
	}
	
	@RequestMapping(value="/detail.do")
	public String detail(Model model, Long id) {
		logger.debug("==> edit ModelCarType with id --> {}", id);
		model.addAttribute("carType", iservCarType.find(id));
		List<ModelDictionary> carTypeCategoryList = iServDictionary.getGroupsBy(DictGroup.CUST_CAR_TYPE_CATEGORY);
		model.addAttribute("carTypeCategoryList", carTypeCategoryList);
		return basePath + "/cartype/detail";
	}
	
	@RequestMapping(value="/edit-ui.do")
	public String editUI(Model model, ModelCarType t) {
		logger.debug("==> edit ModelCarType with id --> {}", t.getId());
		Condition<ModelCarType> condition = new Condition<ModelCarType>(t, null, null, null);
		model.addAttribute("carType", iservCarType.findByCondition(condition, true).get(0));
		List<ModelDictionary> carTypeCategoryList = iServDictionary.getGroupsBy(DictGroup.CUST_CAR_TYPE_CATEGORY);
		model.addAttribute("carTypeCategoryList", carTypeCategoryList);
		return basePath + "/cartype/edit-ui";
	}
	
	@RequestMapping(value="/update.do")
	public void update(HttpServletResponse resp, ModelCarType t) throws Exception {
		// retrieve model form DB
		iservCarType.update(t);
		Message<ModelCarType> msg = new Message<ModelCarType>(Status.SUCCESS, "修改成功", t);
		write(msg, resp);
	}
	
	@RequestMapping(value="/delete.do")
	public void delete(HttpServletResponse resp, @RequestParam(value="ids[]") List<Long> ids) {
		// receive data
		logger.debug("==> batch delete -->{}", ids);
		// retrieve model form DB
		iservCarType.deleteBatch(ids);
		write(new Message<List<Long>>(Status.SUCCESS, "删除成功", ids), resp);
	}
	
	
}
