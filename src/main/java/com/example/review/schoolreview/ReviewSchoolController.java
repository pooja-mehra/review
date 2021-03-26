package com.example.review.schoolreview;

import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.review.exception.ResourceNotFoundException;
import com.example.review.exception.ViolationException;
import com.example.review.school.School;
import com.example.review.school.*;

@RestController
@RequestMapping("/schools/reviews")
public class ReviewSchoolController {
	@Autowired 
	private ReviewSchoolService reviewschoolService;
	private  ViolationException invalidValue;
	private SchoolRepo schoolRepo;
	private ResourceNotFoundException resourceNotFound;
	
	public ReviewSchoolController(
		ReviewSchoolService reviewschoolService,ViolationException invalidValue,SchoolRepo schoolRepo,ResourceNotFoundException resourceNotFound) {
		this.reviewschoolService =reviewschoolService;
		this.invalidValue = invalidValue;
		this.schoolRepo = schoolRepo;
		this.resourceNotFound = resourceNotFound;
	}
	
	@GetMapping
	public Page<ReviewSchool> findAllReviews(@PageableDefault(sort ="id", direction =Sort.Direction.ASC) Pageable pageable) {
		return reviewschoolService.findAllReviews(pageable);
	}
	
	@PostMapping("/{school_id}")
	public ReviewSchool addNewReview(@PathVariable long school_id, @RequestBody @Valid ReviewSchool newReview) throws Throwable{
		Optional<School> addSchoolReview = schoolRepo.findById(school_id);
		if(addSchoolReview.isPresent()) {
		if(newReview.getReviewComment() == "") {
			throw new ViolationException(invalidValue.getMessage("invalid values"));
		}
		else {
			newReview.setSchoolId(addSchoolReview.get());
			ReviewSchool newSchoolReview = reviewschoolService.addNewReview(newReview);
			return newSchoolReview;
		}	
		}
		else {
			throw new ResourceNotFoundException(resourceNotFound.getMessage("School not found for Id: ",school_id));
		}
	}
	@GetMapping("/{id}")
	public ReviewSchool findReviewById(@PathVariable("id") long id) {
		return reviewschoolService.findReviewById(id);
	}
	@PutMapping("/{id}/{school_id}")
	public ReviewSchool updateSchoolReview(
			@PathVariable long id,@PathVariable long school_id, @RequestBody @Valid ReviewSchool editReview ) 
					throws Throwable {
		reviewschoolService.findReviewById(id);
		editReview.setId(id);
		Optional<School> addSchoolReview = schoolRepo.findById(school_id);
		if(addSchoolReview.isPresent()) {
		if(editReview.getReviewComment() == "") {
			throw new ViolationException(invalidValue.getMessage("invalid values"));
		}
		else {
			editReview.setSchoolId(addSchoolReview.get());
			ReviewSchool editedSchoolReview = reviewschoolService.addNewReview(editReview);
			return editedSchoolReview;
		}
		}
		else {
			throw new ResourceNotFoundException(resourceNotFound.getMessage("School not found for Id: ",school_id));
		}
	}
	
	@DeleteMapping("/{id}")
	public String deleteReview(@PathVariable("id") long id) {
		reviewschoolService.deleteReview(id);
		return "Review deleted for Id: " +id; 
	}
}
