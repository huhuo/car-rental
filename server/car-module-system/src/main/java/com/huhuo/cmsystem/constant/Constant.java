package com.huhuo.cmsystem.constant;

import org.apache.commons.configuration.PropertiesConfiguration;

import com.huhuo.integration.config.PropertiesConfigurationManager;

public class Constant {
	
	protected static PropertiesConfiguration config = PropertiesConfigurationManager.getInstance()
			.getPropertiesConfiguration("/META-INF/resources/conf/car-module-system/system.properties");
	
	public static final String MODULE_GROUPED_BY_PARENT_ID = "moduleGroupedByParentId";
	/**
	 * keys of keys' prefix in session
	 */
	public static final String SESSION_MODULE = "seModule";
	public static final String SESSION_MODULE_INDEX_PAGE = "seModuleIdxPage";			//一级菜单
	public static final String SESSION_MODULE_INDEX_PANEL ="seModuleIdxPanel"; 			//二级菜单前缀
	public static final String SESSION_ELEMENT = "seElement";							//页面元素
	public static final String SESSION_USER = "seUser";									//用户
	public static final String SESSION_LOGIN_ERR_MSG = "seLoginErrMsg";					//登录错误信息
	
	public static final String FILE_UPLOAD_CACHE_PATH = config.getString("file.upload.cache.path");
	public static final String FILE_UPLOAD_PERSIST_PATH = config.getString("file.upload.persist.path");
	
	public enum Suffix {
		PNG(1),
		JPG(2),
		GIF(3),
		;
		/** value **/
		private Integer value;
		Suffix(Integer value) {
			this.value = value;
		}
		public Integer getValue() {
			return this.value;
		}
	}
	
	public static void main(String[] args) {
		Suffix suffix = Suffix.valueOf("JPG2");
		System.out.println(suffix);
		System.out.println(suffix.getValue());
	}
	
}
