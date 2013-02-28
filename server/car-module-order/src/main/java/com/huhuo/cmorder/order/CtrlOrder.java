package com.huhuo.cmorder.order;

import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.carservicecore.consumer.ModelConsumer;
import com.huhuo.carservicecore.order.ModelOrder;
import com.huhuo.integration.base.BaseCtrl;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Page;
import com.huhuo.integration.exception.HuhuoException;
import com.huhuo.integration.util.ExtUtils;
import com.huhuo.integration.web.Message;
import com.huhuo.integration.web.Message.Status;


@Controller("cmorderCtrlOrder")
@RequestMapping(value="/order/order")
public class CtrlOrder extends BaseCtrl {
	
	@Resource(name = "cmorderServOrder")
	private IServOrder iservOrder;
	
	@RequestMapping(value="/get.do")
	public void get(Condition<ModelConsumer> condition, OutputStream out){
		try {
			logger.debug("server receive: condition={}", condition);
//			condition.setPage(new Page(0, 30));
//			List<ModelConsumer> list = servConsumer.findByCondition(condition);
			List<ModelOrder> list = iservOrder.findModels(new Page(0, 10));
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
