package com.huhuo.cmsystem.file;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.carservicecore.sys.file.ModelFileUpload;
import com.huhuo.cmsystem.SystemBaseCtrl;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Page;
import com.huhuo.integration.web.JsonStore;


@Controller("cmsystemCtrlFileUpload")
@RequestMapping(value="/cmsystem/file/fileupload")
public class CtrlFileUpload extends SystemBaseCtrl {
	
	@Resource(name = "cmsystemServFileUpload")
	private IServFileUpload iServFileUpload;
	
	@RequestMapping(value="/get.do")
	public String get(Model model, Condition<ModelFileUpload> condition){
		logger.debug("server receive: condition={}", condition);
		List<ModelFileUpload> records = iServFileUpload.findModels(new Page<ModelFileUpload>(0, 10));
		return render(model, new JsonStore<ModelFileUpload>(records, records.size()));
	}
	
}
