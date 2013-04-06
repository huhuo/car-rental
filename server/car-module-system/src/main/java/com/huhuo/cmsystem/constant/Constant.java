package com.huhuo.cmsystem.constant;

public class Constant {
	
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
	
	public enum GeneralPage {
		EXCEPTION_PAGE("msg", "/car-module-system/exception"),
		MSG_PAGE("msg", "/car-module-system/message"),
		;
		/** the key of data stored in request scope **/
		private String attrName;
		/** the location of rendering page the request will jump to **/
		private String location;
		GeneralPage(String attrName, String location) {
			this.attrName = attrName;
			this.location = location;
		}
		public String getAttrName() {
			return this.attrName;
		}
		public String getLocation() {
			return this.location;
		}
	}
	
}
