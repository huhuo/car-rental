package com.huhuo.cmsystem.dict;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.carservicecore.constant.Dictionary.DictGroup;
import com.huhuo.carservicecore.sys.dictionary.ModelDictionary;
import com.huhuo.cmsystem.SystemBaseCtrl;
import com.huhuo.integration.web.JsonStore;
@Controller("cmsystemCtrlDictionary")
@RequestMapping(value="/cmsystem/dict")
public class CtrlDictionary extends SystemBaseCtrl {

	@Resource(name = "cmsystemServDictionary")
	private IServDictionary iServDictionary;
	
	@RequestMapping(value = "/groups/{groupName}.do")
	public String groups(Model model, @PathVariable String groupName) {
		List<ModelDictionary> records = iServDictionary.getGroupsBy(DictGroup.valueOf(groupName));
		return render(model, new JsonStore<ModelDictionary>(records, records.size()));
	}
	
}
