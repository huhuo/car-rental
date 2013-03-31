package com.huhuo.cmsystem.district;

import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.carservicecore.sys.district.ModelCity;
import com.huhuo.carservicecore.sys.district.ModelProvince;
import com.huhuo.integration.base.BaseCtrl;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Page;
import com.huhuo.integration.exception.HuhuoException;
import com.huhuo.integration.util.ExtUtils;
import com.huhuo.integration.web.Message;
import com.huhuo.integration.web.Message.Status;


@Controller("cmsystemCtrlProvince")
@RequestMapping(value="/cmsystem/district/province")
public class CtrlProvince extends BaseCtrl {
	
	@Resource(name = "cmsystemServProvince")
	private IServProvince iServProvince;
	
	@RequestMapping(value="/get.do")
	public void get(Condition<ModelCity> condition, OutputStream out){
		try {
			logger.debug("server receive: condition={}", condition);
			List<ModelProvince> list = iServProvince.findModels(new Page(0, 10));
			write(ExtUtils.getJsonStore(list, list.size()), out);
		} catch (HuhuoException e) {
			logger.warn(e.getMessage());
			write(new Message<String>(Status.FAILURE, e.getMessage()), out);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getFullStackTrace(e));
			write(new Message<String>(Status.ERROR, e.getMessage()), out);
		}
	}
}
