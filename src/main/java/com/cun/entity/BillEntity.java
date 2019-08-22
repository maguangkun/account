package com.cun.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 账单表
 * @author GaoYU
 *
 */
@Entity
@Table(name="T_BillEntity")//账单表
public class BillEntity extends BaseEntity{
	@ManyToOne
	@JoinColumn(name="user_Id")
	private User userId;
	//收货人
	@Column(name="take_Person")
	private String takePerson;
	//货物名称
	@Column(name="cargo_Name")
	private String cargoName;
	//是否欠账
	@Column(name="debt_State")
	private String debtState;
	//货物重量
	@Column(name="cargo_Weight")
	private BigDecimal cargoWeight;
	//货物单价格
	@Column(name="cargo_One_Price")
	private BigDecimal cargoOnePrice;
	//货物总价格
	@Column(name="cargo_Many_Price")
	private BigDecimal cargoManyPrice;
	//实际总价格 
	@Column(name="reality_Price")
	private BigDecimal realityPrice;
	//手机号
	@Column(name="take_Person_Phone")
	private String takePersonPhone;
	//备注
	@Column(name="remark")
	private String remark;
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public String getTakePerson() {
		return takePerson;
	}
	public void setTakePerson(String takePerson) {
		this.takePerson = takePerson;
	}
	public String getCargoName() {
		return cargoName;
	}
	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}
	public String getDebtState() {
		return debtState;
	}
	public void setDebtState(String debtState) {
		this.debtState = debtState;
	}
	public BigDecimal getCargoWeight() {
		return cargoWeight;
	}
	public void setCargoWeight(BigDecimal cargoWeight) {
		this.cargoWeight = cargoWeight;
	}
	public BigDecimal getCargoOnePrice() {
		return cargoOnePrice;
	}
	public void setCargoOnePrice(BigDecimal cargoOnePrice) {
		this.cargoOnePrice = cargoOnePrice;
	}
	public BigDecimal getCargoManyPrice() {
		return cargoManyPrice;
	}
	public void setCargoManyPrice(BigDecimal cargoManyPrice) {
		this.cargoManyPrice = cargoManyPrice;
	}
	public BigDecimal getRealityPrice() {
		return realityPrice;
	}
	public void setRealityPrice(BigDecimal realityPrice) {
		this.realityPrice = realityPrice;
	}
	public String getTakePersonPhone() {
		return takePersonPhone;
	}
	public void setTakePersonPhone(String takePersonPhone) {
		this.takePersonPhone = takePersonPhone;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
