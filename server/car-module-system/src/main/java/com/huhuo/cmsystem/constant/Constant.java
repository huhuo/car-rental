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
	public static final int SECURITY_SESSION_EXPRIE_TIME = config.getInt("security.session.expiretime", 1800);
	
	public static final String FILE_UPLOAD_PERSIST_PATH = config.getString("file.upload.persist.path");
	public static final String FILE_UPLOAD_FILE_SERVER_URL = config.getString("file.upload.file.server.url");
	
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
