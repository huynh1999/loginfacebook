/*
	@author:Quang Truong
	@date: Jan 21, 2020
*/

package com.jwatgroupb.service;

import com.jwatgroupb.constant.SystemConstant;
import com.jwatgroupb.entity.CartEntity;
import com.jwatgroupb.entity.CartItemEntity;
import com.jwatgroupb.entity.ProductEntity;
import com.jwatgroupb.entity.UserEntity;
import com.jwatgroupb.repository.CartItemRepository;
import com.jwatgroupb.repository.CartRepository;
import com.jwatgroupb.repository.ProductRepository;
import com.jwatgroupb.repository.UserRepository;
import com.jwatgroupb.util.RandomStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private ProductRepository productRepository;

	public CartEntity findOneByCartCode(String cartCode) {
		return cartRepository.findOneByCartCode(cartCode);
	}
	public CartItemEntity findOneCartItem(Long id) {
		return cartItemRepository.findOne(id);
	}

	@Transactional
	// Tao moi cart va them san pham cho khach chua dang nhap
	public void saveCartWithCartCodeAndProductId(String cartCode, Long productId) {
		CartEntity cartEntity = new CartEntity();
		cartEntity.setCartCode(cartCode);
		cartRepository.save(cartEntity);
		CartItemEntity cartItemEntity = new CartItemEntity();
		cartItemEntity.setCartEntity(cartEntity);
		cartItemEntity.setProductEntity(productRepository.findOne(productId));
		cartItemEntity.setQuantity(1);
		cartItemRepository.save(cartItemEntity);
	}

	@Transactional
	// Them san pham cho khach da co gio hang
	public void updateCartItem(CartEntity cartEntity, Long productId) {
		ProductEntity productEntity = productRepository.findOne(productId);
		CartItemEntity cartItemEntity = cartItemRepository.findOneByCartEntityAndProductEntity(cartEntity,
				productEntity);
		// Kiem tra co ton tai product trong cart
		if (cartItemEntity == null) {
			cartItemEntity = new CartItemEntity();
			cartItemEntity.setCartEntity(cartEntity);
			cartItemEntity.setProductEntity(productRepository.findOne(productId));
			cartItemEntity.setQuantity(1);
			cartItemRepository.save(cartItemEntity);
		} else {
			cartItemEntity.setQuantity(cartItemEntity.getQuantity() + 1);
			cartItemRepository.save(cartItemEntity);
		}
	}

	@Transactional
	// Them san pham cho khach hang da dang nhap tai khoan
	public void updateOrCreateCartWithUsername(String username, Long productId) {

		ProductEntity productEntity = productRepository.findOne(productId);
		UserEntity userEntity = userRepository.findOneByUserNameAndActive(username, SystemConstant.ACTIVE_STATUS);
		CartEntity cartEntity = cartRepository.findOneByUserEntity(userEntity);
		if (cartEntity != null) {// Ton tai cart
			CartItemEntity cartItemEntity = cartItemRepository.findOneByCartEntityAndProductEntity(cartEntity,
					productEntity);
			// Kiem tra co ton tai product trong cart
			if (cartItemEntity == null) {
				cartItemEntity = new CartItemEntity();
				cartItemEntity.setCartEntity(cartEntity);
				cartItemEntity.setProductEntity(productRepository.findOne(productId));
				cartItemEntity.setQuantity(1);
				cartItemRepository.save(cartItemEntity);
			} else {
				cartItemEntity.setQuantity(cartItemEntity.getQuantity() + 1);
				cartItemRepository.save(cartItemEntity);
			}
		}
//		else {// Khong ton tai cart-> Tao moi
//			cartEntity = new CartEntity();
//			String cartCode = RandomStringUtil.Random();
//			cartEntity.setCartCode(cartCode);
//			cartEntity.setUserEntity(userEntity);
//			cartRepository.save(cartEntity);
//			CartItemEntity cartItemEntity = new CartItemEntity();
//			cartItemEntity.setCartEntity(cartEntity);
//			cartItemEntity.setProductEntity(productRepository.findOne(productId));
//			cartItemEntity.setQuantity(1);
//			cartItemRepository.save(cartItemEntity);
//		}

	}

	@Transactional
	public void addCartAfterLogin(String username, String cartCode) {

		UserEntity userEntity = userRepository.findOneByUserNameAndActive(username, SystemConstant.ACTIVE_STATUS);
		CartEntity newCart = cartRepository.findOneByCartCode(cartCode);
		CartEntity cartOfUser = cartRepository.findOneByUserEntity(userEntity);
		List<CartItemEntity> listNewCartItem = newCart.getListCartItem();
		List<CartItemEntity> listCartItemOfUser = cartOfUser.getListCartItem();

		if (listNewCartItem.isEmpty() == false) {// Neu gio hang trc khi dang nhap khong rong
			if (listCartItemOfUser.isEmpty()) {// Neu Gio Hang cua Khach rong
				for (CartItemEntity newItem : listNewCartItem) {
					newItem.setCartEntity(cartOfUser);
					cartItemRepository.save(newItem);
				}
			} else {// Neu gio hang da co san pham
				List<Long> listProductOfUser = new ArrayList<Long>();
				for (CartItemEntity item : listCartItemOfUser) {
					listProductOfUser.add(item.getProductEntity().getId());
				}
				for (CartItemEntity newItem : listNewCartItem) {
					ProductEntity productEntity = newItem.getProductEntity();
					if (listProductOfUser.contains(productEntity.getId())) {// Neu product da co trong gio hang
						CartItemEntity cartItemEntity = cartItemRepository
								.findOneByCartEntityAndProductEntity(cartOfUser, productEntity);
						cartItemEntity.setQuantity(newItem.getQuantity() + cartItemEntity.getQuantity());
						cartItemRepository.save(cartItemEntity);
						cartItemRepository.delete(newItem);
					} else {// Neu san pham chua co trong gio hang
						newItem.setCartEntity(cartOfUser);
						cartItemRepository.save(newItem);
					}
				}
			}
		}
	}

	//Tim Cart theo username
	public CartEntity findOneByUserName(String username) {
		UserEntity userEntity = userRepository.findOneByUserNameAndActive(username, SystemConstant.ACTIVE_STATUS);
		return cartRepository.findOneByUserEntity(userEntity);
	}

	@Transactional
	public void clearItemInCart(CartEntity cart) {
		List<CartItemEntity> list= cart.getListCartItem();
		for(CartItemEntity cartItem:list) {
			cartItemRepository.delete(cartItem);
		}
	}
	
	@Transactional
	public void upQuantityOfCartItem(Long id) {
		CartItemEntity cartItem=cartItemRepository.findOne(id);
		cartItem.setQuantity(cartItem.getQuantity()+1);
		cartItemRepository.save(cartItem);
	}

	@Transactional
	public void downQuantityOfCartItem(Long id) {
		CartItemEntity cartItem=cartItemRepository.findOne(id);
		cartItem.setQuantity(cartItem.getQuantity()-1);
		cartItemRepository.save(cartItem);
	}
	
	@Transactional
	public void removeCartItem(Long id) {
		cartItemRepository.delete(id);
		
	}
	@Transactional
	public void addCartAfterReg(String userName) {
		UserEntity user=userRepository.findOneByUserNameAndActive(userName, SystemConstant.ACTIVE_STATUS);
		CartEntity cart= new CartEntity();
		cart.setCartCode(RandomStringUtil.Random());
		cart.setUserEntity(user);
		cartRepository.save(cart);
	}
	
	
	
	
}
