package com.example.review.school;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import com.example.review.exception.ViolationException;
import javax.validation.Valid;

@RestController
@RequestMapping("/schools")
public class SchoolController {
	@Autowired 
	private SchoolService schoolService;
	private  ViolationException invalidValue;
	
	public SchoolController(SchoolService schoolService,ViolationException invalidValue) {
		this.schoolService =schoolService;
		this.invalidValue = invalidValue;
	}
	
	@GetMapping
	public Page<School> findAllSchools(@PageableDefault(sort ="schoolLevel", direction =Sort.Direction.ASC) Pageable pageable) {
		return schoolService.findAllSchools(pageable);
	}
	
	@PostMapping
	public School addNewSchool(@RequestBody @Valid School newSchool) throws Throwable{
		if(newSchool.getSchoolCity() == "" || newSchool.getSchoolName() == "" || newSchool.getSchoolLevel() == "") {
			throw new ViolationException(invalidValue.getMessage("invalid values"));
		}
		else {
			School addedSchool = schoolService.addNewSchool(newSchool);
			return addedSchool;
		}
	}
	@GetMapping("/{id}")
	public School findSchoolById(@PathVariable("id") long id) {
		return schoolService.findSchoolById(id);
	}
	@PutMapping("/{id}")
	public School updateSchool(@PathVariable(name = "id") Long id,@RequestBody @Valid School editSchool ) throws Throwable {
		schoolService.findSchoolById(id);
		editSchool.setId(id);
		if(editSchool.getSchoolCity() == "" || editSchool.getSchoolName() == "" || editSchool.getSchoolLevel() == "") {
			throw new ViolationException(invalidValue.getMessage("invalid values"));
		}
		else {
			School editedSchool = schoolService.addNewSchool(editSchool);
			return editedSchool;
		}
	}
	
	@DeleteMapping("/{id}")
	public String deleteSchool(@PathVariable long id) {
		schoolService.deleteSchool(id);
		return "School deleted for Id: "+id;
 	}
}
