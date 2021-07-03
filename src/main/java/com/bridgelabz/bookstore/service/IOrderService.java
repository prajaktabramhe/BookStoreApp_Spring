package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.dto.OrderList;
import com.bridgelabz.bookstore.entity.OrderEntity;
import com.bridgelabz.bookstore.util.Response;

public interface IOrderService {

	// To place an order
	Response placeOrder(String token, OrderDTO orderDTO);

	// To cancel placed order
	Response cancelOrder(String token, long orderId);

	// To get all orders placed
	List<OrderEntity> getAllOrders(String token);

	// To get all order placed by user
	List<OrderList> getUserOrders(String token);

}
