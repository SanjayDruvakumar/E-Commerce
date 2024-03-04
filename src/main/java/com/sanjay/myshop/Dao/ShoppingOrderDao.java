package com.sanjay.myshop.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sanjay.myshop.Dto.ShoppingOrder;
import com.sanjay.myshop.Repository.ShoppingOrderRepository;

@Repository
public class ShoppingOrderDao {
	@Autowired
	ShoppingOrderRepository orderRepository;

	public void saveOrder(ShoppingOrder order) {
		orderRepository.save(order);
	}

	public ShoppingOrder findOrderById(int id) {
		return orderRepository.findById(id).orElseThrow();
	}
}
