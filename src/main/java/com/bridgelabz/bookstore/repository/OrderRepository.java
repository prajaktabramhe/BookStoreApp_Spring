package com.bridgelabz.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.bookstore.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>
{

	Optional<OrderEntity> findByUserId(long id);

	@Query(value = "SELECT * FROM orders WHERE user_id=:id",nativeQuery = true)
	List<OrderEntity> getAllOrdersForUser(long id);

		

}
