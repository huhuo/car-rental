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
		GENERAL_GENDER("GENERAL_GENDER", "性别"),							// gender for general usage
		CUST_CAR_COLOR("CUST_CAR_COLOR", "车辆颜色"),						// car color
		CUST_CAR_STATUS("CUST_CAR_STATUS", "车辆状态"),						// car status
		CUST_CAR_TYPE_CATEGORY("CUST_CAR_TYPE_CATEGORY", "车型类别"),		// car type category
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
		// 1、男；2、女
		GENERAL_GENDER_male(DictGroup.GENERAL_GENDER, 1),
		GENERAL_GENDER_female(DictGroup.GENERAL_GENDER, 2),
		// 1、银色；2、银灰色；3、灰色；4、红色；5、黄色；6、白色；7、 橙色；8、绿色；
		CUST_CAR_COLOR_silvery(DictGroup.CUST_CAR_COLOR, 1),
		CUST_CAR_COLOR_silveryGray(DictGroup.CUST_CAR_COLOR, 2),
		CUST_CAR_COLOR_gray(DictGroup.CUST_CAR_COLOR, 3),
		CUST_CAR_COLOR_red(DictGroup.CUST_CAR_COLOR, 4),
		CUST_CAR_COLOR_yellow(DictGroup.CUST_CAR_COLOR, 5),
		CUST_CAR_COLOR_white(DictGroup.CUST_CAR_COLOR, 6),
		CUST_CAR_COLOR_orange(DictGroup.CUST_CAR_COLOR, 7),
		CUST_CAR_COLOR_green(DictGroup.CUST_CAR_COLOR, 8),
		// 0、删除；1、在库待租；2、已经出租；3、维修保养中；4、报废
		CUST_CAR_STATUS_delete(DictGroup.CUST_CAR_STATUS, 1),
		CUST_CAR_STATUS_free(DictGroup.CUST_CAR_STATUS, 2),
		CUST_CAR_STATUS_rented(DictGroup.CUST_CAR_STATUS, 4),
		CUST_CAR_STATUS_maintaining(DictGroup.CUST_CAR_STATUS, 5),
		CUST_CAR_STATUS_scrap(DictGroup.CUST_CAR_STATUS, 6),
		// 1、轿车；2、越野车；3、客车；4、货车；5、自卸汽车；6、牵引汽车；7、专用汽车
		CUST_CAR_TYPE_CATEGORY_car(DictGroup.CUST_CAR_TYPE_CATEGORY, 1),
		CUST_CAR_TYPE_CATEGORY_suv(DictGroup.CUST_CAR_TYPE_CATEGORY, 2),
		CUST_CAR_TYPE_CATEGORY_coach(DictGroup.CUST_CAR_TYPE_CATEGORY, 3),
		CUST_CAR_TYPE_CATEGORY_truck(DictGroup.CUST_CAR_TYPE_CATEGORY, 4),
		CUST_CAR_TYPE_CATEGORY_autodumper(DictGroup.CUST_CAR_TYPE_CATEGORY, 5),
		CUST_CAR_TYPE_CATEGORY_towingTruck(DictGroup.CUST_CAR_TYPE_CATEGORY, 6),
		CUST_CAR_TYPE_CATEGORY_sepcialCar(DictGroup.CUST_CAR_TYPE_CATEGORY, 7),
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
