package com.huhuo.car.service.core.constant;

/**
 * memcached的分区及区内键名
 * 
 * @author shifengxuan
 *
 */
public class MemcachedRegion {
    
	// 第三方应用信息区
    public enum AppRegion{
    	/**key for ModelAppDataDevCw list*/
    	APP_LIST;
    	
    	/**
    	 * 获取分区�?
    	 *  
    	 * @return
    	 */
    	public static String getRegion(){
    		return AppRegion.class.getSimpleName();
    	}

    }
    // 系统监控�?
    public enum MonitorRegion{
    	/**key for test connection*/
    	TEST;
    	public static String getRegion(){
    		return MonitorRegion.class.getSimpleName();
    	}
    }

}


