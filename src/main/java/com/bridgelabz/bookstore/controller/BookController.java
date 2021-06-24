package com.bridgelabz.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	@PostMapping("/addBook")
	public ResponseEntity<Response> addBook(@RequestBody BookDTO bookDto,@RequestHeader String token){
		log.debug("Add new book");
		Response response = bookService.addNewBook(token,bookDto);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	/**
	 * To get all books
	 * @param token
	 * @return ResponseEntity<List<?>>
	 */
	@GetMapping("/getBooks")
	public ResponseEntity<List<?>> getAllBooks(@RequestHeader String token)
	{
		log.debug("Get all books");
		List<BookEntity> books = bookService.getAllBooks(token);
		return new ResponseEntity<List<?>>(books, HttpStatus.OK);	
	}
	
	/**
	 * Update book details
	 * @param bookDto : Book details to update
	 * @param token : JWT to authorize user
	 * @return : ResponseEntity<Response>
	 */
	@PutMapping("/updateBook")
	public ResponseEntity<Response> updateBook(@RequestHeader BookDTO bookDto,@PathVariable String token)
	{
		log.debug("Update book");
		Response response = bookService.updateBook(token,bookDto);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	/**
	 * Update book price
	 * @param token : JWT to authorize user
	 * @param bookName : Book name to update
	 * @param price : Book price to update
	 * @return : ResponseEntity<Response>
	 */
	@PutMapping("/updateBookPrice")
	public ResponseEntity<Response> updateBookPrice(@RequestHeader String token,@RequestParam("bookName") String bookName,
			@RequestParam("quantity") Double price){
		log.debug("Update book price");
		Response response = bookService.updateBookPrice(token,bookName,price);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	/**
	 * Update book quantity
	 * @param token : JWT to authorize user
	 * @param bookName : Book name to update
	 * @param quantity : Book quantity to update
	 * @return ResponseEntity<Response>
	 */
	@PutMapping("/updateBookQuantity")
	public ResponseEntity<Response> updateBookQuantity(@RequestHeader String token,@RequestParam("bookName") String bookName,
			@RequestParam("quantity") int newQuantity){
		log.debug("Update book quantity");
		Response response = bookService.updateBookQuantity(token,bookName,newQuantity);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	/**
	 * Delete book
	 * @param token : JWT to authorize user
	 * @param bookName
	 * @return ResponseEntity<Response>
	 */
	@DeleteMapping("/deleteBook")
	public ResponseEntity<Response> deleteBook(@RequestHeader String token,@RequestParam("name") String bookName){
		log.debug("Delete book");
		Response response = bookService.deleteBook(token,bookName);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
