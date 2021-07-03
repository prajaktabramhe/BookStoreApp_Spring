package com.bridgelabz.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.dto.OrderList;
import com.bridgelabz.bookstore.entity.OrderEntity;
import com.bridgelabz.bookstore.service.IOrderService;
import com.bridgelabz.bookstore.util.Response;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/order")
@Slf4j
public class OrderController {

	@Autowired
	IOrderService orderService;
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/placeOrder")
	public ResponseEntity<Response> placeOrder(@RequestHeader String token,@RequestBody OrderDTO orderDTO){
		log.debug("Place order");
		Response response = orderService.placeOrder(token,orderDTO);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/cancelOrder")
	public ResponseEntity<Response> cancelOrder(@RequestHeader String token,@RequestParam("orderId") long orderId){
		log.debug("Place order");
		Response response = orderService.cancelOrder(token,orderId);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getAllOrder")
	public ResponseEntity<List<?>> getAllOrders(@RequestHeader String token){
		log.debug("Get all orders");
		List<OrderEntity> orders = orderService.getAllOrders(token);
		return new ResponseEntity<List<?>>(orders,HttpStatus.OK);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getAllUserOrders")
	public ResponseEntity<List<?>> getUserOrders(@RequestHeader String token)
	{
		log.debug("Get all orders for user");
		List<OrderList> orders = orderService.getUserOrders(token);
		return new ResponseEntity<List<?>>(orders,HttpStatus.OK);
	}
}
