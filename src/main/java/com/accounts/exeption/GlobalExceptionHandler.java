package com.accounts.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Error> handleAccessDeniedException(Exception ex, WebRequest request) {
		return new ResponseEntity(new Error(ex.getMessage(),"faild",HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<Error> handleExpiredJwtException(Exception ex, WebRequest request) {
		return new ResponseEntity(new Error(ex.getMessage(),"faild",HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Error> handleGlobalException(Exception ex, WebRequest request) {
		return new ResponseEntity(new Error(ex.getMessage(),"faild",HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
