package com.cun.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;



@Entity
@Table(name="Dictionary_Type")
public class DictionaryType extends BaseEntity{
	@Column(length = 50)
	private String typeName;
	@Column(length = 50)
	private String typeCode;
	@Column(length = 50)
	private String oredrNum;
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getOredrNum() {
		return oredrNum;
	}
	public void setOredrNum(String oredrNum) {
		this.oredrNum = oredrNum;
	}
}
