package com.huhuo.carservicecore.db;

import org.springframework.util.Assert;

/**
 * a proxy class for multiple data source
 * @author root
 */
public class DataSourceContextHolder {

	/** a thread local member variable **/
	private static final ThreadLocal<DataSourceType> ctxHolder = new ThreadLocal<DataSourceType>();
	
	/**
	 * set data source type
	 * @param dataSourceType
	 */
	public static void setDataSourceType(DataSourceType dataSourceType) {
		Assert.notNull(dataSourceType, "DataSourceType can't be null");
		ctxHolder.set(dataSourceType);	
	}
	/**
	 * get data source
	 * @return
	 */
	public static DataSourceType getDataSourceType() {
		return ctxHolder.get();
	}
	/**
	 * clear data source
	 */
	public static void clearDataSourceType() {
		ctxHolder.remove();
	}
}
