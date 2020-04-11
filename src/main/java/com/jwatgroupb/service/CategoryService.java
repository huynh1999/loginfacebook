/*
	@author:Quang Truong
	@date: Feb 11, 2020
*/

/*
	@author:Quang Truong
	@date: Jan 14, 2020
*/

package com.jwatgroupb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwatgroupb.entity.CategoryEntity;
import com.jwatgroupb.entity.ProductEntity;
import com.jwatgroupb.repository.CategoryRepository;

@Service
public class CategoryService {
	
	
	@Autowired
	private CategoryRepository categoryRepository;

	public List<CategoryEntity> listCategory() {	
		return categoryRepository.findAll();
	}
	public List<ProductEntity> findAllProduct(String categoryName) {	
		return categoryRepository.findOneByCategoryName(categoryName).getListProducts();
	}
}
