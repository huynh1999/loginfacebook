/*
	@author:Quang Truong
	@date: Feb 10, 2020
*/

package com.jwatgroupb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="payer")
public class PayerEntity  extends BaseEntity{
	
	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "lastname")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "billid")
	private BillEntity billEntity;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BillEntity getBillEntity() {
		return billEntity;
	}

	public void setBillEntity(BillEntity billEntity) {
		this.billEntity = billEntity;
	}
	
	
}
