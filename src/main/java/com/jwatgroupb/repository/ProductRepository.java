/*
	@author:Quang Truong
	@date: Jan 21, 2020
*/

package com.jwatgroupb.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jwatgroupb.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

	@Transactional
	@Query("select r from ProductEntity r")
	public List<ProductEntity> find10Product(Pageable pagebale);

	@Query(value = "select u from ProductEntity u where u.name like '%' || :keyword || '%'")
	public List<ProductEntity> search(@Param("keyword") String keyword);

	@Transactional
	@Query("delete from ProductEntity r where r.name = :name")
	void delete(String name);

	@Transactional
	@Query("select count (r.id) from ProductEntity r")
	long countTotalRecords();

	@Transactional
	@Query("select r from ProductEntity r")
	public List<ProductEntity> find10Products(Pageable pageable);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update ProductEntity p set p.name=:name, p.url1=:url1, p.amount=:amount, p.price = :price, p.promotion=:promotion where p.id = :id")
	public void update(@Param("id") long id, @Param("name") String name, @Param("url1") String url1,
			@Param("amount") int amount, @Param("price") float price, @Param("promotion") int promotion);
}
