package com.bridgelabz.fundoonotes.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class CollaboratorException extends Exception
{
	private static final long serialVersionUID = 1L;
	private String message;
	HttpStatus status;

	public CollaboratorException(String message,HttpStatus status) 
	{
		this.message = message;
		this.status=status;
	}
}

