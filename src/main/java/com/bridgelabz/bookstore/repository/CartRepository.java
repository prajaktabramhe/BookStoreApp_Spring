package com.bridgelabz.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.bookstore.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long>{

	Optional<CartEntity> findByBookId(long bookId);
	
	Optional<CartEntity> findByUserId(long id);

//	@Query(value = "SELECT * FROM cart WHERE user_id=:id",nativeQuery = true)
//	List<CartEntity> getAllOrdersForUser(long id);

	List<CartEntity> findAllByuserId(long id);

}
