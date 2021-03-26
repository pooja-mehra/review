package com.example.review.exception;

import org.springframework.stereotype.Service;


@Service
public class ResourceNotFoundException extends RuntimeException{
	public ResourceNotFoundException() {
		super();
	}
	public ResourceNotFoundException(String message) {
        super(message);
    }
	public String getMessage(String Message,long id){
		return Message + id;
		
	}
}

