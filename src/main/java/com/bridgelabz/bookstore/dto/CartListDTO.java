package com.bridgelabz.bookstore.dto;

import com.bridgelabz.bookstore.entity.BookEntity;

import lombok.Data;

@Data
public class CartListDTO {
	
	BookEntity book;
	
	int quantity;
	
	long id;
	
	long userId;
	
	public CartListDTO() {
		
	}

}
