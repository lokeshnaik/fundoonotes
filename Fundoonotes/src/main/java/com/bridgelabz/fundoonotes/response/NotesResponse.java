package com.bridgelabz.fundoonotes.response;

public class NotesResponse
{
    public NotesResponse() {
		
     	}
	String message;
	int statusCode;
	Object data;
	public NotesResponse(String message, int statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}
	public NotesResponse(String message, int statusCode, Object data) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.data = data;
	}
}
