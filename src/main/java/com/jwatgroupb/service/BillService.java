/*
	@author:Quang Truong
	@date: Feb 6, 2020
*/

package com.jwatgroupb.service;

import java.util.List;
import java.util.Map;

import com.jwatgroupb.entity.BillEntity;

public interface BillService {
	
	public List<Map<String,Object>> report(String billCode);
	
	public Map<String,Object> getParameter(String billCode);
	
	public BillEntity findOneByBillCode(String billCode);
}
