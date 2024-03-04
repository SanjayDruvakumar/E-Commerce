package com.sanjay.myshop.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sanjay.myshop.Dto.Customer;
import com.sanjay.myshop.Dto.Product;
import com.sanjay.myshop.Repository.CustomerRepository;

@Repository
public class CustomerDao {

	@Autowired
	CustomerRepository customerRepository;
	
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	public boolean checkMobileDuplicate(long mobile) {
		return customerRepository.existsByMobile(mobile);
	}

	public boolean checkEmailDuplicate(String email) {
		return customerRepository.existsByEmail(email);
	}

	public Customer findById(int id) {
		return customerRepository.findById(id).orElseThrow();
	}

	public Customer findByEmail(String email) {
		return customerRepository.findByEmail(email);
	}

}
