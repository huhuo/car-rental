package com.huhuo.cmsystem.store;

import java.util.List;
import java.util.Map;

import com.huhuo.carservicecore.cust.store.ModelStore;
import com.huhuo.integration.base.IBaseExtenseServ;


public interface IServStore extends IBaseExtenseServ<ModelStore> {

	List<Map<String, Object>> multiQuery();
	
	
}
