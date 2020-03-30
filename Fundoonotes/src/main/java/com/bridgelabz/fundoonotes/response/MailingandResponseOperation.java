package com.bridgelabz.fundoonotes.response;

import org.springframework.stereotype.Component;

@Component
public class MailingandResponseOperation {
	public String fromMessage(String url, String token) {
		return url + "/" + token;
	}

}
