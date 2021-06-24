package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.entity.CartEntity;
import com.bridgelabz.bookstore.entity.OrderEntity;
import com.bridgelabz.bookstore.util.Response;

public interface ICartService {

	// to add book to cart
	Response addToCart(String token, CartDTO cartDto);
	
	// to remove product from cart
	Response removeCartItem(String token, long cartId);

	// to update item's quantity in cart
	Response updateCartItem(String token, CartDTO cartDto);

	// To get all cart by user
	List<CartEntity> getUserCart(String token);

}
