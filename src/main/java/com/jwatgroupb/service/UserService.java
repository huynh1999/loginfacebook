/*
	@author:Quang Truong
	@date: Jan 14, 2020
*/

package com.jwatgroupb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwatgroupb.constant.SystemConstant;
import com.jwatgroupb.entity.BillEntity;
import com.jwatgroupb.entity.CartItemEntity;
import com.jwatgroupb.entity.ProfileUserEntity;
import com.jwatgroupb.entity.UserEntity;
import com.jwatgroupb.repository.ProfileUserRepository;
import com.jwatgroupb.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private ProfileUserRepository profileUserRepository;
	
	@Autowired
	private BCryptPasswordEncoder  bCryptPasswordEncoder;

	public List<UserEntity> findAll() {	
		return userRepository.findAll();
	}
	
	public UserEntity findByUsername(String username) {
		return userRepository.findOneByUserNameAndActive(username, SystemConstant.ACTIVE_STATUS);
	}
	
	public List<CartItemEntity> findCartOfUser(String username){
		return findByUsername(username).getCartEntity().getListCartItem();
	}
	
	
	//HAI
	public void saveUser(UserEntity user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
	}
	public void saveProfileUser(ProfileUserEntity profileUser) {
		profileUserRepository.save(profileUser);
	}
	public UserEntity findByEmail(String email) {
		return userRepository.findFirstOneByEmail(email);
	}
	
	public ProfileUserEntity findByPhonenumber(String phonenumber) {
		return profileUserRepository.findOneByPhonenumber(phonenumber);
	}

	public ProfileUserEntity findByUserEntity(UserEntity userEntity) {
		
		return profileUserRepository.findOneByUserEntity(userEntity);
	}

	public List<BillEntity> findAllBill(Long userID) {	
		return userRepository.findOne(userID).getListBills();
	}
}
