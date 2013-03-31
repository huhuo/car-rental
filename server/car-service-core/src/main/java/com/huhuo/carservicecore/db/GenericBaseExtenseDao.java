package com.huhuo.carservicecore.db;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.alibaba.fastjson.JSONArray;
import com.huhuo.integration.base.IBaseExtenseDao;
import com.huhuo.integration.base.IBaseModel;
import com.huhuo.integration.config.GlobalConstant.DateFormat;
import com.huhuo.integration.db.mysql.BeanHelper;
import com.huhuo.integration.db.mysql.BeanHelper.GetterSetter;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Group;
import com.huhuo.integration.db.mysql.Order;
import com.huhuo.integration.db.mysql.Page;
import com.huhuo.integration.exception.DaoException;
import com.huhuo.integration.util.StringUtils;

/**
 * general DBDao support for multiple data source, supply general DB persist operation
 * @author wuyuxuan
 * @param <T>
 */
public abstract class GenericBaseExtenseDao<T extends IBaseModel<Long>> implements IBaseExtenseDao<T>{
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	protected DataSource dataSource;
	
	/**
	 * get table's in DB mapping this entity
	 * @return
	 */
	public abstract String getTableName();
	/**
	 * get class for generic T
	 * @return
	 */
	public abstract Class<T> getModelClazz();
	/**
	 * Return the JDBC DataSource used by this DAO.
	 * subclass should specify a JdbcTemplate instance for all DB persist operation
	 * @param name the data source's bean name
	 * @return
	 */
	public DataSource getDataSource(String name) {
		if(dataSource == null) {
			dataSource = AppContextFactory.getContext().getBean(name, DataSource.class);
		}
		return dataSource;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	/**
	 * @see #getDataSource(String)
	 */
	public DataSource getDataSource() {
		return getDataSource("routingDataSource");
	}
	/**
	 * Return the JdbcTemplate for this DAO,
	 * pre-initialized with the DataSource or set explicitly.
	 */
	public final JdbcTemplate getJdbcTemplate() {
		DataSource dataSource = getDataSource();
		if(dataSource == null) {
			throw new DaoException("---> spring bean 'dataSource' is required");
		}
		return new JdbcTemplate(dataSource);
	}
	
	@Override
	public boolean save(T t) throws DaoException {
		if(t == null)
			throw new DaoException("---> model t can't be null");
		if(update(t) < 1) {
			add(t);
			return true;
		}
		return false;
	}
	
	@Override
	public Integer add(T t) throws DaoException {
		// validate the parameter passed in
		if(t == null) {
			return null;
		}
		SimpleJdbcInsert insert = new SimpleJdbcInsert(getJdbcTemplate());
		insert.withTableName(getTableName()).usingGeneratedKeyColumns("id");
		BeanHelper.GetterSetter[] getterSetterArray = BeanHelper.getGetterSetter(getModelClazz());
		Map<String, Object> args = new HashMap<String, Object>();
		List<String> cols = new ArrayList<String>();
		for(final BeanHelper.GetterSetter gs : getterSetterArray){
			// use auto increase strategy for primary key
			if("id".equals(gs.propertyName)) {
				continue;
			}
			cols.add(gs.propertyName);
			Object value = null;
			try{
				value = gs.getter.invoke(t);
			}catch(Exception e){
				logger.warn(null, e);
			}
			value = value == null ? gs.getter.getDefaultValue() : value;
			args.put(gs.propertyName, value);
		}
		insert.usingColumns(cols.toArray(new String[cols.size()]));
		Number id = insert.executeAndReturnKey(args);
		logger.debug("---> SQL --> {}", insert.getInsertString());
		logger.debug("---> params --> {}", JSONArray.toJSONString(args));
		logger.debug("---> primary key return --> {}", id);
		if(id instanceof Long)
			t.setId((Long) id);
		return 1;
		
	}
	/**
	 * add a record using primitive SQL
	 * @param t
	 * @return
	 */
	protected Integer insert(T t) {
		BeanHelper.GetterSetter[] getterSetterArray = BeanHelper.getGetterSetter(t.getClass());
		final StringBuffer sb = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sb.append("INSERT INTO ").append(getTableName()).append("(");
		boolean first = true;
		for(final BeanHelper.GetterSetter gs : getterSetterArray){
			// use auto increase strategy for primary key
			if("id".equals(gs.propertyName))
				continue;
			if(!first){
				sb.append(",");
			}else{
				first=false;
			}
			sb.append(gs.propertyName);
			Object value = null;
			try{
				value = gs.getter.invoke(t);
			}catch(Exception e){
				logger.warn(null, e);
			}
			values.add(value == null ? gs.getter.getDefaultValue() : value);
		}
		sb.append(") values(");
		for(int i=0;i<values.size();i++){
			sb.append("?");
			if(i<values.size()-1)
				sb.append(",");
		}
		sb.append(")");
		final Object[] objects = values.toArray(new Object[values.size()]);
		int update = getJdbcTemplate().update(sb.toString(), objects);
		logger.debug("---> SQL --> {}", sb.toString());
		logger.debug("---> params --> {}", JSONArray.toJSONString(objects));
		logger.debug("---> row affected --> {}", update);
		return update;
		
	}
	
	@Override
	public int[] addBatch(List<T> list) {
		logger.debug("addBatch begin with list --> {}", JSONArray.toJSONStringWithDateFormat(list, DateFormat.LONG_FORMAT));
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(getDataSource());
		jdbcInsert.withTableName(getTableName()).usingGeneratedKeyColumns("id");
		BeanPropertySqlParameterSource[] batch = new BeanPropertySqlParameterSource[list.size()];
		for(int i=0; i<list.size(); i++) {
			batch[i] = new BeanPropertySqlParameterSource(list.get(i));
		}
		int[] executeBatch = jdbcInsert.executeBatch(batch);
		logger.debug("---> addBatch end with affected row --> {}", executeBatch);
		return executeBatch;
	}

	@Override
	public Integer update(T t) throws DaoException {
		// validate the parameter passed in
		if (t == null) {
			return null;
		}
		BeanHelper.GetterSetter[] getterSetterArray = BeanHelper.getGetterSetter(t.getClass());
		final StringBuffer sb = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sb.append("UPDATE ").append(getTableName()).append(" SET ");
		boolean first = true;
		for(final BeanHelper.GetterSetter gs : getterSetterArray){
			if("id".equals(gs.propertyName)) {
				continue;
			}
			if(!first){
				sb.append(",");
			}else{
				first=false;
			}
			sb.append(gs.propertyName+"=?");
			Object value = null;
			try{
				value = gs.getter.invoke(t);
			}catch(Exception e){
				logger.warn(null,e);
			}
			values.add(value==null?gs.getter.getDefaultValue():value);
		}
		sb.append(" WHERE id=?");
		values.add(t.getId());
		final Object[] objects = values.toArray(new Object[values.size()]);
		int update = getJdbcTemplate().update(sb.toString(),objects);
		logger.debug("---> SQL --> {}", sb.toString());
		logger.debug("---> params --> {}", StringUtils.join(objects, ","));
		logger.debug("---> row affected --> {}", update);
		return update;
	}

	@Override
	public Integer delete(T t) throws DaoException {
		if(t!=null)
			return deleteById(t.getId());
		else
			return null;
	}

	@Override
	public <PK> Integer deleteById(PK id) throws DaoException {
		String sql = String.format("DELETE FROM %s WHERE id=?", getTableName());
		Integer update = getJdbcTemplate().update(sql, id);
		logger.debug("---> SQL --> {}", sql);
		logger.debug("---> params --> {}", id);
		logger.debug("---> row affected --> {}", update);
		return update;
	}
	
	@Override
	public Long count() {
		String sql = String.format("SELECT COUNT(*) FROM %s", getTableName());
		long count = getJdbcTemplate().queryForLong(sql);
		logger.debug("---> SQL --> {}", sql);
		logger.debug("---> result count --> {}", count);
		return count;
	}
	
	@Override
	public List<T> queryForList(String sql, Class<T> clazz, Object... args)
			throws DaoException {
		List<T> rs = getJdbcTemplate().query(sql, args, new BeanPropertyRowMapper<T>(clazz));
		logger.debug("---> SQL --> {}", sql);
		logger.debug("---> params --> {}", StringUtils.join(args, ","));
		if(rs == null) {
			logger.debug("---> result set\t--> {}", rs);
		} else {
			for(int index=1; index<=rs.size(); index++) {
				logger.debug("---> result set {}\t--> {}", index, rs.get(index - 1));
			}
		}
		return rs;
	}
	
	@Override
	public List<T> queryForList(String sql, Object... args)
			throws DaoException {
		return queryForList(sql, getModelClazz(), args);
	}
	
	@Override
	public T queryForObject(String sql, Class<T> clazz, Object... args)
			throws DaoException {
		try {
			T singleResult = getJdbcTemplate().queryForObject(sql, BeanPropertyRowMapper.newInstance(clazz), args);
			logger.debug("---> SQL --> {}", sql);
			logger.debug("---> params --> {}", StringUtils.join(args, ","));
			logger.debug("---> result set -->{}", singleResult);
			return singleResult;
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e.getMessage());
			logger.warn("---> error cause by --> {}", e.getMessage());
			return null;
		}
	}
	
