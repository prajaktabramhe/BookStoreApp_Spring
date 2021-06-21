package com.bridgelabz.bookstore.dto;

import java.time.LocalDate;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

public @Data class UserDTO {

	@NotBlank(message = "First name cannot be blank")
	public String firstName;
	
	@NotBlank(message = "Last name cannot be blank")
	public String lastName;
	
	@JsonFormat(pattern = "dd MMM yyyy")
	public String dob;
	
	public String kyc;
	
	@NotBlank(message = "Email id cannot be blank")
	public String emailId;
	
	@NotBlank(message = "Password cannot be blank")
	public String password;
	
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@FutureOrPresent(message = "Start Date should be future or present.")
	public LocalDate expiryDate;
}
