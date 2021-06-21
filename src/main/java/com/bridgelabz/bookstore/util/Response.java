package com.bridgelabz.bookstore.util;

import lombok.Data;

@Data
public class Response {

	private Integer StatusCode;
	private String Statusmessage;
	private Object data;
	public Response(Integer statusCode, String statusmessage, Object token) {
		super();
		StatusCode = statusCode;
		Statusmessage = statusmessage;
		this.data = token;
	}
	
}
