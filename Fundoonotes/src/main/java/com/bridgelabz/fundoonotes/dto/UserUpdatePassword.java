package com.bridgelabz.fundoonotes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor

public class UserUpdatePassword {
	

	String oldPassword;
	String newPassword;
	String confirmPassword;
	
	
	

}