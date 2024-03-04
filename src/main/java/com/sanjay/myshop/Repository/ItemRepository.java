package com.sanjay.myshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanjay.myshop.Dto.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

}
