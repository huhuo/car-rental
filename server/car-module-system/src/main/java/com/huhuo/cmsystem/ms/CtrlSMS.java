package com.huhuo.cmsystem.ms;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@RequestMapping(value = "/send.do")
	public String get(Model model, HttpServletRequest req, ModelSMS msg) {
		logger.info("send message --> {}", msg);
		ModelUser sender = getSession(req).get(SessionKey.USER);
		iServSMS.send(sender.getId(), msg.getRecieverId(), msg.getContent(),"success");
		return render(model, new Message<ModelSMS>(Status.SUCCESS, "send messsage successfully!", msg));
	}
	
}
