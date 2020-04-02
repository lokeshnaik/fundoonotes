package com.bridgelabz.fundoonotes.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
//import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInformationdto 
{
	@NotBlank(message = "FirstName is mandatory")
	private String firstName;
	@NotBlank(message = "LastName is mandatory")
	private String lastName;
	@Email
	private String email;
//	@NotBlank(message = "Password i mandatory")
	@Size(min = 6,max = 12)
	private String password;
	@Column(length = 10)
	private Long phoneNumber;
	
 
}
