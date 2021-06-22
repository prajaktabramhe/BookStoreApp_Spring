package com.bridgelabz.bookstore.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.ForgotPasswordDTO;
import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.entity.UserEntity;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.repository.UserRegistrationRepository;
import com.bridgelabz.bookstore.util.Response;
import com.bridgelabz.bookstore.util.TokenUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements IUserService {
	
	@Autowired
	public UserRegistrationRepository userRegistrationRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	TokenUtil tokenUtil;
	
	@Override
	public Response registerUserData(UserDTO dto) 
	{
		Optional<UserEntity> isPresent = userRegistrationRepository.findByEmailId(dto.getEmailId());
		if(isPresent.isPresent()) 
		{
			log.error("User exists already. Email:" + dto.getEmailId());
			throw new BookStoreException(400,"User already exists. Email:"+dto.getEmailId());
		}else 
		{
		UserEntity userEntity = modelMapper.map(dto,UserEntity.class);
		userRegistrationRepository.save(userEntity);
		 return new Response(200, "Saved Succefully", null);
		}
	}

	@Override
	public Response loginData(@Valid LoginDTO login) {
		Optional<UserEntity> user = userRegistrationRepository.findByemailIdAndPassword(login.getEmailId(), login.getPassword());
		if(user.isPresent()) {
			String token= tokenUtil.createToken(user.get().getId());
			return new Response(200, "Token Created Succefully", token);
		}else {
			throw new BookStoreException(400, "No such User Found");
		}
	}

	
	@Override
	public Response deleteUser(String token) 
	{
		long id = tokenUtil.decodeToken(token);
		System.out.println("ide checked"+id);
		Optional<UserEntity> isUserPresent = userRegistrationRepository.findById(id);
		System.out.println("checked"+isUserPresent);
		if(isUserPresent.isPresent()) {
			userRegistrationRepository.delete(isUserPresent.get());
			log.debug("User deleted!!");
			return new Response(200, "User deleted successfully", null);
		}
		else {
			log.error("User not found");
			throw new BookStoreException(404,"User not found");
		}
	}

	@Override
	public List<UserEntity> getAllUsers(String token) 
	{
		long id = tokenUtil.decodeToken(token);
		Optional<UserEntity> isContactPresent = userRegistrationRepository.findById(id);
		if(isContactPresent.isPresent()) {
			log.debug("Get all users");
			List<UserEntity> getUsers=userRegistrationRepository.findAll();
			return getUsers;
		}
		else {
			log.error("Token not valid");
			throw new BookStoreException(400,"Token not valid");
		}
	}

	@Override
	public Response updateUser(String token, UserDTO dto) {
		long id = tokenUtil.decodeToken(token);
		Optional<UserEntity> isUserPresent = userRegistrationRepository.findById(id);
		if(isUserPresent.isPresent()) {
			isUserPresent.get().setFirstName(dto.getFirstName());
			isUserPresent.get().setLastName(dto.getLastName());
			isUserPresent.get().setDob(dto.getDob());
			isUserPresent.get().setKyc(dto.getKyc());
			isUserPresent.get().setEmailId(dto.getEmailId());
			isUserPresent.get().setPassword(dto.getPassword());
			isUserPresent.get().setUpdatedDate(LocalDate.now());
			isUserPresent.get().setExpiryDate(dto.getExpiryDate());
			userRegistrationRepository.save(isUserPresent.get());
			log.debug("User updated" + isUserPresent.get());
			return new Response(200, "User updated successfully", null);
		}
		else {
			log.error("User not found.");
			throw new BookStoreException(404,"user Not found");
		}
	}

	@Override
	public Response forgotPassword(ForgotPasswordDTO forgotPasswordDTO) 
	{
       if(forgotPasswordDTO.getNewPassword().equals(forgotPasswordDTO.getConfirmPassword())) 
       {
    	   UserEntity user = userRegistrationRepository.findByEmailId(forgotPasswordDTO.getEmailId()).get();
    	   String updatedPassword = userRegistrationRepository.save(new UserEntity(user, forgotPasswordDTO.getConfirmPassword())).getPassword();
    	   return new Response (200,"Password Change Successfull, login with your new password provided as data", updatedPassword);
		}else {
			return new Response(400, "Password and Confirm Password Do Not Match", null);
		}
	}
	

}


