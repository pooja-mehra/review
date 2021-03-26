package com.example.review.school;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.websocket.Session;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.cfg.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.example.review.exception.ResourceNotFoundException;

@Service
public class SchoolService implements Serializable{
	private SchoolRepo schoolRepo;
	private ResourceNotFoundException resourceNotFound;
	
	
	public SchoolService(SchoolRepo schoolRepo,ResourceNotFoundException resourceNotFound) {
		this.schoolRepo =schoolRepo;
		this.resourceNotFound = resourceNotFound;
		
	}
	public Page<School> findAllSchools(Pageable pageable){
		Page<School> schools= schoolRepo.findAll(pageable);
		return schools;
	}
	public School addNewSchool(School newSchool){
		School savedSchool = schoolRepo.save(newSchool);
		return savedSchool;
	}
	public void deleteSchool(long id) {
		try {
			schoolRepo.deleteById(id);
		}catch(Exception e) {
			throw new ResourceNotFoundException(resourceNotFound.getMessage("school not found for Id: ",id));
		}
		
	}
	public School findSchoolById(long id){
		Optional<School> schoolById = schoolRepo.findById(id);
		return schoolById.orElseThrow(() -> new ResourceNotFoundException(resourceNotFound.getMessage("School not found for Id: ",id)));
		
	}
}
