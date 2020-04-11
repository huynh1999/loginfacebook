/*
	@author:Quang Truong
	@date: Jan 15, 2020
*/

package com.jwatgroupb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class CartEntity extends BaseEntity{

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	private UserEntity userEntity;
	
	@OneToMany(mappedBy = "cartEntity")
	private List<CartItemEntity> listCartItem = new ArrayList<>();
	
	@Column(name = "cartcode")
	private String cartCode;

	public String getCartCode() {
		return cartCode;
	}

	public void setCartCode(String cartCode) {
		this.cartCode = cartCode;
	}

	public List<CartItemEntity> getListCartItem() {
		return listCartItem;
	}

	public void setListCartItem(List<CartItemEntity> listCartItem) {
		this.listCartItem = listCartItem;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	
	

}