	@Override
	public T queryForObject(String sql, Object... args) throws DaoException {
		return queryForObject(sql, getModelClazz(), args);
	}
	
	@Override
	public <PK> T find(Class<T> clazz, PK id)
			throws DaoException {
		String sql = String.format("SELECT * FROM %s WHERE id=?", getTableName());
		return queryForObject(sql, clazz, id);
	}
	
	@Override
	public <PK> T find(PK id) throws DaoException {
		return find(getModelClazz(), id);
	}

	@Override
	public List<T> findModels(Class<T> clazz,
			Integer start, Integer limit) throws DaoException {
		String sql;
		if(start!=null && limit!=null) {
			sql = String.format("SELECT * FROM %s ORDER BY id DESC LIMIT %s, %s", getTableName(), start, limit);
		} else {
			sql = String.format("SELECT * FROM %s ORDER BY id DESC", getTableName());
		}
		return queryForList(sql, clazz);
	}
	
	@Override
	public List<T> findModels(Integer start, Integer limit) throws DaoException {
		return findModels(getModelClazz(), start, limit);
	}
	
	@Override
	public List<T> findByCondition(Condition<T> condition) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * FROM ").append(getTableName());
			List<Object> values = constructCondition(condition, sb);
			List<T> list = queryForList(sb.toString(), values.toArray());
			return list;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	/**
	 * construct condition by condition
	 * @param condition
	 * @param sb
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private List<Object> constructCondition(Condition<T> condition, StringBuilder sb)
			throws IllegalAccessException, InvocationTargetException {
		// append where clause
		List<Object> values = new ArrayList<Object>();
		T t = condition.getT();
		if (t != null) {
			boolean first = true;
			GetterSetter[] getterSetterArray = BeanHelper.getGetterSetter(t.getClass());
			for(final GetterSetter gs : getterSetterArray){
				Object fieldValue = gs.getter.invoke(t);
				if(fieldValue instanceof String
						|| fieldValue instanceof Integer
						|| fieldValue instanceof Float
						|| fieldValue instanceof Double) {
					if(first && fieldValue!=null) {
						sb.append(" WHERE ").append(gs.propertyName);
						if(fieldValue instanceof String) {
							sb.append(" LIKE ? ");
							values.add("%" + fieldValue + "%");
						} else {
							sb.append("=? ");
							values.add(fieldValue);
						}
						first = false;
					} else if(fieldValue!=null) {
						sb.append("AND ").append(gs.propertyName);
						if(fieldValue instanceof String) {
							sb.append(" LIKE ? ");
							values.add("%" + fieldValue + "%");
						} else {
							sb.append("=? ");
							values.add(fieldValue);
						}
					}
				}
			}
		}
		// group by clause
		List<Group> groupList = condition.getGroupList();
		if(groupList!=null && groupList.size()>0) {
			boolean first = true;
			for(Group group : groupList) {
				if(first) {
					sb.append(" GROUP BY ");
					first = false;
				}
				sb.append(group.getField()).append(" ").append(group.getDir()).append(", ");
			}
			sb.deleteCharAt(sb.lastIndexOf(","));
		}
		// order clause
		List<Order> orderList = condition.getOrderList();
		if(orderList!=null && orderList.size()>0) {
			boolean first = true;
			for(Order order : orderList) {
				if(first) {
					sb.append(" ORDER BY ");
					first = false;
				}
				sb.append(order.getField()).append(" ").append(order.getDir()).append(", ");
			}
			sb.deleteCharAt(sb.lastIndexOf(","));
		}
		// limit clause
		Page page = condition.getPage();
		if(page!=null && page.getStart()!=null && page.getLimit()!=null) {
			sb.append("LIMIT ").append(page.getStart()).append(", ").append(page.getLimit());
		}
		sb = new StringBuilder(StringUtils.trim(sb.toString()));	// trim string
		return values;
	}
	
	@Override
	public Long countByCondition(Condition<T> condition) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT COUNT(*) FROM ").append(getTableName());
			condition.setGroupList(new ArrayList<Group>());
			condition.setOrderList(new ArrayList<Order>());
			List<Object> values = constructCondition(condition, sb);
			long ret = getJdbcTemplate().queryForLong(sb.toString(), values.toArray());
			return ret;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
}
