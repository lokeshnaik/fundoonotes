package com.bridgelabz.fundoonotes.response;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UserResponse {

	public UserResponse() {
		// TODO Auto-generated constructor stub
	}
	String message;
	int statusCode;
	Object data;
	public UserResponse(String message, int statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}
	
	

}