package com.sanjay.myshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanjay.myshop.Dto.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	boolean existsByEmail(String email);

	boolean existsByMobile(long mobile);

	Customer findByEmail(String email);
}
