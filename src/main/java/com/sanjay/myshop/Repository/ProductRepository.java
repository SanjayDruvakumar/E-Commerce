package com.sanjay.myshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanjay.myshop.Dto.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	boolean existsByName(String name);
	Object deleteById(int id);

	Product findByName(String name);

}
