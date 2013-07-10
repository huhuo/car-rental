package com.huhuo.cmsystem.ms;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping(value = "/get.do")
	public String get(Model model, HttpServletRequest req, ModelSMS msg) {
		logger.info("send message --> {}", msg);
		ModelUser sender = getSession(req).get(SessionKey.USER);
		iServSMS.send(sender.getId(), msg.getRecieverId(), msg.getContent(),"success");
		return render(model, new Message<ModelSMS>(Status.SUCCESS, "send messsage successfully!", msg));
	}
	
	@RequestMapping(value = "/send.do")
	public String send(Model model, HttpServletRequest req, String content, Date startTime,
			@RequestParam("recieverIds") List<Long> recieverIds, boolean allContact) {
		ModelUser sender = getSession(req).get(SessionKey.USER);
		logger.info("==> message={}, recieverIds={}, allContact={}", content, recieverIds, allContact);
		if (allContact) {
			iServSMS.sendAll(sender.getId(), content, "success", startTime);
		} else {
			iServSMS.send(sender.getId(), recieverIds, content,"success", startTime);
		}
		return render(model, new Message<String>(Status.SUCCESS, "发送成功！"));
	}
	
}
