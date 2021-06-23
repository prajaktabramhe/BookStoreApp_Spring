package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.util.Response;

public interface ICartService {

	// to add book to cart
	Response addToCart(String token, CartDTO cartDto);
	
	// to remove product from cart
	Response removeCartItem(String token, long cartId);

	// to update item's quantity in cart
	Response updateCartItem(String token, CartDTO cartDto);

}
