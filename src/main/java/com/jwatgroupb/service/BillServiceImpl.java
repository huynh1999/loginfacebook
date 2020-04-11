/*
	@author:Quang Truong
	@date: Feb 6, 2020
*/

package com.jwatgroupb.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwatgroupb.entity.BillDetailEntity;
import com.jwatgroupb.entity.BillEntity;
import com.jwatgroupb.repository.BillRepository;

@Service
public class BillServiceImpl implements BillService {

	@Autowired
	private BillRepository billRepository;
	
	
	@Override
	public List<Map<String, Object>> report(String billCode) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		BillEntity bill = billRepository.findOneByBillCode(billCode);
		int i=0;
		for(BillDetailEntity product:bill.getListBillDetails()) {
			i++;
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", i);
			String name =product.getProductEntity().getName();
			item.put("nameProduct", name);
			item.put("quantity", product.getQuantity());
			item.put("promotion", product.getPromotion());
			item.put("price", product.getPrice());
			float total= (product.getPrice()-product.getPrice()*product.getPromotion())*product.getQuantity();
			item.put("total",total );
			result.add(item);
		}
		return result;
	}
	
	@Override
	public Map<String,Object> getParameter(String billCode){
		BillEntity bill = billRepository.findOneByBillCode(billCode);
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("createdDate", bill.getCreatedDate());
		parameter.put("billCode", bill.getId());
		parameter.put("receiver", bill.getReceiver());
		parameter.put("email", bill.getEmail());
		parameter.put("phoneNumber", bill.getPhonenumber());
		parameter.put("address", bill.getAddress());
		parameter.put("note", bill.getNote());
		parameter.put("paymentMethod", bill.getPaymentMethod());
		parameter.put("totalPrice", bill.getTotalMoney());
		InputStream inputStream=this.getClass().getResourceAsStream("/report/logo.png");
		parameter.put("logo", inputStream);
		return parameter;
		
	}

	@Override
	public BillEntity findOneByBillCode(String billCode) {
		
		return billRepository.findOneByBillCode(billCode);
	}

}
