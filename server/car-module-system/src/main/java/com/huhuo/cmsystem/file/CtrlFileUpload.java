package com.huhuo.cmsystem.file;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.huhuo.carservicecore.sys.file.ModelFileUpload;
import com.huhuo.cmsystem.SystemBaseCtrl;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Page;
import com.huhuo.integration.web.JsonStore;
import com.huhuo.integration.web.Message;
import com.huhuo.integration.web.Message.Status;


@Controller("cmsystemCtrlFileUpload")
@RequestMapping(value="/cmsystem/file/fileupload")
public class CtrlFileUpload extends SystemBaseCtrl {
	
	@Resource(name = "cmsystemServFileUpload")
	private IServFileUpload iServFileUpload;
	
	@RequestMapping(value="/get.do")
	public String get(Model model, Condition<ModelFileUpload> condition){
		logger.debug("==> server receive: condition={}", condition);
		List<ModelFileUpload> records = iServFileUpload.findModels(new Page<ModelFileUpload>(0, 10));
		return render(model, new JsonStore<ModelFileUpload>(records, records.size()));
	}
	
	@RequestMapping(value="/cached.do", method=RequestMethod.POST)
	public String cached(Model model, HttpServletRequest req, 
			@RequestParam MultipartFile cachedFile) {
		logger.debug("==> server receive: file={}", cachedFile);
//		uploadFile.transferTo(new File(req.getServletContext().
//				getRealPath("resources") + File.separator + uploadFile.getOriginalFilename()));
		ModelFileUpload data = iServFileUpload.uploadCacheFile(cachedFile);
		return render(model, new Message<ModelFileUpload>(Status.SUCCESS, "file cache success!", data));
	}
	
	
}
