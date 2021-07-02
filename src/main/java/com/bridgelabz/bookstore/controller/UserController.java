package com.bridgelabz.bookstore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/register")
	public ResponseEntity<Response> registerUserData(@Valid @RequestBody UserDTO dto){
		log.debug("Register User");
		System.out.println("request "+ dto);
		Response userEntity = userService.registerUserData(dto);
		return new ResponseEntity<Response>(userEntity,HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/login")
	public ResponseEntity<Response> login(@Valid @RequestBody LoginDTO login){
		Response response = userService.loginData(login);
		System.out.println("login"+ login);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getUser")
	public ResponseEntity<List<?>> getAllUsers(@RequestHeader String token)
	{
		log.debug("Get all users");
		List<UserEntity> userList = userService.getAllUsers(token);
		return new ResponseEntity<>(userList,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/verify/{emailId}")
	public ResponseEntity<Response> verifyUser(@PathVariable String emailId)
	{
		log.debug("verify user");
		Response userEntity = userService.verifyUser(emailId);
		return new ResponseEntity<Response>(userEntity,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/updateUser")
	public ResponseEntity<Response> updateUser(@RequestHeader String token,@RequestBody UserDTO dto){
		log.debug("Update user");
		Response userEntity = userService.updateUser(token,dto);
		return new ResponseEntity<Response>(userEntity,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/forgotpassword")
	public ResponseEntity<Response> forgotPassword(@Valid @RequestBody ForgotPasswordDTO forgotPasswordDTO) 
	{
		return new ResponseEntity<Response>(userService.forgotPassword(forgotPasswordDTO) , HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUser")
	public ResponseEntity<Response> deleteUser(@RequestHeader String token){
		log.debug("Delete user");
		Response userEntity = userService.deleteUser(token);
		return new ResponseEntity<Response>(userEntity,HttpStatus.OK);
	}
	
}
