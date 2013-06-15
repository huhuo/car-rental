package com.huhuo.cmsystem.security;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.huhuo.integration.config.GlobalConstant;

/**
 * session class designed to store all data associated with {@link HttpSession}
 * @author root
 */
public class Session implements Serializable {

	private static final long serialVersionUID = -3426923073061444330L;
	
	/** inner container to store all data for {@link #Session()} **/
	private Map<SessionKey, Object> map = new HashMap<SessionKey, Object>();
	
	public enum SessionKey {
		/** user object **/
		USER,
		/** all menu **/
		MODULE,
		/** first level menu **/
		MODULE_INDEX_PAGE,
		/** second level menu **/
		MODULE_INDEX_PANEL,
		/** elements user can have **/
		ELEMENT,
		/** **/
		LOGIN_ERR_MSG,
		;
	}
	
	public Map<SessionKey, Object> getMap() {
		return map;
	}
	public void setMap(Map<SessionKey, Object> map) {
		this.map = map;
	}
	
	public Session(SessionKey key, Object value) {
		map.put(key, value);
	}
	/**
	 * put a new @param value with @param key, old value will be covered if @param value for @param key exist
	 * @param key
	 * @param value
	 * @return
	 */
	public Boolean put(SessionKey key, Object value) {
		Object object = map.put(key, value);
		return object!=null ? true : false;
	}
	/**
	 * get value with @param key
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(SessionKey key) {
		Object value = map.get(key);
		return (T) value;
	}
	
	@Override
	public String toString() {
		return JSONObject.toJSONStringWithDateFormat(map,
				GlobalConstant.DateFormat.LONG_FORMAT);
	}
	public String toString(String dateFormat) {
		return JSONObject.toJSONStringWithDateFormat(map, dateFormat);
	}
	
}
