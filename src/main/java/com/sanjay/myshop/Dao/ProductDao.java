package com.sanjay.myshop.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sanjay.myshop.Dto.Product;
import com.sanjay.myshop.Repository.ProductRepository;

import jakarta.validation.Valid;
@Component
public class ProductDao {

	@Autowired
	ProductRepository productRepository;
	
	public boolean checkName(String name) {
		return productRepository.existsByName(name);
	}

	public void save(@Valid Product product) {
		productRepository.save(product);
		
	}
	
	public List<Product> fetchAll() {
		return productRepository.findAll();
	}

	public boolean existsById(int id) {
		return productRepository.existsById(id);
	}

	public Product findById(int id) {
		return productRepository.findById(id).orElseThrow();
	}

	public void delete(Product product) {
		productRepository.delete(product);
	}

	public Product findByName(String name) {
		return productRepository.findByName(name);
	}
}
