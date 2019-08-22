package com.cun.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="Dictionary_Detail")//字典明细表
public class DictionaryDetail extends BaseEntity{
	@Column(length = 50)
	private String dictionaryId;
	@Column(length = 50)
	private String dictdataName;
	@Column(length = 50)
	private String dictdataValue;
	@Column(length = 50)
	private String orderNum;
	public String getDictionaryId() {
		return dictionaryId;
	}
	public void setDictionaryId(String dictionaryId) {
		this.dictionaryId = dictionaryId;
	}
	public String getDictdataName() {
		return dictdataName;
	}
	public void setDictdataName(String dictdataName) {
		this.dictdataName = dictdataName;
	}
	public String getDictdataValue() {
		return dictdataValue;
	}
	public void setDictdataValue(String dictdataValue) {
		this.dictdataValue = dictdataValue;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
}
