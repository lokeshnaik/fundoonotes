package com.bridgelabz.fundoonotes.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class UserException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message;
	HttpStatus status;

	public UserException(String message,HttpStatus status) {
		//super(message);
		this.message = message;
		this.status=status;
	}
}
