/*
	@author:Quang Truong
	@date: Feb 11, 2020
*/

package com.jwatgroupb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwatgroupb.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
	CategoryEntity findOneByCategoryName(String categoryName);
}
