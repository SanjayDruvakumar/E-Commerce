package com.sanjay.myshop.Dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Component
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Size(min = 5, max = 50, message = "* Should be between 5 to 50 charecters")
	private String name;
	@NotNull(message = "* It is Compulsory Field")
	@DecimalMin(value = "0.1", inclusive = true, message = "* Price should be more than 1")
	private double price;
	@NotNull(message = "* It is Compulsory Field")
	@Size(min = 5, max = 50, message = "* Should be between 5 to 50 charecters")
	private String category;
	@DecimalMin(value = "1",inclusive = true,message = "* Stock should be more than 1")
	private int stock;
	@Size(min=10, max=100,message="* Should be between 10 to 100 charecters")
	private String description;
//	@Lob
//	@Column(columnDefinition = "MEDIUMBLOB")
//	private byte[] picture;
	private String imagePath;

}
