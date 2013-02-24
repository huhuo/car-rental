package com.huhuo.carservicecore.db;

import java.util.List;

import com.huhuo.integration.base.BaseServ;
import com.huhuo.integration.base.IBaseExtenseDao;
import com.huhuo.integration.base.IBaseExtenseServ;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Page;

/**
 * util service associated with DAO
 * @author wuyuxuan
 * @param <T>
 */
public abstract class GenericBaseExtenseServ<T> 
	extends BaseServ implements IBaseExtenseServ<T>{

	/**
	 * get DAO for this service
	 * @return
	 */
	public abstract IBaseExtenseDao<T> getDao();
	
	public abstract Class<T> getModelClazz();
	
	@Override
	public Integer add(T t) {
		// TODO Auto-generated method stub
		return getDao().add(t);
	}

	@Override
	public <V> T find(V id) {
		// TODO Auto-generated method stub
		return getDao().find(getModelClazz(), id);
	}

	@Override
	public Integer delete(T t) {
		// TODO Auto-generated method stub
		return getDao().delete(t);
	}

	@Override
	public Integer update(T t) {
		// TODO Auto-generated method stub
		return getDao().update(t);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return getDao().count();
	}

	@Override
	public Integer addBatch(List<T> list) {
		// TODO Auto-generated method stub
		int[] addBatch = getDao().addBatch(list);
		return addBatch.length;
	}

	@Override
	public List<T> findByCondition(Condition<T> condition) {
		// TODO Auto-generated method stub
		return getDao().findByCondition(condition);
	}

	@Override
	public List<T> findByCondition(Condition<T> condition, boolean injected) {
		// TODO Auto-generated method stub
		return getDao().findByCondition(condition);
	}

	@Override
	public Long countByCondition(Condition<T> condition) {
		// TODO Auto-generated method stub
		return getDao().countByCondition(condition);
	}

	@Override
	public List<T> findModels(Page page) {
		// TODO Auto-generated method stub
		if(page == null) {
			return null;
		}
		return  getDao().findModels(getModelClazz(), page.getStart(), page.getLimit());
	}
	
}
