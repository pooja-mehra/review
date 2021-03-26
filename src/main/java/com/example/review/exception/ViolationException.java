package com.example.review.exception;

import org.springframework.stereotype.Service;

@Service
public class ViolationException extends RuntimeException{

	public ViolationException() {
		super();
	}
	public ViolationException(String message) {
		super(message);
		
	}
	public String getMessage(String message) {
		// TODO Auto-generated method stub
		return message;
	}
}
