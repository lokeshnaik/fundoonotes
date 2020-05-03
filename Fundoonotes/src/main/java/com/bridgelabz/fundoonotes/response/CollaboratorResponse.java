package com.bridgelabz.fundoonotes.response;

import lombok.Data;
@Data

public class CollaboratorResponse
{
	public CollaboratorResponse()
	{

	}

	String message;
	int statusCode;
	Object data;
	
	public CollaboratorResponse(String message, int statusCode)
	{
		super();
		this.message = message;
		this.statusCode = statusCode;
		
	}

	public CollaboratorResponse(String message, int statusCode, Object data) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.data = data;
	}
}