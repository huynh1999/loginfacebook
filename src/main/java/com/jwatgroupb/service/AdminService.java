
package com.jwatgroupb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwatgroupb.constant.SystemConstant;
import com.jwatgroupb.entity.ProductEntity;
import com.jwatgroupb.entity.RoleUserEntity;
import com.jwatgroupb.entity.UserEntity;
import com.jwatgroupb.repository.ProductRepository;
import com.jwatgroupb.repository.RoleUserRepository;
import com.jwatgroupb.repository.UserRepository;


@Service
public class AdminService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleUserRepository roleUserRepository;
	@Autowired
	private ProductRepository productRepository;

	

	public List<UserEntity> findAllUser() {	
		return userRepository.findAll();
	}
	public List<ProductEntity> findAllProduct() {
		return productRepository.findAll();
				
		
	}
	public List<ProductEntity> find10Products(int offset) {
		return productRepository.find10Products(new PageRequest(offset-1,10));
//offset-1: because the first page-number == 1 but first offset ==0
	}
	
	public List<UserEntity> find10Users(int offset) {
		return userRepository.find10Users(new PageRequest(offset-1,10));
//offset-1: because the first page-number == 1 but first offset ==0
	}
	  
	  
	  public long countTotalRecords() {
	   return productRepository.countTotalRecords();
	  }

	   public void deleteUser(Long id) {
	        userRepository.delete(id);
	    }
	   public void deleteProduct(Long id) {
		   productRepository.delete(id);
	    }
	
	   public void deleteProductByName(String name) {
		   productRepository.delete(name);
	    }
	   public UserEntity findUserById(Long id) {
			return userRepository.findOne(id);
		}
	   @Transactional
	public void userUpdate(UserEntity user) {
		userRepository.update(user.getUserName(),user.getEmail(),user.getPassword(),user.getActive(), user.getRoleUserEntity());
	}
	   @Transactional
	public void productUpdate(ProductEntity product,long id) {
		 productRepository.update(id,product.getName(),product.getUrl1(),product.getAmount(), product.getPrice(),product.getPromotion());
	}
	
	

		public RoleUserEntity findOneById(Long id) {
			return roleUserRepository.findOne(id);
		}
		public ProductEntity findOneProduct(long id) {
			return productRepository.findOne(id);
		}
		
	public UserEntity findUserByUserName(String username) {
		return userRepository.findOneByUserNameAndActive(username, SystemConstant.ACTIVE_STATUS);
	}
	   @Transactional
	public void saveUser(UserEntity user , Long roleid) {
		userRepository.save(user);
	}

	   public void saveProduct(ProductEntity product) {
			productRepository.save(product);
		}

	   
	public List<UserEntity> searchUser(String keyword) {
	    return userRepository.search(keyword);
	}
	public List<ProductEntity> searchProduct(String keyword) {
	    return productRepository.search(keyword);
	}
}
	
