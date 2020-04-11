/*
	@author:Quang Truong
	@date: Jan 21, 2020
*/

package com.jwatgroupb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwatgroupb.entity.CartEntity;
import com.jwatgroupb.entity.CartItemEntity;
import com.jwatgroupb.entity.ProductEntity;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long>{
	CartItemEntity findOneByCartEntityAndProductEntity(CartEntity cartEntity, ProductEntity productEntity);
}
