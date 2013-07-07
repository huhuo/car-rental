package com.huhuo.cmsystem.security.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.alibaba.fastjson.JSON;
import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
import com.huhuo.carservicecore.cust.store.ModelStore;
import com.huhuo.carservicecore.sys.user.IDaoUser;
import com.huhuo.carservicecore.sys.user.ModelUser;
import com.huhuo.cmsystem.SystemBaseCtrl;
import com.huhuo.cmsystem.security.Session.SessionKey;
import com.huhuo.cmsystem.store.IServStore;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Dir;
import com.huhuo.integration.db.mysql.Order;
import com.huhuo.integration.db.mysql.Page;
import com.huhuo.integration.db.mysql.Where;
import com.huhuo.integration.util.ExtUtils;
import com.huhuo.integration.web.JsonStore;
import com.huhuo.integration.web.Message;
import com.huhuo.integration.web.Message.Status;


@Controller("cmsystemCtrlUser")
                        
@RequestMapping(value="/cmsystem/security/user")
public class CtrlUser extends SystemBaseCtrl {
	
	protected String basePath = "/car-module-system/security";
	
	
	/*************************************************************
	 * user info management
	 *************************************************************/
	
	@Resource(name = "cmsystemServUser")
	private IServUser iServUser;
	
	@Resource(name = "cmsystemServStore")
	private IServStore iServStore;
	
	
	@Resource(name = "carservicecoreDaoUser")
	private IDaoUser iDaoUser;
	
	@RequestMapping(value="/index.do")
	public String index() {
		logger.debug("---> access user management page");
		return basePath + "/user/index";
	}
	
