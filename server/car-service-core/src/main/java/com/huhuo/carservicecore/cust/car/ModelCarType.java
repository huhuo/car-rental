package com.huhuo.carservicecore.cust.car;

import com.huhuo.carservicecore.sys.dictionary.ModelDictionary;
import com.huhuo.carservicecore.sys.file.ModelFileUpload;
import com.huhuo.integration.base.BaseModel;
import com.huhuo.integration.db.mysql.NotSqlField;


public class ModelCarType extends BaseModel {
	
	private static final long serialVersionUID = -8126350138823774934L;
	
	/** 车型名称 **/
	private String name;
	/** 图片地址（静态资源） **/
	private Long iconId;
	/** 车辆类别（字典表字段，组名：cust_car_type_category，1、轿车；2、越野汽车；3、客车；4、货车；5、自卸汽车；6、牵引汽车；7、专用汽车） **/
	private Integer category;
	/** 座位数 **/
	private Integer seating;
	/** 油箱容量（单位：升） **/
	private Integer tankCapacity;
	/** 可行驶里程数 **/
	private Double drivingRange;
	/** 车型使用的收费标准id（与csm_charge_standard表一对一） **/
	private Long chargeStandardId;
	
	/***************************************
	 * 外联对象
	 ***************************************/
	@NotSqlField
	private ModelDictionary categoryDict;
	@NotSqlField
	private ModelFileUpload icon;
	
	private @NotSqlField ModelChargeStandard chargeStandard;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getIconId() {
		return iconId;
	}
	public void setIconId(Long iconId) {
		this.iconId = iconId;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public Integer getSeating() {
		return seating;
	}
	public void setSeating(Integer seating) {
		this.seating = seating;
	}
	public Integer getTankCapacity() {
		return tankCapacity;
	}
	public void setTankCapacity(Integer tankCapacity) {
		this.tankCapacity = tankCapacity;
	}
	public Double getDrivingRange() {
		return this.drivingRange;
	}
	public void setDrivingRange(Double drivingRange) {
		this.drivingRange = drivingRange;
	}
	public Long getChargeStandardId() {
		return chargeStandardId;
	}
	public void setChargeStandardId(Long chargeStandardId) {
		this.chargeStandardId = chargeStandardId;
	}
	public ModelDictionary getCategoryDict() {
		return categoryDict;
	}
	public void setCategoryDict(ModelDictionary categoryDict) {
		this.categoryDict = categoryDict;
	}
	public ModelChargeStandard getChargeStandard() {
		return chargeStandard;
	}
	public void setChargeStandard(ModelChargeStandard chargeStandard) {
		this.chargeStandard = chargeStandard;
	}
	public ModelFileUpload getIcon() {
		return icon;
	}
	public void setIcon(ModelFileUpload icon) {
		this.icon = icon;
	}

}
