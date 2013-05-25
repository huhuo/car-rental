package com.huhuo.cmsystem.quartz;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.huhuo.carservicecore.sys.user.ModelUser;
import com.huhuo.cmsystem.SystemBaseCtrl;
import com.huhuo.integration.util.TimeUtils;
@Controller("cmsystemCtrlSchedule")
@RequestMapping(value="/cmsystem/quartz/schedule")
public class CtrlSchedule extends SystemBaseCtrl {

	@Resource(name = "cmsystemServSchedule")
	private IServSchedule<ModelUser> iServSchedule;
	
	/** this is an example for IServSchedule's usage **/
	@RequestMapping(value = "/launch-example.do")
	public String launchExample(Model model) {
		logger.info("launch schedule --> {}", model);
		long now = new Date().getTime();
		ScheduledFuture<ModelUser> schedule = iServSchedule.schedule(new Runnable() {
			@Override
			public void run() {
				logger.info("execute shedule task by ServSchedule --> {}", TimeUtils.format(new Date()));
			}
		}, new Date(now + 5000));
		return render(model, JSON.toJSONString(schedule, true));
	}
	
}
