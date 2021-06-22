package com.bridgelabz.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.entity.BookEntity;
import com.bridgelabz.bookstore.entity.UserEntity;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.UserRegistrationRepository;
import com.bridgelabz.bookstore.util.Response;
import com.bridgelabz.bookstore.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookService implements IBookService
{

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	UserRegistrationRepository userRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	TokenUtil tokenUtil;
	
	
	/**
	 * Add new book
	 * @param token : JWT to verify user, bookDto : Book details
	 * @return Response
	 */
	@Override
	public Response addNewBook(String token, BookDTO bookDto) 
	{
		// check if user is present
				long id = tokenUtil.decodeToken(token);
				Optional<UserEntity> isUserPresent = userRepository.findById(id);
				if(isUserPresent.isPresent()) {
					// check if book is already present
					Optional<BookEntity> isBookPresent = bookRepository.findByBookName(bookDto.getBookName());
					if(isBookPresent.isPresent()) {
						log.error("Book exists already");
						throw new BookStoreException(400,"Book already exists.");
					}
					else {
						BookEntity bookEntity = modelMapper.map(bookDto, BookEntity.class);
						bookRepository.save(bookEntity);
						log.debug("New book added.");
						return new Response(200, "New Book Added.", null);
					}
				}
				else {
					log.error("User not found.");
					throw new BookStoreException(404,"User Not found");
				}
	}

	/**
	 * Get all books
	 */
	@Override
	public List<BookEntity> getAllBooks(String token) 
	{
		// check if user is present
				long id = tokenUtil.decodeToken(token);
				Optional<UserEntity> isUserPresent = userRepository.findById(id);
				if(isUserPresent.isPresent()) {
					List<BookEntity> books = bookRepository.findAll();
					return books;
				}
				else {
					log.error("User not found.");
					throw new BookStoreException(404,"User Not found");
				}
	}

}
