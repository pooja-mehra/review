package com.example.review.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

	@ControllerAdvice
	public class ReviewExceptionHandler extends ResponseEntityExceptionHandler {

	    @ExceptionHandler({ViolationException.class})
	    public ResponseEntity<ReviewErrorResponse> invalidValues(Exception ex, WebRequest request) {
	    	ReviewErrorResponse errors = new ReviewErrorResponse();
	        errors.setTimeStamp(LocalDateTime.now());
	        errors.setError(ex.getMessage());
	        errors.setStatus(HttpStatus.BAD_REQUEST.value());
	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	        }

	    @ExceptionHandler({ResourceNotFoundException.class})
	    public ResponseEntity<ReviewErrorResponse> customHandleNotFound(Exception ex, WebRequest request) {
	    	ReviewErrorResponse errors = new ReviewErrorResponse();
	        errors.setTimeStamp(LocalDateTime.now());
	        errors.setError(ex.getMessage());
	        errors.setStatus(HttpStatus.NOT_FOUND.value());
	        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
	        }
	}
