package com.bridgelabz.bookstore.service;

import java.util.List;

import javax.validation.Valid;

import com.bridgelabz.bookstore.dto.ForgotPasswordDTO;
import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.entity.UserEntity;
import com.bridgelabz.bookstore.util.Response;

public interface IUserService {
	

	Response registerUserData(@Valid UserDTO dto);

	Response loginData( @Valid LoginDTO login);
	
	// To delete user
	Response deleteUser(String token);

	List<UserEntity> getAllUsers(String token);

	Response updateUser(String token, UserDTO dto);


	Response forgotPassword(@Valid ForgotPasswordDTO forgotPasswordDTO);

}
