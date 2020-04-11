/*
	@author:Quang Truong
	@date: Jan 15, 2020
*/

package com.jwatgroupb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roleusers")
public class RoleUserEntity extends BaseEntity{


	@Column(name = "rolename")
	private String roleName;

	@OneToMany(mappedBy = "roleUserEntity")
	private List<UserEntity> listUsers = new ArrayList<>();

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<UserEntity> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<UserEntity> listUsers) {
		this.listUsers = listUsers;
	}

	
	
}
