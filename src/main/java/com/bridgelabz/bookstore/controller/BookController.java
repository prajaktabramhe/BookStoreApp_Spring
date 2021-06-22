package com.bridgelabz.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.entity.BookEntity;
import com.bridgelabz.bookstore.service.IBookService;
import com.bridgelabz.bookstore.util.Response;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {

	@Autowired
	IBookService bookService;
	
	
	/**
	 * Add new book to store
	 * @param bookDto
	 * @param token
	 * @return
	 */
	@PostMapping("/addBook/{token}")
	public ResponseEntity<Response> addBook(@RequestBody BookDTO bookDto,@PathVariable String token){
		log.debug("Add new book");
		Response response = bookService.addNewBook(token,bookDto);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	/**
	 * To get all books
	 * @param token
	 * @return ResponseEntity<List<?>>
	 */
	@GetMapping("/getBooks/{token}")
	public ResponseEntity<List<?>> getAllBooks(@PathVariable String token)
	{
		log.debug("Get all books");
		List<BookEntity> books = bookService.getAllBooks(token);
		return new ResponseEntity<List<?>>(books, HttpStatus.OK);	
	}
}
