package com.accounts.exeption;

import org.springframework.http.HttpStatus;

public class Error {
	
	private String message;
	private String status;
	private HttpStatus code;
	
	public  Error(String message, String status, HttpStatus code) {
		this.message = message;
		this.status = status;
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public HttpStatus getCode() {
		return code;
	}
	public void setCode(HttpStatus code) {
		this.code = code;
	}

}
