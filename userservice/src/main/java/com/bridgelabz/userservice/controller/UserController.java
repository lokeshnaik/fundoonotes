package com.bridgelabz.userservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.userservice.dto.UserInformationdto;
import com.bridgelabz.userservice.dto.UserLoginInformation;
import com.bridgelabz.userservice.dto.UserUpdatePassword;
import com.bridgelabz.userservice.entity.User;
import com.bridgelabz.userservice.exception.UserException;
import com.bridgelabz.userservice.response.Response;
import com.bridgelabz.userservice.response.UserResponse;
import com.bridgelabz.userservice.service.UserServices;

@RestController
public class UserController
{
	@Autowired
	private UserServices services;
	
	
	@PostMapping("/user/register")
	
	ResponseEntity<UserResponse> userRegister(@Valid@RequestBody UserInformationdto informationdto,BindingResult result) throws UserException {
		if(result.hasErrors())
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new UserResponse(result.getAllErrors().get(0).getDefaultMessage(), 200,"null"));
           User information = services.userRegistration(informationdto);
           return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse("Registration Successful", 200, information));

	}
	
	

	@PostMapping("/user/forgotpassword")
	
	public ResponseEntity<Response> forgotPassword(@RequestParam("email") String email) throws UserException
	{

		boolean result = services.isUserExist(email);		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body( new Response("User Exist",200));
			}
	

	@PostMapping("/user/login")

	ResponseEntity<UserResponse> userLogin(@Valid@RequestBody UserLoginInformation information,BindingResult result) throws UserException {
		if(result.hasErrors())
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
					.body(new UserResponse(result.getAllErrors().get(0).getDefaultMessage(), 200,"null"));
		
		User loginResponse = new User();

		loginResponse = services.userLogin(information);

		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new UserResponse("login successfull", 200, loginResponse));

	}
	


	@PutMapping("/user/updatepassword/{token}")
	
	ResponseEntity<UserResponse> updatePassword(@RequestBody UserUpdatePassword updatePassword,@PathVariable("token") String token) throws UserException {
		boolean updatePasswordFlag = services.updatePassword(updatePassword, token);
		
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new UserResponse("password updated successfully", 200, updatePassword));
		
	}
	
	
	@GetMapping("/verify/{token}")
	public ResponseEntity<UserResponse> verficationUser(@PathVariable("token") String token) throws Exception 
	{
		boolean update = services.verify(token);
		if (update) 
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(token, 200, "VERIFIED"));

		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(token, 401, "Not Verified"));
	}
	


	@GetMapping("/user")
	
	ResponseEntity<UserResponse> getUserById(@RequestHeader("token") String token) throws UserException {
		User user = services.getUserById(token);
	
		
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse("user present", 200, user));
		
	}
	
}





	
	
		
	


