package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.util.Response;

public interface IBookService {

	// To add new book to store
	Response addNewBook(String token, BookDTO bookDto);

}
