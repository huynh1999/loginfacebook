/*
	@author:Quang Truong
	@date: Feb 10, 2020
*/

package com.jwatgroupb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwatgroupb.entity.PayerEntity;

public interface PayerRepository extends JpaRepository<PayerEntity, Long>{

}
