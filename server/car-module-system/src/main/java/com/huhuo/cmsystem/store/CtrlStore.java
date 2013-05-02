package com.huhuo.cmsystem.store;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.huhuo.carservicecore.cust.car.ModelCar;
import com.huhuo.carservicecore.cust.store.ModelStore;
import com.huhuo.carservicecore.sys.user.ModelUser;
import com.huhuo.cmsystem.security.user.IServUser;
import com.huhuo.integration.base.BaseCtrl;
import com.huhuo.integration.db.mysql.Condition;
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
	@Resource(name="cmsystemServStore")
	private IServStore iServStore;
	
	@Resource(name = "cmsystemServUser")
	private IServUser iServUser;
	
	@RequestMapping(value="/index.do")
	public String index() {
		logger.debug("---> access order management page");
		return basePath + "/store/index";
	}
	
	@RequestMapping(value="/condition/get.do")
	public String get(Model model, Condition<ModelStore> condition, ModelStore t) {
		//List<Map<String, Object>> records = iServStore.findByCondition(condition);
		Page<ModelStore> page = condition.getPage();
		List<Map<String, Object>> records = iServStore.multiQuery(t, page);
		model.addAttribute("records", records);
		page.setTotal(iServStore.countMultiQuery(t, page));
		model.addAttribute("page", page);
		model.addAttribute("t", t);
		
		return basePath + "/store/page-grid";
	}
	
	@RequestMapping(value="/add-ui.do")
	public String addUI(Model model) {
		List<ModelUser> mgrs = iServUser.findByCondition(null, true);
		model.addAttribute("mgrs", mgrs);
		logger.debug("==> access add ui");
		return basePath + "/store/add-ui";
	}
	@RequestMapping(value="/add.do")
	public void add(HttpServletResponse resp, ModelStore store) {
		logger.debug("==> access add ui");
		iServStore.add(store);
		Message<ModelStore> msg = new Message<ModelStore>(Status.SUCCESS, "add new store success!", store);
		write(msg, resp);
	}
	
	
	@RequestMapping(value="/delete.do")
	public void delete(HttpServletResponse resp, ModelStore t,@RequestParam(value="ids[]") List<Long> ids) {
		// receive data
		logger.debug("==> batch delete -->{}", ids);
		// retrieve model form DB
		iServStore.deleteBatch(ids);
		write(new Message<ModelStore>(Status.SUCCESS, "删除成功", t), resp);
	}
	
	@RequestMapping(value="/detail.do")
	public String detail(Model model, ModelStore t) {
		logger.debug("==> detail ModelStore with id --> {}", t.getId());
//		model.addAttribute("detail", iServStore.detailQuery(t));
		
		ModelStore store = iServStore.find(t.getId());
		model.addAttribute("store", store);
		ModelUser manage = iServUser.find(store.getManagerId());
		model.addAttribute("manage", manage);
		
		Condition<ModelCar> condition = new Condition<ModelCar>();
		ModelCar modelCar = new ModelCar();
		modelCar.setStatus(1);
		modelCar.setStoreId(store.getId());
		condition.setT(modelCar);
		Page<ModelCar> page = new Page<ModelCar>();
		page.setStart(0);
		page.setLimit(12);
		condition.setPage(page);
		List<ModelCar> carList = iServStore.getCarByCondition(condition);
		model.addAttribute("carList", carList);
		
		
		return basePath + "/store/detail";
	}
	
	@RequestMapping(value="/edit-ui.do")
	public String editUI(Model model, Long id) {
		logger.debug("==> edit ModelStore with id --> {}", id);
		ModelStore store = iServStore.find(id);
		ModelUser manager = iServUser.find(store.getManagerId());
		List<ModelUser> users = iServUser.findByCondition(null);
		model.addAttribute("users", users);
		model.addAttribute("manager", manager);
		model.addAttribute("store", store);
		return basePath + "/store/edit-ui";
	}
	
	@RequestMapping(value="/update.do")
	public void update(HttpServletResponse resp, ModelStore t) throws Exception {
		// retrieve model form DB
		iServStore.update(t);
		Message<ModelStore> msg = new Message<ModelStore>(Status.SUCCESS, "修改成功", t);
		write(msg, resp);
	}
}
