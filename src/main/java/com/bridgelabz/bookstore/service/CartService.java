package com.bridgelabz.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.dto.CartListDTO;
import com.bridgelabz.bookstore.entity.BookEntity;
import com.bridgelabz.bookstore.entity.CartEntity;
import com.bridgelabz.bookstore.entity.UserEntity;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.repository.UserRegistrationRepository;
import com.bridgelabz.bookstore.util.Response;
import com.bridgelabz.bookstore.util.TokenUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartService implements ICartService
{
	@Autowired 
	CartRepository cartRepository;

	@Autowired
	UserRegistrationRepository userRegistrationRepository;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	TokenUtil tokenUtil;

	/**
	 * To add product to cart
	 */
	@Override
	public Response addToCart(String token, CartDTO cartDto) 
	{
		long id = tokenUtil.decodeToken(token);
		Optional<UserEntity> isUserPresent = userRegistrationRepository.findById(id);
		if(isUserPresent.isPresent()) 
		{
			Optional<BookEntity> isBookPresent = bookRepository.findById(cartDto.getBookId());
			if(isBookPresent.isPresent()) 
			{
				CartEntity cartEntity = modelMapper.map(cartDto, CartEntity.class);
				cartEntity.setBookId(cartDto.getBookId());
				cartEntity.setUserId(isUserPresent.get().getId());
				cartEntity.setQuantity(cartDto.getQuantity());
				cartRepository.save(cartEntity);
				return new Response(200, "Book with id:" + cartDto.getBookId() + " Added to cart.", null);

			} else 
			{
				log.error("Book not found");
				throw new BookStoreException(404, "Book not found");
			}
		}
		else 
		{
			log.error("User not found");
			throw new BookStoreException(404, "User not found");
		}
	}

	/**
	 * To remove product from cart
	 */
	@Override
	public Response removeCartItem(String token, long cartId) 
	{
		long id = tokenUtil.decodeToken(token);
		Optional<UserEntity> isUserPresent = userRegistrationRepository.findById(id);
		if(isUserPresent.isPresent())
		{
			Optional<CartEntity> isCartItemPresent = cartRepository.findById(cartId);
			if(isCartItemPresent.isPresent())
			{
				cartRepository.delete(isCartItemPresent.get());
				log.debug("Removed from cart");
				return new Response(200, "Removed from cart", null);
			}
			else 
			{
				log.error("Item Not In the cart");
				throw new  BookStoreException(404, "Item not in cart");
			}

		}
		else 
		{
			log.error("User not found");
			throw new BookStoreException(404, "User not found");
		}
	}

	/**
	 * To update quantity of product in cart
	 */
	@Override
	public Response updateCartItem(String token, CartDTO cartDto) 
	{
		long id = tokenUtil.decodeToken(token);
		Optional<UserEntity> isUserPresent = userRegistrationRepository.findById(id);
		if(isUserPresent.isPresent()) 
		{
			Optional<BookEntity> isBookPresent = bookRepository.findById(cartDto.getBookId());
			if(isBookPresent.isPresent()) {
				Optional<CartEntity> isCartItemPresent = cartRepository.findByBookId(cartDto.getBookId());
				if(isCartItemPresent.isPresent()) 
				{
					isCartItemPresent.get().setQuantity(cartDto.getQuantity());
					cartRepository.save(isCartItemPresent.get());
					log.debug("Update item in cart.");
					return new Response(200, "Cart updated.", null);
				}
				else 
				{
					log.error("Product not found in cart.");
					throw new BookStoreException(404, "Product not found in cart.");
				}

			}
			else 
			{
				log.error("Book not found");
				throw new BookStoreException(404, "Book not found");
			}
		}
		else 
		{
			log.error("User not found");
			throw new BookStoreException(404, "User not found");
		}
	}

	@Override
	public List<CartListDTO> getUserCart(String token) 
	{
		List<CartListDTO> cartList = new ArrayList<>();
		long id = tokenUtil.decodeToken(token);
		Optional<UserEntity> isUserPresent = userRegistrationRepository.findById(id);
		if(isUserPresent.isPresent()) 
		{
//			Optional<CartEntity> orderForUserExists = cartRepository.findByUserId(id);
//			if(orderForUserExists.isPresent()) 
//			{
				List<CartEntity> usersOrders = cartRepository.findAllByuserId(id);
				System.out.println("checked data" + usersOrders);
				usersOrders.forEach(cart -> {
					CartListDTO cartData = new CartListDTO();
					cartData.setId(cart.getId());
					cartData.setQuantity(cart.getQuantity());
					cartData.setUserId(cart.getUserId());
					cartData.setBook(bookRepository.findById(cart.getBookId()).get());
					cartList.add(cartData);
				});
				return cartList;
//			}
//			else 
//			{
//				log.error("No orders exists for user");
//				throw new BookStoreException(404, "No orders exists for user");
//			}
		}
		else 
		{
			log.error("User not found");
			throw new BookStoreException(404, "User not found.");
		}
	}

}
