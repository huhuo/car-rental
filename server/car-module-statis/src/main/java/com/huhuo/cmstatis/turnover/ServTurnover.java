package com.huhuo.cmstatis.turnover;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huhuo.carservicecore.csm.order.IDaoOrder;
import com.huhuo.carservicecore.csm.order.ModelOrder;
import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.integration.base.IBaseExtenseDao;

@Service("cmstatisServTurnover")
@Transactional
public class ServTurnover extends GenericBaseExtenseServ<ModelOrder> implements IServTurnover {

	@Resource(name = "carservicecoreDaoOrder")
	private IDaoOrder iDaoOrder;
	
	@Override
	public IBaseExtenseDao<ModelOrder> getDao() {
		// TODO Auto-generated method stub
		return iDaoOrder;
	}

	@Override
	public void inject(ModelOrder t) {
		// TODO Auto-generated method stub
		
	}

}
