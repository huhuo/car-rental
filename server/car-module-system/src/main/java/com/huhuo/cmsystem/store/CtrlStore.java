package com.huhuo.cmsystem.store;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import com.alibaba.fastjson.JSON;
import com.huhuo.carservicecore.cust.car.ModelCar;
import com.huhuo.carservicecore.cust.store.ModelStore;
import com.huhuo.carservicecore.sys.file.ModelFileUpload;
import com.huhuo.carservicecore.sys.user.ModelUser;
import com.huhuo.cmsystem.SystemBaseCtrl;
import com.huhuo.cmsystem.file.IServFileUpload;
import com.huhuo.cmsystem.security.user.IServUser;
import com.huhuo.integration.base.BaseCtrl;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Dir;
import com.huhuo.integration.db.mysql.Order;
import com.huhuo.integration.db.mysql.Page;
import com.huhuo.integration.db.mysql.Where;
import com.huhuo.integration.web.JsonStore;
import com.huhuo.integration.web.Message;
import com.huhuo.integration.web.Message.Status;


@Controller("cmsystemCtrlStore")
@RequestMapping(value="/cmsystem/store")
public class CtrlStore extends SystemBaseCtrl {
	
	protected String basePath = "/car-module-system";
	
	/*************************************************************
	 * store info management
	 *************************************************************/
	@Resource(name="cmsystemServStore")
	private IServStore iServStore;
	
	@Resource(name = "cmsystemServUser")
	private IServUser iServUser;
	
	@Resource(name = "cmsystemServFileUpload")
	private IServFileUpload iServFileUpload;
	
	@RequestMapping(value="/index.do")
	public String index() {
		logger.debug("---> access order management page");
		return basePath + "/store/index";
	}
	
	@RequestMapping(value="/condition/get.do")
	public String get(Model model, Condition<ModelStore> condition, ModelStore t) {
		if(t.getStatus()==null) {
			condition.setWhereList(new Where("status > ?", 0));
		}
		condition.setT(t);
		condition.setOrderList(new Order("createTime", Dir.DESC), new Order("updateTime", Dir.DESC));
		//List<Map<String, Object>> records = iServStore.findByCondition(condition);
		Page<ModelStore> page = condition.getPage();
		//List<Map<String, Object>> records = iServStore.multiQuery(t, page);
		List<ModelStore> records = iServStore.findByCondition(condition, true);
		//Map<Long,ModelStore> map=new HashMap<Long, ModelStore>();
		//变量store集合，以storeId为健，store为值存到map集合；
		/*for(ModelStore store:  list ){
			map.put(store.getId(), store);
		}*/
		//循环records对象
		/*if(records!=null){
			for(Map<String, Object> record: records){
				//取出record对象中的storeId，用来匹配对应的商家对象
				Long storeId = (Long) record.get("id");
				ModelStore modelStore = map.get(storeId);
				String picUrl=null;
				if(modelStore!=null&&modelStore.getPicture()!=null){
					//获得商家对象，取出图片url，存放到record中
					picUrl = modelStore.getPicture().getUrl();
				}
				record.put("url", picUrl);
			}
		}*/
		model.addAttribute("records", records);
		page.setTotal(iServStore.countByCondition(condition));
		//page.setTotal(iServStore.countMultiQuery(t, page));
		model.addAttribute("page", page);
		model.addAttribute("t", t);
		
		return basePath + "/store/page-grid";
	}
	
	@RequestMapping(value="/add-ui.do")
	public String addUI(Model model,Condition<ModelUser> condition, ModelUser t) {
		t.setStatus(1);
		condition.setT(t);
		List<ModelUser> mgrs = iServUser.findByCondition(condition, true);
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
		
		ModelStore store = iServStore.detailFind(t.getId());
		//ModelFileUpload picture = iServFileUpload.find(store.getPictureId());
		//store.setPicture(picture);
		model.addAttribute("store", store);
		//ModelUser manage = iServUser.find(store.getManagerId());
		//model.addAttribute("manage", manage);
		
		/*Condition<ModelCar> condition = new Condition<ModelCar>();
		ModelCar modelCar = new ModelCar();
		modelCar.setStatus(1);
		modelCar.setStoreId(store.getId());
		condition.setT(modelCar);
		Page<ModelCar> page = new Page<ModelCar>();
		page.setStart(0);
		page.setLimit(12);
		condition.setPage(page);
		List<ModelCar> carList = iServStore.getCarByCondition(condition);
		model.addAttribute("carList", carList);*/
		
		
		return basePath + "/store/detail";
	}
	
	@RequestMapping(value="/edit-ui.do")
	public String editUI(Model model, Long id) {
		logger.debug("==> edit ModelStore with id --> {}", id);
		ModelStore store = iServStore.find(id);
		ModelFileUpload picture = iServFileUpload.find(store.getPictureId());
		store.setPicture(picture);
		ModelUser t = new ModelUser();
		Condition<ModelUser> condition = new Condition<ModelUser>();
		t.setStatus(1);
		condition.setT(t);
		ModelUser manager = iServUser.find(store.getManagerId());
		List<ModelUser> users = iServUser.findByCondition(condition,true);
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
	
	@RequestMapping(value = "/typeahead/store.do")
	public String store(Model model, Condition<ModelStore> condition, ModelStore t) {
		condition.setT(t);
		List<ModelStore> records = iServStore.findByCondition(condition);
		return render(model, new JsonStore<ModelStore>(records, records.size()));
	}
	
	@RequestMapping(value = "/typeahead/user.do")
	public String user(Model model, Condition<ModelUser> condition, ModelUser t) {
		condition.setT(t);
		List<ModelUser> records = iServUser.findByCondition(condition);
		return render(model, new JsonStore<ModelUser>(records, records.size()));
	}
	
}
