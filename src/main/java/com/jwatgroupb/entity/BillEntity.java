/*
	@author:Quang Truong
	@date: Jan 15, 2020
*/

package com.jwatgroupb.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.jwatgroupb.validator.Phone;

@Entity
@Table(name = "bill")
public class BillEntity extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	private UserEntity userEntity;

	@OneToMany(mappedBy = "billEntity")
	private List<BillDetailEntity> listBillDetails = new ArrayList<>();

	@Column(name = "totalmoney")
	private float totalMoney;

	@Column(name = "status")
	private int status;

	@Column(name = "deliverydate")
	private Date deliveryDate;
	
	@Column(name = "billcode")
	private String billCode;

	@NotBlank
	@Length(min = 2, max = 30)
	@Column(name = "receiver")
	private String receiver;

	@NotBlank
	@Length(min = 5, max = 70)
	@Column(name = "address")
	private String address;

	@Phone(message = "Phone Number is invalid")
	@Column(name = "phonenumber")
	private String phonenumber;
	
	@NotBlank
	@Column(name = "paymentmethod")
	private String paymentMethod;

	@NotBlank
	@Email
	@Column(name = "email")
	private String email;

	@Length( max = 200)
	@Column(name = "note")
	private String note;
	
	@OneToOne(mappedBy = "billEntity")
	private PayerEntity payerEntity;

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public List<BillDetailEntity> getListBillDetails() {
		return listBillDetails;
	}

	public void setListBillDetails(List<BillDetailEntity> listBillDetails) {
		this.listBillDetails = listBillDetails;
	}

	public float getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(float totalMoney) {
		this.totalMoney = totalMoney;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public PayerEntity getPayerEntity() {
		return payerEntity;
	}

	public void setPayerEntity(PayerEntity payerEntity) {
		this.payerEntity = payerEntity;
	}

	
	
}
