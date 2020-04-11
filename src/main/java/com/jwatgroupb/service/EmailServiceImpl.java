/*
	@author:Quang Truong
	@date: Feb 4, 2020
*/

package com.jwatgroupb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.jwatgroupb.entity.BillEntity;
import com.jwatgroupb.entity.UserEntity;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	private JavaMailSender emailSender;
	
	

	@Override
	public SimpleMailMessage mailConfirmPayment(BillEntity bill) {
		String subject="JWAT-GROUP B Shop - Confirm Payment";
		StringBuilder text= new StringBuilder("");
		text.append("Hi ,\n");
		text.append("- We have received your order.\n");
		text.append("- Products will be sent to you within one week.\n");
		text.append("- Please check the invoice at the URL: http://localhost:8080/bill/\n" );
		text.append("- For further information. Please contact: 0123 456 789" );
		text.append("\n\nThank you for using our service,\n" );
		text.append("JWAT-GROUP B" );
		String textStr = text.toString();
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("17520599@gm.uit.edu.vn");
		message.setSubject(subject);
		message.setText(textStr);
		return message;
	}



	@Override
	public void sendMailConfirmPayment(BillEntity bill) {
		emailSender.send(mailConfirmPayment(bill));
	}

}
