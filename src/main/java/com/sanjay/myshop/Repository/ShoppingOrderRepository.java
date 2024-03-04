package com.sanjay.myshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanjay.myshop.Dto.ShoppingOrder;


public interface ShoppingOrderRepository extends JpaRepository<ShoppingOrder, Integer>{

}
