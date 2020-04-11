/*
	@author:Quang Truong
	@date: Feb 4, 2020
*/

package com.jwatgroupb.service;

import org.springframework.mail.SimpleMailMessage;

import com.jwatgroupb.entity.BillEntity;

public interface EmailService {
	public void sendMailConfirmPayment(BillEntity bill);
	public SimpleMailMessage mailConfirmPayment(BillEntity bill);
}
