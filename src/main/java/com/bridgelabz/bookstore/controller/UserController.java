package com.bridgelabz.bookstore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.ForgotPasswordDTO;
import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.entity.UserEntity;
import com.bridgelabz.bookstore.service.IUserService;
import com.bridgelabz.bookstore.util.Response;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

	@Autowired
	IUserService userService;
	
		
	@PostMapping("/register")
	public ResponseEntity<Response> registerUserData(@Valid @RequestBody UserDTO dto){
		log.debug("Register User");
		System.out.println("request "+ dto);
		Response userEntity = userService.registerUserData(dto);
		return new ResponseEntity<Response>(userEntity,HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<Response> login(@Valid @RequestBody LoginDTO login){
		Response response = userService.loginData(login);
		System.out.println("login"+ login);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/getUser/{token}")
	public ResponseEntity<List<?>> getAllUsers(@PathVariable String token){
		log.debug("Get all users");
		List<UserEntity> userList = userService.getAllUsers(token);
		return new ResponseEntity<>(userList,HttpStatus.OK);
	}
	
	@PutMapping("/updateUser/{token}")
	public ResponseEntity<Response> updateUser(@PathVariable String token,@RequestBody UserDTO dto){
		log.debug("Update user");
		Response userEntity = userService.updateUser(token,dto);
		return new ResponseEntity<Response>(userEntity,HttpStatus.OK);
	}
	
	@PutMapping("/forgotpassword")
	public ResponseEntity<Response> forgotPassword(@Valid @RequestBody ForgotPasswordDTO forgotPasswordDTO) 
	{
		return new ResponseEntity<Response>(userService.forgotPassword(forgotPasswordDTO) , HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUser/{token}")
	public ResponseEntity<Response> deleteUser(@PathVariable String token){
		log.debug("Delete user");
		Response userEntity = userService.deleteUser(token);
		return new ResponseEntity<Response>(userEntity,HttpStatus.OK);
	}
	
}
