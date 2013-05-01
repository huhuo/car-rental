package com.huhuo.carservicecore.cust.car;

import com.huhuo.carservicecore.sys.dictionary.ModelDictionary;
import com.huhuo.carservicecore.sys.file.ModelFileUpload;
import com.huhuo.integration.base.BaseModel;
import com.huhuo.integration.db.mysql.NotSqlField;


public class ModelCar extends BaseModel {

	private static final long serialVersionUID = -7109163662313613337L;
	
	/** 车辆型号（1个车型对应多个车辆） **/
	private Long carTypeId;
	/** 上传图片的id，与sys_file_upload表关联 **/
	private Long pictureId;
	/** 车牌号 **/
	private String licencePlate;
	/** 所属门店 **/
	private Long storeId;
	/** 引擎编号 **/
	private String engineNo;
	/** GPS设备编号 **/
	private String gpsNo;
	/**
	 * 车身颜色（字典查询，组别关键字cust_car_color：1、银色；2、银灰色；3、灰色；4、红色；
	 * 5、黄色；6、白色；7、 橙色；8、绿色；）
	 */
	private Integer color;
	/** 已经行驶里程数（单位：公里） **/
	private Long drivedKilometer;
	/** 油量（单位，升） **/
	private Double oilMass;
	/** 入库门店的id，与cust_store表关联 **/
	private Long warehouseId;
	/** 车辆位置信息id，与表cust_car_location表关联 **/
	private Long locationId;
	/**
	 * 车辆状态，字典查询，关键字cust_car_status，0、删除；1、在库待租；2、已经出租；
	 * 3、维修保养中；4、报废 
	 */
	private Integer status;
	
	/**
	 * 外联对象
	 */
	@NotSqlField
	private ModelFileUpload picture;
	
	private transient ModelDictionary colorDict;
	
	private transient ModelDictionary statusDict;
	
	public Long getCarTypeId() {
		return carTypeId;
	}
	public void setCarTypeId(Long carTypeId) {
		this.carTypeId = carTypeId;
	}
	public Long getPictureId() {
		return pictureId;
	}
	public void setPictureId(Long pictureId) {
		this.pictureId = pictureId;
	}
	public String getLicencePlate() {
		return licencePlate;
	}
	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public String getEngineNo() {
		return engineNo;
	}
	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}
	public String getGpsNo() {
		return gpsNo;
	}
	public void setGpsNo(String gpsNo) {
		this.gpsNo = gpsNo;
	}
	public Integer getColor() {
		return color;
	}
	public void setColor(Integer color) {
		this.color = color;
	}
	public Long getDrivedKilometer() {
		return drivedKilometer;
	}
	public void setDrivedKilometer(Long drivedKilometer) {
		this.drivedKilometer = drivedKilometer;
	}
	public Double getOilMass() {
		return oilMass;
	}
	public void setOilMass(Double oilMass) {
		this.oilMass = oilMass;
	}
	public Long getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public ModelFileUpload getPicture() {
		return picture;
	}
	public void setPicture(ModelFileUpload picture) {
		this.picture = picture;
	}
	public ModelDictionary getColorDict() {
		return colorDict;
	}
	public void setColorDict(ModelDictionary colorDict) {
		this.colorDict = colorDict;
	}
	public ModelDictionary getStatusDict() {
		return statusDict;
	}
	public void setStatusDict(ModelDictionary statusDict) {
		this.statusDict = statusDict;
	}
	
}
