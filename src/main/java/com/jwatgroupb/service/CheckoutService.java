/*
	@author:Quang Truong
	@date: Feb 2, 2020
*/

package com.jwatgroupb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwatgroupb.constant.SystemConstant;
import com.jwatgroupb.entity.BillDetailEntity;
import com.jwatgroupb.entity.BillEntity;
import com.jwatgroupb.entity.CartEntity;
import com.jwatgroupb.entity.CartItemEntity;
import com.jwatgroupb.entity.PayerEntity;
import com.jwatgroupb.repository.BillDetailRepository;
import com.jwatgroupb.repository.BillRepository;
import com.jwatgroupb.repository.PayerRepository;
import com.jwatgroupb.util.RandomStringUtil;

@Service
public class CheckoutService {

	@Autowired
	private BillRepository billRepository;

	@Autowired
	private BillDetailRepository billDetailRepository;
	
	@Autowired
	private PayerRepository payerRepository;

	
	@Transactional
	public String addBill(BillEntity bill,PayerEntity payer) {
		String billCode= RandomStringUtil.Random();
		bill.setBillCode(billCode);
		bill.setStatus(SystemConstant.ACTIVE_STATUS);
		billRepository.save(bill);
		if(payer!=null) {
			bill=billRepository.findOneByBillCode(billCode);
			payer.setBillEntity(bill);
			payerRepository.save(payer);
		}
		return billCode;
	}

	@Transactional
	public void addBillDetail(CartEntity cart,BillEntity bill) {
		List<CartItemEntity> list = cart.getListCartItem();
		for(CartItemEntity cartItem:list) {
			BillDetailEntity billDetail= new BillDetailEntity();
			billDetail.setBillEntity(bill);
			billDetail.setPrice(cartItem.getProductEntity().getPrice());
			billDetail.setProductEntity(cartItem.getProductEntity());
			billDetail.setPromotion(cartItem.getProductEntity().getPromotion());
			billDetail.setQuantity(cartItem.getQuantity());
			billDetailRepository.save(billDetail);
		}
	}
}
