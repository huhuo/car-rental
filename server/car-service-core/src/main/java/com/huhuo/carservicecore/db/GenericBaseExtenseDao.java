package com.huhuo.carservicecore.db;


import javax.sql.DataSource;

import com.huhuo.integration.base.IBaseModel;
import com.huhuo.integration.db.spring.JdbcTplBaseExtenseDao;

/**
 * general DBDao support for multiple data source, supply general DB persist operation
 * @author wuyuxuan
 * @param <T>
 */
public abstract class GenericBaseExtenseDao<T extends IBaseModel<Long>> extends JdbcTplBaseExtenseDao<T>{

	@Override
	public DataSource getDataSource() {
		if(dataSource == null) {
			dataSource = AppContextFactory.getContext().getBean("routingDataSource", DataSource.class);
		}
		return dataSource;
	}
	
}
