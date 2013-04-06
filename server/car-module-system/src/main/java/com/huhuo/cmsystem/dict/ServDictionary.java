package com.huhuo.cmsystem.dict;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huhuo.carservicecore.constant.Dictionary.Dict;
import com.huhuo.carservicecore.constant.Dictionary.DictGroup;
import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.carservicecore.sys.dictionary.IDaoDictionary;
import com.huhuo.carservicecore.sys.dictionary.ModelDictionary;
import com.huhuo.integration.base.IBaseExtenseDao;

@Service("cmsystemServDictionary")
public class ServDictionary extends GenericBaseExtenseServ<ModelDictionary> 
	implements IServDictionary {

	@Resource(name = "carservicecoreDaoDictionary")
	private IDaoDictionary<ModelDictionary> iDaoDictionary;
	
	@Override
	public IBaseExtenseDao<ModelDictionary> getDao() {
		return iDaoDictionary;
	}

	@Override
	public Class<ModelDictionary> getModelClazz() {
		return ModelDictionary.class;
	}

	@Override
	public List<ModelDictionary> getGroupsBy(DictGroup dictGroup) {
		return iDaoDictionary.getGroupsBy(dictGroup);
	}

	@Override
	public ModelDictionary getBy(DictGroup dictGroup, Integer dictKey) {
		return iDaoDictionary.getBy(dictGroup, dictKey);
	}

	@Override
	public ModelDictionary getBy(Dict dict) {
		return iDaoDictionary.getBy(dict);
	}
	
}
