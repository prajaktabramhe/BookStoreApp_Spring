package com.bridgelabz.bookstore.dto;

import lombok.Data;

public @Data class BookDTO {

private String bookName;
	
	private String author;
	
	private String description;
	
	private String logo;
	
	private double price;
	
	private int quantity;
}
