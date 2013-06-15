package com.huhuo.carservicecore.constant;

import com.huhuo.carservicecore.sys.dictionary.ModelDictionary;
import com.huhuo.integration.util.StringUtils;

/**
 * 统一管理所有模块的memcached的分区及区内键名，防止出现memcached分区键名的冲突，
 * 同时也满足了不同模块之间跨区查询和操作数据的需求
 * @author root
 */
public interface MemcachedRegion {
    
	String getRegion();
	
	/**
	 * memcached region for module system
	 * @author root
	 */
	public enum RegionSys implements MemcachedRegion {
		/** security session region which taking SECURITY_SESSION_ID_sessionId as key and  **/
		SECURITY_SESSION,
		
		/** dictionary group region which taking DICT_GROUP_groupName as key and List of {@link ModelDictionary} as value **/
		DICT_GROUP,
		
		;

		@Override
		public String getRegion() {
			return StringUtils.join("_", getClass().getSimpleName(), this.toString());
		}
		
	}
	
}


