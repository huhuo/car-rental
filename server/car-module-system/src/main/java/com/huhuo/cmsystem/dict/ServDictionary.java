package com.huhuo.cmsystem.dict;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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

}
