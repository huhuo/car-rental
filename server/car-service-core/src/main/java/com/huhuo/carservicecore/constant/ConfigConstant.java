package com.huhuo.carservicecore.constant;

import org.apache.commons.configuration.PropertiesConfiguration;

import com.huhuo.integration.config.PropertiesConfigurationManager;

public class ConfigConstant {
	protected static PropertiesConfiguration config = PropertiesConfigurationManager
			.getInstance().getPropertiesConfiguration("/META-INF/resources/conf/mobile-service-new/system.properties");
	public static final String ERAY_ENCODING = config.getString("eray.encoding","UTF-8"); 
	public static final String ERAY_DATEFORMAT = config.getString("eray.dateformat", "yyyy-MM-dd HH:mm:ss");
	public static final String FILE_GPS_PATH = config.getString("file.gps.path");
	public static final String SERVICE_CODE = config.getString("service.code");
	public static final String META_DATA_BASEDIR_PREVIEW = config.getString("meta.data.baseDir.preview", "/opt/preview");
	public static final String META_DATA_BASEDIR = config.getString("meta.data.baseDir", "/opt/meta");
}
