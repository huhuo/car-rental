package com.huhuo.carservicecore.db;

import com.huhuo.integration.base.IBaseModel;
import com.huhuo.integration.db.spring.JdbcTplBaseExtenseServ;

/**
 * util service associated with DAO
 * @author wuyuxuan
 * @param <T>
 */
public abstract class GenericBaseExtenseServ<T extends IBaseModel<Long>> 
	extends JdbcTplBaseExtenseServ<T>{

}
