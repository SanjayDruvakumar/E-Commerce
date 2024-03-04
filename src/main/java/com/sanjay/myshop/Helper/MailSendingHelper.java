package com.sanjay.myshop.Helper;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.sanjay.myshop.Dao.CustomerDao;
import com.sanjay.myshop.Dto.Customer;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailSendingHelper {
	
	@Autowired
	JavaMailSender mailSender;

	public void sendOtp(Customer customer) {
		MimeMessage message=mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message);
		try {
		helper.setFrom("sanjaydhruvakumar@gmail.com","MyShop");
		helper.setTo(customer.getEmail());
		helper.setSubject("Verification OTP");
		String gen="";
		if(customer.getGender().equalsIgnoreCase("male")) {
			gen="Mr. ";
		}else {
			gen="Ms. ";
		}
		String body="<html><body><h1>Hello"+gen+customer.getName()+"</h1>"
				+ "<h2>You OTP : "+customer.getOtp()+"</h2>"
						+ "<h3>Thanks and Regeards</h3></body></html>";
		helper.setText(body,true);
		}catch(UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}
		
	}

	public void resendOtp(Customer customer) {
	
		
	}


	
}
