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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity{

	@OneToMany(mappedBy = "productEntity")
	private List<BillDetailEntity> listBillDetail = new ArrayList<>();
	
	@OneToMany(mappedBy = "productEntity")
	private List<CartItemEntity> listCartItem = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "productid"), 
								  inverseJoinColumns = @JoinColumn(name = "categoryid"))
	private List<CategoryEntity> listCategories = new ArrayList<>();
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "amount")
	private int amount;
	
	@Column(name = "url1")
	private String url1;
	
	@Column(name = "url2")
	private String url2;
	
	@Column(name = "url3")
	private String url3;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "promotion")
	private int promotion;

	public List<BillDetailEntity> getListBillDetail() {
		return listBillDetail;
	}

	public void setListBillDetail(List<BillDetailEntity> listBillDetail) {
		this.listBillDetail = listBillDetail;
	}

	public List<CategoryEntity> getListCategories() {
		return listCategories;
	}

	public void setListCategories(List<CategoryEntity> listCategories) {
		this.listCategories = listCategories;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getUrl1() {
		return url1;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}

	public String getUrl2() {
		return url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}

	public String getUrl3() {
		return url3;
	}

	public void setUrl3(String url3) {
		this.url3 = url3;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getPromotion() {
		return promotion;
	}

	public void setPromotion(int promotion) {
		this.promotion = promotion;
	}

	public List<CartItemEntity> getListCartItem() {
		return listCartItem;
	}

	public void setListCartItem(List<CartItemEntity> listCartItem) {
		this.listCartItem = listCartItem;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
