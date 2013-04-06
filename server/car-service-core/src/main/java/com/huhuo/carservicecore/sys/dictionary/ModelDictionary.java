package com.huhuo.carservicecore.sys.dictionary;

import com.huhuo.integration.base.BaseModel;

public class ModelDictionary extends BaseModel {

	private static final long serialVersionUID = -5442698614579807178L;

	/** 组名（字典组关键字） **/
	private String groupName;
	/** 组别显示名称 **/
	private String groupDisplayName;
	/** 典字key **/
	private Integer dictKey;
	/** 典字vaue **/
	private String dictValue;
	/** 字典值显示名称 **/
	private String dictDisplayName;
	/** 存储用于排序的数字，值越大越往后排 **/
	private Integer orderNo;
	/** 备注 **/
	private String comment;
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupDisplayName() {
		return groupDisplayName;
	}
	public void setGroupDisplayName(String groupDisplayName) {
		this.groupDisplayName = groupDisplayName;
	}
	public Integer getDictKey() {
		return dictKey;
	}
	public void setDictKey(Integer dictKey) {
		this.dictKey = dictKey;
	}
	public String getDictValue() {
		return dictValue;
	}
	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}
	public String getDictDisplayName() {
		return dictDisplayName;
	}
	public void setDictDisplayName(String dictDisplayName) {
		this.dictDisplayName = dictDisplayName;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
