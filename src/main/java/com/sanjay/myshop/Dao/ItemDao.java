package com.sanjay.myshop.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sanjay.myshop.Dto.Item;
import com.sanjay.myshop.Repository.ItemRepository;

@Repository
public class ItemDao {
	
	@Autowired
	ItemRepository itemRepository;
	
	public Item findById(int id) {
		return itemRepository.findById(id).orElseThrow();
	}

	public void delete(Item item) {
		itemRepository.delete(item);
	}

	public void save(Item item) {
		itemRepository.save(item);
	}
	
}
