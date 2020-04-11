/*
	@author:Quang Truong
	@date: Feb 2, 2020
*/

package com.jwatgroupb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwatgroupb.entity.BillEntity;

public interface BillRepository extends JpaRepository<BillEntity, Long> {

	BillEntity findOneByBillCode(String billCode);
}
