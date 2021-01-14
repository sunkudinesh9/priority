package com.userpriority.priority.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerConfig {

	@ExceptionHandler(UserNotFounDException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String userNotFoundException(UserNotFounDException userNotFounDException) {
		return userNotFounDException.getMessage();
	}
}
