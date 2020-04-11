/*
	@author:Quang Truong
	@date: Jan 14, 2020
*/

package com.jwatgroupb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jwatgroupb.entity.ProductEntity;
import com.jwatgroupb.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	public List<ProductEntity> findAllProduct() {	
		return productRepository.findAll();
	}

	public List<ProductEntity> find10Product(int offset){
		return productRepository.find10Product(new PageRequest(offset-1, 6));
	}
	public List<ProductEntity> search(String keyword) {
	    return productRepository.search(keyword);
	}

	public ProductEntity findByOneProductId(Long id) {
		
		return productRepository.findOne(id);
	}
}