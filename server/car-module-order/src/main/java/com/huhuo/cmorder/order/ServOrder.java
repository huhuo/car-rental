package com.huhuo.cmorder.order;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huhuo.carservicecore.csm.consumer.IDaoConsumer;
import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
import com.huhuo.carservicecore.csm.order.IDaoOrder;
import com.huhuo.carservicecore.csm.order.ModelOrder;
import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.integration.base.IBaseExtenseDao;

@Service("cmorderServOrder")
public class ServOrder extends GenericBaseExtenseServ<ModelOrder> implements IServOrder {

	@Resource(name = "carservicecoreDaoOrder")
	private IDaoOrder idaoOrder;
	
	@Resource(name = "carservicecoreDaoConsumer")
	private IDaoConsumer iDaoConsumer;

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
	
	@Override
	public List<ModelConsumer> getConsumerListByPhone(String phone){
		StringBuilder sb=new StringBuilder();
		List<Object> list=new ArrayList<Object>();
		sb.append("select * from csm_consumer where 1=1 ");
		if(phone!=null){
			sb.append(" and mobileNumber like ?");
			list.add("%"+phone+"%");
		}
		sb.append(" limit 0 , 10");
		List<ModelConsumer> queryForList = iDaoConsumer.queryForList(sb.toString(), ModelConsumer.class, list.toArray());
		
		return queryForList;
	}

	

}
