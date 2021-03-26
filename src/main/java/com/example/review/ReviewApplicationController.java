package com.example.review;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewApplicationController {

	@GetMapping
	    public String Index() {
	        return "School Reviews!";
	}

}
