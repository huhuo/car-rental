package com.huhuo.carservicecore.order;

import org.springframework.stereotype.Repository;

import com.huhuo.carservicecore.db.GenericBaseExtenseDao;
@Repository("carservicecoreDaoOrder")
public class DaoOrder extends GenericBaseExtenseDao<ModelOrder> implements IDaoOrder {

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "csm_order";
	}


}
