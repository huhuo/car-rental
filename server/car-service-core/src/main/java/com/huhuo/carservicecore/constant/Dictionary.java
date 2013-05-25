package com.huhuo.carservicecore.constant;
/**
 * dictionary definition
 * @author wuyuxuan
 */
public interface Dictionary {
	/**
	 * get group for a specified dictionary
	 * @return
	 */
	ModelDictGroup getGroup();
	/**
	 * get dict key for a specified dictionary
	 * @return
	 */
	Integer getDicKey();
	/**
	 * group name for selecting a group of dictionary
	 * @author wuyuxuan
	 */
	public enum ModelDictGroup {
		GENERAL_SYS_GENDER("GENERAL_SYS_GENDER", "性别"),							// gender for general usage
		;
		private String groupName;
		private String groupDisplayName;
		ModelDictGroup(String groupName, String groupDisplayName) {
			this.groupName = groupName;
			this.groupDisplayName = groupDisplayName;
		}
		public String getGroupName() {
			return groupName;
		}
		public String getGroupDisplayName() {
			return groupDisplayName;
		}
	}
	/**
	 * a specified dictionary
	 * @author wuyuxuan
	 */
	public enum ModelDict implements Dictionary {
		// 1、男；2、女
		GENERAL_SYS_GENDER_male(ModelDictGroup.GENERAL_SYS_GENDER, 1),
		GENERAL_SYS_GENDER_female(ModelDictGroup.GENERAL_SYS_GENDER, 2),
		;
		private ModelDictGroup groupName;
		private Integer dicKey;
		ModelDict(ModelDictGroup groupName, Integer dicKey) {
			this.groupName = groupName;
			this.dicKey = dicKey;
		}
		@Override
		public ModelDictGroup getGroup() {
			return groupName;
		}
		@Override
		public Integer getDicKey() {
			return dicKey;
		}
	}
	
}
