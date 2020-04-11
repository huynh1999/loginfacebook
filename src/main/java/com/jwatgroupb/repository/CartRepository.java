/*
	@author:Quang Truong
	@date: Jan 21, 2020
*/

package com.jwatgroupb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jwatgroupb.entity.CartEntity;
import com.jwatgroupb.entity.UserEntity;


public interface CartRepository extends JpaRepository<CartEntity,Long>{
	CartEntity findOneByCartCode(String cartCode);
	CartEntity findOneByUserEntity(UserEntity userEntity);
}
