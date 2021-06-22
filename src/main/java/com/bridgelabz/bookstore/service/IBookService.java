package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.entity.BookEntity;
import com.bridgelabz.bookstore.util.Response;

public interface IBookService {

	// To add new book to store
	Response addNewBook(String token, BookDTO bookDto);

	// Get all books
	List<BookEntity> getAllBooks(String token);

}
