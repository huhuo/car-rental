package com.huhuo.cmorder.order;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.carservicecore.order.IDaoOrder;
import com.huhuo.carservicecore.order.ModelOrder;
import com.huhuo.integration.base.IBaseExtenseDao;

@Service("cmorderServOrder")
public class ServOrder extends GenericBaseExtenseServ<ModelOrder> implements IServOrder {

	@Resource(name = "carservicecoreDaoOrder")
	private IDaoOrder idaoOrder;

	@Override
	public IBaseExtenseDao<ModelOrder> getDao() {
		// TODO Auto-generated method stub
		return idaoOrder;
	}

	@Override
	public Class<ModelOrder> getModelClazz() {
		// TODO Auto-generated method stub
		return ModelOrder.class;
	}

	

}
