package com.huhuo.cmsystem.ms;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.carservicecore.csm.consumer.IDaoConsumer;
import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
import com.huhuo.carservicecore.cust.ms.ModelSMS;
import com.huhuo.carservicecore.sys.user.ModelUser;
import com.huhuo.cmsystem.SystemBaseCtrl;
import com.huhuo.cmsystem.security.Session.SessionKey;
import com.huhuo.integration.web.Message;
import com.huhuo.integration.web.Message.Status;
@Controller("cmsystemCtrlSMS")
@RequestMapping(value="/cmsystem/ms/sms")
public class CtrlSMS extends SystemBaseCtrl {

	@Resource(name = "cmsystemServSMS")
	private IServSMS iServSMS;
	
	@Resource(name="carservicecoreDaoConsumer")
	private IDaoConsumer idaoConsumer;
	
	@RequestMapping(value = "/send.do")
	public String get(Model model, HttpServletRequest req, ModelSMS msg) {
		logger.info("send message --> {}", msg);
		ModelUser sender = getSession(req).get(SessionKey.USER);
		iServSMS.send(sender.getId(), msg.getRecieverId(), msg.getContent(),"success");
		return render(model, new Message<ModelSMS>(Status.SUCCESS, "send messsage successfully!", msg));
	}
	
	@RequestMapping(value = "/sendSMS.do")
	public String send(Model model, HttpServletRequest req, ModelSMS msg) {
		ModelUser sender = getSession(req).get(SessionKey.USER);
		logger.info("send message --> {}", msg);
		
		boolean allContacts = msg.isAllContacts();
		if (allContacts) {
			Long count = idaoConsumer.count();
			
			//计算分页 真麻烦
			int myCount = Integer.parseInt(count + "");
			int pageSize = 10;
			int pageCount = 0;
			int result = myCount % pageSize;
			if (result != 0) {
				pageCount = (myCount / pageSize) + 1;
			} else {
				pageCount = (myCount / pageSize);
			}
			logger.info("consumer count:" + myCount + " pageCount:" + pageCount + " pageSize:" + pageSize);
			int start = 0;
			int end = 0;
			for (int i = 0; i < pageCount; i ++) {
				//该页起始数据为当前页数 * pageSize
				start = i * pageSize ;
				//如果开始也小于0，置为0
				if (start < 0) {
					start = 0;
				}
				List<ModelConsumer> models = idaoConsumer.findModels(ModelConsumer.class, start, pageSize);
				for (ModelConsumer consumer : models) {
					iServSMS.send(sender.getId(), consumer.getId(), msg.getContent(),"success");
				}
			}
		} else {
			String send = iServSMS.send(sender.getId(), msg.getRecieverId(), msg.getContent(),"success");
		}
		
		return null;
	}
	
}
