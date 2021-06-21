package com.bridgelabz.bookstore.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.bridgelabz.bookstore.dto.UserDTO;

import javax.persistence.Id;

import lombok.Data;

@Entity
@Table(name = "user")
public @Data class UserEntity {
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	public long Id;
	
	@Column(name = "fname")
	public String firstName;
	
	@Column(name = "lname")
	public String lastName;
	
	public String kyc;
	
	@Column(name = "dob")
	public String dob;

	@Column(name = "registerDate")
	public LocalDate registerDate = LocalDate.now();

	@Column(name = "updateddate")
	public LocalDate updatedDate;
	
	@Column(name = "emailid")
	public String emailId;

	@Column(name = "password")
	public String password;
	
	@Column(name = "verification")
	public boolean verify = false;
	
	public long otp;
	
	public LocalDate purchaseDate;
	
	public LocalDate expiryDate;
	
	/*
	 * Constructor for updating user data
	 */
	public UserEntity(long id, String firstName, String lastName, String kyc, String dob, LocalDate registerDate,
			LocalDate updatedDate, String emailId, String password, boolean verify, long otp, LocalDate purchaseDate,
			LocalDate expiryDate) {
		super();
		Id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.kyc = kyc;
		this.dob = dob;
		this.registerDate = registerDate;
		this.updatedDate = updatedDate;
		this.emailId = emailId;
		this.password = password;
		this.verify = verify;
		this.otp = otp;
		this.purchaseDate = purchaseDate;
		this.expiryDate = expiryDate;
	}

	/*
	 * Constructor for creating new User Record
	 */
	public UserEntity(UserDTO dto) {
		// TODO Auto-generated constructor stub
		this.firstName = dto.firstName;
		this.lastName = dto.lastName;
		this.kyc = dto.kyc;
		this.dob = dto.dob;
		this.emailId = dto.emailId;
		this.password = dto.password;
	}
	
	/*
	 * For Forgot Password,
	 * setting every value so that no field becomes null while updating password
	 */
	public UserEntity(UserEntity user, String newPassword) {
		this.Id = user.Id;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.kyc = user.kyc;
		this.dob = user.dob;
		this.registerDate = user.registerDate;
		this.updatedDate = user.updatedDate;
		this.emailId = user.emailId;
		this.password = newPassword;
		this.verify = user.verify;
		this.otp = user.otp;
		this.purchaseDate = user.purchaseDate;
		this.expiryDate = user.expiryDate;
	}
	
	public UserEntity () {}
}
