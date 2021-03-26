package com.example.review.schoolreview;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.review.exception.ResourceNotFoundException;

@Service
public class ReviewSchoolService implements Serializable{
	private ReviewSchoolRepo reviewSchoolRepo;
	private ResourceNotFoundException resourceNotFound;
	
	
	public ReviewSchoolService(ReviewSchoolRepo reviewSchoolRepo,ResourceNotFoundException resourceNotFound) {
		this.reviewSchoolRepo =reviewSchoolRepo;
		this.resourceNotFound = resourceNotFound;
		
	}
	public Page<ReviewSchool> findAllReviews(Pageable pageable){
		Page<ReviewSchool> review= reviewSchoolRepo.findAll(pageable);
		return review;
	}
	public ReviewSchool addNewReview(ReviewSchool newReview){
		ReviewSchool savedReview = reviewSchoolRepo.save(newReview);
		return savedReview;
	}
	public void deleteReview(long id) {
		try {
			reviewSchoolRepo.deleteById(id);
		}
		catch(Exception e) {
			throw new ResourceNotFoundException(resourceNotFound.getMessage("School review not found for Id: ",id));
		}
		
	}
	public ReviewSchool findReviewById(long id){
		Optional<ReviewSchool> reviewById = reviewSchoolRepo.findById(id);
		return reviewById.orElseThrow(() -> new ResourceNotFoundException(resourceNotFound.getMessage("School review not found for Id: ",id)));
		
	}
}
