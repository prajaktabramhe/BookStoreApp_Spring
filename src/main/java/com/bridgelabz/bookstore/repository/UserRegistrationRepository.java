package com.bridgelabz.bookstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.entity.UserEntity;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserEntity,Long> {

	Optional<UserEntity> findByEmailId(String emailId);

	Optional<UserEntity> findByemailIdAndPassword(String emailId, String password);


}
