package com.huhuo.cmsystem.dict;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huhuo.carservicecore.constant.Dictionary.ModelDictGroup;
import com.huhuo.carservicecore.sys.dictionary.ModelDictionary;
import com.huhuo.cmsystem.SystemBaseCtrl;
import com.huhuo.integration.db.mysql.Dict;
import com.huhuo.integration.db.mysql.DictMgr;
import com.huhuo.integration.web.JsonStore;
@Controller("cmsystemCtrlDictionary")
@RequestMapping(value="/cmsystem/dict")
public class CtrlDictionary extends SystemBaseCtrl {

	@Resource(name = "cmsystemServDictionary")
	private IServDictionary iServDictionary;
	
	@RequestMapping(value = "/groups/{groupName}.do")
	public String groups(Model model, @PathVariable String groupName) {
		List<ModelDictionary> records = iServDictionary.getGroupsBy(ModelDictGroup.valueOf(groupName));
		return render(model, new JsonStore<ModelDictionary>(records, records.size()));
	}
	@RequestMapping(value = "/groups/simple/{group}.do")
	public String simpleDict(Model model, @PathVariable String group, String disp) {
		List<Dict> records = DictMgr.match(group, disp);
		return render(model, new JsonStore<Dict>(records, records.size()));
	}
	
}
