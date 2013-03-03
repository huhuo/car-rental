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
	DictGroup getGroup();
	/**
	 * get dict key for a specified dictionary
	 * @return
	 */
	Integer getDicKey();
	/**
	 * group name for selecting a group of dictionary
	 * @author wuyuxuan
	 */
	public enum DictGroup {
		GENERAL_GENDER("GENERAL_GENDER", "性别"),		// gender for general usage
		;
		private String groupName;
		private String groupDisplayName;
		DictGroup(String groupName, String groupDisplayName) {
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
	public enum Dict implements Dictionary {
		GENERAL_GENDER_male(DictGroup.GENERAL_GENDER, 1),		// gender for male
		GENERAL_GENDER_female(DictGroup.GENERAL_GENDER, 2),		// gender for female
		;
		private DictGroup groupName;
		private Integer dicKey;
		Dict(DictGroup groupName, Integer dicKey) {
			this.groupName = groupName;
			this.dicKey = dicKey;
		}
		@Override
		public DictGroup getGroup() {
			return groupName;
		}
		@Override
		public Integer getDicKey() {
			return dicKey;
		}
	}
	
}