	@RequestMapping(value="/get.do")
	public void get(HttpServletResponse resp, Condition<ModelConsumer> condition) {
		logger.debug("---> server receive: condition={}", condition);
		write(ExtUtils.getJsonStore(null, 10), resp);
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
	
	@RequestMapping(value="/condition/get.do")
	public String get(Model model, Condition<ModelUser> condition, ModelUser t) {
		if(t.getStatus()==null) {
			condition.setWhereList(new Where("status > ?", 0));
		}
		//List<Map<String, Object>> records = iServStore.findByCondition(condition);
		condition.setT(t);
		condition.setOrderList(new Order("createTime", Dir.DESC), new Order("updateTime", Dir.DESC));
		logger.debug("---> server receive: condition={}", condition);
		Page<ModelUser> page = condition.getPage();
		if(page == null) {
			page = new Page<ModelUser>();
		}
		
		List<ModelUser> list = iServUser.findByCondition(condition, true);
		model.addAttribute("list", JSON.parseArray(list.toString()));
		page.setTotal(iServUser.countByCondition(condition));
		model.addAttribute("page", page);
		model.addAttribute("t", t);
		
		return basePath + "/user/page-grid";
	}
	
	@RequestMapping(value="/validate/name.do")
	public void validaName(String loginName, HttpServletResponse resp)  {
		if((loginName.trim()).length()==0) {
			
		}
		ModelUser user = iDaoUser.findByName(loginName);
		if (user == null) {
			write("true", resp);
		} else {
			write("false", resp);
		}
		logger.debug("==> access validaName");
	}
	@RequestMapping(value="/add-ui.do")
	public String addUI(Model model,Condition<ModelStore> condition, ModelStore t) {
		t.setStatus(1);
		condition.setT(t);
		List<ModelStore> mgrs = iServStore.findByCondition(condition,true);
		model.addAttribute("mgrs", mgrs);
		logger.debug("==> access add ui");
		return basePath + "/user/add-ui";
	}
	
	@RequestMapping(value="/add.do")
	public void add(HttpServletResponse resp, ModelUser user ,String birthday1) throws ParseException {
		birthday1 = birthday1+" 00:00:00";
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");  
		Date date = format2.parse(birthday1);
		user.setBirthday(date);
		user.setCreateTime(new Date());
		logger.debug("==> access add ui");
		iServUser.add(user);
		Message<ModelUser> msg = new Message<ModelUser>(Status.SUCCESS, "add new store success!", user);
		write(msg, resp);
	}
	
	@RequestMapping(value="/delete.do")
	public void delete(HttpServletResponse resp,@RequestParam(value="ids[]") List<Long> ids) {
		// receive data
		logger.debug("==> batch delete -->{}", ids);
		// retrieve model form DB
		iServUser.deleteBatch(ids);
		write(new Message<List<Long>>(Status.SUCCESS, "删除成功", ids), resp);
	}
	@RequestMapping(value="/activateLock.do")
	public void activateLock(HttpServletResponse resp,@RequestParam(value="ids[]") List<Long> ids) {
		// receive data
		logger.debug("==> batch delete -->{}", ids);
		// retrieve model form DB
		ArrayList<ModelUser> list = new ArrayList<ModelUser>();
		for(long id :ids) {
			ModelUser user = iServUser.find(id);
			list.add(user);
		}
		iServUser.deleteBatch(ids);
		write(new Message<List<Long>>(Status.SUCCESS, "删除成功", ids), resp);
	}
	
	
	@RequestMapping(value="/edit-ui.do")
	public String editUI(Model model, ModelUser t) {
		logger.debug("==> edit ModelUser with id --> {}", t.getId());
		Condition<ModelUser> condition = new Condition<ModelUser>(t, null, null, null);
		ModelUser user = iServUser.findByCondition(condition, true).get(0);
		ModelStore store = iServStore.find(user.getStoreId());
		ModelStore modelStore = new ModelStore();
		modelStore.setStatus(1);
		Condition<ModelStore> condition1 = new Condition<ModelStore>(modelStore, null, null, null);
		List<ModelStore> list = iServStore.findByCondition(condition1, true);
		model.addAttribute("list", list);
		model.addAttribute("user", user);
		model.addAttribute("store", store);
		return basePath + "/user/edit-ui";
	}
	
	@RequestMapping(value="/update.do")
	public void update(HttpServletResponse resp, ModelUser t,String birthday1) throws Exception {
		// retrieve model form DB
		if(birthday1==null||birthday1=="") {
			ModelUser user = iDaoUser.find(t.getId());
			Date birthday = user.getBirthday();
			birthday1=birthday.toString().substring(0, 9)+" 00:00:00";
		}else {
			birthday1 = birthday1+" 00:00:00";
		}
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");  
		Date date = format2.parse(birthday1);
		t.setBirthday(date);
		t.setUpdateTime(new Date());
		iServUser.update(t);
		Message<ModelUser> msg = new Message<ModelUser>(Status.SUCCESS, "修改成功", t);
		write(msg, resp);
	}
	
	@RequestMapping(value="/detail.do")
	public String detail(Model model, ModelUser t) {
		logger.debug("==> edit ModelUser with id --> {}", t.getId());
		Condition<ModelUser> condition = new Condition<ModelUser>(t, null, null, null);
		ModelUser user = iServUser.findByCondition(condition, true).get(0);
		ModelStore store = iServStore.find(user.getStoreId());
		ModelStore modelStore = new ModelStore();
		modelStore.setStatus(1);
		Condition<ModelStore> condition1 = new Condition<ModelStore>(modelStore, null, null, null);
		List<ModelStore> list = iServStore.findByCondition(condition1, true);
		model.addAttribute("list", list);
		model.addAttribute("user", user);
		model.addAttribute("store", store);
		return basePath + "/user/detail";
	}
	@RequestMapping(value="/activate.do")
	public void activate(HttpServletResponse resp, ModelUser t) {
		ModelUser user = iServUser.find(t.getId());
		user.setStatus(1);
		iServUser.update(user);
		logger.debug("==> activate ModelUser with id --> {}", t.getId());
		Message<ModelUser> msg = new Message<ModelUser>(Status.SUCCESS, "激活成功", t);
		write(msg, resp);
	}
	@RequestMapping(value="/lock.do")
	public void lock(HttpServletResponse resp, ModelUser t) {
		ModelUser user = iServUser.find(t.getId());
		user.setStatus(2);
		iServUser.update(user);
		logger.debug("==> activate ModelUser with id --> {}", t.getId());
		Message<ModelUser> msg = new Message<ModelUser>(Status.SUCCESS, "锁定成功", t);
		write(msg, resp);
	}
	/*************************************************************
	 * person info management, password modify, person info modify, etc
	 *************************************************************/
	
	@RequestMapping(value="/person.do")
	public String person(HttpServletRequest req, Model model) {
		ModelUser user = getSession(req).get(SessionKey.USER);
		Condition<ModelUser> condition = new Condition<ModelUser>(user, null, null, null);
		ModelUser DBuser = iServUser.findByCondition(condition, true).get(0);
		logger.debug("=========>>>"+DBuser.getPicture());
		model.addAttribute("user", DBuser);
		logger.debug("---> access person management page");
		return basePath + "/user/person";
	}
	@RequestMapping(value="/editPerson.do")
	public void editPerson(HttpServletResponse resp, ModelUser t) {
		iServUser.update(t);
		logger.debug("---> access person management page");
		Message<ModelUser> msg = new Message<ModelUser>(Status.SUCCESS, "修改成功", t);
		write(msg, resp);
	}
	
}
