package com.huhuo.cmbiz.ms;

import java.util.List;

import javax.annotation.Resource;

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


@Controller("cmbizCtrlSMS")
@RequestMapping(value="/cmbiz/ms")
public class CtrlSMS extends BaseCtrl {
	
	protected String basePath = "/car-module-biz/ms";
	
	@Resource(name = "carservicecoreDaoConsumer")
	private IDaoConsumer iDaoConsumer;
	
	/*************************************************************
	 * short message service page
	 *************************************************************/
	
	@RequestMapping(value="/index.do")
	public String index(Model model) {
		logger.info("==> access short message service page");
		return basePath + "/index";
	}
	
	@RequestMapping(value="/condition/get.do")
	public String get(Model model, Condition<ModelConsumer> condition, ModelConsumer t) {
		t.setStatus(1);
		condition.setT(t);
		condition.setOrderList(new Order("createTime", Dir.DESC), new Order("updateTime", Dir.DESC));
		logger.info("==> retrieve page grid");
		logger.info("---> server receive: condition={}", condition);
		Page<ModelConsumer> page = condition.getPage();
		if(page == null) {
			page = new Page<ModelConsumer>();
		}
		List<ModelConsumer> records = iDaoConsumer.findByCondition(condition);
		model.addAttribute("records", records);
		page.setTotal(iDaoConsumer.countByCondition(condition));
		model.addAttribute("page", page);
		model.addAttribute("t", t);
		return basePath + "/page-grid";
	}
	
}
