package com.bridgelabz.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.service.ICartService;
import com.bridgelabz.bookstore.util.Response;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("cart")
@Slf4j
public class CartController {

	@Autowired
	ICartService cartService;
	
	/**
	 * Add Cart 
	 * @param token
	 * @param cartDto
	 * @return :ResponseEntity<Response>
	 */
	@PostMapping("/addToCart")
	public ResponseEntity<Response> addBookToCart(@RequestHeader String token,@RequestBody CartDTO cartDto) 
	{
		log.debug("Add prod to cart");
		Response response = cartService.addToCart(token,cartDto);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	/**
	 * Remove Cart Item
	 * @param token
	 * @param cartId
	 * @return
	 */
	@DeleteMapping("/removeCartItem")
	public ResponseEntity<Response> removeCartItem(@RequestHeader String token,@RequestParam("cartId") long cartId) 
	{
		log.debug("Remove prod from cart");
		Response response = cartService.removeCartItem(token,cartId);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	/**
	 * Update Cart Item
	 * @param token
	 * @param cartDto
	 * @return
	 */
	@PutMapping("/updateCartItem")
	public ResponseEntity<Response> updateCartItem(@RequestHeader String token,@RequestBody CartDTO cartDto) 
	{
		log.debug("Update cart");
		Response response = cartService.updateCartItem(token,cartDto);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}


