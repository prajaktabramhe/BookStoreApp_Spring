package com.bridgelabz.bookstore.dto;

import java.time.LocalDate;
import java.util.Date;

import com.bridgelabz.bookstore.entity.BookEntity;
import com.bridgelabz.bookstore.entity.UserEntity;

import lombok.Data;
@Data
public class OrderList {

	UserEntity user;
	
	long id;
	
    private int quantity;
	
	private double price;
	
	private long bookId;
	
	private String address;
	
	private LocalDate orderDate;
	
	public OrderList() {
		
	}
}
