package com.huhuo.carservicecore.cust.car;

import com.huhuo.carservicecore.cust.store.ModelStore;
import com.huhuo.carservicecore.sys.file.ModelFileUpload;
import com.huhuo.integration.base.BaseModel;
import com.huhuo.integration.db.mysql.Dict;
import com.huhuo.integration.db.mysql.DictMgr;
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
	 * 车辆状态，字典查询，关键字cust_car_status，0、删除；1、在库待租；2、已经预定；3、已经出租；
	 * 4、维修保养中；5、报废
	 */
	private Integer status;
	
	/***************************************
	 * 外联对象
	 ***************************************/
	@NotSqlField
	private ModelCarType carType;
	@NotSqlField
	private ModelFileUpload picture;
	@NotSqlField
	private ModelStore store;
	@NotSqlField
	private Dict colorDict;
	@NotSqlField
	private ModelStore warehouse;
	@NotSqlField
	private Dict statusDict;
	
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
		Dict dict = DictMgr.get(GROUP_CUST_CAR_COLOR, color);
		setColorDict(dict);
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
		Dict dict = DictMgr.get(GROUP_CUST_CAR_STATUS, status);
		setStatusDict(dict);
		this.status = status;
	}
	public ModelCarType getCarType() {
		return carType;
	}
	public void setCarType(ModelCarType carType) {
		this.carType = carType;
	}
	public ModelFileUpload getPicture() {
		return picture;
	}
	public void setPicture(ModelFileUpload picture) {
		this.picture = picture;
	}
	public ModelStore getStore() {
		return store;
	}
	public void setStore(ModelStore store) {
		this.store = store;
	}
	public Dict getColorDict() {
		return colorDict;
	}
	public void setColorDict(Dict colorDict) {
		this.colorDict = colorDict;
	}
	public ModelStore getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(ModelStore warehouse) {
		this.warehouse = warehouse;
	}
	public Dict getStatusDict() {
		return statusDict;
	}
	public void setStatusDict(Dict statusDict) {
		this.statusDict = statusDict;
	}
	
	/** color definition **/
	public static final String GROUP_CUST_CAR_COLOR = "CUST_CAR_COLOR";
	public static final Dict COLOR_silvery = new Dict(GROUP_CUST_CAR_COLOR, 1, "银色");
	public static final Dict COLOR_silvery_Gray = new Dict(GROUP_CUST_CAR_COLOR, 2, "银灰色");
	public static final Dict COLOR_gray = new Dict(GROUP_CUST_CAR_COLOR, 3, "灰色");
	public static final Dict COLOR_red = new Dict(GROUP_CUST_CAR_COLOR, 4, "红色");
	public static final Dict COLOR_yellow = new Dict(GROUP_CUST_CAR_COLOR, 5, "黄色");
	public static final Dict COLOR_white = new Dict(GROUP_CUST_CAR_COLOR, 6, "白色");
	public static final Dict COLOR_orange = new Dict(GROUP_CUST_CAR_COLOR, 7, "橙色");
	public static final Dict COLOR_green = new Dict(GROUP_CUST_CAR_COLOR, 8, "绿色");
	
	/** status definition **/
	public static final String GROUP_CUST_CAR_STATUS = "CUST_CAR_STATUS";
	public static final Dict STATUS_DELETED = new Dict(GROUP_CUST_CAR_STATUS, 0, "已经删除");
	public static final Dict STATUS_ON_RENT = new Dict(GROUP_CUST_CAR_STATUS, 1, "在库待租");
	public static final Dict STATUS_BOOKED = new Dict(GROUP_CUST_CAR_STATUS, 2, "已经预定");
	public static final Dict STATUS_RENTED = new Dict(GROUP_CUST_CAR_STATUS, 3, "已经出租");
	public static final Dict STATUS_ON_MAINTAIN = new Dict(GROUP_CUST_CAR_STATUS, 4, "维修保养中");
	public static final Dict STATUS_SCRAP = new Dict(GROUP_CUST_CAR_STATUS, 5, "报废");

}
